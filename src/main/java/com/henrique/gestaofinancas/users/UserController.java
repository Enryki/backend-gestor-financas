package com.henrique.gestaofinancas.users;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping(path = "api/registrar")
public class UserController {
    
    @GetMapping
    public String getUser() {
        return "This is and String";
    }

    
    
}
