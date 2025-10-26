package com.example.eje13;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
//Referencias
    Lienzo lienzo;
    Spinner spTipoFigura;

    Spinner spColor;

    TipoFigura figura[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lienzo = findViewById((R.id.dvLienzo));
        spColor = findViewById(R.id.spColor);
        spTipoFigura = findViewById(R.id.spFigura);
        figura = TipoFigura.values();
        ArrayAdapter <TipoFigura> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, figura );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoFigura.setAdapter(adapter);
        ArrayAdapter<CharSequence> colorAdapter = ArrayAdapter.createFromResource(this, R.array.color_names, android.R.layout.simple_spinner_item);
        colorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spColor.setAdapter(colorAdapter);

        spTipoFigura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Establecer la figura diferente
                lienzo.establecerFigura(TipoFigura.values()[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
       spColor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               //Estableer el color diferente
               String[] hex = getResources().getStringArray(R.array.color_hex);
               int selectedColor = android.graphics.Color.parseColor(hex[position]);
               lienzo.establecerColor(selectedColor);
           }

           @Override
           public void onNothingSelected(AdapterView<?> parent) {

           }
       });
    }
}