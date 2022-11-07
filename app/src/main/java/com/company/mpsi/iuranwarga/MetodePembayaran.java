package com.company.mpsi.iuranwarga;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MetodePembayaran extends AppCompatActivity implements IIntentChange{

    View tombolTransfer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.metode_pembayaran_layout);
        View t_layout = findViewById(R.id.metode_pembayaran_toolbar);
        Toolbar tbar = t_layout.findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        getSupportActionBar().setTitle("Pilih Metode Pembayaran");
        tombolTransfer = findViewById(R.id.trasferBankButton);
        tombolTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeIntent(KonfirmasiPembayaran.class);
            }
        });
    }

    @Override
    public void ChangeIntent(Class targetIntent) {
        Intent intent = new Intent(this,targetIntent);
        startActivity(intent);
    }
}
