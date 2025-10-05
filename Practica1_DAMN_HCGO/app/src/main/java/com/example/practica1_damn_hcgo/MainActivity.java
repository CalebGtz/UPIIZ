package com.example.practica1_damn_hcgo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.practica1_damn_hcgo.ui.extras.AboutFragment;
import com.example.practica1_damn_hcgo.ui.extras.AccessFragment;
import com.example.practica1_damn_hcgo.ui.noticias.NoticiasFragment;
import com.example.practica1_damn_hcgo.ui.home.HomeFragment;
import com.example.practica1_damn_hcgo.ui.servicios.ServiciosFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottom = findViewById(R.id.bottom_nav);
        bottom.setOnItemSelectedListener(item -> {
            Fragment f;
            int id = item.getItemId();
            if (id == R.id.nav_home) f = new HomeFragment();
            else if (id == R.id.nav_news) f = new NoticiasFragment();
            else if (id == R.id.nav_services) f = new ServiciosFragment();
            else if (id == R.id.nav_about) f = new AboutFragment();
            else f = new AccessFragment();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, f)
                    .commit();
            return true;
        });

        // Tab inicial
        bottom.setSelectedItemId(R.id.nav_home);
    }
}
