package com.company.mpsi.iuranwarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.solver.widgets.Snapshot;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

import individual.Iuran;
import individual.Warga;

public class BayarIuran extends AppCompatActivity implements IIntentChange{

    Button bayar,riwayat;
    DatabaseReference ref;
    FirebaseAuth auth;
    TextView lunas, tagihan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bayar_iuran_layout);
        View t_layout = findViewById(R.id.pembayaran_toolbar);
        Toolbar tbar = t_layout.findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        getSupportActionBar().setTitle("Bayar Iuran");
        auth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("iuran");
        Date date = new Date(System.currentTimeMillis());
        bayar = findViewById(R.id.tombolBayar);
        lunas = findViewById(R.id.bayar_iuran_lunas);
        tagihan = findViewById(R.id.bayar_iuran_uang);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iuran iuran = new Iuran();
                iuran = CheckHasPaid(date.getMonth() + 1, GlobalVariable.active_warga,dataSnapshot);
                GlobalVariable.iuran = iuran;
                if(iuran != null){
                    if(iuran.isSudahBayar()){
                        bayar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(BayarIuran.this,"Kamu sudah membayar iuran bulan ini", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else{
                        bayar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ChangeIntent(MetodePembayaran.class);
                            }
                        });
                        lunas.setText("Belum Lunas");
                        tagihan.setText("Rp. "+(iuran.getTotalIuran()) + "");
                    }
                }else{
                    Log.d("iuran","gk masuk");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        riwayat = findViewById(R.id.tombolRiwayat);
        riwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeIntent(RiwayatPembayaran.class);
            }
        });
    }

    private Iuran CheckHasPaid(int month, Warga warga, DataSnapshot snapshot){
        Iuran iuran = new Iuran();
        for (DataSnapshot snap : snapshot.getChildren()){
            iuran.setBulan(snap.getValue(Iuran.class).getBulan());
            iuran.setPembayarIuran(snap.getValue(Iuran.class).getPembayarIuran());
            iuran.setSudahBayar(snap.getValue(Iuran.class).isSudahBayar());
            if(month == iuran.getBulan()){
                Log.d("iuran",warga.toString());
                    if(warga.getNik().equals(iuran.getPembayarIuran().getNik())){
                        GlobalVariable.iuran_ref = snap.getKey();
                        return snap.getValue(Iuran.class);
                    }
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
