package br.com.fiap.Bloom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.Bloom.model.Post;

@RestController
@RequestMapping("/posts")
public class PostController {

    private List<Post> repository = new ArrayList<>();

    // Listar todos os posts
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public List<Post> index() {
        return repository;
    }

    // Cadastrar um post
    @PostMapping
    public ResponseEntity<Post> create(@RequestBody Post post) {
        repository.add(post);
        System.out.println("Cadastrando post " + post.getTitulo());
        return ResponseEntity.status(201).body(post);
    }

    // Buscar um post por ID
    @GetMapping("/{id}")
    public ResponseEntity<Post> get(@PathVariable Long id) {
        System.out.println("Buscando post " + id);
        var post = repository.stream()
                .filter(p -> p.getId_post().equals(id))
                .findFirst();

        if (post.isEmpty())
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok(post.get());
    }
}
