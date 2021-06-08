package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class MainActivity2 extends AppCompatActivity {
    TextInputEditText txt1, txt2, txt3, txt4, txt5, txt6, txt7, txt8;
    String string1, string2, string3, string4, string5, value, value2, string6, string7, string8,pass,fotp,userotp;
    ChipGroup group;
    Button btn, bt1;
    StringBuilder builder;
    DatabaseReference database;
    private FirebaseAuth mFirebaseAuth;
    int maxid,id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt1 = (TextInputEditText) findViewById(R.id.text1);
        txt2 = (TextInputEditText) findViewById(R.id.text2);
        txt3 = (TextInputEditText) findViewById(R.id.text3);
        txt4 = (TextInputEditText) findViewById(R.id.text4);
        txt5 = (TextInputEditText) findViewById(R.id.text5);
        txt6 = (TextInputEditText) findViewById(R.id.text6);
        txt7 = (TextInputEditText) findViewById(R.id.text7);
        txt8 = (TextInputEditText) findViewById(R.id.text8);
        group = (ChipGroup) findViewById(R.id.grp);
        btn = (Button) findViewById(R.id.button1);
        bt1 = (Button) findViewById(R.id.button2);
        mFirebaseAuth=FirebaseAuth.getInstance();
        txt6.setEnabled(false);
        database = FirebaseDatabase.getInstance().getReference().child("Data").child("event");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                int i=0;
                database=FirebaseDatabase.getInstance().getReference().child("Data").child("event");
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String value=snapshot.child("Event").getValue().toString();
                        String array[] =value.split("  ");
                        for(String event:array){
                            Chip chip = new Chip(MainActivity2.this);
                            chip.setText(event);
                            group.addView(chip);
                            chip.setCheckable(true);
                            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                @Override
                                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                    builder = new StringBuilder("");
                                    for (int j = 0; j < group.getChildCount(); j++) {
                                        Chip chip = (Chip) group.getChildAt(j);
                                        if (chip.isChecked()) {
                                            if (j < group.getChildCount() - 1) {
                                                builder.append(chip.getText()).append(",");
                                            } else {
                                                builder.append(chip.getText());
                                            }
                                        }
                                    }
                                    value2 = builder.toString();
                                    if (value2.contains("ppt")) {
                                        txt6.setEnabled(true);
                                    } else {
                                        txt6.setEnabled(false);
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        database = FirebaseDatabase.getInstance().getReference().child("Data");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                maxid = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                string1=txt1.getText().toString();
                try{
                    string2=txt2.getText().toString();
                }catch(Exception e){

                }
                id = maxid + 1;
                string3=txt3.getText().toString();
                string4=txt4.getText().toString();
                string5=txt5.getText().toString();
                string6=value2;
                string7=txt6.getText().toString();
                string8=txt7.getText().toString();
                if(txt6.isEnabled()){
                    if(TextUtils.isEmpty(string7)){
                        Toast.makeText(MainActivity2.this,"Enter all the details",Toast.LENGTH_LONG).show();
                    }
                }
                if(TextUtils.isEmpty(string1)||TextUtils.isEmpty(string3)||TextUtils.isEmpty(string4)||TextUtils.isEmpty(string5)||TextUtils.isEmpty(string6)||TextUtils.isEmpty(string8)){
                    Toast.makeText(MainActivity2.this,"Enter all the details",Toast.LENGTH_LONG).show();
                }
                else if(!TextUtils.isEmpty(string1)||!TextUtils.isEmpty(string3)||!TextUtils.isEmpty(string4)||!TextUtils.isEmpty(string5)||!TextUtils.isEmpty(string6)||!TextUtils.isEmpty(string8))
                {
                    database = FirebaseDatabase.getInstance().getReference().child("Data");
                    database.orderByChild("phone").equalTo(string4).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                String a = dataSnapshot.getValue().toString();
                                String b = a.substring(25, 35);
                                Toast.makeText(MainActivity2.this, "Number already exists", Toast.LENGTH_LONG).show();
                            }else{
                                startphonenoverification(string4);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userotp=txt8.getText().toString();
                PhoneAuthCredential credential=PhoneAuthProvider.getCredential(fotp,userotp);
                signwithCredential(credential);
            }
        });
    }

    private void startphonenoverification(String string4) {
        PhoneAuthOptions mAuthOptions= PhoneAuthOptions.newBuilder()
                .setPhoneNumber("+91"+string4)
                .setTimeout(60L, TimeUnit.SECONDS)
                .setActivity(this)
                .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        super.onCodeSent(s, forceResendingToken);
                        fotp=s;
                    }

                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        signwithCredential(phoneAuthCredential);
                    }



                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }
                }).build();
        PhoneAuthProvider.verifyPhoneNumber(mAuthOptions);
    }

    private void signwithCredential(PhoneAuthCredential phoneAuthCredential) {
        mFirebaseAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(MainActivity2.this,MainActivity3.class);
                            intent.putExtra("name1",string1);
                            intent.putExtra("name2",string2);
                            intent.putExtra("clgname",string3);
                            intent.putExtra("phone",string4);
                            intent.putExtra("mail id",string5);
                            intent.putExtra("eventname",string6);
                            intent.putExtra("ppttopic",string7);
                            intent.putExtra("password",string8);
                            intent.putExtra("id",String.valueOf(id));
                            startActivity(intent);
                            Toast.makeText(MainActivity2.this,"otp verified",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(MainActivity2.this,"otp failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}