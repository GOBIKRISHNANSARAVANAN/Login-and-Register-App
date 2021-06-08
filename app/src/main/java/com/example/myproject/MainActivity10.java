package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity10 extends AppCompatActivity {
DatabaseReference database;
String form1;
WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main10);
        web=(WebView)findViewById(R.id.web);
        database = FirebaseDatabase.getInstance().getReference().child("Data").child("form");
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                form1=dataSnapshot.child("form1").getValue().toString();
                WebSettings webSettings=web.getSettings();
                webSettings.setJavaScriptEnabled(true);
                web.loadUrl(form1);
                web.setWebViewClient(new WebViewClient());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
    public void onBackPressed() {
        if(web.canGoBack()){
            web.goBack();
        }else{
            Intent intent=new Intent(MainActivity10.this,MainActivity4.class);
            startActivity(intent);
        }
        super.onBackPressed();
    }
}