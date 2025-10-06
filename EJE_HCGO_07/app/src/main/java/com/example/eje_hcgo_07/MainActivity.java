package com.example.eje_hcgo_07;


import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_NOMBRE = "EXTRA_NOMBRE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etNombre = findViewById(R.id.etNombre);
        Button btnIr = findViewById(R.id.btnIrSegunda);

        btnIr.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString().trim();
            if (nombre.isEmpty()) {
                Toast.makeText(this, "Ingresa tu nombre", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent i = new Intent(this, SecondActivity.class);
            i.putExtra(EXTRA_NOMBRE, nombre);
            startActivity(i);
        });
    }
}
