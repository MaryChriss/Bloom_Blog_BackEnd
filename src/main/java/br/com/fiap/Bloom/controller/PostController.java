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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.fiap.Bloom.model.Post;
import br.com.fiap.Bloom.repository.PostRepository;

@RestController
@RequestMapping("/posts")
public class PostController {

    private Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    private PostRepository repository;
    
    // Listar 
    @GetMapping
    public List<Post> index() {
        return repository.findAll();
    }

    // Cadastrar 
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@RequestBody Post post) {
        log.info("Post postado: " + post.getTitulo());
        repository.save(post);
        return post;
    }

    // Buscar um post por ID
    @GetMapping("/{id}")
    public Post get(@PathVariable Long id) {
        log.info("Buscando post " + id);
        return getPost(id);
    }

    // Deletar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        log.info("Deletando post " + id);
        repository.delete(getPost(id));
    }

    //Atualizar 
    @PutMapping("/{id}")
    public Post update(@PathVariable Long id, @RequestBody Post post) {
        log.info("Atualizando post " + id + "" + post);
    
        getPost(id);
        post.setId_post(id);
        return repository.save(post);
    }
    
    // buscar post por ID
    private Post getPost(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post " + id + " não encontrado")
                );
    }
}
