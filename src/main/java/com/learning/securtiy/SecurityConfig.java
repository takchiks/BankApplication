package com.learning.securtiy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.util.UrlPathHelper;



@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)

@EnableWebSecurity


public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/customer*").hasRole("Customer")
                .antMatchers("/api/customer*").permitAll()
                .antMatchers("/api/staff*").hasRole("Staff")
                .antMatchers("/api/admin*").hasRole("Admin")
                .anyRequest()
                .authenticated()
                .and()
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
}





