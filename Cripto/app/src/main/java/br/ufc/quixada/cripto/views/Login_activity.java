package br.ufc.quixada.cripto.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import br.ufc.quixada.cripto.R;
import br.ufc.quixada.cripto.controller.Codes;

public class Login_activity extends AppCompatActivity {
    private FirebaseAuth mAuth;

    ProgressBar loginProgressBar;
    CheckBox checkBoxVisibilityPassword;
    Intent intent, intent2;
    TextView textRegister;
    Button buttonLoggin;
    EditText emailLogin, senhaLogin;
    String email, password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        loginProgressBar = findViewById(R.id.progressBarLogin);
        mAuth = FirebaseAuth.getInstance();

        handleEvents();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null ){
            Intent intent = new Intent(Login_activity.this, Feed_activity.class);
            intent.putExtra(Codes.Key_BemVindo, currentUser.getDisplayName());
            startActivity(intent);
        }
    }

    public void handleEvents(){
        intent = new Intent(Login_activity.this, Sigup_activity.class);
        intent2 = new Intent(Login_activity.this, Feed_activity.class);

        buttonLoggin = findViewById(R.id.buttonSubmitLogin);
        emailLogin = findViewById(R.id.loginEmail);
        senhaLogin = findViewById(R.id.loginSenha);

        checkBoxVisibilityPassword = findViewById(R.id.checkBoxLogin);


        textRegister = findViewById(R.id.textViewLogin4);


        buttonLoggin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailLogin.getText().toString();
                password = senhaLogin.getText().toString();

                if (!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
                    loginProgressBar.setVisibility(View.VISIBLE);
                    mAuth.signInWithEmailAndPassword(email, password)
                                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()){
                                                intent2.putExtra(Codes.Key_BemVindo, email);
                                                startActivity(intent2);
                                            }
                                            else {
                                                String error = task.getException().toString();
                                                Toast.makeText(
                                                        Login_activity.this,
                                                        ""+error,
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                            loginProgressBar.setVisibility(View.INVISIBLE);

                                        }
                                    });
                }
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intent);
            }
        });

        checkBoxVisibilityPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    senhaLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    senhaLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
}