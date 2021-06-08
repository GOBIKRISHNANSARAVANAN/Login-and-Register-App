package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MainActivity6 extends AppCompatActivity {
    Data data;
    DatabaseReference db;
    LinearLayout lv;
    Button btn1,btn2,btn3;
    EditText txt1,txt2,txt3;
    int id;
    String events;
    String newevent;
    StringBuilder builder=new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        btn1=(Button)findViewById(R.id.button1);
        btn2=(Button)findViewById(R.id.button2);
        btn3=(Button)findViewById(R.id.button3);
        txt1=(EditText)findViewById(R.id.txt1);
        txt2=(EditText)findViewById(R.id.txt2);

        lv=(LinearLayout)findViewById(R.id.layout);
        data=new Data();

        db= FirebaseDatabase.getInstance().getReference().child("Data").child("event");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot item:dataSnapshot.getChildren()){
                    String item55=item.getValue().toString();
                    final TextView text1=new TextView(MainActivity6.this);
                    int no=0;
                    int no1=no+1;
                    text1.setText(item55);
                    lv.addView(text1);
                    events=text1.getText().toString();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                id=(int)snapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1=txt1.getText().toString();
                db.child("Data").child("event");
                db.child("Event").setValue(events+"  "+str1);

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str2=txt2.getText().toString();
                db.child("Data").child("event");
                db.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String string1=snapshot.child("Event").getValue().toString();
                        int index=0;
                        ArrayList<String> list=new ArrayList<>();
                        String array[]=string1.split("  ");
                        for(int i=0;i<array.length;i++){
                            list.add(array[i]);
                            if(str2.equals(array[i])){
                                list.remove(str2);
                            }
                        }
                        Iterator itr=list.iterator();

                        StringBuilder builder=new StringBuilder();
                        while(itr.hasNext()) {
                            Object element = itr.next();
                            builder.append(element+"  ");
                        }
                        newevent=builder.toString();
                        newevent=builder.toString();
                        HashMap map=new HashMap();
                        map.put("Event",newevent);
                        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                        db.child("Data").child("event").updateChildren(map).addOnSuccessListener(new OnSuccessListener() {
                            @Override
                            public void onSuccess(Object o) {

                            }
                        });
                        Toast.makeText(MainActivity6.this,newevent,Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


            }
        });

    }
}