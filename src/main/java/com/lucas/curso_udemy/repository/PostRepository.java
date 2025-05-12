package com.lucas.curso_udemy.repository;

import com.lucas.curso_udemy.domain.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post,String> {


    /**
     * Poderia ser substituída pelo queryMethod findByTitleContainingIgnoreCase.
     * @param text
     * @return uma lista de posts que no campo title, contenham o valor text, ignorando maiusculas e minusculas.
     */
    @Query("{ 'title':  { $regex:  ?0, $options: 'i' } }")
    List<Post> findByTitle(String text);
}
