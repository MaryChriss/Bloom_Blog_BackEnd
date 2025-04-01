package br.com.fiap.Bloom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Login {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_login;
    private String email_login;
    private String senha_login;
    private Long idTeste;
    private Teste teste;

    public Login() {
    }

    public Login(Teste teste, String emailLogin, Long idLogin, String senhaLogin) {
        this.teste = teste;
        this.email_login = emailLogin;
        this.id_login = idLogin;
        this.senha_login = senhaLogin;
    }

    public Teste getTeste() {
        return teste;
    }

    public void setTeste(Teste teste) {
        this.teste = teste;
    }

    public String getEmail_login() {
        return email_login;
    }

    public void setEmail_login(String email_login) {
        this.email_login = email_login;
    }

    public Long getIdTeste() {
        return idTeste;
    }

    public void setId_teste(Long id_teste) {
        this.idTeste = id_teste;
    }

    public Long getId_login() {
        return id_login;
    }

    public void setId_login(Long id_login) {
        this.id_login = id_login;
    }

    public String getSenha_login() {
        return senha_login;
    }

    public void setSenha_login(String senha_login) {
        this.senha_login = senha_login;
    }
}
