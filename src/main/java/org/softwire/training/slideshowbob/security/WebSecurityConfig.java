package org.softwire.training.slideshowbob.security;

import org.softwire.training.slideshowbob.security.security.AuthenticationFailureHandler;
import org.softwire.training.slideshowbob.security.security.AuthenticationSuccessHandler;
import org.softwire.training.slideshowbob.security.security.TokenAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

    private AuthenticationSuccessHandler successHandler;
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    public WebSecurityConfig(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        this.successHandler = successHandler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests()
                .antMatchers("/",
                        "/fail",
                        "/success",
                        "/login",
                        "/signup",
                        "/js/*",
                        "/styles/*",
                        "/images/*",
                        "/slideshow").permitAll()
                .anyRequest().permitAll()
                .and().formLogin().loginPage("/login").loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and().csrf().disable()
                .addFilterAfter(tokenAuthenticationFilter(), BasicAuthenticationFilter.class);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
