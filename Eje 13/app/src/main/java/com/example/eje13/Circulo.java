package com.example.eje13;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circulo implements Figura {

    private float x;
    private float y;
    private float radio;
    private int color;

    public Circulo(float x, float y, float radio, int color){
        this.x = x;
        this.y = y;
        this.radio = radio;
        this.color = color;
    }

    public float getRadio() {
        return radio;
    }

    public float getX() {
        return x;
    }

    public void setRadio(float radio) {
        this.radio = radio;
    }

    public float getY() {
        return y;
    }

    public int getColor() {
        return color;
    }

    @Override
    public void dibujar(Canvas canvas, Paint plumon) {
        plumon.setColor(color);
        canvas.drawCircle(x, y, radio, plumon);

    }
}
