package com.lucas.curso_udemy.service;

import com.lucas.curso_udemy.domain.User;
import com.lucas.curso_udemy.exception.ObjectNotFoundException;
import com.lucas.curso_udemy.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    public User findById(String id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException("Usuário não encontrado.")
        );
    }

}
