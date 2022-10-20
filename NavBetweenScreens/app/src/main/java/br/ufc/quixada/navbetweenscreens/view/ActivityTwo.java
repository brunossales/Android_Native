package br.ufc.quixada.navbetweenscreens.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.ufc.quixada.navbetweenscreens.R;
import br.ufc.quixada.navbetweenscreens.controller.Codes;

public class ActivityTwo extends AppCompatActivity {

    EditText edtitNome;
    EditText editSimbolo;
    EditText editValor;

    Button buttonBack;
    Button buttonAdd;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_two);

        id = -1;

        edtitNome = findViewById(R.id.edtNomeTela02);
        editSimbolo = findViewById(R.id.edtSimboloTela2);
        editValor = findViewById(R.id.edtValorTela2);

        buttonBack = findViewById(R.id.buttonVoltar);
        buttonAdd = findViewById(R.id.buttonAdd);

        handleStorage();
    }

    public void handleStorage(){
        if (getIntent().getExtras() != null){
            String nome = getIntent().getExtras().getString(Codes.Key_Name);
            String simbolo = getIntent().getExtras().getString(Codes.Key_Simbolo);
            String valor = getIntent().getExtras().getString(Codes.Key_Valor);
            String idString = getIntent().getExtras().getString(Codes.Key_ID);

            if(idString != null ) {
                id = Integer.parseInt(idString);
            }

            edtitNome.setText(nome);
            editSimbolo.setText(simbolo);
            editValor.setText(valor);
        }
    }


    public void back(View v) {
        finish();
    }


    public void add(View view){
        String nome = edtitNome.getText().toString();
        String simbolo = editSimbolo.getText().toString();
        String valor = editValor.getText().toString();

        Intent intent = new Intent();

        intent.putExtra(Codes.Key_Name, nome);
        intent.putExtra(Codes.Key_Simbolo, simbolo);
        intent.putExtra(Codes.Key_Valor, valor);

        if( id >= 0) intent.putExtra(Codes.Key_ID, ""+id);

        setResult(Codes.Response_OK, intent);
        finish();
    }
}
