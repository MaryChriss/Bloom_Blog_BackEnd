package br.com.fiap.Bloom.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import br.com.fiap.Bloom.model.Client;
import br.com.fiap.Bloom.repository.ClientRepository;

@RestController
@RequestMapping("/posts/client")
public class ClientController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ClientRepository repository;

    @GetMapping
    public List<Client> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client create(@RequestBody Client client) {
        log.info("Cliente adicionado: " + client.getNomeClient());
        repository.save(client);
        return client;
    }

    @GetMapping("/{id}")
    public Client get(@PathVariable Long id) {
        log.info("Buscando cliente " + id);
        return getClient(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deletando cliente " + id);
        repository.delete(getClient(id));
    }

    @PutMapping("/{id}")
    public Client update(@PathVariable Long id, @RequestBody Client client) {
        log.info("Atualizando cliente " + id + "" + client);

        getClient(id);
        client.setIdClient(id);
        return repository.save(client);
    }

    private Client getClient(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente " + id + " não encontrado"));
    }
}
