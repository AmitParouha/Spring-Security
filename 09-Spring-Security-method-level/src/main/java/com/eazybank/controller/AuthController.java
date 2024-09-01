package com.eazybank.controller;

import com.eazybank.constant.ApplicationConstants;
import com.eazybank.entity.Customer;
import com.eazybank.model.LoginRequestDto;
import com.eazybank.model.LoginResponseDto;
import com.eazybank.service.CustomerService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {
    private CustomerService service;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final Environment env;

    @PostMapping(value = {"/register","sign-up"})
    public ResponseEntity<String> registerCustomer(@RequestBody Customer customer){
        Customer savedCustomer = null;
        ResponseEntity response = null;
        try{
            customer.setRole("ROLE_USER");
            customer.setCreateDt(new Date(System.currentTimeMillis()).toString());
            savedCustomer = service.saveCustomerDetails(customer);
            if(savedCustomer.getId()>0){
                response = ResponseEntity.status(HttpStatus.CREATED)
                        .body("Your account is created");
            }
        }catch (Exception ex){
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occurred due to: "+ex.getMessage());
        }

        return response;
    }

    @GetMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication){
        List<Customer> customers = service.getCustomerDetails(authentication);
        if(customers != null){
            return customers.get(0);
        }
        return null;
    }

    @PostMapping("/apiLogin")
    public ResponseEntity<LoginResponseDto> apiLogin (@RequestBody LoginRequestDto loginRequest) {
        String jwt = "";

        // Here we are converting the LoginRequestDto content into the Authentication object
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(),
                loginRequest.password());

        // authenticate method will perform the authentication if user is valid then it will return authentication
        // object or else null
        // EazyBankUsernamePwdAuthenticationProvider class authenticate method will call because it's an implementation
        // class of AuthenticationManager
        Authentication authenticationResponse = authenticationManager.authenticate(authentication);

        // logic for generating jwt token
        if(null != authenticationResponse && authenticationResponse.isAuthenticated()) {
            if (null != env) {
                String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
                        ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                jwt = Jwts.builder().issuer("Banking Application").subject("JWT Token")
                        .claim("username", authenticationResponse.getName())
                        .claim("authorities", authenticationResponse.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                        .issuedAt(new java.util.Date())
                        .expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                        .signWith(secretKey).compact();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,jwt)
                .body(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(), jwt));
    }
}