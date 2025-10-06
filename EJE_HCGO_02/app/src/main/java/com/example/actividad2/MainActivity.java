package com.example.actividad2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewSaludo;
    Button btnCambiarTexto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias a los elementos del layout
        textViewSaludo = findViewById(R.id.textViewSaludo);
        btnCambiarTexto = findViewById(R.id.btnCambiarTexto);

        // Acción al presionar el botón
        btnCambiarTexto.setOnClickListener(v -> {
            textViewSaludo.setText("¡Hola, soy Caleb!");
        });
    }
}
