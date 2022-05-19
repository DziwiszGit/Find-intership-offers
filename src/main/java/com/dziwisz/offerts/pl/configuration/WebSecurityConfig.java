package com.dziwisz.offerts.pl.configuration;

import com.dziwisz.offerts.pl.DAO.UserDAO;
import com.dziwisz.offerts.pl.model.User;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private UserDetailsServiceImpl userDetailsService;
    private UserDAO userDAO;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, UserDAO userDAO) {
        this.userDetailsService = userDetailsService;
        this.userDAO = userDAO;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyRole("ROOT","USER")
                .antMatchers("/main").hasAnyRole("ROOT","USER")
                .and()
                .formLogin().permitAll()
                .and()
                .csrf().disable()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get(){
        User user = new User("user", passwordEncoder().encode("user"),"ROLE_USER");
        User root = new User("root", passwordEncoder().encode("root"),"ROLE_ROOT");
        userDAO.save(user);
        userDAO.save(root);
    }

}
