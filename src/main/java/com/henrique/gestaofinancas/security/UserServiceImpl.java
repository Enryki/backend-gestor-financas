package com.henrique.gestaofinancas.security;

import java.util.Optional;
import org.springframework.stereotype.Service;

import com.henrique.gestaofinancas.users.User;
import com.henrique.gestaofinancas.users.UserData;
import com.henrique.gestaofinancas.users.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class UserServiceImpl implements UserDetailsService{

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()){
            throw new UsernameNotFoundException("Usuário [" + username + "] Não encontrado");
        }

        return new UserData(user);
    }

}
