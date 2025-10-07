package com.example.eje09_hcgo;

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
    Button btnRegresar, btnAgregar;
    //Almacenar los nombres
    ArrayList<String> listadoHeroes = new ArrayList<String>();
    //Desplegar los nombres almacenados
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

        //Agregamos los nombres

        cargarListas();
        lvBasicListView = findViewById(R.id.BasicListView);
        btnRegresar = findViewById(R.id.btnBasicListViewReturn);
        btnAgregar = findViewById(R.id.btnAgregar);
        adaptador = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listadoHeroes
        );
        btnRegresar.setOnClickListener(this);
        btnAgregar.setOnClickListener((this));
        btnAgregar.setOnClickListener(v -> agregarSuperheroe());

        lvBasicListView.setAdapter(adaptador);
        lvBasicListView.setOnItemClickListener(this);
    }

    private void cargarListas() {
        listadoHeroes.add("Spider Man");
        listadoHeroes.add("Batman");
        listadoHeroes.add("Iron Man");
        listadoHeroes.add("Capitan America");
        listadoHeroes.add("Dr Strange");
        listadoHeroes.add("Spider Man");
        listadoHeroes.add("Scarlet Witch");
        listadoHeroes.add("Black Widow");
        listadoHeroes.add("Dr Doom");
        listadoHeroes.add("Professor X");
        listadoHeroes.add("Kitty Pride");
        listadoHeroes.add("Storm");
        listadoHeroes.add("Thanos");
        listadoHeroes.add("She Hulk");
        listadoHeroes.add("Magneto");
        listadoHeroes.add("Wolverine");
        listadoHeroes.add("Black Panther");
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view.getId() == btnRegresar.getId()) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this,"Diste click sobre superheroe: "+listadoHeroes.get(position), Toast.LENGTH_SHORT).show();

    }

    public void agregarSuperheroe(){
        //Fuente de datos
        listadoHeroes.add("SuperHeroe Agregado");
        //Notificamos al adaptador para que se actualice la vista - listado
        adaptador.notifyDataSetChanged();
    }
}