package com.henrique.gestaofinancas.dto;

public class ValorRequestDTO {
    private Integer tipo;
    private Float valor;
    private String categoria;
    private String observacao;


    public ValorRequestDTO(Integer tipo, Float valor, String categoria, String observacao){
        this.tipo = tipo;
        this.valor = valor;
        this.categoria = categoria;
        this.observacao = observacao;

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


}
