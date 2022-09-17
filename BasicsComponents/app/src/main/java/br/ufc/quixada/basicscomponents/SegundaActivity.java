package br.ufc.quixada.basicscomponents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

import br.ufc.quixada.basicscomponents.model.Contato;
import br.ufc.quixada.basicscomponents.view.CustomAdapter;

public class SegundaActivity extends AppCompatActivity {
    private Button goMainActivity, ButtonRec, buttonRecycler2;
    private ListView listView;
    ArrayList<Sports> sports = new ArrayList<>();
    ArrayList<Contato> lista = new ArrayList<>();
    CustomAdapter adapter;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);
        adapter = new CustomAdapter( lista );

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView = findViewById(R.id.reclycerView2);
        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter( adapter );

        actionsScreenSecondActivity();

    }

    public void actionsScreenSecondActivity(){
        goMainActivity = findViewById(R.id.button1S);
        ButtonRec = findViewById(R.id.buttonRec);
        buttonRecycler2 = findViewById(R.id.buttonAddRecycler);

        listView = findViewById(R.id.listViewId);
        addValuesToSportList();
        ArrayAdapter<Sports> adapter = new ArrayAdapter<Sports>(this, android.R.layout.simple_list_item_1, sports);

        //Voltar
        goMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SegundaActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        ButtonRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SegundaActivity.this, RecycleViewAndMusic.class);
                startActivity(i);
            }
        });

        buttonRecycler2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addValuesToContato();
            }
        });

        //Questão 14 e 15
        listView.setAdapter(adapter);
    }

    public void addValuesToSportList(){
        this.sports.add(new Sports("Jogo de bola", "Jogando bola na praia", 15));
        this.sports.add(new Sports("Jogo de basquete", "Jogando basquete na praia", 35));
        this.sports.add(new Sports("Jogo de Volei", "Jogando Volei na praia", 25));
    }

    public void addValuesToContato(){
        this.lista.add(new Contato("Bruno", "8855", "Rua nova"));
        adapter.notifyDataSetChanged();
    }
}