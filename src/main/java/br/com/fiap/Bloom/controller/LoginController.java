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

import br.com.fiap.Bloom.model.Login;
import br.com.fiap.Bloom.repository.LoginRepository;

@CrossOrigin(origins = "http://localhost:3002")
@RestController
@RequestMapping("/posts/login")
public class LoginController {
    
    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private LoginRepository repository;
    
    // Listar 
    @GetMapping
    public List<Login> index() {
        return repository.findAll();
    }

    // Cadastrar 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Login create(@RequestBody Login login) {
        log.info("Login adicionado email: " + login.getEmail_login());
        repository.save(login);
        return login;
    }

    // Buscar um post por ID
    @GetMapping("/{id}")
    public Login get(@PathVariable Long id) {
        log.info("Buscando login " + id);
        return getLogin(id);
    }

    // Deletar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deletando login " + id);
        repository.delete(getLogin(id));
    }

    //Atualizar 
    @PutMapping("/{id}")
    public Login update(@PathVariable Long id, @RequestBody Login login) {
        log.info("Atualizando login " + id + "" + login);
    
        getLogin(id);
        login.setId_login(id);
        return repository.save(login);
    }
    
    // buscar post por ID
    private Login getLogin(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Login " + id + " não encontrado")
                );
    }
}
