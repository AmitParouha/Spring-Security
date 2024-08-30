package com.eazybank.controller;

import com.eazybank.payload.RegisterDto;
import com.eazybank.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private UserService service;

    @PostMapping(value = {"/register","sign-up"})
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterDto registerDto){
        RegisterDto createdUser = service.registerUser(registerDto);
        if(createdUser==null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("User is already existed");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Your account is created");
    }

}
