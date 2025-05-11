package com.lucas.curso_udemy.service;

import com.lucas.curso_udemy.domain.User;
import com.lucas.curso_udemy.dto.UserDTO;
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

    public User save(User user) {
        return userRepository.insert(user);
    }

    public void delete(String id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }
    }

    public User update(User user) {
        User userExist = findById(user.getId());
        updateData(userExist, user);
        return userRepository.save(user);

    }

    public User fromUserDTO(UserDTO userDTO) {
        return new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
    }

    private void updateData(User newUser, User user) {
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail());
    }
}
