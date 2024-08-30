package com.eazybank.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class ProjectSecurityConfig   {

    //  Mandatory to create a PasswordEncoder bean either you are using it or not
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf-> csrf.disable())
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/account/**","/balance/**","/loans/**","/cards/**").authenticated()
                .requestMatchers("/notices/**","/contact/**","/auth/**").permitAll())

                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }


////    jdbc authentication
//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        return new JdbcUserDetailsManager(dataSource);
//    }
//---------------------------------------------------------------------------------------
//    In memory authentication
//    @Bean
//    public UserDetailsService userDetailsService(){
//        // define user details and role
//        UserDetails admin = User.builder()
//                .username("amit07@gmail.com")
//                .password(passwordEncoder().encode("amit1234"))
//                .roles("ADMIN")
//                .build();
//
//        UserDetails user = User.withUsername("rahul07@gmail.com")
//                .password(passwordEncoder().encode("rahul1234"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }

}
