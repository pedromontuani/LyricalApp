package com.domain.lyricalapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;

public class Login extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private AlertDialog alerta;
    private Button buttonSair;
    private Button esqueSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Log.d("AUTH", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    Log.d("AUTH", "onAuthStateChanged:signed_out");
                }
            }
        };

        //Button nomeParaOBotao = (Button) findViewById(R.id.btnCadastrar);

        final EditText email = (EditText) findViewById(R.id.usernameTxt);
        final EditText senha = (EditText) findViewById(R.id.passwordTxt);
        esqueSenha = (Button) findViewById(R.id.forgotPassButt);
        Button btnLogar = (Button) findViewById(R.id.loginButt);
        Button btnCadastar = (Button) findViewById(R.id.signUpButt);

        btnCadastar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent abreCadastro = new Intent(Login.this, SignUp.class);
                startActivity(abreCadastro);
            }
        });
        esqueSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ass = new Intent(Login.this,localtroca.class);
                startActivity(ass);
            }
        });
        btnLogar.setOnClickListener(this);
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View view) {
        EditText editemail = (EditText) findViewById(R.id.usernameTxt);
        EditText editsenha = (EditText) findViewById(R.id.passwordTxt);
        String email = editemail.getText().toString();
        String senha = editsenha.getText().toString();

        mAuth.signInWithEmailAndPassword(email,senha).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    startActivity(new Intent(Login.this,Home.class));
                    finish();
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setMessage("Usuario não encontrado");
                    builder.setTitle("Erro");

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });

                    alerta = builder.create();
                    alerta.show();
                }
            }
        });
    }
}

