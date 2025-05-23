package com.henrique.gestaofinancas.categorias;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria addCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public Categoria updateCategoria(Categoria novCategoria){
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(novCategoria.getId());

        if(categoriaOptional.isEmpty()){
            throw new RuntimeException("Erro ao editar categoria.");
        }

        Categoria categoriaExistente = categoriaOptional.get();
        categoriaExistente.setCategoria(novCategoria.getCategoria());
        
        return categoriaRepository.save(categoriaExistente);
    }

    
}