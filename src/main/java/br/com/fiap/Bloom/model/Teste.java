package br.com.fiap.Bloom.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Teste {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTeste;
    public String nomeTeste;

    public Teste() {
    }

    public Long getIdTeste() {
        return idTeste;
    }

    public void setIdTeste(Long idTeste) {
        this.idTeste = idTeste;
    }

    public String getNomeTeste() {
        return nomeTeste;
    }

    public void setNomeTeste(String nomeTeste) {
        this.nomeTeste = nomeTeste;
    }

    
}
