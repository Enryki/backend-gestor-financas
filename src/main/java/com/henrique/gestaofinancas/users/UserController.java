package com.henrique.gestaofinancas.users;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping(path = "api/registrar")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("listar")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.listAll();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public ResponseEntity<User> addNewUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userService.addNewUser(user);
        return ResponseEntity.ok(savedUser);
    }
    
    @GetMapping("/validarSenha")
    public ResponseEntity<Boolean> validarSenha(@RequestParam String username, @RequestParam String password) {
        
        Optional<User> optionalUser = userService.verificaUsuario(username);
        
        if (optionalUser.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
        }

        User user = optionalUser.get();
        boolean valid = passwordEncoder.matches(password, user.getPassword() );

        HttpStatus status = (valid) ? HttpStatus.OK: HttpStatus.UNAUTHORIZED;

        return ResponseEntity.status(status).body(valid);
    }
    
    
    
}
