package com.eazybank.config;

import com.eazybank.entity.Authority;
import com.eazybank.entity.Customer;
import com.eazybank.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EazyBankAuthenticationProvider implements AuthenticationProvider {

    private CustomerService customerService;
    private PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // get username and password from the Authentication object
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        // fetch user details from the database using username given by user
        List<Customer> customers = customerService.getCustomerDetails(authentication);

        if (customers!=null){
            Customer customer = customers.get(0);
            // check password is valid or not
            if(passwordEncoder.matches(password, customer.getPwd())){
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(customer.getRole()));
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            }else {
                throw new BadCredentialsException("Invalid password");
            }
        }else {
            throw new BadCredentialsException("Invalid username");
        }
    }

//    private List<GrantedAuthority> grantedAuthorityList(Set<Authority> authorities){
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//
//        authorities.stream()
//                .map(authority->grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthorityName())))
//                .collect(Collectors.toList());
//
//        return grantedAuthorities;
//    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
