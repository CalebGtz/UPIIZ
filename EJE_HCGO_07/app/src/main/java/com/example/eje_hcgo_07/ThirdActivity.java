package com.example.eje_hcgo_07;


import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        TextView tvNombre = findViewById(R.id.tvNombre3);
        Button btnVolver = findViewById(R.id.btnVolverSegunda);

        String nombre = getIntent().getStringExtra(MainActivity.EXTRA_NOMBRE);
        if (nombre == null) nombre = "";
        tvNombre.setText(nombre);

        btnVolver.setOnClickListener(v -> finish()); // cierra y regresa a SecondActivity
    }
}
