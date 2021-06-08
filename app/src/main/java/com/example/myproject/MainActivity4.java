package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public class MainActivity4 extends AppCompatActivity {
    EditText txt1,txt2,txt3,txt4,txt5,txt6,txt7,txt8,txt9,txt10;
    Button btn,btn1,btn2,btn3;
    DatabaseReference db,database;
    String id;
    ImageView img;
    String event;
    MainActivity5 mc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        txt1= (EditText) findViewById(R.id.text1);
        txt2=(EditText)findViewById(R.id.text2);
        txt3=(EditText)findViewById(R.id.text3);
        txt4=(EditText)findViewById(R.id.text4);
        txt5=(EditText)findViewById(R.id.text5);
        txt6=(EditText)findViewById(R.id.text6);
        txt7=(EditText)findViewById(R.id.text7);
        txt8=(EditText)findViewById(R.id.text8);
        txt9=(EditText)findViewById(R.id.text9);
        txt10=(EditText)findViewById(R.id.text10);
        btn=(Button)findViewById(R.id.button);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);
        btn1.setEnabled(false);
        btn2.setEnabled(false);
        img=(ImageView)findViewById(R.id.img);
        id=getIntent().getExtras().getString("id");
        db= FirebaseDatabase.getInstance().getReference("Data").child(id);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name1=snapshot.child("name1").getValue().toString();
                String name2=snapshot.child("name2").getValue().toString();
                String clgname=snapshot.child("clgname").getValue().toString();
                String phone=snapshot.child("phone").getValue().toString();
                String eventname=snapshot.child("eventname").getValue().toString();
                String ppt=snapshot.child("ppt").getValue().toString();
                String id=snapshot.child("id").getValue().toString();
                String token1=snapshot.child("token1").getValue().toString();
                String token2=snapshot.child("token2").getValue().toString();
                txt1.setText(name1);
                txt2.setText(name2);
                txt3.setText(clgname);
                txt4.setText(phone);
                txt5.setText(eventname);
                txt6.setText(ppt);
                txt7.setText(id);
                txt8.setText(token1);
                txt9.setText(token2);
                String name11=txt1.getText().toString();
                String name22=txt2.getText().toString();
                String name33="";
                if(name22.equals(name33)){
                    txt9.setText("");
                }
                if(name11.equals(name33)){
                    txt8.setText("");
                }
                if(eventname.contains("code debug")){
                    btn1.setEnabled(true);
                }
                if(eventname.contains("quiz")){
                    btn2.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MultiFormatWriter writer=new MultiFormatWriter();
                try {
                    BitMatrix bm=writer.encode(txt7.getText().toString(), BarcodeFormat.CODE_128,img.getWidth(),img.getHeight());
                    Bitmap m=Bitmap.createBitmap(img.getWidth(),img.getHeight(),Bitmap.Config.RGB_565);
                    for(int i=0;i<img.getWidth();i++){
                        for(int j=0;j<img.getHeight();j++){
                            m.setPixel(i,j,bm.get(i,j)? Color.BLACK:Color.WHITE);
                        }
                    }
                    img.setImageBitmap(m);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity4.this,MainActivity10.class);
                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity4.this,MainActivity9.class);
                startActivity(intent);
            }
        });
        try{
            database= FirebaseDatabase.getInstance().getReference().child("Data").child("Passwords");
            database.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    String pass=snapshot.child("Register1").getValue().toString();
                    if(!pass.isEmpty()){
                        btn3.setEnabled(true);
                        btn.setEnabled(false);
                    }else{
                        btn3.setEnabled(false);
                        btn.setEnabled(true);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }catch (Exception e){

        }
        btn3.setOnClickListener(new View.OnClickListener() {
            String form3;
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance().getReference().child("Data").child("form");
                database.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        form3 = dataSnapshot.child("form3").getValue().toString();
                        txt10.setText(form3);
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}