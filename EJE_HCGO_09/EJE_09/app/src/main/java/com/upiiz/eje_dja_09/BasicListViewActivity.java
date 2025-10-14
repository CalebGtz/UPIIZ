package com.upiiz.eje_dja_09;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class BasicListViewActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    ListView lvBasicListView;
    Button btnRegresar,btnAgregar;
    //Almacenar los nombres
    ArrayList<String> listadoHeroes = new ArrayList<>();
    // Desplegar los nombres en el ListView
    ArrayAdapter<String> adaptador;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_basic_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //Asignamos el adaptador al ListView


        cargarListado();
        lvBasicListView = findViewById(R.id.BasicListView);
        btnRegresar = findViewById(R.id.btnBasicListViewReturn);
        btnAgregar = findViewById(R.id.btnAgregar);
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listadoHeroes);
        btnRegresar.setOnClickListener(this);
        btnAgregar.setOnClickListener(this);
        lvBasicListView.setAdapter(adaptador);
        lvBasicListView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == btnRegresar.getId()) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        if(v.getId() == btnAgregar.getId()){
            agregarSuperHeroe();
        }

    }
    private void cargarListado() {
        //Agregamos los nombres
        listadoHeroes.add("Spider Man");
        listadoHeroes.add("Batman");
        listadoHeroes.add("Iron Man");
        listadoHeroes.add("Capitán America");
        listadoHeroes.add("Doctor Strange");
        listadoHeroes.add("Scarlet Witch");
        listadoHeroes.add("Black Widow");
        listadoHeroes.add("Doctor Doom");
        listadoHeroes.add("Profesor X");
        listadoHeroes.add("Kitty Pryde");
        listadoHeroes.add("Storm");
        listadoHeroes.add("Thanos");
        listadoHeroes.add("She-Hulk");
        listadoHeroes.add("Magneto");
        listadoHeroes.add("Wolverine");
        listadoHeroes.add("Black Panter");
    }
    public void agregarSuperHeroe(){
        //Fuente de datos
        listadoHeroes.add("Superheroe Agregado");
        //Notificamos al adaptador para que actualice la vista
        adaptador.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,
        "Diste click sobre Super Heroe: "+listadoHeroes.get(position),
        Toast.LENGTH_SHORT).show();
    }
}