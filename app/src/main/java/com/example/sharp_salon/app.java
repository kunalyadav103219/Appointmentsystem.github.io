package com.example.sharp_salon;




import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.arch.core.internal.SafeIterableMap;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;




import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldPath;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.channels.AsynchronousFileChannel;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//implements PaymentResultListener
public class app extends AppCompatActivity  implements PaymentResultListener   {

    CheckBox hairone, hairtwo, hairthree, hairfour, hairfive, hairsix, hairseven, haireight;
    TextView total;
    Button next;

    RadioGroup slot;
    RadioButton slot1, slot2, slot3, slot4, slot5, slot6;

    EditText cname, cphone;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    int amount=0;

    FirebaseFirestore fstore;
  


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Checkout.preload(getApplicationContext());
        slot = findViewById(R.id.slot);
        slot1 = findViewById(R.id.slot1);
        slot2 = findViewById(R.id.slot2);
        slot3 = findViewById(R.id.slot3);
        slot4 = findViewById(R.id.slot4);
        slot5 = findViewById(R.id.slot5);
        slot6 = findViewById(R.id.slot6);


        next = findViewById(R.id.next);
        hairone = findViewById(R.id.hairone);
        hairtwo = findViewById(R.id.hairtwo);
        hairthree = findViewById(R.id.hairthree);
        hairfour = findViewById(R.id.hairfour);
        hairfive = findViewById(R.id.hairfive);
        hairsix = findViewById(R.id.hairsix);
        hairseven = findViewById(R.id.hairtseven);
        haireight = findViewById(R.id.hairteight);

        cname = findViewById(R.id.cname);
        cphone = findViewById(R.id.cphone);
        LocalTime currentTime = null;

            currentTime = LocalTime.now();


            int currentHour = currentTime.getHour();



            int currentMinute = currentTime.getMinute();
        Toast.makeText(this, +currentHour +":"+currentMinute, Toast.LENGTH_SHORT).show();

        FirebaseFirestore fstore;

        fstore=FirebaseFirestore.getInstance();
        DocumentReference documentReference=fstore.collection("users/pIgOVhRFKCRIX4ssT89ekZXTLUC3/home/6y85IIkQKVFrpJ86o7rO/Appointment")
                .document("FePGAc9VUlAyr5La0WZA");

        Map<String, Object> updates = new HashMap<>();
        if (currentHour==0 && currentMinute==0)
        {

            updates.put("t1","10:30AM");
            updates.put("t2","11:00AM");
            updates.put("t3","11:30AM");
            updates.put("t4","12:00PM");
            updates.put("t5","01:00PM");
            updates.put("t6","01:30PM");
            documentReference.set(updates, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                }
            });

        }




        total = findViewById(R.id.total);
        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        fstore =FirebaseFirestore.getInstance();

        DocumentReference documentReferenc=fstore
                .collection("users/pIgOVhRFKCRIX4ssT89ekZXTLUC3/home/6y85IIkQKVFrpJ86o7rO/Appointment")
                .document("FePGAc9VUlAyr5La0WZA");

        documentReferenc.get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if(documentSnapshot.exists())
                        {
//                            String s1=slot1.getText().toString();
//                            String s2=slot2.getText().toString();
//                            String s3=slot3.getText().toString();
//                            String s4=slot4.getText().toString();
//                            String s5=slot5.getText().toString();
//                            String s6=slot6.getText().toString();


                            String s1=documentSnapshot.getString("t1");
                            String s2=documentSnapshot.getString("t2");
                            String s3=documentSnapshot.getString("t3");
                            String s4=documentSnapshot.getString("t4");
                            String s5=documentSnapshot.getString("t5");
                            String s6=documentSnapshot.getString("t6");






                            slot1.setText(s1);
                            slot2.setText(s2);
                            slot3.setText(s3);
                            slot4.setText(s4);
                            slot5.setText(s5);
                            slot6.setText(s6);


                        }

                        else
                        {

                        }
                    }
                });



        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.setTitle("Appointment");
        actionBar.setDisplayHomeAsUpEnabled(true);



        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        // Obtain reference to ActionBar
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.clr));
        // Set BackgroundDrawable.

        String radio = "";
           if (slot1.isChecked()) {

            }
            if (slot2.isClickable()) {
                radio = slot2.getText().toString()+"\n";
           }
           if (slot3.isChecked())
           {
                radio =slot3.getText().toString()+"\n";
            }
            if (slot4.isChecked())
            {
                radio =slot4.getText().toString()+"\n";
            }
            if (slot5.isChecked()) {
                radio =slot5.getText().toString()+"\n";
            }
            if (slot6.isChecked())
            {
                radio =slot6.getText().toString()+"\n";
           }




        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = cname.getText().toString();
                String phone = cphone.getText().toString();
                if (name.length() < 2 || phone.length() == 9) {

                    Toast.makeText(app.this, "write name & phone number", Toast.LENGTH_SHORT).show();

                } else {


                    if (slot1.isChecked() || slot2.isChecked() || slot3.isChecked() || slot4.isChecked() || slot5.isChecked() || slot6.isChecked()) {

                        Makepayment();

                    }
                    else
                    {
                        Toast.makeText(app.this, "chose slot", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });


        hairone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (hairone.isChecked())
                {
                    amount=amount+120;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }
                else
                {
                    amount=amount-120;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }

            }
        });
        hairtwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (hairtwo.isChecked())
                {
                    amount=amount+110;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }
                else
                {
                    amount=amount-110;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }

            }
        });
        hairthree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (hairthree.isChecked())
                {
                    amount=amount+130;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }
                else
                {
                    amount=amount-130;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }
            }
        });
        hairfour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (hairfour.isChecked())
                {
                    amount=amount+120;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }
                else
                {
                    amount=amount-120;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }
            }
        });
        hairfive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (hairfive.isChecked())
                {
                    amount=amount+120;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }
                else
                {
                    amount=amount-120;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }
            }
        });
        hairsix.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (hairsix.isChecked())
                {
                    amount=amount+120;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }
                else
                {
                    amount=amount-120;
                    String kunal=Integer.toString(amount);
                    total.setText(kunal);
                }
            }
        });
         hairseven.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
             {
                 if (hairseven.isChecked())
             {
                 amount=amount+120;
                 String kunal=Integer.toString(amount);
                 total.setText(kunal);
             }
             else
             {
                 amount=amount-120;

                 String kunal=Integer.toString(amount);
                 total.setText(kunal);
             }

             }
         });
         haireight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
             @Override
             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
             {
                 if (haireight.isChecked())
                 {

                     amount=amount+120;
                     String kunal=Integer.toString(amount);
                     total.setText(kunal);
                 }
                 else
                 {
                     amount=amount-120;
                     String kunal=Integer.toString(amount);
                     total.setText(kunal);
                 }
             }
         });




//        else if (currentHour>=11 && currentMinute>=00)
//        {
//            View slotView=slot2;
//            slotView.setVisibility(View.GONE);
//
//        }
//        else if (currentHour>=11 && currentMinute>=30)
//        {
//            View slotView=slot3;
//            slotView.setVisibility(View.GONE);
//        }
//        else if (currentHour>=12 && currentMinute>=00)
//        {
//            View slotView=slot4;
//            slotView.setVisibility(View.GONE);
//
//        }
//        else if (currentHour>=12 && currentMinute>=30)
//        {
//            View slotView=slot5;
//            slotView.setVisibility(View.GONE);
//
//        }
//        else if (currentHour>=13 && currentMinute>=00)
//        {
//            View slotView=slot6;
//            slotView.setVisibility(View.GONE);
//
//        }


    }




    public String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());

    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + "-" + day + "-" + year;
    }

    private String getMonthFormat(int month) {
        if (month == 1)
            return "JAN";
        if (month == 2)
            return "FEB";
        if (month == 3)
            return "MAR";
        if (month == 4)
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6)
            return "JUN";
        if (month == 7)
            return "JUL";
        if (month == 8)
            return "AUG";
        if (month == 9)
            return "SEP";
        if (month == 10)
            return "OCT";
        if (month == 11)
            return "NOV";
        if (month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }


    private void Makepayment() {

        JSONObject object=new JSONObject();
        try {


            // 12 15 to 12 30


            String finalamount=String.valueOf(amount);
            int aamount=Math.round(Float.parseFloat(finalamount)*100);


            object.put("name","kunal");
            object.put("Description","salon");
            object.put("currency","INR");
            object.put("amount",aamount);
            JSONObject prefill=new JSONObject();
            prefill.put("contact","9835145722");
            prefill.put("Email","kunalkumar41456@gmail.com");
            object.put("prefill",prefill);

            Checkout co=new Checkout();
            co.open(app.this,object);

        }
        catch (JSONException e)
        {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onPaymentSuccess(String s)
    {
        Calendar currentTime = Calendar.getInstance();
        int currentHour = currentTime.get(Calendar.HOUR_OF_DAY);
        int currentMinute = currentTime.get(Calendar.MINUTE);
        String radio = "";
        if (slot1.isChecked()) {


            radio =slot1.getText().toString()+"\n";
            slot1.setEnabled(false);

        }

        if (slot2.isChecked())
        {
            radio =slot2.getText().toString()+"\n";
            slot2.setVisibility(View.INVISIBLE);
        }
        if (slot3.isChecked())
        {
            radio =slot3.getText().toString()+"\n";
            slot2.setVisibility(View.INVISIBLE);
        }
        if (slot4.isChecked())

        {
            radio =slot4.getText().toString()+"\n";
            slot4.setVisibility(View.INVISIBLE);
        }


        if (slot5.isChecked())
        {
            radio =slot5.getText().toString()+"\n";
            slot5.setVisibility(View.INVISIBLE);
        }
        if (slot6.isChecked()  )
        {
            radio =slot6.getText().toString();
            slot6.setVisibility(View.INVISIBLE);
        }
        String phone= cphone.getText().toString();
        String namee= cname.getText().toString();
        String ammm= total.getText().toString();


        dataholder obj=new dataholder(phone,radio,ammm);

        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference node=db.getReference("Name");
        node.child(namee).setValue(obj);

        cphone.setText("");
        cname.setText("");
        total.setText("");
        slot.setEnabled(false);
        slot.setBackgroundColor(getResources().getColor(R.color.purple_200));

        FirebaseFirestore fstore;

        fstore=FirebaseFirestore.getInstance();

        DocumentReference documentReference=fstore.collection("users/pIgOVhRFKCRIX4ssT89ekZXTLUC3/home/6y85IIkQKVFrpJ86o7rO/Appointment")
                .document("FePGAc9VUlAyr5La0WZA");

        Map<String, Object> updates = new HashMap<>();
        if (currentHour==3 && currentMinute==11)
        {

            updates.put("t1","10:30AM");
            updates.put("t2","11:00AM");
            updates.put("t3","11:30AM");
            updates.put("t4","12:00PM");
            updates.put("t5","01:00PM");
            updates.put("t6","01:30PM");
            documentReference.set(updates, SetOptions.merge()).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {

                }
            });

        }

        if (slot1.isChecked()) {
    if (currentHour==10 && currentMinute==30) {
        updates.put("t1", "booked");
        
       
        documentReference.set(updates, SetOptions.merge())
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Field successfully updated
//                    slot1.setVisibility(View.GONE);
                        slot1.setEnabled(false);


                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error occurred while updating the field
                    }
                });
        
    }


}

        if (slot2.isChecked()) {
            updates.put("t2", "booked");

            documentReference.set(updates, SetOptions.merge())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Field successfully updated
//                            slot2.setVisibility(View.GONE);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error occurred while updating the field
                        }
                    });
        }

        if (slot3.isChecked()) {
            updates.put("t3", "booked");

            documentReference.set(updates, SetOptions.merge())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Field successfully updated
//                            slot3.setVisibility(View.GONE);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error occurred while updating the field
                        }
                    });
        }
        if (slot4.isChecked()) {
            updates.put("t4", "booked");

            documentReference.set(updates, SetOptions.merge())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Field successfully updated
                            slot1.setEnabled(false);


                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error occurred while updating the field
                        }
                    });
        }

        if (slot5.isChecked()) {
            updates.put("t5", "booked");

            documentReference.set(updates, SetOptions.merge())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Field successfully updated
                            slot5.setVisibility(View.GONE);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error occurred while updating the field
                        }
                    });
        }
        if (slot6.isChecked()) {
            updates.put("t6", "booked");

            documentReference.set(updates, SetOptions.merge())
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Field successfully updated
                                slot6.setVisibility(View.GONE);

                                // Set appropriate styling for a booked slot

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Error occurred while updating the field
                        }
                    });
        }


    }

    @Override
    public void onPaymentError(int i, String s)
    {

    }


}