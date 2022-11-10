package br.ufc.quixada.cripto.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import br.ufc.quixada.cripto.R;
import br.ufc.quixada.cripto.controller.Codes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class About_activity extends AppCompatActivity {
    Intent intent;
    String nameUser;

    BottomNavigationView nav;

    TextView textViewNameUser;

    MapView mapView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        getSupportActionBar().hide();

        nameUser = getIntent().getExtras().getString(Codes.Key_BemVindo);
        textViewNameUser = findViewById(R.id.textViewNameAbout);
        textViewNameUser.setText(textViewNameUser.getText() + nameUser);


        nav = findViewById(R.id.bottomNavigationView);
        nav.setSelectedItemId(R.id.aboutwithus);
        nav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.star:
                        intent = null;
                        intent = new Intent(About_activity.this, Star_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;
                    case R.id.search:
                        intent = null;
                        intent = new Intent(About_activity.this, Find_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;
                    case R.id.homee:
                        intent = null;
                        intent = new Intent(About_activity.this, Feed_activity.class);
                        intent.putExtra(Codes.Key_BemVindo, nameUser);
                        startActivity(intent);
                        break;

                    default:
                }
                return true;
            }
        });
    }
}