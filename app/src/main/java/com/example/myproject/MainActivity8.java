package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;

public class MainActivity8 extends AppCompatActivity implements View.OnClickListener{
    TextView txt;
    Button btn;
    EditText txt1,txt2;
    ImageButton ibb1,ibb2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main8);
        btn=(Button)findViewById(R.id.button1);
        txt=(TextView)findViewById(R.id.textView);
        btn.setOnClickListener(this);
        ibb1=(ImageButton)findViewById(R.id.id1);
        ibb2=(ImageButton)findViewById(R.id.ib2);
        txt1=(EditText)findViewById(R.id.et1);
        txt2=(EditText)findViewById(R.id.et2);
        ibb1.setEnabled(false);
        ibb2.setEnabled(false);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        scancode();
    }

    private void scancode() {
        IntentIntegrator intentIntegrator=new IntentIntegrator(this);
        intentIntegrator.setCaptureActivity(captureactivity.class);
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Scanning Code");
        intentIntegrator.initiateScan();
    }
    protected void onActivityResult(int requestCode, int reslutCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, reslutCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                scancode();
                txt.setText(result.getContents());
            }
        } else {
            super.onActivityResult(requestCode, reslutCode, data);
        }
        String no = txt.getText().toString();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference().child("Data").child(no);
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String name11 = dataSnapshot.child("name1").getValue().toString();
                String name22 = dataSnapshot.child("name2").getValue().toString();
                txt1.setText(name11);
                txt2.setText(name22);
                String name77 = "";
                String name55 = txt1.getText().toString();
                String name66 = txt2.getText().toString();
                if (name55.equals(name77)) {

                } else {
                    ibb1.setEnabled(true);
                }
                if (name66.equals(name77)) {
                } else {
                    ibb2.setEnabled(true);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ibb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String token1 = "expired";
                String valid = txt1.getText().toString();
                HashMap hashMap = new HashMap();
                hashMap.put("token1", valid);
                String no1 = txt.getText().toString();
                DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                db.child("Data").child(no1).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(MainActivity8.this, "Expired", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        ibb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String token2 = "expired";
                String valid = txt2.getText().toString();
                HashMap hashMap = new HashMap();
                hashMap.put("token2", valid);
                String no1 = txt.getText().toString();
                DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                db.child("Data").child(no1).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
                    @Override
                    public void onSuccess(Object o) {
                        Toast.makeText(MainActivity8.this, "Expired", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }
}