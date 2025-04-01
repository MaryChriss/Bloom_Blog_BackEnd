package br.com.fiap.Bloom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Bloom.model.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {
    
}
