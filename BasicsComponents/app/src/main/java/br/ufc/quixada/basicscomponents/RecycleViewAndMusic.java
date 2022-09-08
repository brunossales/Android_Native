package br.ufc.quixada.basicscomponents;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class RecycleViewAndMusic extends AppCompatActivity {
    private RecyclerView recycler;
    private JogoAdapter adapter;
    private ArrayList<Jogo> itens;

    MediaPlayer mySound;
    Button playMusic, voltarMain;

    @Override
    protected void onPause() {
        super.onPause();
        mySound.release();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view);

        recycler = findViewById(R.id.recyclerView);
        itens = new ArrayList<Jogo>();
        itens.add(new Jogo("Assassins Creed", "Xbox"));
        itens.add(new Jogo("Forza Horizon", "Xbox"));
        itens.add(new Jogo("God Of War", "PS4"));
        adapter = new JogoAdapter(RecycleViewAndMusic.this, itens);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RecycleViewAndMusic.this,
                                                                            LinearLayoutManager.VERTICAL,
                                                                false);
        recycler.setLayoutManager(layoutManager);
        recycler.setAdapter(adapter);

        playMusic = findViewById(R.id.buttonMusic);
        voltarMain = findViewById(R.id.VoltarMain);
        mySound = MediaPlayer.create(this, R.raw.sobejunto);


        playMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySound.start();
            }
        });

        voltarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RecycleViewAndMusic.this, MainActivity.class);
                startActivity(i);
            }
        });
    }
}