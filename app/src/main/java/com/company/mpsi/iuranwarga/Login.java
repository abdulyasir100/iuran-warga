package com.company.mpsi.iuranwarga;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
import com.google.firebase.database.core.view.Change;

import individual.Warga;

public class Login extends AppCompatActivity implements IIntentChange{

    EditText nik_editText;
    EditText pass_editText;
    Button masuk_button;
    Button back_button;

    ProgressDialog p_dialog;
    FirebaseAuth f_auth;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        f_auth = FirebaseAuth.getInstance();
        nik_editText = findViewById(R.id.loginnikEditText);
        pass_editText = findViewById(R.id.loginpasswordEditText);
        masuk_button = findViewById(R.id.loginButton_masuk);
        masuk_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });
        p_dialog = new ProgressDialog(this);
        back_button = findViewById(R.id.login_back);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoBack();
            }
        });
    }

    private void Login(){
        String nik = nik_editText.getText().toString().trim();
        String pass = pass_editText.getText().toString().trim();
        if(TextUtils.isEmpty(nik)){
            Toast.makeText(this,"Please Input NIK",Toast.LENGTH_SHORT).show();
            return;
        }
        else if(TextUtils.isEmpty(pass)){
            Toast.makeText(this,"Please Input Password",Toast.LENGTH_SHORT).show();
            return;
        }
        p_dialog.setMessage("Login...");
        p_dialog.show();
        ref = FirebaseDatabase.getInstance().getReference().child("Warga");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Warga warga = checkUser(nik,pass,dataSnapshot);
                if(warga != null){
                    GlobalVariable.active_warga = warga;

                    p_dialog.dismiss();
                    ChangeIntent(Beranda.class);

                }else{
                    p_dialog.dismiss();
                    Toast.makeText(Login.this,"NIK or Password wrong",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private Warga checkUser(String nik, String password, DataSnapshot snapshot){
        Warga warga = new Warga();
        for (DataSnapshot snap : snapshot.getChildren()){
            warga.setNik(snap.getValue(Warga.class).getNik());
            warga.setPassword(snap.getValue(Warga.class).getPassword());
            if(nik.equals(warga.getNik()) && password.equals(warga.getPassword())){
                return snap.getValue(Warga.class);
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

    public void GoBack(){
        this.finish();
    }
}
