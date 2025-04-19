package br.com.fiap.Bloom.controller;

import java.io.IOException;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
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
import br.com.fiap.Bloom.model.PostFilter;
import br.com.fiap.Bloom.repository.PostRepository;
import br.com.fiap.Bloom.specification.PostSpecification;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort.Direction;

//@CrossOrigin(origins = "http://localhost:3002")
@RestController
@RequestMapping("/posts")
@Slf4j

public class PostController {

    @Autowired
    private PostRepository repository;

    // Listar
    @GetMapping
    @Operation(summary = "Listar todos os posts", description = "Lista todos os posts com filtros opcionais por título e autor, com paginação", tags = "Posts")
    public Page<Post> index(
            PostFilter filter,
            @PageableDefault(size = 8, sort = "date", direction = Direction.DESC) Pageable pageable) {
        return repository.findAll(PostSpecification.withFilters(filter), pageable);
    }

    // Cadastrar
    @PostMapping(consumes = "multipart/form-data")
    @Operation(summary = "Adicionar posts no sistema", description = "Cadastro de posts podendo ter imagens ou não", tags = "Posts")
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
    @Operation(summary = "Buscar post por ID", description = "Buscar um post em especifico", tags = "Posts")
    public Post get(@PathVariable Long id) {
        log.info("Buscando post " + id);
        return getPost(id);
    }

    // Deletar
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletando post do sistema", description = "Deletando posts", tags = "Posts")
    public void delete(@PathVariable Long id) {
        log.info("Deletando post " + id);
        repository.delete(getPost(id));
    }

    // Atualizar
    @PutMapping(value = "/{id}", consumes = "multipart/form-data")
    @Operation(summary = "Atualizando posts", description = "Atualiza o titulo, conteuno, imagem etc", tags = "Posts")
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
