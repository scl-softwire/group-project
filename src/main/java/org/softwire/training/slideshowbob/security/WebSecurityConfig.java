package org.softwire.training.slideshowbob.security;

import org.softwire.training.slideshowbob.security.jwt.JwtTokenFilterConfigurer;
import org.softwire.training.slideshowbob.security.jwt.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private TokenAuthenticationService tokenAuthenticationService;

    @Autowired
    public WebSecurityConfig(TokenAuthenticationService tokenAuthenticationService) {
        this.tokenAuthenticationService = tokenAuthenticationService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable caching
        http.headers().cacheControl();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable(); // disable csrf for our requests.

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/signup").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/styles/*").permitAll()
                .antMatchers("/js/*").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated();
        http.exceptionHandling().accessDeniedPage("/");
        http.apply(new JwtTokenFilterConfigurer(tokenAuthenticationService));

    }
}
