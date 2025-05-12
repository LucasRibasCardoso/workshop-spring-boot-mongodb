package com.lucas.curso_udemy.repository;

import com.lucas.curso_udemy.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {
    List<Post> findByTitleContainingIgnoreCase(String text);
}
