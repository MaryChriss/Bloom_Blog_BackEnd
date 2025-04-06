package br.com.fiap.Bloom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Bloom.model.User;



public interface UserRepository  extends JpaRepository<User, Long> {
    
}
