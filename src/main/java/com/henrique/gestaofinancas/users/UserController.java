package com.henrique.gestaofinancas.users;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.henrique.gestaofinancas.dto.UserDTO;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    public ResponseEntity<?> addNewUser(@RequestBody User user) {

        boolean alreadyexists = userService.verificaExistencia(user.getEmail(), user.getUsername());

        if(alreadyexists){
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "Usuário com esse email ou username já existe.");
            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userService.addNewUser(user);
        UserDTO userDTO = new UserDTO(
        savedUser.getId(),
        savedUser.getUsername(),
        savedUser.getEmail()
    );
        final int TOKEN_EXPIRACAO = 600_000;
        final String TOKEN_SENHA = "1e42f221-69bb-46b1-a057-a2aecfb21238";
        String token = JWT.create()
        .withSubject(savedUser.getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + TOKEN_EXPIRACAO))
        .sign(Algorithm.HMAC512(TOKEN_SENHA));
        Map<String, Object> response = new HashMap<>();
        response.put("user", userDTO);
        response.put("token", token);
        return ResponseEntity.ok(response);
        //return ResponseEntity.ok(userDTO);
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
