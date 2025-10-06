package com.example.eje_hcgo_04;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvContador;
    Button btnSumar, btnRestar;
    private int contador = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvContador = findViewById(R.id.tvContador);
        btnSumar = findViewById(R.id.btnSumar);
        btnRestar = findViewById(R.id.btnRestar);

        // Botón para sumar
        btnSumar.setOnClickListener(v -> {
            contador++;
            tvContador.setText(String.valueOf(contador));
        });

        // Botón para restar
        btnRestar.setOnClickListener(v -> {
            contador--;
            tvContador.setText(String.valueOf(contador));
        });
    }
}
