package br.ufc.quixada.cripto.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import br.ufc.quixada.cripto.DAO.CriptoDAOInterface;
import br.ufc.quixada.cripto.DAO.CriptoDAOPreferences;
import br.ufc.quixada.cripto.R;
import br.ufc.quixada.cripto.adapters.CustomAdapter;
import br.ufc.quixada.cripto.adapters.CustomAdapterStar;
import br.ufc.quixada.cripto.controller.Codes;
import br.ufc.quixada.cripto.model.Criptomoeda;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;

public class Star_activity extends AppCompatActivity {
    Intent intent;
    String nameUser;

    TextView userText;

    BottomNavigationView nav;

    CustomAdapterStar customAdapter;
    RecyclerView recyclerView;

    CriptoDAOInterface criptoDAO;

    ArrayList<Criptomoeda> listStar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);
        getSupportActionBar().hide();


        userText = findViewById(R.id.textViewName);
        nameUser = getIntent().getExtras().getString(Codes.Key_BemVindo);
        userText.setText(nameUser);

        criptoDAO = CriptoDAOPreferences.getInstance(this);
        listStar = criptoDAO.getListaCriptoStars();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Star_activity.this);


        customAdapter = new CustomAdapterStar(this, listStar);

        recyclerView = findViewById(R.id.recyclerStarCripto);
        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter(customAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration( Star_activity.this, DividerItemDecoration.VERTICAL));

        customAdapter.notifyDataSetChanged();


        nav = findViewById(R.id.bottomNavigationView);

        nav.setSelectedItemId(R.id.star);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homee:
                        intent = null;
                        intent = new Intent(Star_activity.this, Feed_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;
                    case R.id.search:
                        intent = null;
                        intent = new Intent(Star_activity.this, Find_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;
                    case R.id.aboutwithus:
                        intent = null;
                        intent = new Intent(Star_activity.this, About_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;

                    default:
                }
                return true;
            }
        });

    }
}