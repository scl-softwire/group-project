package org.softwire.training.slideshowbob.security;

import org.softwire.training.slideshowbob.security.jwt.AuthenticationSuccessHandler;
import org.softwire.training.slideshowbob.security.jwt.CookieAuthenticationFilter;
import org.softwire.training.slideshowbob.security.jwt.JwtTokenFilterConfigurer;
import org.softwire.training.slideshowbob.security.jwt.TokenAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationSuccessHandler authSuccessHandler;

    @Autowired
    SimpleUrlAuthenticationFailureHandler authFailureHandler;

    @Autowired
    private TokenAuthenticationService tokenAuthenticationService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // disable caching
        http.headers().cacheControl();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable(); // disable csrf for our requests.

        http.authorizeRequests()
                .antMatchers("/",
                        "/login",
                        "/signup",
                        "/js/**",
                        "/styles/**",
                        "/images/**",
                        "/slideshow").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .failureHandler(authFailureHandler)
                .loginPage("/login")
                .loginProcessingUrl("/login")
                .successHandler(authSuccessHandler)
                .and()
                // JWT cookie filter
                .addFilterAfter( getCookieAuthenticationFilter(
                        new AndRequestMatcher( new AntPathRequestMatcher( "/admin" ) )
                ) , UsernamePasswordAuthenticationFilter.class );

        http.exceptionHandling().accessDeniedPage("/");
    }

    @Bean
    SimpleUrlAuthenticationFailureHandler getAuthFailureHandler() {

        SimpleUrlAuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler( "/fail" );
        handler.setDefaultFailureUrl( "/fail" );
        //handler.setUseForward( true );
        return handler;

    }

    CookieAuthenticationFilter getCookieAuthenticationFilter(RequestMatcher requestMatcher ) {

        CookieAuthenticationFilter filter = new CookieAuthenticationFilter( requestMatcher );
        filter.setAuthenticationFailureHandler( authFailureHandler );
        return filter;
    }
}
