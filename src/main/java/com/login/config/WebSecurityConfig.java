package com.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        /**
         * Custom configurations as per our requirement
         */
        http.authorizeHttpRequests((auth) -> {
			try {
				auth
				     .requestMatchers("/login", "/css/**").permitAll()
				     .anyRequest().authenticated()
				     .and()
				     .formLogin()
				     .loginPage("/login")
				     .defaultSuccessUrl("/welcome")
				    .failureUrl("/login?error")
				    .permitAll()
				    .and()
				    .logout()
				    .permitAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
        ).httpBasic(Customizer.withDefaults());
        return http.build();

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//            .antMatchers("/resources/**", "/WEB-INF/jsp/*").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .loginPage("/login")
//            .defaultSuccessUrl("/welcome")
//            .failureUrl("/login?error")
//            .permitAll()
//            .and()
//            .logout()
//            .permitAll();
//    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
    }
}
