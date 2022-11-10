package br.ufc.quixada.cripto.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.ufc.quixada.cripto.DAO.CriptoDAOInterface;
import br.ufc.quixada.cripto.DAO.CriptoDAOPreferences;
import br.ufc.quixada.cripto.R;
import br.ufc.quixada.cripto.adapters.CustomAdapter;
import br.ufc.quixada.cripto.controller.Codes;
import br.ufc.quixada.cripto.model.Criptomoeda;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.Serializable;
import java.util.ArrayList;

public class Feed_activity extends AppCompatActivity {
    String nameUser;

    Intent intent;

    ArrayList<Criptomoeda> list;

    CustomAdapter customAdapter;
    RecyclerView recyclerView;

    CriptoDAOInterface criptoDAO;

    BottomNavigationView nav;

    TextView textViewBemVindo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        getSupportActionBar().hide();

        nameUser = getIntent().getExtras().getString(Codes.Key_BemVindo);


        textViewBemVindo = findViewById(R.id.textViewName);
        textViewBemVindo.setText(textViewBemVindo.getText() + nameUser);

        criptoDAO = CriptoDAOPreferences.getInstance(this);
        list = criptoDAO.getListaCripto();


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Feed_activity.this);

        customAdapter = new CustomAdapter(this, list);
        recyclerView = findViewById(R.id.recyclerCriptoFeed);
        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter(customAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration( this, DividerItemDecoration.VERTICAL));

        nav = findViewById(R.id.bottomNavigationView);
        nav.setSelectedItemId(R.id.homee);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.star:
                        intent = null;
                        intent = new Intent(Feed_activity.this, Star_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;
                    case R.id.search:
                        intent = null;
                        intent = new Intent(Feed_activity.this, Find_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;
                    case R.id.aboutwithus:
                        intent = null;
                        intent = new Intent(Feed_activity.this, About_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;

                    default:
                }
                return true;
            }
        });
    }

    public void removerCripto(Criptomoeda cri){
        criptoDAO.deleteCripto(cri.getId());
        customAdapter.notifyDataSetChanged();
    }

    public void backTologin(View v) {
        intent = null;
        intent = new Intent(Feed_activity.this, Login_activity.class);
        startActivity(intent);
    }

    public void handleAdd(View view){
        Intent intent = new Intent(this, Change_add_activity.class);
        startActivityForResult(intent, Codes.REQUEST_ADD);
    }

    public void setCriptoStarFeed(int criptoID){
        criptoDAO.editIsStar(criptoID);
        customAdapter.notifyDataSetChanged();
    }

    public void updateCripto(int pos){
        Criptomoeda c = criptoDAO.getCripto(pos);

        String nome = c.getNome();
        String simbolo = c.getSimbolo();
        String valor = c.getValor();
        int id = c.getId();

        Intent intent = new Intent(this, Change_add_activity.class);
        intent.putExtra(Codes.Key_Name, nome);
        intent.putExtra(Codes.Key_Simbolo, simbolo);
        intent.putExtra(Codes.Key_Valor, valor);
        intent.putExtra(Codes.Key_ID, ""+id);

        startActivityForResult(intent, Codes.REQUEST_EDT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Codes.REQUEST_ADD && resultCode == Codes.Response_OK){
            String nome = data.getExtras().getString( Codes.Key_Name);
            String valor = data.getExtras().getString( Codes.Key_Valor);
            String simbolo = data.getExtras().getString( Codes.Key_Simbolo);

            Criptomoeda cri = new Criptomoeda(nome, simbolo, valor);

            criptoDAO.addCripto(cri);
            customAdapter.notifyDataSetChanged();
        }

        else if (requestCode == Codes.REQUEST_EDT && resultCode == Codes.Response_OK){
            String nome = data.getExtras().getString( Codes.Key_Name);
            String valor = data.getExtras().getString( Codes.Key_Valor);
            String simbolo = data.getExtras().getString( Codes.Key_Simbolo);
            String idString = data.getExtras().getString(Codes.Key_ID);


            int id = -1;
            if (idString != null){
                id = Integer.parseInt(idString);

                Criptomoeda c = new Criptomoeda(nome, simbolo, valor);
                c.setId(id);

                criptoDAO.editCripto(c);
                customAdapter.notifyDataSetChanged();
            }

            customAdapter.notifyDataSetChanged();
        }
    }
}

