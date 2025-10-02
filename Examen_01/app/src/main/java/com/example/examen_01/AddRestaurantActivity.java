package com.example.examen_01;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class AddRestaurantActivity extends AppCompatActivity {
    private EditText etNombre, etDireccion, etTelefono, etTipo;
    @Override protected void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_add_restaurant);
        etNombre = findViewById(R.id.etNombre);
        etDireccion = findViewById(R.id.etDireccion);
        etTelefono = findViewById(R.id.etTelefono);
        etTipo = findViewById(R.id.etTipo);
        findViewById(R.id.btnGuardar).setOnClickListener(v -> {
            String n = etNombre.getText().toString().trim();
            if(n.isEmpty()){ etNombre.setError("Requerido"); return; }
            RestaurantRepository.get().add(n, etDireccion.getText().toString().trim(),
                    etTelefono.getText().toString().trim(), etTipo.getText().toString().trim());
            Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show();
            etNombre.setText(""); etDireccion.setText(""); etTelefono.setText(""); etTipo.setText("");
        });
        findViewById(R.id.btnRegresar).setOnClickListener(v -> finish());
    }
}