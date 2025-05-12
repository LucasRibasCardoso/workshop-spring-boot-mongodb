package com.lucas.curso_udemy.service;

import com.lucas.curso_udemy.domain.Post;
import com.lucas.curso_udemy.exception.ObjectNotFoundException;
import com.lucas.curso_udemy.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.Date;
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
        return postRepository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
        Date maxDatePlusOneDay = Date.from(maxDate.toInstant().plus(1, ChronoUnit.DAYS));
        return  postRepository.fullSearch(text, minDate, maxDatePlusOneDay);
    }
}
