package br.ufc.quixada.cripto.DAO;

import android.content.Context;

import java.util.ArrayList;

import br.ufc.quixada.cripto.model.Criptomoeda;

public interface CriptoDAOInterface {
    static CriptoDAOInterface getInstance(Context context) {
        return null;
    }

    boolean addCripto(Criptomoeda c);
    boolean editCripto(Criptomoeda c);
    boolean deleteCripto(int criptoId);
    boolean deleteAll();

    Criptomoeda getCripto(int criptoId);
    ArrayList<Criptomoeda> getListaCripto();

}
