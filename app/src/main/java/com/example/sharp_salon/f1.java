package com.example.sharp_salon;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.sharp_salon.R.id;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;


public class f1 extends Fragment {


    ImageSlider imgslider;
    LinearLayout appoint,map,acadmey;

    Activity context;
    TextView appp,mp,acc,nc,c,cc;
    FirebaseAuth auth;
    FirebaseFirestore fstore;
    String uid;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                             context=getActivity();

       View view=inflater.inflate(R.layout.fragment_f1, container, false);

        imgslider=view.findViewById(R.id.imgslider);
        appoint=view.findViewById(R.id.Appoint);
        map=view.findViewById(R.id.mapp);
        acadmey=view.findViewById(R.id.Acadmey);

        appp=view.findViewById(R.id.appp);
        mp=view.findViewById(id.mppp);
        acc=view.findViewById(id.acad);
        nc=view.findViewById(R.id.nc);
        c=view.findViewById(R.id.c);
        cc=view.findViewById(R.id.cutt);

              fstore=FirebaseFirestore.getInstance();
              DocumentReference documentReference=fstore.collection("users/pIgOVhRFKCRIX4ssT89ekZXTLUC3/home").document(
                              "6y85IIkQKVFrpJ86o7rO");
              documentReference.get()
                      .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                          @Override
                          public void onSuccess(DocumentSnapshot documentSnapshot) {
                              if(documentSnapshot.exists())
                              {
                                  String appointment=documentSnapshot.getString("f1");
                                  String map=documentSnapshot.getString("f2");
                                  String academy=documentSnapshot.getString("f3");
                                  String color=documentSnapshot.getString("f4");
                                  String styl=documentSnapshot.getString("f5");
                                  String clr=documentSnapshot.getString("f6");


                                appp.setText(appointment);
                                mp.setText(map);
                                acc.setText(academy);
                                nc.setText(color);
                                c.setText(styl);
                                cc.setText(clr);
                              }

                              else
                              {
                                  Toast.makeText(context, "document not exist", Toast.LENGTH_SHORT).show();
                              }
                          }
                      })
                      .addOnFailureListener(new OnFailureListener() {
                          @Override
                          public void onFailure(@NonNull Exception e) {

                              Toast.makeText(context, "failed to retrive data", Toast.LENGTH_SHORT).show();

                          }
                      });





        List<SlideModel> slideModels=new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.h1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.h2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.h3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.h4, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.girl1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.h6, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.h7, ScaleTypes.FIT));

        imgslider.setImageList(slideModels,ScaleTypes.FIT);


       return view;
    }
    public void onStart()
    {
        super.onStart();
        appoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog=new ProgressDialog(getActivity());
                progressDialog.setMessage("please wait....");
                progressDialog.setTitle("appointment");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();

//
//                Animation anim = new AlphaAnimation(0.5f, 1.0f);
//                anim.setDuration(1); //You can manage the blinking time with this parameter
//                anim.setStartOffset(200);
//                anim.setRepeatMode(Animation.REVERSE);
//
//                appoint.startAnimation(anim);

                Intent intent=new Intent(context,app.class);
                 startActivity(intent);
                progressDialog.dismiss();
            }
        });
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Tracking.class);
                startActivity(intent);
            }
        });
        acadmey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Acadmey.class);
                startActivity(intent);
            }
        });

    }
}