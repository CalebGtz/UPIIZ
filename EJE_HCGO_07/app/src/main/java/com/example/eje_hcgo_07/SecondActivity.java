package com.example.eje_hcgo_07;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView tvNombre = findViewById(R.id.tvNombre);
        Button btnVolverMain = findViewById(R.id.btnVolverMain);
        Button btnIrTercera = findViewById(R.id.btnIrTercera);

        nombre = getIntent().getStringExtra(MainActivity.EXTRA_NOMBRE);
        if (nombre == null) nombre = "";
        tvNombre.setText(nombre);

        btnVolverMain.setOnClickListener(v -> finish());  // vuelve a Main

        btnIrTercera.setOnClickListener(v -> {
            Intent i = new Intent(this, ThirdActivity.class);
            i.putExtra(MainActivity.EXTRA_NOMBRE, nombre);
            startActivity(i);
        });
    }
}
