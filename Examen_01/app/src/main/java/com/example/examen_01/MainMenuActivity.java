package com.example.examen_01;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;

public class MainMenuActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnAgregar).setOnClickListener(v -> startActivity(new Intent(this, AddRestaurantActivity.class)));
        findViewById(R.id.btnBuscar).setOnClickListener(v -> startActivity(new Intent(this, SearchRestaurantActivity.class)));
        findViewById(R.id.btnEliminar).setOnClickListener(v -> startActivity(new Intent(this, DeleteRestaurantActivity.class)));
        findViewById(R.id.btnCreditos).setOnClickListener(v -> startActivity(new Intent(this, CreditsActivity.class)));
        findViewById(R.id.btnSalir).setOnClickListener(v -> finish());
    }
}