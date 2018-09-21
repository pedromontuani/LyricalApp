package com.domain.lyricalapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toolbar;

public class Feed extends AppCompatActivity {

    //private RecyclerView recyclerView;
    private TextView txtBemVindo;
    private Toolbar toolbarLyrical;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

    }
}
