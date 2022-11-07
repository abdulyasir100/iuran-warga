package com.company.mpsi.iuranwarga;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import individual.Warga;

public class ProfileFragment extends Fragment {

    TextView nama, alamat, nama2, ttl, alamat2, nik, nokk;
    Button profil_postingan,profil_tentang;

    public ProfileFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile, container, false);
        nama = v.findViewById(R.id.nama);
        nama2 = v.findViewById(R.id.nama2);
        alamat = v.findViewById(R.id.alamat);
        alamat2 = v.findViewById(R.id.alamat2);
        ttl = v.findViewById(R.id.tempat);
        nik = v.findViewById(R.id.nik);
        nokk = v.findViewById(R.id.kk);
        Log.d("test",GlobalVariable.active_warga.getNama().toString());
        Warga warga = GlobalVariable.active_warga;
        nama.setText(warga.getNama());
        nama2.setText(warga.getNama());
        alamat.setText(warga.getAlamat());
        alamat2.setText(warga.getAlamat());
        ttl.setText(warga.getTempat_lahir() + ", "+warga.getTanggal_lahir()+"-"+warga.getBulan_lahir()+"-"+warga.getTahun_lahir());
        nik.setText(warga.getNik());
        nokk.setText(warga.getNo_kk());

        View view1 = v.findViewById(R.id.profil_recycler);
        View view2 = v.findViewById(R.id.tentang_layout);

        profil_postingan = v.findViewById(R.id.profil_postingan);
        profil_postingan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HideView(view2,view1);
            }
        });

        profil_tentang = v.findViewById(R.id.profil_tentang);
        profil_tentang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HideView(view1,view2);
            }
        });
        return v;
    }

    private void HideView(View hide, View unhide){
        hide.setVisibility(View.GONE);
        unhide.setVisibility(View.VISIBLE);
    }

}
