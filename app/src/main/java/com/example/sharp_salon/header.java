package com.example.sharp_salon;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class header  extends AppCompatActivity {
    TextView name,gmail;
    FirebaseFirestore fstore;

    FirebaseAuth fauth;

    String id;

//    ProgressBar progressBar;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.header);
        getSupportActionBar().hide();

        name=findViewById(R.id.drawerUname);
        gmail=findViewById(R.id.drawerEmail);
//        progressBar=findViewById(R.id.progress);
//        progressBar.setProgress(View.VISIBLE);

        fstore=FirebaseFirestore.getInstance();
        id=FirebaseAuth.getInstance().getUid();

        DocumentReference documentReference=fstore.collection("users").document(id);

      documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
          @Override
          public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
              if (documentSnapshot.exists())
              {
                  String email=documentSnapshot.getString("mail");
                  String uname=documentSnapshot.getString("name");


                  name.setText(uname);
                  gmail.setText(email);



              }
              else
              {
                  Toast.makeText(header.this, "document not exist ", Toast.LENGTH_SHORT).show();
              }

          }
      });






    }
}