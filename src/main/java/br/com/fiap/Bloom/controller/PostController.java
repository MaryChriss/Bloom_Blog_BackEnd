package br.com.fiap.Bloom.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import br.com.fiap.Bloom.model.Post;

@CrossOrigin(origins = "http://localhost:3002")
@RestController
@RequestMapping("/posts")
public class PostController {

    private Logger log = LoggerFactory.getLogger(getClass());
    private List<Post> repository = new ArrayList<>();
    
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Listar 
    @GetMapping
    public List<Post> index() {
        return repository;
    }

    // Cadastrar 
    @PostMapping
    public ResponseEntity<Post> create(
            @RequestParam("titulo") String titulo,
            @RequestParam("conteudo") String conteudo,
            @RequestParam(value = "imagem", required = false) MultipartFile imagem) {

        log.info("Post postado: " + titulo);

        Post post = new Post();
        post.setId_post(idGenerator.getAndIncrement());
        post.setTitulo(titulo);
        post.setConteudo(conteudo);

        if (imagem != null && !imagem.isEmpty()) {
            try {
                post.setImagem(imagem.getBytes());
                log.info("Imagem recebida: " + imagem.getOriginalFilename());
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar a imagem", e);
            }
        }

        repository.add(post);
        return ResponseEntity.status(201).body(post);
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
        repository.remove(getPost(id));
    }

    //Atualizar 
    @PutMapping("/{id}")
    public Post update(
            @PathVariable Long id,
            @RequestParam("titulo") String titulo,
            @RequestParam("conteudo") String conteudo,
            @RequestParam(value = "imagem", required = false) MultipartFile imagem) {

        log.info("Atualizando post " + id + " - Novo título: " + titulo);

        Post post = getPost(id);
        repository.remove(post);

        post.setTitulo(titulo);
        post.setConteudo(conteudo);

        if (imagem != null && !imagem.isEmpty()) {
            try {
                post.setImagem(imagem.getBytes());
                log.info("Imagem atualizada: " + imagem.getOriginalFilename());
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Erro ao processar a imagem", e);
            }
        }

        repository.add(post);

        return post;
    }

    // buscar post por ID
    private Post getPost(Long id) {
        return repository.stream()
                .filter(p -> p.getId_post().equals(id))
                .findFirst()
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post " + id + " não encontrado")
                );
    }
}
