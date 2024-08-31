package com.eazybank.filter;

import com.eazybank.constant.ApplicationConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JWTTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // get the jwt token from the request
        String jwt = request.getHeader(ApplicationConstants.JWT_HEADER);
        System.out.println("JWT Token coming from the request: "+jwt);
        if(null != jwt) {
            try {
                Environment env = getEnvironment();
                if (null != env) {
                    // fetching the secret from the environment variable
                    String secret = env.getProperty(ApplicationConstants.JWT_SECRET_KEY,
                            ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);

                    // getting the SecretKey based on the secret
                    SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                    if(null !=secretKey) {
                        // verifying the token with the secretKey if anyone tried to change the token this line will generate
                        // exception. based on this secretKey we generated the token that's why based on this secretKey
                        // it will extract the token and validate it
                        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();

                        // getting the payload section of the token in the form of Claims object
                        Claims claims = jwtParser.parseSignedClaims(jwt).getPayload();

                        // extracting username and authority from the claims. we set this data at the time of token generation
                        String username = String.valueOf(claims.get("username"));
                        String authorities = String.valueOf(claims.get("authorities"));

                        // Token verified and user is valid now we are creating authentication object
                        Authentication authentication = new UsernamePasswordAuthenticationToken(username, null,
                                AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));

                        // parent class method
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }

            } catch (Exception exception) {
                throw new BadCredentialsException("Invalid Token received!");
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/auth/user");
    }
}
