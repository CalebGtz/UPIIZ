package com.example.graph;


import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GraphView graphView;

    private RadioGroup rgType;
    private RadioButton rbLinear, rbQuadratic;

    // lineal
    private LinearLayout boxLinear, boxQuadratic;
    private EditText etM, etBLinear;

    // cuadrática
    private EditText etA, etBQuad, etC;

    // rango X
    private EditText etXMin, etXMax;

    private Button btnPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        graphView = findViewById(R.id.graphView);

        rgType = findViewById(R.id.rgType);
        rbLinear = findViewById(R.id.rbLinear);
        rbQuadratic = findViewById(R.id.rbQuadratic);

        boxLinear = findViewById(R.id.boxLinear);
        boxQuadratic = findViewById(R.id.boxQuadratic);

        etM = findViewById(R.id.etM);
        etBLinear = findViewById(R.id.etBLinear);

        etA = findViewById(R.id.etA);
        etBQuad = findViewById(R.id.etBQuad);
        etC = findViewById(R.id.etC);

        etXMin = findViewById(R.id.etXMin);
        etXMax = findViewById(R.id.etXMax);

        btnPlot = findViewById(R.id.btnPlot);

        // Estado inicial
        graphView.setFunctionType(GraphView.FunctionType.LINEAR);
        graphView.setXRange(-5, 5);
        graphView.setAutoY(true);

        rgType.setOnCheckedChangeListener((group, checkedId) -> {
            boolean isLinear = checkedId == R.id.rbLinear;
            boxLinear.setVisibility(isLinear ? View.VISIBLE : View.GONE);
            boxQuadratic.setVisibility(isLinear ? View.GONE : View.VISIBLE);
            graphView.setFunctionType(isLinear ? GraphView.FunctionType.LINEAR : GraphView.FunctionType.QUADRATIC);
        });

        btnPlot.setOnClickListener(v -> plot());
    }

    private double parse(EditText e, double def) {
        String s = e.getText().toString().trim();
        if (s.isEmpty() || s.equals("-") || s.equals(".")) return def;
        try { return Double.parseDouble(s); } catch (Exception ex) { return def; }
    }

    private void plot() {
        double xmin = parse(etXMin, -5);
        double xmax = parse(etXMax, 5);
        if (xmax <= xmin) {
            Toast.makeText(this, "xMax debe ser mayor que xMin", Toast.LENGTH_SHORT).show();
            return;
        }
        graphView.setXRange(xmin, xmax);

        if (rbLinear.isChecked()) {
            double m = parse(etM, 1);
            double b = parse(etBLinear, 0);
            graphView.setLinear(m, b);
        } else {
            double a = parse(etA, 1);
            double b = parse(etBQuad, 0);
            double c = parse(etC, 0);
            graphView.setQuadratic(a, b, c);
        }
        // Auto-ajustar Y cada vez
        graphView.setAutoY(true);
    }
}
