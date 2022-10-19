package br.ufc.quixada.navbetweenscreens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

import br.ufc.quixada.navbetweenscreens.DAO.CriptoDAOInterface;
import br.ufc.quixada.navbetweenscreens.DAO.CriptoDAOPreferences;
import br.ufc.quixada.navbetweenscreens.model.Criptomoeda;
import br.ufc.quixada.navbetweenscreens.view.CustomAdapter;

public class MainActivity extends AppCompatActivity {

    ArrayList<Criptomoeda> lista;
    Button btnAdd;
    Button btnLimpar;

    //implement this button latter
    Button btnUpdate;

    CustomAdapter adapter;
    RecyclerView recyclerView;

    CriptoDAOInterface criptoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        criptoDAO = CriptoDAOPreferences.getInstance(this);


    }
}