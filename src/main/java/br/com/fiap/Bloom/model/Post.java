package br.com.fiap.Bloom.model;

import java.util.Random;

public class Post {

    private Long id_post;
    private String titulo;
    private String conteudo;
    private byte[] imagem;

    public Post(Long id_post, String titulo, String conteudo, byte[] imagem) {
        this.id_post = Math.abs(new Random().nextLong());
        this.titulo = titulo;
        this.conteudo = conteudo;
        this.imagem = imagem;
    }

    public Long getId_post() {
        return id_post;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public byte[] getImagem() {
        return imagem;
    }
}
