package com.henrique.gestaofinancas.valores;
import org.springframework.stereotype.Service;

@Service
public class ValorService {
    private final ValorRepository valorRepository;

    public ValorService(ValorRepository valorRepository){
        this.valorRepository = valorRepository;
    }   

    public Valor addValue(Valor valor){
        return valorRepository.save(valor);
    }
}
