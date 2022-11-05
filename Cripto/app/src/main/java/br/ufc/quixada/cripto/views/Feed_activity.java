package br.ufc.quixada.cripto.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.ufc.quixada.cripto.DAO.CriptoDAOInterface;
import br.ufc.quixada.cripto.DAO.CriptoDAOPreferences;
import br.ufc.quixada.cripto.R;
import br.ufc.quixada.cripto.model.Criptomoeda;

import android.os.Bundle;

import java.util.ArrayList;

public class Feed_activity extends AppCompatActivity {

    ArrayList<Criptomoeda> list;

    CustomAdapter customAdapter;
    RecyclerView recyclerView;

    CriptoDAOInterface criptoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        getSupportActionBar().hide();

        criptoDAO = CriptoDAOPreferences.getInstance(this);
        list = criptoDAO.getListaCripto();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        customAdapter = new CustomAdapter(this, list);
        recyclerView = findViewById(R.id.recyclerViewCriptomoedas);
        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter(customAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration( this, DividerItemDecoration.VERTICAL));



    }
}