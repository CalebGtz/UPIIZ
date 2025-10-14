package com.upiiz.eje_dja_09;

public class Heroe {
    private long id;
    private String nombre;
    private String poder;
    private int anioCreacion;
    private int imagen;

    public Heroe(long id, String nombre, String poder, int anioCreacion, int imagen) {
        this.id = id;
        this.nombre = nombre;
        this.poder = poder;
        this.anioCreacion = anioCreacion;
        this.imagen = imagen;
    }

    public Heroe(int id, String nombre, String poder, String acreacion) {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPoder() {
        return poder;
    }

    public void setPoder(String poder) {
        this.poder = poder;
    }

    public int getAnioCreacion() {
        return anioCreacion;
    }

    public void setAnioCreacion(int anioCreacion) {
        this.anioCreacion = anioCreacion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
