package com.example.eje_hcgo_07;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    TextView tvNombre3;
    Button btnRegresar3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        tvNombre3 = findViewById(R.id.tvNombre3);
        btnRegresar3 = findViewById(R.id.btnRegresar3);

        String usuario = getIntent().getStringExtra("usuario");
        tvNombre3.setText(usuario);

        btnRegresar3.setOnClickListener(v -> finish());
    }
}
