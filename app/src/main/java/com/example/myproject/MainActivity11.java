package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity11 extends AppCompatActivity {
EditText txt1,txt2,txt3;
String str1,str2,str3;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main11);
        txt1=(EditText)findViewById(R.id.p1);
        txt2=(EditText)findViewById(R.id.p2);
        txt3=(EditText)findViewById(R.id.p3);
        btn=(Button)findViewById(R.id.update);
        DatabaseReference db=FirebaseDatabase.getInstance().getReference("Data").child("form");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txt1.setText(snapshot.child("form1").getValue().toString());
                txt2.setText(snapshot.child("form2").getValue().toString());
                txt3.setText(snapshot.child("form3").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                str1=txt1.getText().toString();
                str2=txt2.getText().toString();
                str3=txt3.getText().toString();
                HashMap map=new HashMap();
                map.put("form1",str1);
                DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                db.child("Data").child("form").updateChildren(map).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
                HashMap map1=new HashMap();
                map1.put("form2",str2);
                DatabaseReference db1=FirebaseDatabase.getInstance().getReference();
                db1.child("Data").child("form").updateChildren(map1).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
                HashMap map2=new HashMap();
                map2.put("form3",str3);
                DatabaseReference db2=FirebaseDatabase.getInstance().getReference();
                db2.child("Data").child("form").updateChildren(map2).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
            }
        });


    }
}