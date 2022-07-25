package com.learning.securtiy;

import com.learning.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import org.springframework.web.bind.annotation.CrossOrigin;


import org.springframework.util.ClassUtils;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.lang.reflect.InvocationTargetException;
import java.sql.Driver;



import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@Configuration

//@EnableWebSecurity

//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)

//@EnableWebSecurity
@EnableGlobalMethodSecurity(
		  prePostEnabled = true, 
		  securedEnabled = true, 
		  jsr250Enabled = true)

@EnableWebSecurity
@EnableWebMvc
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JWTFilter filter;
    @Autowired
    private MyUserDetailsService uds;
   

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http .cors().and()
                .csrf().disable()
                .authorizeRequests()

//                .antMatchers("/api/customer*").hasRole("Customer")

                .antMatchers("/api/customer/authenticate","/api/staff/authenticate","/api/admin/authenticate","/api/customer/register","/api/customer/forgotpassword","/api/customer/{username}/forgot/{question}/{answer}").permitAll()
//                .antMatchers("/api/customer/register").permitAll()
//                //.antMatchers("/api/customer/{customerID}").permitAll()
//                .antMatchers("/api/customer/getuser").permitAll()
//                .antMatchers("/api/customer/getuserID").permitAll()
//                .antMatchers("/api/customer/{customerID}/account").permitAll()
//                //.antMatchers("/api/customer/getuser").permitAll()
//                .antMatchers("/api/customer/forgotpassword").permitAll()
//                .antMatchers("/api/customer/{username}/forgot/{question}/{answer}").permitAll()
//                .antMatchers("/api/customer/authenticate").permitAll()
                .antMatchers("/api/staff*").hasRole("Staff")
                .antMatchers("/api/admin*").hasRole("Admin")



                .antMatchers("/api/customer/authenticate","/api/admin/login","/api/staff/authenticate","/api/staff/getuser").permitAll()
                .antMatchers("/api/staff/authenticate").permitAll()



                .antMatchers("/api/customer/authenticate","/api/customer/getuser","/api/customer/register","/api/admin/login","/api/staff/authenticate","/api/staff/getuser","/api/admin/authenticate").permitAll()
                .antMatchers("/api/customer/authenticate","/api/admin/login","/api/staff/authenticate","/api/staff/getuser","/api/staff/beneficiary","/api/admin/authenticate").permitAll()
               // .antMatchers("/api/staff/authenticate").permitAll()

                //.antMatchers("/api/staff/transfer").permitAll()
                //.antMatchers("/api/staff/beneficiary").permitAll()
                //.antMatchers("api/admin/staff").permitAll()
//                .antMatchers("/api/admin*").hasRole("Admin")

             
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
//        auth.inMemoryAuthentication().withUser("taku").password(this.bCryptPasswordEncoder().encode("taku")).roles("ADMIN");
        auth.jdbcAuthentication().passwordEncoder(bCryptPasswordEncoder());
    }
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}





