package br.ufc.quixada.cripto.views;

import androidx.appcompat.app.AppCompatActivity;
import br.ufc.quixada.cripto.R;
import android.os.Bundle;

public class Feed_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        getSupportActionBar().hide();
    }
}