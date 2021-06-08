package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity3 extends AppCompatActivity {
    MainActivity2 m=new MainActivity2();
    String string11,string12,string13,string14,string15,string16,string17,string18,string19,string20,string21;
    int maxid;
    DatabaseReference database;
    Button btn;
    TextInputEditText txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn=(Button)findViewById(R.id.button1);
        txt=(TextInputEditText)findViewById(R.id.text1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    database= FirebaseDatabase.getInstance().getReference().child("Data").child("Passwords");
                    database.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            String pass=snapshot.child("Register").getValue().toString();
                            String pass1=txt.getText().toString();
                            if(pass.equals(pass1)){
                                database=FirebaseDatabase.getInstance().getReference().child("Data");
                                string11=getIntent().getExtras().getString("name1");
                                string12=getIntent().getExtras().getString("name2");
                                string13=getIntent().getExtras().getString("clgname");
                                string14=getIntent().getExtras().getString("phone");
                                string15=getIntent().getExtras().getString("mail id");
                                string16=getIntent().getExtras().getString("eventname");
                                string17=getIntent().getExtras().getString("ppttopic");
                                string18=getIntent().getExtras().getString("password");
                                string19=getIntent().getExtras().getString("id");
                                Data data=new Data();
                                data.setName1(string11);
                                data.setName2(string12);
                                data.setClgname(string13);
                                data.setPhone(string14);
                                data.setMail(string15);
                                data.setEventname(string16);
                                data.setPpt(string17);
                                data.setPassword(string18);
                                data.setId(string19);
                                String token1="use";
                                String token2="use";
                                data.setToken1(token1);
                                data.setToken2(token2);
                                int id=Integer.parseInt(string19)+1;
                                database.child(String.valueOf(id)).setValue(data);
                               Toast.makeText(MainActivity3.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(MainActivity3.this,"enter the password",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }catch (Exception e){
                    Toast.makeText(MainActivity3.this,"enter the password",Toast.LENGTH_LONG).show();
                }
            }
        });
        try{
            database= FirebaseDatabase.getInstance().getReference().child("Data").child("Passwords");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String pass=snapshot.child("Register1").getValue().toString();
                    if(!pass.isEmpty()){
                        database=FirebaseDatabase.getInstance().getReference().child("Data");
                        string11=getIntent().getExtras().getString("name1");
                        string12=getIntent().getExtras().getString("name2");
                        string13=getIntent().getExtras().getString("clgname");
                        string14=getIntent().getExtras().getString("phone");
                        string15=getIntent().getExtras().getString("mail id");
                        string16=getIntent().getExtras().getString("eventname");
                        string17=getIntent().getExtras().getString("ppttopic");
                        string18=getIntent().getExtras().getString("password");
                        string19=getIntent().getExtras().getString("id");
                        Data data=new Data();
                        data.setName1(string11);
                        data.setName2(string12);
                        data.setClgname(string13);
                        data.setPhone(string14);
                        data.setMail(string15);
                        data.setEventname(string16);
                        data.setPpt(string17);
                        data.setPassword(string18);
                        data.setId(string19);
                        String token1="use";
                        String token2="use";
                        data.setToken1(token1);
                        data.setToken2(token2);
                        int id=Integer.parseInt(string19)+1;
                        database.child(String.valueOf(id)).setValue(data);
                        Toast.makeText(MainActivity3.this,"Registered Successfully",Toast.LENGTH_LONG).show();
                        txt.setEnabled(false);
                        btn.setEnabled(false);
                    }else{
                        Toast.makeText(MainActivity3.this,"enter the password",Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){
            Toast.makeText(MainActivity3.this,"enter the password",Toast.LENGTH_LONG).show();
        }
    }
}