package com.dimax.retrospectator.Controllers;


import com.dimax.retrospectator.Entity.AuthUser;
import com.dimax.retrospectator.Entity.User;
import com.dimax.retrospectator.Services.UserRepository;
import com.dimax.retrospectator.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.servlet.*;
import java.io.IOException;
import java.util.Collections;

@Component
public class AuthenticationUserFilter implements Filter {

    @Autowired
    UserService userRepository;

    @Value("${auth0.audience}")
    String uri;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String  sub = authentication.getPrincipal().toString();
        if (authentication.isAuthenticated() && userRepository != null) {
            User user = userRepository.getBySub(sub);

            if(user == null) {

                AuthUser authUser = getAuthUser(authentication);
                user = userRepository.getUser(authUser);
                request.setAttribute("user", user);

            } else {
                request.setAttribute("user", user);
            }
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private AuthUser getAuthUser(Authentication authentication) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String tockenForIdentifier = authentication.getCredentials().toString();
        headers.set("Authorization", "Bearer " + tockenForIdentifier);

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<AuthUser> responseEntity = restTemplate.exchange(uri, HttpMethod.GET, entity, AuthUser.class);

        return responseEntity.getBody();


    }
}