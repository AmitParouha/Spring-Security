package com.eazybank.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Enumeration;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

public class RequestValidationBeforeFilter implements Filter{

    public static final String AUTHENTICATION_SCHEME_BASIC = "Basic";
    private Charset credentialsCharset = StandardCharsets.UTF_8;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Type casting Request and Response object into the HttpServletRequest and HttpServletResponse object
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // getting the header of login request (email and password)
        Enumeration<String> header = req.getHeaderNames();
        while (header.hasMoreElements()){
            System.out.println("Value: "+header.nextElement().contains("test"));
        }
        if (false && header != null) {
            String email="" ;//= header.trim();
            if (email.toLowerCase().contains("test")) {
                res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

//            if (StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
//                byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
//                byte[] decoded;
//                try {
//                    decoded = Base64.getDecoder().decode(base64Token);
//                    String token = new String(decoded, credentialsCharset);
//                    int delim = token.indexOf(":");
//                    if (delim == -1) {
//                        throw new BadCredentialsException("Invalid basic authentication token");
//                    }
//                    String email = token.substring(0, delim);
//                    if (email.toLowerCase().contains("test")) {
//                        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//                        return;
//                    }
//                } catch (IllegalArgumentException e) {
//                    throw new BadCredentialsException("Failed to decode basic authentication token");
//                }
//            }
        }
        chain.doFilter(request, response);
    }

}
