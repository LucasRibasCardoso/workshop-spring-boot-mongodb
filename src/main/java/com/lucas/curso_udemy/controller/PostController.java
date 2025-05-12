package com.lucas.curso_udemy.controller;

import com.lucas.curso_udemy.controller.controllerUtils.URL;
import com.lucas.curso_udemy.domain.Post;
import com.lucas.curso_udemy.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping("/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        List<Post> posts = postService.findByTitle(URL.decodeUrlParam(text));
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping("/fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate
    ) {
        text = URL.decodeUrlParam(text);
        Date convertedMinDate = URL.convertDate(minDate, new Date(0L)); // defaultValue Jan 01 00:00:00 GMT 1970
        Date  convertedMaxDate = URL.convertDate(maxDate, new Date()); // defaultValue data atual

        List<Post> posts = postService.fullSearch(text, convertedMinDate, convertedMaxDate);
        return ResponseEntity.ok().body(posts);
    }
}
