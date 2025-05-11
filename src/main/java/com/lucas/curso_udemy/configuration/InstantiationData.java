package com.lucas.curso_udemy.configuration;

import com.lucas.curso_udemy.domain.Post;
import com.lucas.curso_udemy.domain.User;
import com.lucas.curso_udemy.dto.AuthorDTO;
import com.lucas.curso_udemy.dto.CommentDTO;
import com.lucas.curso_udemy.repository.PostRepository;
import com.lucas.curso_udemy.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class InstantiationData implements CommandLineRunner {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public InstantiationData(UserRepository userRepository,  PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepository.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        userRepository.saveAll(List.of(maria, alex, bob));

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para São Paulo. Abraços!", new AuthorDTO(maria));
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Acordei feliz hoje!", "Tenha um ótimo dia!", new AuthorDTO(maria));

        CommentDTO comment1 = new CommentDTO("Boa viagem!!!", sdf.parse("21/03/2018"), new AuthorDTO(alex));
        CommentDTO comment2 = new CommentDTO("Aproveite!!!", sdf.parse("23/03/2018"), new AuthorDTO(bob));
        CommentDTO comment3 = new CommentDTO("Tenha um ótimo dia!!!", sdf.parse("23/03/2018"), new AuthorDTO(alex));

        post1.getComments().addAll(List.of(comment1, comment2));
        post2.getComments().add(comment3);

        postRepository.saveAll(List.of(post1, post2));

        maria.getPosts().addAll(List.of(post1, post2));
        userRepository.save(maria);
    }
}
