package com.dimax.retrospectator.Config;

import com.auth0.spring.security.api.JwtWebSecurityConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
                .cors().and().csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated();
    }


//    @Override
//    protected void configure(HttpSecurity security) throws Exception {
//        security.httpBasic().disable();
//        security.csrf().disable();
//    }


}
