package br.ufc.quixada.cripto.DAO;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import br.ufc.quixada.cripto.model.Criptomoeda;
import br.ufc.quixada.cripto.views.Feed_activity;

public class CriptoDAOPreferences implements CriptoDAOInterface{
    private static ArrayList<Criptomoeda> list = new ArrayList<>();
    private static ArrayList<Criptomoeda> criptoFavorites = new ArrayList<>();

    private static Context context;

    private static CriptoDAOPreferences criptoDAOPreferences = null;
    private static Criptomoeda criptomoeda = null;

    private CriptoDAOPreferences(Context c){
        CriptoDAOPreferences.context = c;
    }

    public static CriptoDAOInterface getInstance(Context context){
        if(criptoDAOPreferences == null){
            criptoDAOPreferences = new CriptoDAOPreferences(context);
        }

        return criptoDAOPreferences;
    }

    @Override
    public boolean addCripto(Criptomoeda c) {
        c.salvar();
        list.add(c);
        return true;
    }

    @Override
    public boolean editCripto(Criptomoeda c) {
        getMinhasMoedasReference().child(c.getId()).setValue(c);
        boolean edited = false;
        for (Criptomoeda criptomoeda : list) {
            if (criptomoeda.getId() == c.getId()) {
                criptomoeda.setNome(c.getNome());
                criptomoeda.setSimbolo(c.getSimbolo());

                criptomoeda.setValor(c.getValor());
                edited = true;
                break;
            }
        }
        return edited;
    }

    @Override
    public boolean editIsStar(String criptoId) {
        boolean modified = false;
        for (Criptomoeda criptomoeda : list){
            if (criptomoeda.getId().equals(criptoId)){
                if (criptomoeda.isStar()){
                    criptomoeda.setStar(false);
                    getMinhasMoedasReference().child(criptomoeda.getId()).child("star").setValue(false);
                }
                else {
                    criptomoeda.setStar(true);
                    getMinhasMoedasReference().child(criptomoeda.getId()).child("star").setValue(true);

                }
                modified = true;
                break;
            }
        }
        return modified;
    }

    @Override
    public boolean deleteCripto(String criptoId) {
        boolean deleted = false;
        Criptomoeda criptomoedaAux = null;

        for (Criptomoeda criptomoeda : list){
            if (criptomoeda.getId() == criptoId){
                criptomoedaAux = criptomoeda;
                deleted = true;
                break;
            }
        }

        if (deleted == true) {
            getMinhasMoedasReference().child(criptoId).removeValue();
            list.remove(criptomoedaAux);
        }
        return deleted;
    }

    @Override
    public boolean deleteAll() {
        list.clear();
        return true;
    }

    @Override
    public Criptomoeda getCripto(String criptoId) {
        criptomoeda = new Criptomoeda();
        getMinhasMoedasReference().child(criptoId).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    criptomoeda = task.getResult().getValue(Criptomoeda.class);
                }
                else {
                    Toast.makeText(
                            context.getApplicationContext(),
                            "Error ao pesquisar",
                            Toast.LENGTH_SHORT).show();
                    }
                }
            });
        return criptomoeda;
    }

    @Override
    public ArrayList<Criptomoeda> findByName(String name){
        getListaCripto();
        ArrayList<Criptomoeda> listFindByName = new ArrayList<>();
        for (Criptomoeda cri : list){
            if (cri.getNome().indexOf(name) != -1){listFindByName.add(cri);}
        }
        return listFindByName;
    }

    @Override
    public List<String> getNameList(){
        getListaCripto();
        List<String> aux = new ArrayList<>();
        for (Criptomoeda cri : list){
            aux.add(cri.getNome());
        }
        return aux;
    }

    @Override
    public ArrayList<Criptomoeda> getListaCripto() {
        Query nomeCriptoQuery = getMinhasMoedasReference();
        nomeCriptoQuery
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()){
                            list.clear();
                            for (DataSnapshot document : task.getResult().getChildren()){
                                Criptomoeda c = document.getValue(Criptomoeda.class);
                                list.add(c);
                            }
                        }
                    }
                });
        return list;
    }

    @Override
    public ArrayList<Criptomoeda> getListaCriptoStars() {
        Query nomeCriptoQuery = getMinhasMoedasReference();
        nomeCriptoQuery
                .get()
                .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (task.isSuccessful()){
                            criptoFavorites.clear();
                            for (DataSnapshot document : task.getResult().getChildren()){
                                Criptomoeda c = document.getValue(Criptomoeda.class);
                                if(c.isStar()) {
                                    criptoFavorites.add(c);
                                }
                            }
                        }
                    }
                });
        return criptoFavorites;
    }

    public DatabaseReference getMinhasMoedasReference() {
        DatabaseReference referenceFirebase = FirebaseDatabase.getInstance().getReference();
        return referenceFirebase.child("moedas");
    }
}
