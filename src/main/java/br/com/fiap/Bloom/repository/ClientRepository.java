package br.com.fiap.Bloom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Bloom.model.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
