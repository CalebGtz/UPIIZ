package com.upiiz.eje_dja_09;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import java.util.ArrayList;
import java.util.List;

public class CustomListViewActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    //1. Variables
Button btnreturn, btnadd;
ListView lvCustomlv;
CustomAdapter adapter;
List<Heroe> listheroes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_custom_list_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //2. Referencias
        lvCustomlv = findViewById(R.id.lvCustomListView);
        btnreturn = findViewById(R.id.btnReturn);
        btnadd = findViewById(R.id.btnAgregar);
        listheroes = new ArrayList<>();
        cargarHeroes();

        adapter = new CustomAdapter(this, listheroes);
        lvCustomlv.setAdapter(adapter);
        //3. Acciones
        btnreturn.setOnClickListener(this);
        btnadd.setOnClickListener(this);
        lvCustomlv.setOnItemClickListener(this);

    }

    private void agregarHeroes()  {
        AlertDialog.Builder dialogHeroe = new AlertDialog.Builder(this);
        View vistaDialog = LayoutInflater.from(this).inflate(R.layout.diag_custom_add_heroes, null);
        //Agregamps la vista al Dialog
        dialogHeroe.setView(vistaDialog);

        dialogHeroe
                .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Recoger
                                EditText nombre = vistaDialog.findViewById(R.id.etNombreHeroe);
                                EditText poder = vistaDialog.findViewById(R.id.etPoderHeroe);
                                EditText anioCreacion = vistaDialog.findViewById(R.id.etEAnioHeroe);
                                //Agregamos a la lista
                                listheroes.add(new Heroe(listheroes.size() + 1, nombre.getText().toString(), poder.getText().toString(), Integer.parseInt(anioCreacion.getText().toString()), R.drawable.spooderman));
                                //Notificamos el cambio del adaptador
                                adapter.notifyDataSetChanged();
                            }
                            });

                        dialogHeroe.setNegativeButton("Rechazar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                            });
        dialogHeroe.create().show();
    }


    private void cargarHeroes() {
        listheroes.add(new Heroe(1, "Spider-Man", "Telarañas, agilidad y sentido arácnido", 1962, R.drawable.spooderman));
        listheroes.add(new Heroe(2, "Iron Man", "Armadura de alta tecnología, vuelo y repulsores", 1963, R.drawable.ironman));
        listheroes.add(new Heroe(3, "Capitán América", "Súper soldado, escudo de vibranium", 1941, R.drawable.cptamerica));
        listheroes.add(new Heroe(4, "Doctor Strange", "Hechicería y artefactos místicos", 1963, R.drawable.drstrange));
        listheroes.add(new Heroe(5, "Scarlet Witch", "Alteración de la realidad / probabilidad", 1964, R.drawable.scarletwitch));
        listheroes.add(new Heroe(6, "Black Widow", "Espionaje y combate cuerpo a cuerpo", 1964, R.drawable.blackwidow));
        listheroes.add(new Heroe(7, "Doctor Doom", "Genio, magia y tecnología", 1962, R.drawable.drdoom));
        listheroes.add(new Heroe(8, "Profesor X", "Telepatía y poderes psíquicos", 1963, R.drawable.professorx));
        listheroes.add(new Heroe(9, "Kitty Pryde", "Intangibilidad", 1980, R.drawable.kittyprode));
        listheroes.add(new Heroe(10, "Storm", "Manipulación del clima y vuelo", 1975, R.drawable.storm));





    }

    @Override
    public void onClick(View v) {
        //Para el click de los botones
        Intent intent;
        if (v.getId() == btnreturn.getId()) {
            intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        if(v.getId() == btnadd.getId()){
            agregarHeroes();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        //Click sobre la lista de heroes
        Toast.makeText(this,
                "Diste click sobre Super Heroe: "+listheroes.get(position).getNombre(),
                Toast.LENGTH_SHORT).show();
    }
}