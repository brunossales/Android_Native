package br.ufc.quixada.cripto.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import br.ufc.quixada.cripto.R;

public class Sigup_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sigup);
    }
}