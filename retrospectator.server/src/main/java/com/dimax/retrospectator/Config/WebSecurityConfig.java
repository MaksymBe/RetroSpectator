package com.dimax.retrospectator.Config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import com.dimax.retrospectator.Controllers.AuthenticationUserFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@Configuration
@EnableWebSecurity(debug = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Value("${auth0.audience}")
    private String audience;

    @Value("${auth0.issuer}")
    private String issuer;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        JwtWebSecurityConfigurer
                .forRS256(audience, issuer)
                .configure(http)
                .cors().and().addFilterAfter(new AuthenticationUserFilter(), FilterSecurityInterceptor.class)
                .authorizeRequests()
                .anyRequest().authenticated();
    }


//    @Override
//    public void configure(AuthenticationManagerBuilder builder)
//            throws Exception {
//        builder.userDetailsService(new MyUserDetailsService());
//    }

//    @Override
//    protected void configure(HttpSecurity security) throws Exception {
//        security.httpBasic().disable();
//        security.csrf().disable();
//    }


}
