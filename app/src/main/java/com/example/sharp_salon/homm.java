package com.example.sharp_salon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.sharp_salon.R.id;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class homm extends AppCompatActivity {

    Toolbar toolbar;
    NavigationView navigationview;
    DrawerLayout drawer ;
    ActionBarDrawerToggle toggle;
    FrameLayout frame;

    ImageButton call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homm);
        getSupportActionBar().hide();

        toolbar=findViewById(R.id.toolbar);
        drawer=findViewById(R.id.drawer);
        navigationview=findViewById(id.navigationview);
        frame=findViewById(R.id.frame);

        call=findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent idial = new Intent(Intent.ACTION_DIAL);
//                we can also call the imiplicit intent with the help of intent object
                Intent idial = new Intent();
                 idial.setAction(Intent.ACTION_DIAL);
//                idial.setAction(Intent.ACTION_CALL);
                idial.setData(Uri.parse("tel: +91983514722"));
                startActivity(idial);
            }
        });




        toggle=new ActionBarDrawerToggle(this,drawer ,toolbar ,R.string.open,R.string.close );
         drawer.addDrawerListener(toggle);
         toggle.syncState();
         toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.white));

           getSupportFragmentManager().beginTransaction().add(R.id.frame ,new f1()).commit();
           navigationview.setCheckedItem(R.id.nav_home);
         navigationview.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
             @Override
             public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId())
                 {
                     case R.id.nav_home:
                         getSupportFragmentManager().beginTransaction().replace(R.id.frame ,new f1()).commit();
                         break;
                     case R.id.nav_about:
                         getSupportFragmentManager().beginTransaction().replace(R.id.frame ,new appointment()).commit();
                         break;
                     case R.id.navlogout:
                         FirebaseAuth.getInstance().signOut();
                         Intent intent=new Intent(homm.this ,Home.class);
                         Toast.makeText(homm.this, "logout successfull", Toast.LENGTH_SHORT).show();
//                         SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
//                         SharedPreferences.Editor bditor=pref.edit();
//                         bditor.putBoolean("flag",true);
//                         bditor.apply();
                         startActivity(intent);
                         finish();

                 }
                 drawer.closeDrawer(GravityCompat.START



                 );

                 return true;
             }
         });

    }
}