package br.com.fiap.Bloom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.Bloom.model.Teste;
import br.com.fiap.Bloom.repository.ClientRepository;

@CrossOrigin(origins = "http://localhost:3002")
@RestController
@RequestMapping("/posts/client")
public class ClientController {

    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private ClientRepository repository;
    
    // Listar 
    @GetMapping
    public List<Teste> index() {
        return repository.findAll();
    }

    // Cadastrar 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Teste create(@RequestBody Teste teste) {
        log.info("Cliente adicionado: " + teste.getNomeTeste());
        repository.save(teste);
        return teste;
    }

    // Buscar um post por ID
    @GetMapping("/{id}")
    public Teste get(@PathVariable Long id) {
        log.info("Buscando cliente " + id);
        return getTeste(id);
    }

    // Deletar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deletando cliente " + id);
        repository.delete(getTeste(id));
    }

    //Atualizar 
    @PutMapping("/{id}")
    public Teste update(@PathVariable Long id, @RequestBody Teste teste) {
        log.info("Atualizando cliente " + id + "" + teste);
    
        getTeste(id);
        teste.setIdTeste(id);
        return repository.save(teste);
    }
    
    // buscar post por ID
    private Teste getTeste(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente " + id + " não encontrado")
                );
    }
}
