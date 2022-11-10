package br.ufc.quixada.cripto.DAO;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.cripto.model.Criptomoeda;

public interface CriptoDAOInterface {
    static CriptoDAOInterface getInstance(Context context) {
        return null;
    }

    boolean addCripto(Criptomoeda c);
    boolean editCripto(Criptomoeda c);
    boolean editIsStar(int criptoId);

    boolean deleteCripto(int criptoId);
    boolean deleteAll();

    Criptomoeda getCripto(int criptoId);

    ArrayList<Criptomoeda> findByName(String name);

    List<String> getNameList();
    ArrayList<Criptomoeda> getListaCripto();
    ArrayList<Criptomoeda> getListaCriptoStars();


}
