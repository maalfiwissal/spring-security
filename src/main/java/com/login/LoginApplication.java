package com.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.login.entity.User;
import com.login.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories
public class LoginApplication implements CommandLineRunner {

    @Autowired
    @Lazy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;
    
	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);
	}
	
    @Override
    public void run(String...args) throws Exception {
        userRepository.save(new User("wissal", bCryptPasswordEncoder.encode("wissal")));
    }

}
