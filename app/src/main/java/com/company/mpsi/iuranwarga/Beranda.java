package com.company.mpsi.iuranwarga;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Beranda extends AppCompatActivity implements IFragmentChange{

    BottomNavigationView navbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.beranda_layout);
        View t_layout = findViewById(R.id.beranda_toolbar);
        Toolbar tbar = t_layout.findViewById(R.id.toolbar);
        setSupportActionBar(tbar);
        getSupportActionBar().setTitle("Beranda");
        View n_layout = findViewById(R.id.bottom_navbar_included);
        navbar = n_layout.findViewById(R.id.bottom_navigation_view);
        ChangeScreen(new BerandaFragment());
        navbar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.homenav:
                        tbar.setTitle("Beranda");
                        fragment = new BerandaFragment();
                        break;
                    case R.id.searchnav:
                        tbar.setTitle("Pencarian");
                        fragment = new SearchFragment();
                        break;
                    case R.id.notifnav:
                        tbar.setTitle("Notifikasi");
                        fragment = new NotifFragment();
                        break;
                    case R.id.profilnav:
                        tbar.setTitle("Profile");
                        fragment = new ProfileFragment();
                        break;
                    default:
                        break;

                }
                return ChangeScreen(fragment);
            }
        });
    }

    @Override
    public boolean ChangeScreen(Fragment fragment) {
        if(fragment != null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_cont,fragment).commit();
            return true;
        }
        return false;
    }
}
