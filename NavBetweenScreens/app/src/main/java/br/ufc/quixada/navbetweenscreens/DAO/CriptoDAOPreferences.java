package br.ufc.quixada.navbetweenscreens.DAO;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.ArrayList;

import br.ufc.quixada.navbetweenscreens.model.Criptomoeda;

public class CriptoDAOPreferences implements CriptoDAOInterface{

    private static ArrayList<Criptomoeda>list;
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Context context;
    private static boolean initialized = false;

    private static CriptoDAOPreferences criptoDAOPreferences = null;

    private CriptoDAOPreferences(Context c){ CriptoDAOPreferences.context = c;}

    public static CriptoDAOInterface getInstance(Context context){
        if(criptoDAOPreferences == null){
            criptoDAOPreferences = new CriptoDAOPreferences(context);
        }

        return criptoDAOPreferences;
    }

    @Override
    public boolean addCripto(Criptomoeda c) {
        list.add(c);
        return true;
    }

    @Override
    public boolean editCripto(Criptomoeda c) {
        boolean edited = false;
        for (Criptomoeda criptomoeda : list){
            if (criptomoeda.getId() == c.getId()){
                criptomoeda.setNome(c.getNome());
                criptomoeda.setSimbolo(c.getSimbolo());

                double valor = Double.parseDouble(c.getValor());
                criptomoeda.setValor(valor);
                edited = true;
                break;
            }
        }
        return edited;
    }

    @Override
    public boolean deleteCripto(int criptoId) {

        boolean deleted = false;
        Criptomoeda criptomoedaAux = null;

        for (Criptomoeda criptomoeda : list){
            if (criptomoeda.getId() == criptoId){
                criptomoedaAux = criptomoeda;
                deleted = true;
                break;
            }
        }

        if (deleted == true) list.remove(criptomoedaAux);
        return deleted;
    }

    @Override
    public boolean deleteAll() {
        list.clear();
        return true;
    }

    @Override
    public Criptomoeda getCripto(int criptoId) {
        Criptomoeda criptomoeda = null;
        for (Criptomoeda cri : list){
            if (cri.getId() == criptoId){
                criptomoeda = cri;
                break;
            }
        }
        return criptomoeda;
    }

    @Override
    public ArrayList<Criptomoeda> getListaCripto() {
        return list;
    }
}