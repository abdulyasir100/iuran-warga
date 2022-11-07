package com.company.mpsi.iuranwarga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class KonfirmasiPembayaran extends AppCompatActivity {

    Button confirm;
    DatabaseReference ref;
    TextView iuran;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.konfirmasi_pembayaran_layout);
        View t_layout = findViewById(R.id.konfirmasi_pembayaran_toolbar);
        Toolbar tbar = t_layout.findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        getSupportActionBar().setTitle("Konfirmasi Pembayaran");
        ref = FirebaseDatabase.getInstance().getReference().child("iuran");
        iuran = findViewById(R.id.konfirmasi_pembayaran_uang);
        iuran.setText(GlobalVariable.iuran.getTotalIuran() + "");
        confirm = findViewById(R.id.konfirmasi_pembayaran_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmPembayaran();
            }
        });
    }

    private void ConfirmPembayaran(){
        setContentView(R.layout.konfirmasi_popup);
        GlobalVariable.iuran.setSudahBayar(true);
        ref.child(GlobalVariable.iuran_ref).setValue(GlobalVariable.iuran);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(KonfirmasiPembayaran.this, Beranda.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        }, 1000);
    }

}
