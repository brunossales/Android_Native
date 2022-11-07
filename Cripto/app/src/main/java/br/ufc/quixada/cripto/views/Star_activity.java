package br.ufc.quixada.cripto.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import br.ufc.quixada.cripto.R;
import br.ufc.quixada.cripto.controller.Codes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Star_activity extends AppCompatActivity {
    Intent intent;
    String nameUser;

    TextView userText;

    BottomNavigationView nav;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_star);
        getSupportActionBar().hide();


        userText = findViewById(R.id.textViewName);
        nameUser = getIntent().getExtras().getString(Codes.Key_BemVindo);
        userText.setText(nameUser);

        nav = findViewById(R.id.bottomNavigationView);

        nav.setSelectedItemId(R.id.star);

        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homee:
                        intent = null;
                        intent = new Intent(Star_activity.this, Feed_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;
                    case R.id.search:
                        Toast.makeText(Star_activity.this, "Pesquisar pressionado", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.aboutwithus:
                        Toast.makeText(Star_activity.this, "Sobre pressionado", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                }
                return true;
            }
        });

    }
}