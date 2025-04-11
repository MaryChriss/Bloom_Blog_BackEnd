package br.com.fiap.Bloom.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuario") 
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;

    @NotBlank(message = "campo obrigatório")
    @Pattern(regexp = "^[A-Z].*", message = "deve começar com maiúscula")
    public String nomeUser;

    @OneToOne(mappedBy = "user")
    private Login login;

    @OneToMany(mappedBy = "user")
    private List<Post> posts;

}
