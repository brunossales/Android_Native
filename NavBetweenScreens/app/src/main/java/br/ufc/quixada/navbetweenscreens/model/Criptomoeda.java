package br.ufc.quixada.navbetweenscreens.model;

public class Criptomoeda {
    static int geradorDeIds = -1;
    int id;


    String nome;
    String simbolo;
    double valor;

    public Criptomoeda(String nomeC, String simboloC, double valorC){
        this.nome = nomeC;
        this.simbolo = simboloC;
        this.valor = valorC;

        geradorDeIds++;
        this.id = geradorDeIds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }
}
