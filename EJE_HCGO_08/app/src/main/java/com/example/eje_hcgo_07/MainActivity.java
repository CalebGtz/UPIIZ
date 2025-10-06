package com.example.eje_hcgo_07;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etUsuario, etClave;
    Button btnIngresar, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsuario = findViewById(R.id.etUsuario);
        etClave = findViewById(R.id.etClave);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnSalir = findViewById(R.id.btnSalir);

        btnIngresar.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString().trim();
            String clave = etClave.getText().toString().trim();

            if (usuario.isEmpty() || clave.isEmpty()) {
                Toast.makeText(this, "Por favor ingrese usuario y clave", Toast.LENGTH_SHORT).show();
            } else if (usuario.equals("Caleb") && clave.equals("1234")) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
            }
        });

        btnSalir.setOnClickListener(v -> finish());
    }
}
