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

import br.com.fiap.Bloom.model.Login;
import br.com.fiap.Bloom.repository.LoginRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/posts/login")
@Slf4j

public class LoginController {

    @Autowired
    private LoginRepository repository;

    // Listar
    @GetMapping
    @Operation(summary = "Listar todos os logins", description = "Lista todos os logins salvos do sistema", tags = "Login")
    public List<Login> index() {
        return repository.findAll();
    }

    // Cadastrar
    @Operation(summary = "Realizando o cadastro no sistema", description = "Cadastro de usuarios", tags = "Login")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Login create(@RequestBody Login login) {
        log.info("Login adicionado email: " + login.getEmail_login());
        return repository.save(login);
    }

    // Buscar um post por ID
    @Operation(summary = "Buscando um login especifico por ID", description = "BUscar login por ID", tags = "Login")
    @GetMapping("/{id}")
    public Login get(@PathVariable Long id) {
        log.info("Buscando login " + id);
        return getLogin(id);
    }

    // Deletar
    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar o login do sistema", description = "Deleta o login pelo ID", tags = "Login")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deletando login " + id);
        repository.delete(getLogin(id));
    }

    // Atualizar
    @PutMapping("/{id}")
    @Operation(summary = "Atualizando login", description = "Atualiza as informações do login como email e senha", tags = "Login")
    public Login update(@PathVariable Long id, @RequestBody Login login) {
        log.info("Atualizando login " + id + "" + login);

        getLogin(id);
        login.setId_login(id);
        return repository.save(login);
    }

    @PostMapping("/auth")
    @Operation(summary = "Realizando a autenticação ", description = "Autentifica se o login esta no sistema ", tags = "Login")
    public Login autenticar(@RequestBody Login login) {
        log.info("Autenticando login para o e-mail: " + login.getEmail_login());
    
        return repository.findAll().stream()
            .filter(l -> l.getEmail_login().equals(login.getEmail_login())
                      && l.getSenha_login().equals(login.getSenha_login()))
            .findFirst()
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email ou senha inválidos"));
    }

    // buscar post por ID
    private Login getLogin(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Login " + id + " não encontrado"));
    }
}
