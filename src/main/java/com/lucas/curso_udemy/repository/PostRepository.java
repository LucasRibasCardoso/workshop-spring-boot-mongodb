package com.lucas.curso_udemy.repository;

import com.lucas.curso_udemy.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {
}
