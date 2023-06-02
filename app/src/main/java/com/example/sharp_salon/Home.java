package com.example.sharp_salon;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

public class Home extends AppCompatActivity {
    EditText uname,pass;
    Button btn;
    TextView newuser;
    ProgressDialog progressDialog;

    FirebaseAuth kunal;
    FirebaseUser abhi;

    CircleImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        getSupportActionBar().hide();
        uname=findViewById(R.id.loginPageUsernsme);
        pass=findViewById(R.id.loginPagePassword);
        btn=findViewById(R.id.loginButton);
        newuser=findViewById(R.id.tv_Newuser);

        img=findViewById(R.id.photo);

        progressDialog =new ProgressDialog(this);
        kunal= FirebaseAuth.getInstance();
        abhi= kunal.getCurrentUser();
//
//        Bundle bundle=getIntent().getExtras();
//        String uuname= bundle.getString("uname");
//        String mmail= bundle.getString("Email");

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.animation);
        btn.startAnimation(animation);
        uname.startAnimation(animation);
        pass.startAnimation(animation);
        img.startAnimation(animation);



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String unamee=uname.getText().toString();
                 String password= pass.getText().toString();





                if(unamee.length()==0||password.length()==0)
                {
                    Toast.makeText(Home.this, "Fill the all detail", Toast.LENGTH_SHORT).show();
                }
                else
                {





                    progressDialog.setMessage("please wait login....");
                    progressDialog.setTitle("login");
                    progressDialog.setCanceledOnTouchOutside(false);
                    progressDialog.show();
                    kunal.signInWithEmailAndPassword(unamee,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(Home.this, "Login successful", Toast.LENGTH_SHORT).show();
                                SharedPreferences pref=getSharedPreferences("login",MODE_PRIVATE);
                                SharedPreferences.Editor bditor=pref.edit();
                                bditor.putBoolean("flag",true);
                                 bditor.apply();

                                progressDialog.dismiss();
                                Toast.makeText(Home.this, "Login successfull", Toast.LENGTH_SHORT).show();
                                Intent intent=new Intent(Home.this,homm.class);
//                                intent.putExtra("buname",uuname);
//                                intent.putExtra("bmail",mmail);
                                startActivity(intent);
                                finish();

                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(Home.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                            }


                        }
                    });
                    }


                }

        });
        newuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Home.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}