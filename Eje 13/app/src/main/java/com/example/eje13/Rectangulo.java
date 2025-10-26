package com.example.eje13;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Rectangulo implements Figura{
    private float x1, x2, y1, y2;
    private int color;

    public Rectangulo(float x1, float y1, float x2, float y2, int color) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = color;
    }

    public float getY2() {
        return y2;
    }

    public void setY2(float y2) {
        this.y2 = y2;
    }

    public float getX2() {
        return x2;
    }

    public void setX2(float x2) {
        this.x2 = x2;
    }

    @Override
    public void dibujar(Canvas canvas, Paint plumon) {
        plumon.setColor(color);
        canvas.drawRect(x1, y1, x2, y2, plumon);
    }
}
