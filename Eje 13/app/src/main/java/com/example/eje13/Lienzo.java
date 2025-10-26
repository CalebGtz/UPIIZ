package com.example.eje13;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class Lienzo extends View {
    List<Figura> figuras;
    //Vamos a tener varias figuras
    //Vamos a tener un color actual
    private int color;
    //Vamos a tener un tipo de figura actual
    private TipoFigura tipoFigura;
    //Vamos a tener un plumon
    private Paint plumon;
    float x1, y1;

    Linea linea;
    Circulo circulo;

    Rectangulo rectangulo;
    public Lienzo(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // Iniciar componentes
        iniciarComponentes();
    }

    private void iniciarComponentes() {
        color = Color.WHITE;
        tipoFigura = TipoFigura.LINEA;
        figuras = new ArrayList<>();
        plumon = new Paint();
        plumon.setColor(color);
        plumon.setStrokeWidth(10f);
    }
    //Crear los atributos externos
    @Override
    protected void onDraw(@NonNull Canvas canvas){
        super.onDraw(canvas);

        if(tipoFigura == TipoFigura.LINEA && linea != null)
            linea.dibujar(canvas, plumon);

        if(tipoFigura == TipoFigura.CIRCULO && circulo != null)
            circulo.dibujar(canvas, plumon);
        if(tipoFigura == TipoFigura.RECTANGULO && rectangulo != null)
            rectangulo.dibujar(canvas, plumon);


        for(Figura figura:figuras){
            figura.dibujar(canvas, plumon);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = event.getX();
                y1 = event.getY();
                if(tipoFigura == TipoFigura.LINEA)
                {
                    linea = new Linea(x1, y1, x1, y1, color);
                }
                if(tipoFigura == TipoFigura.CIRCULO)
                {
                    circulo = new Circulo(x1, y1, 0, color);
                }
                if(tipoFigura == TipoFigura.RECTANGULO)
                {
                    rectangulo = new Rectangulo(x1, y1, x1, y1, color);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(tipoFigura == TipoFigura.LINEA)
                {
                    linea.setX2(event.getX());
                    linea.setY2(event.getY());
                }
                if(tipoFigura == TipoFigura.CIRCULO)
                {
                    circulo.setRadio(distanciaPuntos(x1, y1, event.getX(), event.getY()));
                }
                if(tipoFigura == TipoFigura.RECTANGULO)
                {
                    rectangulo.setX2(event.getX());
                    rectangulo.setY2(event.getY());
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                if(tipoFigura == TipoFigura.LINEA)
                {
                    figuras.add(linea);
                }
                if(tipoFigura == TipoFigura.CIRCULO)
                {
                    figuras.add(circulo);
                }
                if(tipoFigura == TipoFigura.RECTANGULO)
                {
                    figuras.add(rectangulo);
                }
                break;
        }

        return true;
    }

    private float distanciaPuntos(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x2 - x1, 2)+Math.pow(y2 - y1, 2));
    }

    //Establecer el color de la figura - Spinner

    public void establecerColor(int color){
        this.color = color;
    }
    //Establecer el tipo de figura - Spinner
    public void establecerFigura(TipoFigura tipoFigura){
        this.tipoFigura = tipoFigura;
    }
}
