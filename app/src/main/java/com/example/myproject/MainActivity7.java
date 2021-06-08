package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class MainActivity7 extends AppCompatActivity {
EditText txt1,txt2,txt3,txt4;
Button btn;
    String str1,str2,str3,str4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);
        txt1=(EditText)findViewById(R.id.p1);
        txt2=(EditText)findViewById(R.id.p2);
        txt3=(EditText)findViewById(R.id.p3);
        txt4=(EditText)findViewById(R.id.p4);
        btn=(Button)findViewById(R.id.update);
        DatabaseReference db=FirebaseDatabase.getInstance().getReference("Data").child("Passwords");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                txt1.setText(snapshot.child("Admin").getValue().toString());
                txt2.setText(snapshot.child("Food").getValue().toString());
                txt3.setText(snapshot.child("Register").getValue().toString());
                txt4.setText(snapshot.child("Register1").getValue().toString());
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
                str4=txt4.getText().toString();
                HashMap map=new HashMap();
                map.put("Admin",str1);
                DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                db.child("Data").child("Passwords").updateChildren(map).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
                HashMap map1=new HashMap();
                map1.put("Food",str2);

                db.child("Data").child("Passwords").updateChildren(map1).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                    }
                });
                HashMap map2=new HashMap();
                map2.put("Register",str3);

                db.child("Data").child("Passwords").updateChildren(map2).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                    }
                });
                HashMap map3=new HashMap();
                map3.put("Register1",str4);

                db.child("Data").child("Passwords").updateChildren(map3).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {

                    }
                });
            }
        });





    }
}