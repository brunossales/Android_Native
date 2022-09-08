package br.ufc.quixada.basicscomponents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class SegundaActivity extends AppCompatActivity {
    private Button goMainActivity, ButtonRec;
    private ListView listView;
    ArrayList<Sports> sports = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda);

        actionsScreenSecondActivity();
    }

    public void actionsScreenSecondActivity(){
        goMainActivity = findViewById(R.id.button1S);
        ButtonRec = findViewById(R.id.buttonRec);

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

        //Questão 14 e 15
        listView.setAdapter(adapter);
    }

    public void addValuesToSportList(){
        this.sports.add(new Sports("Jogo de bola", "Jogando bola na praia", 15));
        this.sports.add(new Sports("Jogo de basquete", "Jogando basquete na praia", 35));
        this.sports.add(new Sports("Jogo de Volei", "Jogando Volei na praia", 25));
    }



}