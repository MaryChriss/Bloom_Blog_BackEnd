package br.com.fiap.Bloom.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.Bloom.model.Post;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/posts")
public class PostController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private List<Post> repository = new ArrayList<>();

    // Listar todos os posts
    @GetMapping
    public List<Post> index() {
        return repository;
    }

    // Cadastrar um post
    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        log.info("Post postado " + post.getTitulo());
        repository.add(post);
        return ResponseEntity.status(201).body(post);
    }

    // Buscar um post por ID
    @GetMapping("{id}")
    public Post get(@PathVariable Long id) {
        log.info("Buscando post " + id);
        return getPost(id);
    }

    //deletar post
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        log.info("Deletado post " + id);
        repository.remove(getPost(id));
    } 

    //atualizar posts
    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        log.info("Atualizando post " + id + " -Novo conteudo: " + post);

        repository.remove(getPost(id));
        post.setId_post(id);
        repository.add(post);

        return post;
    }

    private Post getPost(Long id){
        return repository.stream()
        .filter(p -> p.getId_post().equals(id))
                .findFirst()
                .orElseThrow(
                    () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post  " + id + "  não encontrado")
                );
    }
}
