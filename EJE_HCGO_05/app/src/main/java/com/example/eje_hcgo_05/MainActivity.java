package com.example.eje_hcgo_05;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eje_hcgo_05.util.Basicas;

public class MainActivity extends AppCompatActivity {

    private EditText etNum1, etNum2;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        tvResultado = findViewById(R.id.tvResultado);

        Button btnSumar = findViewById(R.id.btnSumar);
        Button btnRestar = findViewById(R.id.btnRestar);
        Button btnMultiplicar = findViewById(R.id.btnMultiplicar);
        Button btnDividir = findViewById(R.id.btnDividir);

        btnSumar.setOnClickListener(v -> operar("+"));
        btnRestar.setOnClickListener(v -> operar("-"));
        btnMultiplicar.setOnClickListener(v -> operar("*"));
        btnDividir.setOnClickListener(v -> operar("/"));
    }

    private void operar(String op) {
        // Validar que haya datos
        String s1 = etNum1.getText().toString().trim();
        String s2 = etNum2.getText().toString().trim();

        if (s1.isEmpty() || s2.isEmpty()) {
            Toast.makeText(this, "Ingrese ambos números", Toast.LENGTH_SHORT).show();
            return;
        }

        double a, b;
        try {
            a = Double.parseDouble(s1);
            b = Double.parseDouble(s2);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Datos inválidos: use números", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            double r;
            switch (op) {
                case "+": r = Basicas.sumar(a, b); break;
                case "-": r = Basicas.restar(a, b); break;
                case "*": r = Basicas.multiplicar(a, b); break;
                case "/": r = Basicas.dividir(a, b); break; // lanza si b==0
                default:  r = 0;
            }
            tvResultado.setText(String.format("%.4f", r));
        } catch (IllegalArgumentException ex) {
            // División por cero
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
