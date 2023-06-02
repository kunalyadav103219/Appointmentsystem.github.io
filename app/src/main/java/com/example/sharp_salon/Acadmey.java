package com.example.sharp_salon;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Acadmey extends AppCompatActivity {
 ActionBar actionBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acadmey);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setTitle("Acadmey");
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.clr));

    }
}