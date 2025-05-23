package com.henrique.gestaofinancas.categorias;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.henrique.gestaofinancas.dto.CategoriaDTO;
import com.henrique.gestaofinancas.security.UserPrincipal;
import com.henrique.gestaofinancas.users.User;
import com.henrique.gestaofinancas.users.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping(path = "api/categoria")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoriaController {
    private final CategoriaService categoriaService;
    private final UserService userService;

    public CategoriaController(CategoriaService categoriaService, UserService userService){
        this.categoriaService = categoriaService;
        this.userService = userService;
    }


    @PostMapping
    public ResponseEntity<Object> AddValue(@RequestBody CategoriaDTO categoriaDTO){
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long userId = userPrincipal.getId();

        User user = userService.verificaUsuarioporId(userId);

        Categoria categoria = new Categoria(user,categoriaDTO.getCategoria());
        
        categoriaService.addCategoria(categoria);


        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Object> editValue(@RequestBody Categoria categoria) {

        categoriaService.updateCategoria(categoria);
        
        return ResponseEntity.noContent().build();
    }
    
}
