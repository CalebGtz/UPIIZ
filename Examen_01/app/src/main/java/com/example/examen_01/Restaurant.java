package com.example.examen_01;

import android.os.Parcel;
import android.os.Parcelable;

public class Restaurant implements Parcelable {
    private int id;
    private String nombre;
    private String direccion;
    private String telefono;
    private String tipoComida;

    public Restaurant(int id, String nombre, String direccion, String telefono, String tipoComida) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.tipoComida = tipoComida;
    }

    // --- Constructor desde Parcel ---
    protected Restaurant(Parcel in) {
        id = in.readInt();
        nombre = in.readString();
        direccion = in.readString();
        telefono = in.readString();
        tipoComida = in.readString();
    }

    // --- CREATOR necesario para Parcelable ---
    public static final Creator<Restaurant> CREATOR = new Creator<Restaurant>() {
        @Override
        public Restaurant createFromParcel(Parcel in) {
            return new Restaurant(in);
        }

        @Override
        public Restaurant[] newArray(int size) {
            return new Restaurant[size];
        }
    };

    // --- Getters y Setters ---
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getTipoComida() { return tipoComida; }
    public void setTipoComida(String tipoComida) { this.tipoComida = tipoComida; }

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + tipoComida + ")";
    }

    // --- Métodos de Parcelable ---
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeInt(id);
        parcel.writeString(nombre);
        parcel.writeString(direccion);
        parcel.writeString(telefono);
        parcel.writeString(tipoComida);
    }
}
