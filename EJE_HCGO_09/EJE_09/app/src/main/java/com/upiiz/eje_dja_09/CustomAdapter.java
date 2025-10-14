package com.upiiz.eje_dja_09;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Heroe> {



    public CustomAdapter(@NonNull Context context, @NonNull List<Heroe> heroes) {
        super(context, 0, heroes);
    }
    // Este metodo es el que muestra cada elemento en el listView
    // Se ejecuta el numero de veces que la cantidad de heroes = 10 veces

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Regrese la vista personalizada
        View  itemPersonalizado = convertView;
        // Aqui creamos el item en tiempo de ejecucion

        if(itemPersonalizado==null){
            itemPersonalizado = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_item,parent,false);
        }
        // Hacemos referencia: A la imagen, nombre, poder, anio
        ImageView ivHeroes = itemPersonalizado.findViewById(R.id.ivHeroes);
        TextView tvNombre = itemPersonalizado.findViewById(R.id.tvNombre);
        TextView tvPoder = itemPersonalizado.findViewById(R.id.tvPoder);
        TextView tvAnio = itemPersonalizado.findViewById(R.id.tvAnio);
        // Obtener el heroe actual
        Heroe heroe = getItem(position);
        // Asignar los datos a las vistas
        ivHeroes.setImageResource(heroe.getImagen());
        tvNombre.setText(heroe.getNombre());
        tvPoder.setText(heroe.getPoder());
        tvAnio.setText(String.valueOf(heroe.getAnioCreacion()));
        return itemPersonalizado;
    }
}
