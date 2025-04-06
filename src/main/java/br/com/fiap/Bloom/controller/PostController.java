package br.com.fiap.Bloom.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.Bloom.model.Post;
import br.com.fiap.Bloom.repository.PostRepository;

//@CrossOrigin(origins = "http://localhost:3002")
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
    @PostMapping(consumes = "multipart/form-data")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(
            @RequestParam("titulo") String titulo,
            @RequestParam("conteudo") String conteudo,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) throws IOException {

        Post post = new Post();
        post.setTitulo(titulo);
        post.setConteudo(conteudo);

        if (imagem != null && !imagem.isEmpty()) {
            post.setImagem(imagem.getBytes());
        }

        return repository.save(post);
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

    // Atualizar
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    public Post update(
            @PathVariable Long id,
            @RequestParam("titulo") String titulo,
            @RequestParam("conteudo") String conteudo,
            @RequestPart(value = "imagem", required = false) MultipartFile imagem) throws IOException {

        log.info("Atualizando post ID: " + id);

        Post post = getPost(id);

        post.setTitulo(titulo);
        post.setConteudo(conteudo);

        if (imagem != null && !imagem.isEmpty()) {
            post.setImagem(imagem.getBytes());
        }

        return repository.save(post);
    }

    // buscar post por ID
    private Post getPost(Long id) {
        return repository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post " + id + " não encontrado"));
    }
}
