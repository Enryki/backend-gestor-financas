package com.henrique.gestaofinancas.categorias;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.henrique.gestaofinancas.dto.CategoriaUpdateDTO;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria addCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(CategoriaUpdateDTO novCategoria, Long userId){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(novCategoria.getId());
 
        if(categoriaOptional.isEmpty() || !userId.equals(categoriaOptional.get().getId())){
            throw new RuntimeException("Erro ao editar categoria.");
        }

        Categoria categoriaExistente = categoriaOptional.get();
        categoriaExistente.setCategoria(novCategoria.getCategoria());
        
        return categoriaRepository.save(categoriaExistente);
    }


    
}