package br.ufc.quixada.cripto.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.UUID;

public class Criptomoeda implements Serializable {
    String id;


    String nome;
    String simbolo;
    String valor;
    boolean isStar = false;

    public Criptomoeda(String nomeC, String simboloC, String valorC){
        this.nome = nomeC;
        this.simbolo = simboloC;
        this.valor = valorC;
    }

    public Criptomoeda(){}

    public void salvar(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        setId(UUID.randomUUID().toString());
        reference.child("moedas").child(getId()).setValue(this);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public String getValor() {
        return valor;
    }

    public void setStar(boolean star) {
        isStar = star;
    }

    public boolean isStar() {
        return isStar;
    }

    @Override
    public String toString() {
        return "Nome: " + nome;
    }
}
