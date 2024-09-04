package com.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests((requests) -> requests.requestMatchers("/secure").authenticated()
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }

//    @Bean
//    public ClientRegistrationRepository clientRegistrationRepository() {
//        ClientRegistration github = githubClientRegistration();
//        //ClientRegistration google = gooleClientRegistration();
//        return new InMemoryClientRegistrationRepository(github); // this constructor will accept multiple object
//    }
//
//    private ClientRegistration githubClientRegistration() {
//        // CommonOAuth2Provider it's an enum class
//        return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("Ov23lighIAaDKJApeZ2W")
//                .clientSecret("cbf8f6ac90327607329c7a31b8c6d77f5bf62774").build();
//    }

//    private ClientRegistration gooleClientRegistration() {
//        return CommonOAuth2Provider.GOOGLE.getBuilder("google").clientId("974042741122392")
//                .clientSecret("36d48c25c1767d58b3101551513d7e1e").build();
//    }
}
