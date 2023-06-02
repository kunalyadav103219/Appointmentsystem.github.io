package com.example.sharp_salon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    EditText name,email,password,cnfpassword;
    Button  signbtn;
    ProgressDialog progressDialog;

    FirebaseAuth kunal;
    FirebaseUser abhi;

    FirebaseFirestore fstore;

    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        name=findViewById(R.id.signup_name);
        email=findViewById(R.id.signup_emnil);
        password=findViewById(R.id.signup_pass);
        cnfpassword=findViewById(R.id.signupCnf_pass);
        signbtn=findViewById(R.id.signupButton);


        fstore=FirebaseFirestore.getInstance();
        progressDialog =new ProgressDialog(this);
        kunal= FirebaseAuth.getInstance();
        abhi= kunal.getCurrentUser();

        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String naam = name.getText().toString();
                String mail = email.getText().toString();
                String pas = password.getText().toString();
                String cnfpas = cnfpassword.getText().toString();


                if (naam.length() == 0 || mail.length() == 0 || pas.length() == 0 || cnfpas.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "Fill the all detail", Toast.LENGTH_SHORT).show();
                }

                else {
                    if(pas.compareTo(cnfpas)==0) {
                      if (isvalid(pas)) {

                          progressDialog.setMessage("please while registration....");
                          progressDialog.setTitle("Registration");
                          progressDialog.setCanceledOnTouchOutside(false);
                          progressDialog.show();
                          progressDialog.setProgressStyle(R.color.purple_200);



                          kunal.createUserWithEmailAndPassword(mail,pas).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                              @Override
                              public void onComplete(@NonNull Task<AuthResult> task) {

                                  if(task.isSuccessful())
                                  {
                                      id=kunal.getCurrentUser().getUid();


                                      DocumentReference documentReference=fstore.collection("users").document(id);

                                      Map<String,Object>user =new HashMap<>();
                                      user.put("name",naam);
                                      user.put("mail",mail);

                                      documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                          @Override
                                          public void onSuccess(Void unused) {
                                              Log.d(TAG, "onSuccess:user proifile created for  "+id);
                                          }
                                      });

                                      Intent intent=new Intent(RegisterActivity.this,Home.class);


                                      startActivity(intent);
                                      progressDialog.dismiss();
                                  }
                                  else
                                  {
                                      progressDialog.dismiss();
                                      Toast.makeText(RegisterActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                                  }

                              }
                          });
                       } else {
                        Toast.makeText(RegisterActivity.this, "password must contain at least 8 character having letter,digit and special symbol", Toast.LENGTH_SHORT).show();
                       }
                    } else {

                       Toast.makeText(RegisterActivity.this, "password not same", Toast.LENGTH_SHORT).show();

                      }

            }
        }

        });

    }
    public static boolean isvalid(String passss)
    {
      int f1=0,f2=0,f3=0;
      if (passss.length()<8)
      {
          return false;
      }
      else
      {
          for(int p=0;p<passss.length();p++){
              if (Character.isLetter(passss.charAt(p))){
                  f1=1;
              }
          }
          for(int r=0;r<passss.length();r++){
              if (Character.isDigit(passss.charAt(r))){
                  f2=1;
              }
          }
          for(int s=0;s<passss.length();s++){
             char c=passss.charAt(s);
                 if (c>=33&&c<=46||c==64)
                 {
                  f3=1;
              }
          }
         if(f1==1 && f2==1||f3==1)
            return true;
         return false;
      }
    }
 }
