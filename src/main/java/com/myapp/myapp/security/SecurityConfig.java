 package com.myapp.myapp.security;

import java.text.Format;

import com.myapp.myapp.dao.UserDetailsService;
import com.myapp.myapp.dao.UserRepository;
import com.myapp.myapp.web.UserController;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
    private final UserRepository userRepo;

    public SecurityConfig(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      
    //    PasswordEncoder passwordEncoder = passwordEncoder(); 
    //    auth.inMemoryAuthentication().withUser("user1").password(passwordEncoder.encode("1234")).roles("USER");
    //    auth.inMemoryAuthentication().withUser("user2").password(passwordEncoder.encode("123456")).roles("USER");
    //    auth.inMemoryAuthentication().withUser("user3").password(passwordEncoder.encode("1234567")).roles("USER", "ADMIN");
    }

   
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.formLogin();
        // //http.httpBasic();
        // http.authorizeRequests().antMatchers("/save-patient**/**", "/deletePatient**/**", "/create**/**").hasRole("ADMIN");
        // http.authorizeRequests().antMatchers("/patient**/**").hasRole("USER");
        // // http.authorizeRequests().anyRequest().authenticated();
        // http.exceptionHandling().accessDeniedPage("/notAutorized");
        
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
