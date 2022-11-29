package br.ufc.quixada.navbetweenscreens.DAO;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import br.ufc.quixada.navbetweenscreens.model.Criptomoeda;

public class CriptoDAOFirebase implements CriptoDAOInterface{

    private static ArrayList<Criptomoeda> list = new ArrayList<>();
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    private static Context context;
    private static boolean initialized = false;
    private static Criptomoeda criptomoeda = null;

    private static CriptoDAOFirebase criptoDAOFirebase = null;

    private CriptoDAOFirebase(Context c){ CriptoDAOFirebase.context = c;}

    public static CriptoDAOFirebase getInstance(Context context){
        if(criptoDAOFirebase == null){
            criptoDAOFirebase = new CriptoDAOFirebase(context);
        }

        return criptoDAOFirebase;
    }

    @Override
    public boolean addCripto(Criptomoeda c) {
        c.salvar();
        return true;
    }

    @Override
    public boolean editCripto(Criptomoeda c) {
        boolean edited = true;
        try{
            getMinhasMoedasReference().child(c.getId()).setValue(c);
        }catch (Exception e){
            edited = false;
        }
        return edited;
    }

    @Override
    public boolean deleteCripto(String criptoId) {
//        Query query = getMinhasMoedasReference().child(criptoId);
        getMinhasMoedasReference().child(criptoId).removeValue();
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
        return true;
    }

    @Override
    public boolean deleteAll() {
        list.clear();
        getMinhasMoedasReference();
        return true;
    }

    @Override
    public Criptomoeda getCripto(String criptoId) {
        criptomoeda = null;
        Query query = getMinhasMoedasReference().orderByChild(""+criptoId).limitToFirst(1);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                criptomoeda = snapshot.getValue(Criptomoeda.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(
                        context.getApplicationContext(),
                        "Error ao pesquisar",
                        Toast.LENGTH_SHORT).show();
            }
        });
        return criptomoeda;
    }

    @Override
    public ArrayList<Criptomoeda> getListaCripto() {
        Query nomeCriptoQuery = getMinhasMoedasReference();
        nomeCriptoQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
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

    public DatabaseReference getMinhasMoedasReference() {
        DatabaseReference referenceFirebase = FirebaseDatabase.getInstance().getReference();
        return referenceFirebase.child("moedas");
    }
}