package com.company.mpsi.iuranwarga;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class BerandaFragment extends Fragment implements IIntentChange {

    Button chat,bayar,lapor,status;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beranda, null);
        chat = view.findViewById(R.id.brndnav_hubungi);
        bayar = view.findViewById(R.id.brndnav_bayar);
        lapor = view.findViewById(R.id.brndnav_lapor);
        status = view.findViewById(R.id.brndnav_post);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeIntent(Chat.class);
            }
        });
        bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeIntent(BayarIuran.class);
            }
        });
        lapor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeIntent(Lapor.class);
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChangeIntent(Post.class);
            }
        });
        return view;
    }

    @Override
    public void ChangeIntent(Class targetIntent) {
        Context cont = getActivity().getApplicationContext();
        Intent intent = new Intent(cont,targetIntent);
        startActivity(intent);
    }
}
