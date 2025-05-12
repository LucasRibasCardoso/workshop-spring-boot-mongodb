package com.lucas.curso_udemy.service;

import com.lucas.curso_udemy.domain.Post;
import com.lucas.curso_udemy.exception.ObjectNotFoundException;
import com.lucas.curso_udemy.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post findById(String id) {
        return postRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Nenhum post encontrado com esse id."));
    }

    public List<Post> findByTitle(String text){
        return postRepository.findByTitle(text);
    }

}
