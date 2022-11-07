package com.company.mpsi.iuranwarga;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Register extends AppCompatActivity implements Serializable {

    Button lanjut_button,back;
    EditText nama,email,telp,tempat,bulan,tanggal,tahun,pass;
    FirebaseAuth auth;
    private final static int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_layout);
        nama = findViewById(R.id.regisNamaEditText);
        email = findViewById(R.id.regisEmailEditText);
        telp = findViewById(R.id.regisTelpEditText);
        tempat = findViewById(R.id.regisTempatLahirEditText);
        bulan = findViewById(R.id.regisBulanEditText);
        tanggal = findViewById(R.id.regisTanggalEditText);
        tahun = findViewById(R.id.regisTahunEditText);
        pass = findViewById(R.id.regisPasswordEditText);
        lanjut_button = findViewById(R.id.register_lanjut_button);
        lanjut_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lanjutkan();
            }
        });
        back = findViewById(R.id.register_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBack();
            }
        });

    }

    private void Lanjutkan(){
        String nama = this.nama.getText().toString().trim();
        String email = this.email.getText().toString().trim();
        String telp =  this.telp.getText().toString().trim();
        String tempat = this.tempat.getText().toString().trim();

        int bulan = 0,tanggal = 0,tahun = 0;
        try{
            bulan = Integer.parseInt(this.bulan.getText().toString().trim());
            tanggal = Integer.parseInt(this.tanggal.getText().toString().trim());
            tahun = Integer.parseInt(this.tahun.getText().toString().trim());
        }catch (Exception e){
            Toast.makeText(this,"Parse int failed", Toast.LENGTH_SHORT).show();
            return;
        }
        String pass = this.pass.getText().toString().trim();

        if(TextUtils.isEmpty(nama) || TextUtils.isEmpty(email) || TextUtils.isEmpty(telp) || TextUtils.isEmpty(tempat) || bulan == 0 || tanggal == 0 || tahun == 0
        || TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please fill all the input", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this,RegisterCont.class);
        intent.putExtra("nama",nama);
        intent.putExtra("email",email);
        intent.putExtra("telp",telp);
        intent.putExtra("tempat",tempat);
        intent.putExtra("pass",pass);
        intent.putExtra("tanggal",tanggal);
        intent.putExtra("tahun",tahun);
        intent.putExtra("bulan",bulan);
        startActivity(intent);
    }

    public void GoBack(){
        this.finish();
    }

}
