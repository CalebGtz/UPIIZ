package com.example.eje_hcgo_03;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etMetros, etPies;
    Button btnConvertirPies, btnConvertirMetros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etMetros = findViewById(R.id.etMetros);
        etPies = findViewById(R.id.etPies);
        btnConvertirPies = findViewById(R.id.btnConvertirPies);
        btnConvertirMetros = findViewById(R.id.btnConvertirMetros);

        // Convertir de Metros a Pies
        btnConvertirPies.setOnClickListener(v -> {
            try {
                double metros = Double.parseDouble(etMetros.getText().toString());
                double pies = metros * 3.28084;
                etPies.setText(String.format("%.2f", pies));
            } catch (Exception e) {
                Toast.makeText(this, "Ingrese un valor válido en metros", Toast.LENGTH_SHORT).show();
            }
        });

        // Convertir de Pies a Metros
        btnConvertirMetros.setOnClickListener(v -> {
            try {
                double pies = Double.parseDouble(etPies.getText().toString());
                double metros = pies / 3.28084;
                etMetros.setText(String.format("%.2f", metros));
            } catch (Exception e) {
                Toast.makeText(this, "Ingrese un valor válido en pies", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
