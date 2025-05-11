package com.lucas.curso_udemy.configuration;

import com.lucas.curso_udemy.domain.Post;
import com.lucas.curso_udemy.domain.User;
import com.lucas.curso_udemy.repository.PostRepostory;
import com.lucas.curso_udemy.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimeZone;

@Configuration
public class InstantiationData implements CommandLineRunner {

    private final PostRepostory  postRepostory;
    private final UserRepository userRepository;

    public InstantiationData(UserRepository userRepository,  PostRepostory postRepostory) {
        this.userRepository = userRepository;
        this.postRepostory = postRepostory;
    }

    @Override
    public void run(String... args) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        userRepository.deleteAll();
        postRepostory.deleteAll();

        User maria = new User(null, "Maria Brown", "maria@gmail.com");
        User alex = new User(null, "Alex Green", "alex@gmail.com");
        User bob = new User(null, "Bob Grey", "bob@gmail.com");

        Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para São Paulo. Abraços!", maria);
        Post post2 = new Post(null, sdf.parse("23/03/2018"), "Acordei feliz hoje!", "Tenha um ótimo dia!", maria);

        userRepository.saveAll(List.of(maria, alex, bob));
        postRepostory.saveAll(List.of(post1, post2));
    }
}
