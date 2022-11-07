package com.company.mpsi.iuranwarga;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import individual.Lokasi;
import individual.Warga;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Start extends AppCompatActivity implements IIntentChange{
    Button login_button;
    Button register_button;

    static long counter = 0;
    FirebaseAuth auth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("Warga");
        if(auth.getCurrentUser()!=null){
            String uid = auth.getCurrentUser().getUid();
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Warga warga = getCurrentWarga(dataSnapshot,uid);
                    if(warga != null){
                        GlobalVariable.active_warga = warga;
//                        finish();
//                        ChangeIntent(Beranda.class);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_layout);

        login_button = findViewById(R.id.startButton_masuk);
        register_button = findViewById(R.id.startButton_daftar);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                MakeLocation();
                ChangeIntent(Login.class);
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeIntent(Register.class);
            }
        });
    }

    private Warga getCurrentWarga(DataSnapshot snaphot, String uid){
        for (DataSnapshot snap : snaphot.getChildren()){
            Log.d("test",snap.getKey());
            if(snap.getKey().equals(uid)){
                return snap.getValue(Warga.class);
            }
        }
        return null;
    }
    @Override
    public void ChangeIntent(Class targetIntent) {
        Intent intent = new Intent(this,targetIntent);
        startActivity(intent);
    }
}
