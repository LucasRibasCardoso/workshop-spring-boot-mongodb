package com.lucas.curso_udemy.controller;

import com.lucas.curso_udemy.dto.UserDTO;
import com.lucas.curso_udemy.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> usersDTO = userService.findAll().stream()
                .map(UserDTO::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(usersDTO);
    }
}
