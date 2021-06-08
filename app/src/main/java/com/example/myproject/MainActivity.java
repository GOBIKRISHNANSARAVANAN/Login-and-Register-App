package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    TextInputEditText txt1,txt2;
    TextView txt3;
    Button btn1,btn2;
    String str1,str2;
    DatabaseReference db;
    String pass2,pass1,e,b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1=(TextInputEditText)findViewById(R.id.text1);
        txt2=(TextInputEditText)findViewById(R.id.text2);
        txt3=(TextView)findViewById(R.id.txt);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1=txt1.getText().toString();
                str2=txt2.getText().toString();
                if(TextUtils.isEmpty(str1)||TextUtils.isEmpty(str2)){
                    Toast.makeText(MainActivity.this,"Enter Login details",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this,"Login details are entered",Toast.LENGTH_LONG).show();
                    if(str1.equals("Food")){
                        db= FirebaseDatabase.getInstance().getReference().child("Data").child("Passwords");
                        db.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                pass2=snapshot.child("Food").getValue().toString();
                                if(str1.equals("Food")){
                                    if(str2.equals(pass2)){
                                        Intent intent=new Intent(MainActivity.this,MainActivity8.class);
                                        startActivity(intent);
                                    }else{
                                        Toast.makeText(MainActivity.this,"Enter the correct password",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }
                    else if(str1.equals("Admin")){

                        db=FirebaseDatabase.getInstance().getReference().child("Data").child("Passwords");
                        db.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                pass1=snapshot.child("Admin").getValue().toString();
                                if(str1.equals("Admin")){
                                    if(str2.equals(pass1)){
                                        Intent intent1=new Intent(MainActivity.this,MainActivity5.class);
                                        startActivity(intent1);
                                        Toast.makeText(MainActivity.this,"Admin Login",Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(MainActivity.this,"Enter correct password",Toast.LENGTH_LONG).show();
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    }else{
                        db = FirebaseDatabase.getInstance().getReference().child("Data");
                        db.orderByChild("phone").equalTo(str1).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String a = dataSnapshot.getValue().toString();
                                    b = a.substring(25, 35);
                                    db=FirebaseDatabase.getInstance().getReference().child("Data");
                                    Query q=db.orderByChild("phone").equalTo(str1);
                                    q.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String item=snapshot.getValue().toString();
                                            String a=item.substring(0,3);
                                            b=a.replace("{","");
                                            String c=b.replace("=","");
                                            txt3.setText(c);
                                            e=txt3.getText().toString();
                                            db=FirebaseDatabase.getInstance().getReference().child("Data").child(String.valueOf(e));
                                            db.addValueEventListener(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                    String phone1=snapshot.child("phone").getValue().toString();
                                                    String pass1=snapshot.child("password").getValue().toString();
                                                    if(str1.equals(phone1)&&str2.equals(pass1)){
                                                            Intent intent=new Intent(MainActivity.this,MainActivity4.class);
                                                            intent.putExtra("id",e);
                                                            Toast.makeText(MainActivity.this,"Login Successfull",Toast.LENGTH_LONG).show();
                                                            startActivity(intent);
                                                            finish();
                                                    }else{
                                                        Toast.makeText(MainActivity.this,"Enter correct Login details",Toast.LENGTH_LONG).show();
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
                                }else{
                                    Toast.makeText(MainActivity.this,"Number not found",Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}