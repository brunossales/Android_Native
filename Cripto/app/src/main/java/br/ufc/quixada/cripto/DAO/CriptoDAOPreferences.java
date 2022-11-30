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

public class CriptoDAOPreferences implements CriptoDAOInterface{
    private static ArrayList<Criptomoeda> list = new ArrayList<>();
    private static Context context;
//    private static boolean initialized = false;

    private static CriptoDAOPreferences criptoDAOPreferences = null;
    private static Criptomoeda criptomoeda = null;

    private CriptoDAOPreferences(Context c){ CriptoDAOPreferences.context = c;}

    public static CriptoDAOInterface getInstance(Context context){
        if(criptoDAOPreferences == null){
            criptoDAOPreferences = new CriptoDAOPreferences(context);
        }

        return criptoDAOPreferences;
    }

    @Override
    public boolean addCripto(Criptomoeda c) {
        c.salvar();
        getListaCripto();
//        list.add(c);
        return true;
    }

    @Override
    public boolean editCripto(Criptomoeda c) {

        boolean edited = true;
        try{
            getMinhasMoedasReference().child(c.getId()).setValue(c);
            getListaCripto();
        }catch (Exception e){
            edited = false;
        }
        return edited;
//        boolean edited = false;
//        for (Criptomoeda criptomoeda : list){
//            if (criptomoeda.getId() == c.getId()){
//                criptomoeda.setNome(c.getNome());
//                criptomoeda.setSimbolo(c.getSimbolo());
//
//                criptomoeda.setValor(c.getValor());
//                edited = true;
//                break;
//            }
//        }
//        return edited;
    }

    @Override
    public boolean editIsStar(String criptoId) {
        boolean modified = true;

        Query nomeCriptoQuery = getMinhasMoedasReference();
        nomeCriptoQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dados : snapshot.getChildren()){
                    Criptomoeda c = dados.getValue(Criptomoeda.class);
                    if (c.getId().equals(criptoId)){
                        if (c.isStar()){
                            c.setStar(false);
                            getMinhasMoedasReference().child(c.getId()).setValue(c);
                            getListaCripto();
                        }
                        else {
                            c.setStar(true);
                            getMinhasMoedasReference().child(c.getId()).setValue(c);
                            getListaCripto();
                        }
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(
                        context.getApplicationContext(),
                        "Error ao pesquisar",
                        Toast.LENGTH_SHORT).show();
            }
        });

//        for (Criptomoeda criptomoeda : list){
//            if (criptomoeda.getId() == criptoId){
//                if (criptomoeda.isStar()){criptomoeda.setStar(false);}
//                else {criptomoeda.setStar(true);}
//                modified = true;
//                break;
//            }
//        }
        getListaCripto();
        return modified;
    }

    @Override
    public boolean deleteCripto(String criptoId) {

        boolean deleted = true;
        Criptomoeda criAux = getCripto(criptoId);

        try{
            getMinhasMoedasReference().child(criptoId).removeValue();
            list.remove(criAux);
        }catch (Exception e){
            deleted = false;
        }
        return deleted;

//        boolean deleted = false;
//        Criptomoeda criptomoedaAux = null;
//
//        for (Criptomoeda criptomoeda : list){
//            if (criptomoeda.getId() == criptoId){
//                criptomoedaAux = criptomoeda;
//                deleted = true;
//                break;
//            }
//        }
//
//        if (deleted == true) list.remove(criptomoedaAux);
//        return deleted;
    }

    @Override
    public boolean deleteAll() {
        list.clear();
        return true;
    }

    @Override
    public Criptomoeda getCripto(String criptoId) {
        try{
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
        }catch (Exception e){
            Toast.makeText(
                    context.getApplicationContext(),
                    "Error ao pesquisar Throw",
                    Toast.LENGTH_SHORT).show();
        }

        return criptomoeda;
    }

    @Override
    public ArrayList<Criptomoeda> findByName(String name) {
        getListaCripto();
        ArrayList<Criptomoeda> listFindByName = new ArrayList<>();
        for (Criptomoeda cri : list){
            if (cri.getNome().indexOf(name) != -1){listFindByName.add(cri);}
        }
        return listFindByName;
    }

    @Override
    public List<String> getNameList() {
        getListaCripto();
        List<String> aux = new ArrayList<>();
        for (Criptomoeda cri : list){
            aux.add(cri.getNome());
        }
        return aux;
    }

    @Override
    public ArrayList<Criptomoeda> getListaCripto() {
//        list.clear();
        Query nomeCriptoQuery = getMinhasMoedasReference();
        nomeCriptoQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dados : snapshot.getChildren()){
                    Criptomoeda c = dados.getValue(Criptomoeda.class);
                    list.add(c);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(
                        context.getApplicationContext(),
                        "Error ao pesquisar",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return list;
    }

    @Override
    public ArrayList<Criptomoeda> getListaCriptoStars() {
        ArrayList<Criptomoeda> criptoFavorites = new ArrayList<>();
        Query nomeCriptoQuery = getMinhasMoedasReference();
        nomeCriptoQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dados : snapshot.getChildren()){
                    Criptomoeda c = dados.getValue(Criptomoeda.class);
                    if (c.isStar()){criptoFavorites.add(c);}
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(
                        context.getApplicationContext(),
                        "Error ao pesquisar",
                        Toast.LENGTH_SHORT).show();
            }
        });

        return criptoFavorites;

//        ArrayList<Criptomoeda> criptoFavorites = new ArrayList<>();
//        for (Criptomoeda cri : list){
//            if(cri.isStar()){criptoFavorites.add(cri);}
//        }
//
//        return criptoFavorites;
    }

    public DatabaseReference getMinhasMoedasReference() {
        DatabaseReference referenceFirebase = FirebaseDatabase.getInstance().getReference();
        return referenceFirebase.child("moedas");
    }
}
