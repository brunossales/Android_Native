package br.ufc.quixada.crypto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        Screen1();
    }

    public void Screen1(){
        ImageView imageViewIntro = (ImageView) findViewById(R.id.imageView);
        imageViewIntro.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                setContentView(R.layout.login_activity);
                Screen2();
            }
        });
    }

    public void Screen2(){
        Button buttonLogin = (Button) findViewById(R.id.button);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.sigup_activity);
                Screen3();
            }
        });
    }

    public void Screen3(){
        Button buttonSeguir = (Button) findViewById(R.id.button2);
        buttonSeguir.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                setContentView(R.layout.activity_intro);
                Screen1();
            }
        });
    }


}