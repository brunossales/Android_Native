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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class Feed_activity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    String nameUser;

    Intent intent;

    ArrayList<Criptomoeda> list;

    CustomAdapter customAdapter;
    RecyclerView recyclerView;

    CriptoDAOInterface criptoDAO;

    BottomNavigationView nav;

    TextView textViewBemVindo;
    ImageView imageLogout;
    boolean progressBar = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        getSupportActionBar().hide();


        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        criptoDAO = CriptoDAOPreferences.getInstance(Feed_activity.this);
        getListCriptoFeed();

        nameUser = getIntent().getExtras().getString(Codes.Key_BemVindo);


        textViewBemVindo = findViewById(R.id.textViewName);
        textViewBemVindo.setText(textViewBemVindo.getText() + currentUser.getEmail());

        imageLogout = findViewById(R.id.feedLogout);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Feed_activity.this);

        customAdapter = new CustomAdapter(Feed_activity.this, list);
        recyclerView = findViewById(R.id.recyclerCriptoFeed);
        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter(customAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration( Feed_activity.this, DividerItemDecoration.VERTICAL));
        customAdapter.notifyDataSetChanged();

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

        imageLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(Feed_activity.this, Login_activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public void removerCripto(Criptomoeda cri){
        criptoDAO.deleteCripto(cri.getId());
        customAdapter.notifyDataSetChanged();
    }


    public void handleAdd(View view){
        Intent intent = new Intent(this, Change_add_activity.class);
        startActivityForResult(intent, Codes.REQUEST_ADD);
    }

    public void setCriptoStarFeed(String criptoID){
        criptoDAO.editIsStar(criptoID);
        criptoDAO.getListaCriptoStars();

        customAdapter.notifyDataSetChanged();
    }

    public void updateCripto(Criptomoeda cri){

        String nome = cri.getNome();
        String simbolo = cri.getSimbolo();
        String valor = cri.getValor();
        String id = cri.getId();

        Intent intent = new Intent(this, Change_add_activity.class);
        intent.putExtra(Codes.Key_Name, nome);
        intent.putExtra(Codes.Key_Simbolo, simbolo);
        intent.putExtra(Codes.Key_Valor, valor);
        intent.putExtra(Codes.Key_ID, id);

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


            String id = "-1";
            if (idString != null){
                id = idString;

                Criptomoeda c = new Criptomoeda(nome, simbolo, valor);
                c.setId(id);

                criptoDAO.editCripto(c);
                customAdapter.notifyDataSetChanged();
            }

            customAdapter.notifyDataSetChanged();
        }
    }

    public void getListCriptoFeed(){
        this.list = criptoDAO.getListaCripto();
    }

}

