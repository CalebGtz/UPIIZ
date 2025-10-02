package com.example.examen_01;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DeleteRestaurantActivity extends AppCompatActivity {

    private EditText etQuery, etNombre, etDireccion, etTelefono, etTipo;
    private Restaurant current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_restaurant);

        etQuery = findViewById(R.id.etQuery);
        etNombre = findViewById(R.id.etNombre);
        etDireccion = findViewById(R.id.etDireccion);
        etTelefono = findViewById(R.id.etTelefono);
        etTipo = findViewById(R.id.etTipo);

        // 🔎 Buscar
        findViewById(R.id.btnBuscar).setOnClickListener(v -> {
            String q = etQuery.getText().toString().trim();
            if (q.isEmpty()) {
                etQuery.setError("Ingresa un nombre");
                return;
            }
            current = RestaurantRepository.get().findByName(q);
            if (current == null) {
                clearFields();
                Toast.makeText(this, "No se encontró el restaurante", Toast.LENGTH_SHORT).show();
            } else {
                fillFields(current);
            }
        });

        // 🗑️ Eliminar
        findViewById(R.id.btnEliminar).setOnClickListener(v -> {
            if (current == null) {
                Toast.makeText(this, "Primero busca un restaurante", Toast.LENGTH_SHORT).show();
                return;
            }
            boolean ok = RestaurantRepository.get().deleteById(current.getId());
            if (ok) {
                Toast.makeText(this, "Eliminado", Toast.LENGTH_SHORT).show();
                clearFields();
                etQuery.setText("");
                current = null;
            } else {
                Toast.makeText(this, "No se pudo eliminar", Toast.LENGTH_SHORT).show();
            }
        });

        // ⬅️ Regresar
        findViewById(R.id.btnRegresar).setOnClickListener(v -> finish());
    }

    private void fillFields(Restaurant r) {
        etNombre.setText(r.getNombre());
        etDireccion.setText(r.getDireccion());
        etTelefono.setText(r.getTelefono());
        etTipo.setText(r.getTipoComida());
    }

    private void clearFields() {
        etNombre.setText("");
        etDireccion.setText("");
        etTelefono.setText("");
        etTipo.setText("");
    }
}
