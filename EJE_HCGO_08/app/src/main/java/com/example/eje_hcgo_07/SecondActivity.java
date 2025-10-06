package com.example.eje_hcgo_07;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView tvNombre;
    Button btnRegresar, btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tvNombre = findViewById(R.id.tvNombre);
        btnRegresar = findViewById(R.id.btnRegresar);
        btnContinuar = findViewById(R.id.btnContinuar);

        String usuario = getIntent().getStringExtra("usuario");
        tvNombre.setText(usuario);

        btnRegresar.setOnClickListener(v -> finish());

        btnContinuar.setOnClickListener(v -> {
            Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
            intent.putExtra("usuario", usuario);
            startActivity(intent);
        });
    }
}
