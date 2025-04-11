package br.com.fiap.Bloom.controller;

import java.util.List;

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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/posts/user")
@Slf4j
public class UserController {

    @Autowired
    private UserRepository repository;
    
    // Listar 
    @GetMapping
    @Operation(summary = "Listar todos os usuarios do sistema", description = "Lista todas aos users salvos ", tags = "User")
    public List<User> index() {
        return repository.findAll();
    }

    @PostMapping
    @Operation(summary = "Realizando o cadastro no sistema", description = "Cadastro de usuarios", tags = "User")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user) {
        log.info("Usuario adicionado: " + user.getNomeUser());
        return repository.save(user);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Usuario por ID", description = "BUsca usuario expecifico pelo ID", tags = "User")
    public User get(@PathVariable Long id) {
        log.info("Buscando usuario " + id);
        return getUser(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletando Usuario", description = "Deletando usuarios do sistema", tags = "User")
    public void delete(@PathVariable Long id) {
        log.info("Deletando usuario " + id);
        repository.delete(getUser(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizando usuarios", description = "Atualiza informaç~çoes do usuario", tags = "User")
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
