package com.example.examen_01;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SearchRestaurantActivity extends AppCompatActivity {

    private EditText etQuery, etNombre, etDireccion, etTelefono, etTipo;
    private TextView tvId;
    private Restaurant current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_restaurant);

        etQuery = findViewById(R.id.etQuery);
        etNombre = findViewById(R.id.etNombre);
        etDireccion = findViewById(R.id.etDireccion);
        etTelefono = findViewById(R.id.etTelefono);
        etTipo = findViewById(R.id.etTipo);
        tvId = findViewById(R.id.tvId);


        findViewById(R.id.btnBuscar).setOnClickListener(v -> {
            String q = etQuery.getText().toString().trim();
            current = RestaurantRepository.get().findByName(q);
            if (current == null) {
                Toast.makeText(this, "No encontrado", Toast.LENGTH_SHORT).show();
                clearFields();
            } else {
                fillFields(current);
            }
        });


        findViewById(R.id.btnActualizar).setOnClickListener(v -> {
            if (current == null) {
                Toast.makeText(this, "Primero busca un restaurante", Toast.LENGTH_SHORT).show();
                return;
            }
            current.setNombre(etNombre.getText().toString().trim());
            current.setDireccion(etDireccion.getText().toString().trim());
            current.setTelefono(etTelefono.getText().toString().trim());
            current.setTipoComida(etTipo.getText().toString().trim());
            boolean ok = RestaurantRepository.get().update(current);
            Toast.makeText(this, ok ? "Actualizado" : "Error al actualizar", Toast.LENGTH_SHORT).show();
        });


        findViewById(R.id.btnRegresar).setOnClickListener(v -> finish());
    }

    private void fillFields(Restaurant r) {
        tvId.setText("ID: " + r.getId());
        etNombre.setText(r.getNombre());
        etDireccion.setText(r.getDireccion());
        etTelefono.setText(r.getTelefono());
        etTipo.setText(r.getTipoComida());
    }

    private void clearFields() {
        tvId.setText("");
        etNombre.setText("");
        etDireccion.setText("");
        etTelefono.setText("");
        etTipo.setText("");
    }
}
