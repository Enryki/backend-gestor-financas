package com.henrique.gestaofinancas.valores;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.gestaofinancas.dto.ValorRequestDTO;
import com.henrique.gestaofinancas.users.User;
import com.henrique.gestaofinancas.users.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping(path = "api/valor")
@CrossOrigin(origins = "http://localhost:4200")
public class ValorController {
    private final ValorService valorService;
    private final UserService userService;

    public ValorController(ValorService valorService, UserService userService){
        this.valorService = valorService;
        this.userService = userService;
    }

    @PostMapping
    public String AddValue(@RequestBody ValorRequestDTO valorDTO, HttpServletRequest request) {
        System.out.println("teste1");
        Long userId = (Long) request.getAttribute("userId");
        System.out.println("teste2");
        System.out.println(userId);
        User user = userService.verificaUsuarioporId(userId);

        Valor valor = new Valor(
            valorDTO.getTipo(),
            valorDTO.getValor(),
            valorDTO.getCategoria(),
            valorDTO.getObservacao(),
            user
        );

        valorService.addValue(valor);
        
        return "deu certo!";
    }
    
}
