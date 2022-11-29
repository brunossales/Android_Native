package br.ufc.quixada.navbetweenscreens.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import br.ufc.quixada.navbetweenscreens.DAO.CriptoDAOFirebase;
import br.ufc.quixada.navbetweenscreens.DAO.CriptoDAOInterface;
import br.ufc.quixada.navbetweenscreens.DAO.CriptoDAOPreferences;
import br.ufc.quixada.navbetweenscreens.R;
import br.ufc.quixada.navbetweenscreens.controller.Codes;
import br.ufc.quixada.navbetweenscreens.model.Criptomoeda;
import br.ufc.quixada.navbetweenscreens.view.ActivityTwo;
import br.ufc.quixada.navbetweenscreens.view.CustomAdapter;

public class MainActivity extends AppCompatActivity {

    ArrayList<Criptomoeda> lista;
    Button btnAdd;
    Button getBtnUpdate;
    EditText editText;

    //implement this button latter
    Button btnUpdate;

    CustomAdapter adapter;
    RecyclerView recyclerView;

    CriptoDAOFirebase criptoDAOFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        criptoDAOFirebase = CriptoDAOFirebase.getInstance(this);
        lista = criptoDAOFirebase.getListaCripto();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        adapter = new CustomAdapter(this, lista);
        recyclerView =  findViewById(R.id.RecyclerViewCriptomoedas);
        recyclerView.setLayoutManager( linearLayoutManager );
        recyclerView.setAdapter(adapter);

        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        adapter.notifyDataSetChanged();

        editText = findViewById(R.id.textUpdate);
        btnAdd = findViewById(R.id.buttonAdd);
        btnUpdate = findViewById(R.id.buttonUpdate);
    }

    public void handleAdd(View view){
        Intent intent = new Intent(this, ActivityTwo.class);
        startActivityForResult(intent, Codes.REQUEST_ADD);
    }

    public void removerCripto(String pos){
        criptoDAOFirebase.deleteCripto(pos);
        adapter.notifyDataSetChanged();
    }

    public void handleEdit(View view){
        String idAux = editText.getText().toString();

        Criptomoeda cri = criptoDAOFirebase.getCripto(idAux);

        if(cri == null){
            Toast.makeText( getApplicationContext(), "Não encontrado", Toast.LENGTH_LONG ).show();
            return ;
        }
        String nome = cri.getNome();
        String simbolo = cri.getSimbolo();
        String valor = cri.getValor();

        Intent intent = new Intent(getApplicationContext(), ActivityTwo.class);
        intent.putExtra(Codes.Key_Name, nome);
        intent.putExtra(Codes.Key_Simbolo, simbolo);
        intent.putExtra(Codes.Key_Valor, valor);
        intent.putExtra( Codes.Key_ID, ""+idAux );

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
            Toast.makeText(this, valor, Toast.LENGTH_SHORT).show();

            criptoDAOFirebase.addCripto(cri);
            adapter.notifyDataSetChanged();
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

                criptoDAOFirebase.editCripto(c);
                adapter.notifyDataSetChanged();
            }

            adapter.notifyDataSetChanged();
        }
    }
}