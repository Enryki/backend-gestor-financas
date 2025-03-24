package com.henrique.gestaofinancas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class, ManagementWebSecurityAutoConfiguration.class})
public class GestaofinancasApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestaofinancasApplication.class, args);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder(){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
}
