package com.eazybank.config;

import com.eazybank.entity.User;
import com.eazybank.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EazyBankUserDetails implements UserDetailsService {

    private UserService userService;

    // loading User details from the database based the username which pass by the client from the frontend
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByEmailOrUserName(username, username)
                .orElseThrow(()-> new UsernameNotFoundException(username+" not found"));
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole()));

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username(username)
                .password(user.getPassword())
                .authorities(authorities)
                .build();

        return userDetails;
    }
}
