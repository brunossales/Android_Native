package br.ufc.quixada.navbetweenscreens.DAO;

import android.content.Context;

import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.Array;
import java.util.ArrayList;

import br.ufc.quixada.navbetweenscreens.model.Criptomoeda;

public interface CriptoDAOInterface {

    static CriptoDAOInterface getInstance(Context context) {
        return null;
    }

    boolean addCripto(Criptomoeda c);
    boolean editCripto(Criptomoeda c);
    boolean deleteCripto(String criptoId);
    boolean deleteAll();

    Criptomoeda getCripto(String criptoId);
    ArrayList<Criptomoeda> getListaCripto();


}
