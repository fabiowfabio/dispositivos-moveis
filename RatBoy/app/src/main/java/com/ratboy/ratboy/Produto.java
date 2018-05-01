package com.ratboy.ratboy;

import java.io.Serializable;


public class Produto implements Serializable{
    public long id;
    public String codigo;
    public String descricao;
    public double preco;
    public String quantidade;
    public String fabricacao;
    public String observacao;
    public byte[] imagem;

    public Produto() {

    }

}
