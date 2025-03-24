package com.henrique.gestaofinancas.users;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;

@Configuration
public class UserConfig {
    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
                User teste = new User(
                    "teste",
                    "teste@gmail.com",
                    "123"
                );

            repository.save(teste);
        };
    }
}
