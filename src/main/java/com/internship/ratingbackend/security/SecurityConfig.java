package com.internship.ratingbackend.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security configuration of the application
 */

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final OAuthFilter oAuthFilter;

    /**
     * config method that protects routes
     *
     * @param http
     * @throws Exception
     */

    @Override
    protected void configure(HttpSecurity http) throws Exception {http
            .cors().and().csrf().disable()
            .authorizeRequests()
            .antMatchers(HttpMethod.GET,"/api/v1/ratings").hasRole("ADMIN")
            .antMatchers(HttpMethod.PATCH,"/api/v1/settings").hasRole("ADMIN")
            .anyRequest()
            .permitAll()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(oAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
