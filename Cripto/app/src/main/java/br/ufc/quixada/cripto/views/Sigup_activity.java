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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.ufc.quixada.cripto.R;
import br.ufc.quixada.cripto.model.Criptomoeda;

public class Sigup_activity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText edtEmailRegister;
    EditText edtPasswordRegister;
    EditText edtPasswordConfirmRegister;
    ProgressBar progressBarRegister;
    Button btnRegister;
    CheckBox checkBoxVisibilityPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sigup);

        mAuth = FirebaseAuth.getInstance();

        edtEmailRegister = findViewById(R.id.edtRegisterEmail);
        edtPasswordRegister = findViewById(R.id.edtPasswordRegister);
        edtPasswordConfirmRegister = findViewById(R.id.edtPasswordRegisterConfirmer);
        progressBarRegister = findViewById(R.id.progressBarRegister);
        btnRegister = findViewById(R.id.buttonRegister);
        checkBoxVisibilityPassword = findViewById(R.id.checkBoxRegister);


        checkBoxVisibilityPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    edtPasswordRegister.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    edtPasswordConfirmRegister.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    edtPasswordRegister.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    edtPasswordConfirmRegister.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String registerEmail = edtEmailRegister.getText().toString();
                String senha = edtPasswordRegister.getText().toString();
                String confirmarSenha = edtPasswordConfirmRegister.getText().toString();

                if(!TextUtils.isEmpty(registerEmail) || !TextUtils.isEmpty(senha) || !TextUtils.isEmpty(confirmarSenha)){
                    if (senha.equals(confirmarSenha)){
                        progressBarRegister.setVisibility(View.VISIBLE);

                        mAuth.createUserWithEmailAndPassword(registerEmail, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    Intent intent = new Intent(Sigup_activity.this, Login_activity.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    String error = task.getException().toString();
                                    Toast.makeText(
                                            Sigup_activity.this,
                                            ""+error,
                                            Toast.LENGTH_SHORT).show();
                                }
                                progressBarRegister.setVisibility(View.INVISIBLE);
                            }
                        });
                    }
                    else {
                        Toast.makeText(
                                Sigup_activity.this,
                                "As senhas devem ser iguais!",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}