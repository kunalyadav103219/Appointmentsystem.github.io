package com.example.sharp_salon;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {
    LottieAnimationView animation_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animation_view=findViewById(R.id.animation_view);
        animation_view.setAnimation(R.raw.dermatology);
        getSupportActionBar().hide();



        Thread thread=new Thread() {
            @Override
            public void run() {
                try {

                  sleep(3000);
                }
                catch (Exception e)
                {
                    e.getStackTrace();
                }
                finally {
                    Intent inext;
                    SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
                   Boolean cheak=pref.getBoolean("flag",false);
                   if(cheak)
                   {
                       inext=new Intent(MainActivity.this,homm.class);
                   }
                   else
                   {
                       inext=new Intent(MainActivity.this,Home.class);
                   }
                   startActivity(inext);

                }
                }

            };thread.start();


    }
}