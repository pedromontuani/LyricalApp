package com.domain.lyricalapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.splash_screen);

        int splashInterval = 2000;

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if(currentUser != null) {
                    Intent i = new Intent(SplashScreen.this, Home.class);
                    startActivity(i);
                    this.finish();
                } else {
                    Intent i = new Intent(SplashScreen.this, Intro.class);
                    startActivity(i);
                    this.finish();
                }

            }

            private void finish() {
            }}, splashInterval);

    } // end onCreate()



} //@end
