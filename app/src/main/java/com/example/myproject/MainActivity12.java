package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity12 extends AppCompatActivity {
LinearLayout lv;
Data data;
DatabaseReference db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);
        lv=(LinearLayout) findViewById(R.id.layout);
        data=new Data();
        db= FirebaseDatabase.getInstance().getReference().child("Data");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item:dataSnapshot.getChildren()){
                    String item55=item.getValue().toString();
                    final TextView txt1=new TextView(MainActivity12.this);
                    int no=0;
                    int no1=no+1;
                    txt1.setText("\n"+"Participant Details");
                    final TextView txt=new TextView(MainActivity12.this);
                    txt.setText("\n"+item55);
                    txt.setTextSize(15);
                    lv.addView(txt1);
                    lv.addView(txt);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}