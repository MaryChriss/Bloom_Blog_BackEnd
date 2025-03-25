package br.com.fiap.Bloom.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.Bloom.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}
