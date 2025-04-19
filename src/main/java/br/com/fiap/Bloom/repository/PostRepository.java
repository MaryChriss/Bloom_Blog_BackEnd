package br.com.fiap.Bloom.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.fiap.Bloom.model.Post;

public interface PostRepository extends JpaRepository<Post, Long>, JpaSpecificationExecutor<Post> {

    //List<Post> findByTitleContainingIgnoreCase(String title);

    //List<Post> findByAuthorContainingIgnoreCase(String author);

    //List<Post> findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);

    List<Post> findByDate(LocalDate date);

}
