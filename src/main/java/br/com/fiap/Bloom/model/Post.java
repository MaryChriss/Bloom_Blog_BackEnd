package br.com.fiap.Bloom.model;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_post;

    @NotBlank(message = "campo obrigatório")
    private String titulo;

    @NotBlank(message = "campo obrigatório")
    private String conteudo;
    @Lob
    @Column(name = "imagem", columnDefinition = "BLOB")
    private byte[] imagem;

    @PastOrPresent(message = "não pode ser no futuro")
    private LocalDateTime date;

    @PrePersist
    public void setDefaultDate() {
        if (date == null) {
            date = LocalDateTime.now();
        }
    }
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
