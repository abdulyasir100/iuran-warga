package com.company.mpsi.iuranwarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

import individual.Iuran;
import individual.Lokasi;
import individual.Warga;

public class RegisterCont extends AppCompatActivity implements IIntentChange{

    EditText nik,nokk,alamat,rt,rw,kelurahan,kecamatan,kota,kode;
    Button regis_button,back;
    Intent valuePass;

    //Firebase
    FirebaseAuth auth;
    DatabaseReference ref;
    DatabaseReference ref2;
    Lokasi exist = null;
    static long counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_cont_layout);
        valuePass = getIntent();
        nik = findViewById(R.id.regis2nikEditText);
        nokk = findViewById(R.id.regis2nokkEditText);
        alamat = findViewById(R.id.regis2AlamatEditText);
        rt = findViewById(R.id.regis2rtEditText);
        rw = findViewById(R.id.regis2rwEditText);
        kelurahan = findViewById(R.id.regis2kelurahanEditText);
        kecamatan = findViewById(R.id.regis2kecamatanEditText);
        kota = findViewById(R.id.regis2kotaEditText);
        kode = findViewById(R.id.regis2kodeEditText);
        auth = FirebaseAuth.getInstance();
        regis_button = findViewById(R.id.regis2_regis_button);
        regis_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Register();
            }
        });
        back = findViewById(R.id.register2_back);

    }

    private void Register(){
        String nik = this.nik.getText().toString().trim();
        String nokk = this.nik.getText().toString().trim();
        String alamat = this.alamat.getText().toString().trim();
        String rt = this.rt.getText().toString().trim();
        String rw = this.rw.getText().toString().trim();
        String kelurahan = this.kelurahan.getText().toString().trim();
        String kecamatan = this.kecamatan.getText().toString().trim();
        String kota = this.kota.getText().toString().trim();
        String kode = this.kode.getText().toString().trim();
        if(TextUtils.isEmpty(nik) || TextUtils.isEmpty(nokk) || TextUtils.isEmpty(alamat) || TextUtils.isEmpty(rt)
        || TextUtils.isEmpty(rw) || TextUtils.isEmpty(kelurahan) || TextUtils.isEmpty(kecamatan) || TextUtils.isEmpty(kota)
        || TextUtils.isEmpty(kode)){
            Toast.makeText(this,"Please fill all the input", Toast.LENGTH_SHORT).show();
            return;
        }
        Date tanggal_lahir;
        int tanggal = valuePass.getIntExtra("tanggal",0);
        int bulan = valuePass.getIntExtra("bulan",0);
        int tahun = valuePass.getIntExtra("tahun",0);
        tanggal_lahir = new Date(tahun,bulan,tanggal);
        String alamat_full = alamat + " RT " + rt + " RW " + rw + " ,"+kelurahan + " ," + kecamatan + " ," + kota;
        String nama = valuePass.getStringExtra("nama");
        String email = valuePass.getStringExtra("email");
        String telp = valuePass.getStringExtra("telp");
        String tempat = valuePass.getStringExtra("tempat");
        String password = valuePass.getStringExtra("pass");
        ref = FirebaseDatabase.getInstance().getReference().child("lokasi");

        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(ExistingCode(kode,dataSnapshot) != null){
                    exist = ExistingCode(kode,dataSnapshot);
                    Warga warga = new Warga(nama,email,password,nik,nokk,telp,tanggal_lahir,tempat,alamat_full,exist);

                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(RegisterCont.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isComplete()){

                                if(task.isSuccessful()){
                                    ref = FirebaseDatabase.getInstance().getReference().child("Warga");
                                    ref.child(task.getResult().getUser().getUid()).setValue(warga);
                                    GiveFirstIuran(warga);
                                }
                                else{

                                }
                            }
                        }
                    });

                }else{
                    Toast.makeText(RegisterCont.this,"Kode tidak ditemukan",Toast.LENGTH_SHORT).show();
                    return;
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    static boolean hasClicked = false;

    private void GiveFirstIuran(Warga warga){
        ref2 = FirebaseDatabase.getInstance().getReference().child("iuran");
        ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                counter = dataSnapshot.getChildrenCount();
                Date date = new Date(2019,5,2);
                Iuran iuranPertama = new Iuran(10000.00, date, warga, false);
                String c = (++counter) + "";
                if(!hasClicked){
                    hasClicked = true;
                    ref2.child(c).setValue(iuranPertama);
                }

                ChangeIntent(Login.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private Lokasi ExistingCode(String kode, DataSnapshot dataSnapshot){
        Lokasi loc = new Lokasi();
        for (DataSnapshot snap : dataSnapshot.getChildren()){
            loc.setKode_lokasi(snap.getValue(Lokasi.class).getKode_lokasi());
            if(loc.getKode_lokasi().equals(kode)){
                loc.setAlamat(snap.getValue(Lokasi.class).getAlamat());
                return loc;
            }
        }
        return null;
    }

    @Override
    public void ChangeIntent(Class targetIntent) {
        Intent intent = new Intent(this,targetIntent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}

