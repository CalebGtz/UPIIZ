package com.example.graph;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class GraphView extends View {

    public enum FunctionType { LINEAR, QUADRATIC }

    private FunctionType type = FunctionType.LINEAR;

    // Lineal: y = m x + b
    private double m = 1, bLin = 0;

    // Cuadrática: y = a x^2 + b x + c
    private double a = 1, b = 0, c = 0;

    // Rango X e Y
    private double xMin = -5, xMax = 5;
    private double yMin = -5, yMax = 5;
    private boolean autoY = true;

    private final Paint grid = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint axis = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint curve = new Paint(Paint.ANTI_ALIAS_FLAG);

    public GraphView(Context ctx) { super(ctx); init(); }
    public GraphView(Context ctx, AttributeSet attrs) { super(ctx, attrs); init(); }
    public GraphView(Context ctx, AttributeSet attrs, int defStyleAttr) { super(ctx, attrs, defStyleAttr); init(); }

    private void init() {
        grid.setColor(Color.parseColor("#E0E0E0"));
        grid.setStrokeWidth(1f);

        axis.setColor(Color.BLACK);
        axis.setStrokeWidth(3f);

        curve.setColor(Color.parseColor("#7E57C2")); // morado
        curve.setStrokeWidth(4f);
        curve.setStyle(Paint.Style.STROKE);

        setBackgroundColor(Color.WHITE);
    }

    // ---------- Setters públicos ----------
    public void setFunctionType(FunctionType t) { this.type = t; invalidate(); }
    public void setLinear(double m, double b) { this.m = m; this.bLin = b; if (autoY) recomputeAutoY(); invalidate(); }
    public void setQuadratic(double a, double b, double c) { this.a = a; this.b = b; this.c = c; if (autoY) recomputeAutoY(); invalidate(); }
    public void setXRange(double xmin, double xmax) { this.xMin = xmin; this.xMax = xmax; if (autoY) recomputeAutoY(); invalidate(); }
    public void setAutoY(boolean enabled) { this.autoY = enabled; if (autoY) { recomputeAutoY(); } invalidate(); }

    // ---------- Cálculos ----------
    private double f(double x) {
        if (type == FunctionType.LINEAR) return m * x + bLin;
        return a * x * x + b * x + c;
    }

    private void recomputeAutoY() {
        double min = Double.POSITIVE_INFINITY, max = Double.NEGATIVE_INFINITY;
        int N = 800;
        double step = (xMax - xMin) / N;
        for (int i = 0; i <= N; i++) {
            double x = xMin + i * step;
            double y = f(x);
            if (Double.isFinite(y)) {
                if (y < min) min = y;
                if (y > max) max = y;
            }
        }
        if (!Double.isFinite(min) || !Double.isFinite(max) || min == max) {
            min = -5; max = 5; // fallback
        } else {
            double pad = (max - min) * 0.1; // margen 10%
            min -= pad; max += pad;
        }
        yMin = min; yMax = max;
    }

    // ---------- Utilidades de mapeo ----------
    private float pxX(double x, int w) { return (float) ((x - xMin) * w / (xMax - xMin)); }
    private float pxY(double y, int h) { return (float) (h - (y - yMin) * h / (yMax - yMin)); }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int w = getWidth(), h = getHeight();

        // Cuadrícula cada 1 unidad
        int xStart = (int) Math.ceil(xMin);
        int xEnd   = (int) Math.floor(xMax);
        for (int xi = xStart; xi <= xEnd; xi++) {
            float x = pxX(xi, w);
            canvas.drawLine(x, 0, x, h, grid);
        }
        int yStart = (int) Math.ceil(yMin);
        int yEnd   = (int) Math.floor(yMax);
        for (int yi = yStart; yi <= yEnd; yi++) {
            float y = pxY(yi, h);
            canvas.drawLine(0, y, w, y, grid);
        }

        // Ejes X e Y
        if (xMin <= 0 && 0 <= xMax) {
            float x0 = pxX(0, w);
            canvas.drawLine(x0, 0, x0, h, axis);
        }
        if (yMin <= 0 && 0 <= yMax) {
            float y0 = pxY(0, h);
            canvas.drawLine(0, y0, w, y0, axis);
        }

        // Curva
        Path path = new Path();
        int N = 1000;
        double step = (xMax - xMin) / N;
        boolean started = false;
        for (int i = 0; i <= N; i++) {
            double x = xMin + i * step;
            double y = f(x);
            if (!Double.isFinite(y)) { started = false; continue; }
            float px = pxX(x, w), py = pxY(y, h);
            if (!started) { path.moveTo(px, py); started = true; }
            else { path.lineTo(px, py); }
        }
        canvas.drawPath(path, curve);
    }
}
