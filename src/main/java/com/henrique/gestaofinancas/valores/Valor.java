package com.henrique.gestaofinancas.valores;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "valores")
public class Valor {

    private Long id;
    private Integer tipo;
    private Float valor;
    private String categoria;
    private String observacao;

    public Valor(){
    }

    public Valor(Long id, Integer tipo, Float valor, String categoria, String observacao){
        this.id = id;
        this.tipo = tipo;
        this.valor = valor;
        this.categoria = categoria;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getTipo() {
        return tipo;
    }
    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }
    public Float getValor() {
        return valor;
    }
    public void setValor(Float valor) {
        this.valor = valor;
    }
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    public String getObservacao() {
        return observacao;
    }
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return "Valor [id=" + id + ", tipo=" + tipo + ", valor=" + valor + ", categoria=" + categoria + ", observacao="
                + observacao + "]";
    }

    
}
