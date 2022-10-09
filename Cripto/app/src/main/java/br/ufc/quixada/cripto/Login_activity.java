package br.ufc.quixada.cripto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.ufc.quixada.cripto.R;

public class Login_activity extends AppCompatActivity {
    Intent intent;
    Button buttonLoggin;
    EditText emailLogin, senhaLogin;
    String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        handleEvents();
    }

    public void handleEvents(){
        intent = new Intent(Login_activity.this, Sigup_activity.class);
        buttonLoggin = findViewById(R.id.buttonSubmitLogin);
        emailLogin = findViewById(R.id.loginEmail);
        senhaLogin = findViewById(R.id.loginSenha);

        buttonLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailLogin.getText().toString();
                password = senhaLogin.getText().toString();

                if (email.equals("admin") && password.equals("admin")){
                    startActivity(intent);
                }
                else{
                    Toast.makeText(
                      getApplicationContext(),
                      "Login ou senha incorretos",
                      Toast.LENGTH_LONG
                    ).show();
                }
            }
        });
    }
}