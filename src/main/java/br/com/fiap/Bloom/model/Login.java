package br.com.fiap.Bloom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_login;

    @NotBlank(message = "Email não pode ser vazio")
    private String email_login;

   @NotBlank(message = "Senha não pode ser vazio")
   @Size(min = 6, message = "deve ter pelo menos 6 caracteres")
    private String senha_login;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
