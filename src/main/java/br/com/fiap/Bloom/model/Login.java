package br.com.fiap.Bloom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Login {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_login;
    private String email_login;
    private String senha_login;
    // private Long idClient;
    // private Client client;

    public Login() {
    }

    public Login(Client client, String emailLogin, Long idLogin, String senhaLogin) {
        // this.client = client;
        this.email_login = emailLogin;
        this.id_login = idLogin;
        this.senha_login = senhaLogin;
    }

    // public Client getClient() {
    // return client;
    // }

    // public void setClient(Client client) {
    // this.client = client;
    // }

    public String getEmail_login() {
        return email_login;
    }

    public void setEmail_login(String email_login) {
        this.email_login = email_login;
    }

    // public Long getIdClient() {
    // return idClient;
    // }

    // public void setId_Client(Long idClient) {
    // this.idClient = idClient;
    // }

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
