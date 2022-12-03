package br.ufc.quixada.cripto.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import br.ufc.quixada.cripto.DAO.CriptoDAOInterface;
import br.ufc.quixada.cripto.DAO.CriptoDAOPreferences;
import br.ufc.quixada.cripto.R;

public class MainActivity extends AppCompatActivity {
    ImageView buttonIntro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.intro_activity);

        CriptoDAOInterface criptoDAO = CriptoDAOPreferences.getInstance(MainActivity.this);
        criptoDAO.getListaCripto();
        criptoDAO.getListaCriptoStars();
        handleEvents();
    }

    public void handleEvents(){
        Intent intent = new Intent(MainActivity.this, Login_activity.class);
        buttonIntro = findViewById(R.id.imageViewIntro);

        buttonIntro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }



}