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
import br.ufc.quixada.cripto.adapters.CustomAdapterFind;
import br.ufc.quixada.cripto.adapters.CustomAdapterStar;
import br.ufc.quixada.cripto.controller.Codes;
import br.ufc.quixada.cripto.model.Criptomoeda;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class Find_activity extends AppCompatActivity {
    Intent intent;
    String nameUser;

    TextView userText;

    BottomNavigationView nav;

    CustomAdapterFind customAdapter;
    RecyclerView recyclerView;

    CriptoDAOInterface criptoDAO;

    AutoCompleteTextView autoCompleteFind;

    ArrayList<Criptomoeda> listFind;

    LinearLayoutManager linearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        getSupportActionBar().hide();

        userText = findViewById(R.id.textViewName);
        nameUser = getIntent().getExtras().getString(Codes.Key_BemVindo);
        userText.setText(nameUser);

        criptoDAO = CriptoDAOPreferences.getInstance(this);

        ArrayAdapter<String> adapterStringFind = new ArrayAdapter<String>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, criptoDAO.getNameList());

        autoCompleteFind = findViewById(R.id.autoCompleteTextViewFind);
        autoCompleteFind.setAdapter(adapterStringFind);

        linearLayoutManager = new LinearLayoutManager(Find_activity.this);


        nav = findViewById(R.id.bottomNavigationView);

        nav.setSelectedItemId(R.id.search);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.star:
                        intent = null;
                        intent = new Intent(Find_activity.this, Star_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;
                    case R.id.homee:
                        intent = null;
                        intent = new Intent(Find_activity.this, Feed_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;
                    case R.id.aboutwithus:
                        intent = null;
                        intent = new Intent(Find_activity.this, About_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;

                    default:
                }
                return true;
            }
        });
    }

    public void handleFind(View view){
        String findStringBase = autoCompleteFind.getText().toString();
        listFind = criptoDAO.findByName(findStringBase);
        if(listFind.isEmpty()){Toast.makeText(Find_activity.this, "Não existe esse nome nos arquivos", Toast.LENGTH_SHORT).show();}

        customAdapter = new CustomAdapterFind(this, listFind);
        recyclerView = findViewById(R.id.recycletFindCripto);
        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter(customAdapter);

        recyclerView.addItemDecoration(new DividerItemDecoration( this, DividerItemDecoration.VERTICAL));
    }
}