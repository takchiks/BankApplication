package com.learning.securtiy;

import com.learning.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;


@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)

@EnableWebSecurity


public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTFilter filter;
    @Autowired
    private MyUserDetailsService uds;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
//                .antMatchers("/api/customer*").hasRole("Customer")
                .antMatchers("/api/customer/authenticate","/api/staff/authenticate").permitAll()
//                .antMatchers().hasRole("Staff")
                .antMatchers("/api/staff*").hasRole("Staff")
                .antMatchers("/api/admin*").hasRole("Admin")
                .anyRequest()
                .authenticated()
                .and()
                .userDetailsService(uds)
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized")
                )
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class)
                .httpBasic();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("taku").password(this.bCryptPasswordEncoder().encode("taku")).roles("ADMIN");
        auth.jdbcAuthentication().passwordEncoder(bCryptPasswordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}





