package com.example.eje_hcgo_06;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eje_hcgo_06.util.Basicas;

public class MainActivity extends AppCompatActivity {

    TextView tvDisplay;
    private StringBuilder current = new StringBuilder(); // número en edición
    private Double first = null;                         // primer operando
    private Character pending = null;                    // operación pendiente
    private boolean overwrite = false;                   // tras "=" empezar nuevo número

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);

        int[] numIds = { R.id.btn0,R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,
                R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9 };

        for (int id : numIds) {
            findViewById(id).setOnClickListener(v ->
                    appendDigit(((Button) v).getText().toString()));
        }
        findViewById(R.id.btnDot).setOnClickListener(v -> appendDot());

        findViewById(R.id.btnPlus).setOnClickListener(v -> setOp('+'));
        findViewById(R.id.btnMinus).setOnClickListener(v -> setOp('-'));
        findViewById(R.id.btnMul).setOnClickListener(v -> setOp('*'));
        findViewById(R.id.btnDiv).setOnClickListener(v -> setOp('/'));

        findViewById(R.id.btnEqual).setOnClickListener(v -> evaluate());
        findViewById(R.id.btnC).setOnClickListener(v -> clearAll());

        refreshDisplay();
    }

    private void appendDigit(String d) {
        if (overwrite) { current.setLength(0); overwrite = false; }
        if (current.length() == 1 && current.charAt(0) == '0' && !d.equals(".")) {
            current.setCharAt(0, d.charAt(0));
        } else {
            current.append(d);
        }
        refreshDisplay();
    }

    private void appendDot() {
        if (overwrite) { current.setLength(0); overwrite = false; }
        if (current.indexOf(".") == -1) {
            if (current.length() == 0) current.append("0");
            current.append(".");
            refreshDisplay();
        }
    }

    private void setOp(char op) {
        Double val = parseCurrentOrToast();
        if (val == null) return;

        if (first == null) first = val;
        else if (pending != null) {
            try { first = operate(first, val, pending); }
            catch (IllegalArgumentException ex) { toast(ex.getMessage()); return; }
        }

        pending = op;
        current.setLength(0); // ← empezamos a capturar el segundo operando
        showValue(first);     // ← SOLO muestra, NO copia a 'current'
    }

    private void evaluate() {
        if (pending == null) {  // nada que calcular
            Double val = parseCurrentOrToast();
            if (val != null) showValue(val);
            return;
        }

        Double val = parseCurrentOrToast();
        if (val == null) return;

        try {
            double res = operate(first, val, pending);
            showValueAndSync(res);  // ← muestra y ahora sí sincroniza 'current'
            first = res;
            pending = null;
            overwrite = true;       // la próxima tecla empieza número nuevo
        } catch (IllegalArgumentException ex) {
            toast(ex.getMessage());
        }
    }

    private void clearAll() {
        current.setLength(0);
        first = null;
        pending = null;
        overwrite = false;
        tvDisplay.setText("0");
    }

    private Double parseCurrentOrToast() {
        if (current.length() == 0) { toast("Ingrese un número"); return null; }
        try { return Double.parseDouble(current.toString()); }
        catch (NumberFormatException e) { toast("Número inválido"); return null; }
    }

    private double operate(double a, double b, char op) {
        switch (op) {
            case '+': return Basicas.sumar(a, b);
            case '-': return Basicas.restar(a, b);
            case '*': return Basicas.multiplicar(a, b);
            case '/': return Basicas.dividir(a, b);
            default:  return b;
        }
    }

    /* ---------- helpers de UI ---------- */

    private void refreshDisplay() {
        tvDisplay.setText(current.length() == 0 ? "0" : current.toString());
    }

    // Muestra un valor sin tocar 'current' (para cuando eliges operador)
    private void showValue(double val) {
        tvDisplay.setText(format(val));
    }

    // Muestra y sincroniza 'current' (para el resultado de '=')
    private void showValueAndSync(double val) {
        String s = format(val);
        tvDisplay.setText(s);
        current.setLength(0);
        current.append(s);
    }

    private String format(double v) {
        return (v == Math.rint(v)) ? String.format("%.0f", v) : String.valueOf(v);
    }

    private void toast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

