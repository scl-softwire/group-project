package org.softwire.training.slideshowbob.security;

import org.apache.catalina.SessionListener;
import org.softwire.training.slideshowbob.models.database.AdminUser;
import org.softwire.training.slideshowbob.security.security.AuthenticationFailureHandler;
import org.softwire.training.slideshowbob.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.annotation.PostConstruct;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthenticationSuccessHandler successHandler;
    private AuthenticationFailureHandler failureHandler;

    @Autowired
    public WebSecurityConfig(AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
        this.failureHandler = failureHandler;
        this.successHandler = successHandler;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers("/",
                        "/favicon.ico",
                        "/js/*",
                        "/styles/*",
                        "/images/*",
                        "/error",
                        "/login",
                        "/slideshow").permitAll()
                .anyRequest().authenticated()
                .and().formLogin().loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .logout()
                .deleteCookies("JSESSIONID")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public SessionListener listener(){
        return sessionEvent -> sessionEvent.getSession().setMaxInactiveInterval(10);
    }

    @Bean
    public UsersService usersService(){return new UsersService(bCryptPasswordEncoder());}

    @PostConstruct
    public void init() {
        AdminUser user = new AdminUser();
        user.setUsername("root");
        user.setPassword("pass");
        if (usersService().getAlladmins().stream()
                .noneMatch(user1 -> user1.getUsername().equalsIgnoreCase(user.getUsername())))
            usersService().createUser(user);
    }
}
