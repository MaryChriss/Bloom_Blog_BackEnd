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

import br.com.fiap.Bloom.model.User;
import br.com.fiap.Bloom.repository.UserRepository;

@RestController
@RequestMapping("/posts/user")
public class UserController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserRepository repository;
    
    // Listar 
    @GetMapping
    public List<User> index() {
        return repository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        log.info("Usuario adicionado: " + user.getNomeUser());
        repository.save(user);
        return user;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        log.info("Buscando usuario " + id);
        return getUser(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deletando usuario " + id);
        repository.delete(getUser(id));
    }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) {
        log.info("Atualizando usuario " + id + "" + user);
    
        getUser(id);
        user.setIdUser(id);
        return repository.save(user);
    }
    
    // buscar post por ID
    private User getUser(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario " + id + " não encontrado")
                );
    }
}
