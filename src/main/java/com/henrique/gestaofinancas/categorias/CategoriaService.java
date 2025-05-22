package com.henrique.gestaofinancas.categorias;
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

    
}