package br.com.fiap.Bloom.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.fiap.Bloom.model.Post;
import br.com.fiap.Bloom.repository.PostRepository;

public class DatabaseSeeder {

    @Autowired
    private PostRepository postRepository;

    public void init() {
        if (postRepository.count() > 0) return;

        var posts = List.of(
            Post.builder().titulo("Bem-vindo ao Bloom ")
                .conteudo("Esse é o primeiro post do nosso blog. Esperamos que você goste!")
                .build(),
                Post.builder()
                .titulo("Dicas de Desenvolvimento Pessoal")
                .conteudo("Confira algumas práticas diárias para melhorar seu bem-estar.")
                .build(),
            Post.builder()
                .titulo("Como manter uma rotina de estudos")
                .conteudo("Organização e consistência são chaves. Veja como aplicá-las no dia a dia.")
                .build()
        );

        postRepository.saveAll(posts);
    }
}