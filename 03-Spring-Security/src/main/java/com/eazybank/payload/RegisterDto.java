package com.eazybank.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    private long id;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 3, message = "Name must contains minimum 3 letter")
    private String name;

    @NotEmpty(message = "Email should not be empty")
    private String email;

    @NotEmpty(message = "Username should not be empty")
    @Size(min = 3, message = "Username must contains minimum 3 letter")
    private String userName;

    @NotEmpty(message = "Password should not be empty")
    @Size(min = 8, message = "Password length must be 8 letter")
    private String password;

    private String userRole;
}

