package com.example.one.configuration.application;

import com.example.one.services.JisUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true)
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final JisUserDetailsService jisUserDetailsService;

    @Autowired
    public ApplicationSecurityConfiguration(JisUserDetailsService jisUserDetailsService) {
        this.jisUserDetailsService = jisUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/jis/secure/**").authenticated()
                .and().formLogin()  //login configuration
                .loginPage("/jis/login")
                .loginProcessingUrl("/login")
                .defaultSuccessUrl("/jis/secure/actors")
                .and().logout()    //logout configuration
                .logoutUrl("/jis-logout")
                .logoutSuccessUrl("/jis/login")
                .and().exceptionHandling() //exception handling configuration
                .accessDeniedPage("/jis/error");
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.userDetailsService(jisUserDetailsService).passwordEncoder(passwordEncoder);
    }
}
