package com.henrique.gestaofinancas.users;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User addNewUser(User user){
        return userRepository.save(user);
    }

    public List<User> listAll(){
        return userRepository.findAll();
    }

    public Optional<User> verificaUsuario(String username){
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional;
    }

    public User verificaUsuarioporId(Long id) {
        return userRepository.findById(id)
                 .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));
    }
    
    public boolean verificaExistencia(String email, String username){
        return userRepository.existsByEmailOrUsername(email, username);
    }
}
