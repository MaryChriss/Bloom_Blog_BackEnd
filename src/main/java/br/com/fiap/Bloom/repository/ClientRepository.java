package br.com.fiap.Bloom.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Bloom.model.Teste;

public interface ClientRepository  extends JpaRepository<Teste, Long> {
    
}
