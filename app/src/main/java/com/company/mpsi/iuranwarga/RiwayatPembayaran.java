package com.company.mpsi.iuranwarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.ArrayList;

import individual.Iuran;

public class RiwayatPembayaran extends AppCompatActivity {

    RecyclerView rview;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.riwayat_pembayaran_layout);
        View t_layout = findViewById(R.id.riwayat_pembayaran_toolbar);
        Toolbar tbar = t_layout.findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        getSupportActionBar().setTitle("Riwayat Pembayaran");
        rview = findViewById(R.id.recycler_riwayat);
        rview.setHasFixedSize(true);
        LinearLayoutManager lmanager = new LinearLayoutManager(this);
        rview.setLayoutManager(lmanager);
        List<Iuran> list_iuran = new ArrayList<>();
        RiwayatAdapter adapter = new RiwayatAdapter(list_iuran);
        rview.setAdapter(adapter);
        ref = FirebaseDatabase.getInstance().getReference().child("iuran");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snap : dataSnapshot.getChildren()){
                    Iuran iur = snap.getValue(Iuran.class);
                    if(iur.getPembayarIuran().getNik().equals(GlobalVariable.active_warga.getNik())){
                        if(iur.isSudahBayar()){
                            list_iuran.add(iur);
                        }
                    }
                }
                rview.setAdapter(new RiwayatAdapter(list_iuran));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

    }

}
