package com.henrique.gestaofinancas.users;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>{
    public boolean existsByEmailOrUsername(String email, String username);

    public Optional<User> findByUsername(String username);
}

