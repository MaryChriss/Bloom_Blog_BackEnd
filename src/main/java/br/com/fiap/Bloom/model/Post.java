package br.com.fiap.Bloom.model;

import java.util.Arrays;
import java.util.Random;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post;
    private String titulo;
    private String conteudo;
    @Lob
    @Column(name = "imagem", columnDefinition = "BLOB")
    private byte[] imagem;

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

    public void setId_post(Long id_post) {
        this.id_post = id_post;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    @Override
    public String toString() {
        return "Post [id_post=" + id_post + ", titulo=" + titulo + ", conteudo=" + conteudo + ", imagem="
                + Arrays.toString(imagem) + "]";
    }
}
