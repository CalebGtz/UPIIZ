package com.example.damn_hcgo_eje11;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    private Button btnToggleDraw;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawingView = findViewById(R.id.drawingView);
        btnToggleDraw = findViewById(R.id.btnToggleDraw);

        // Estado inicial: dibujo deshabilitado
        drawingView.setDrawingEnabled(false);


        btnToggleDraw.setOnClickListener(v -> {
            boolean enable = !drawingView.isDrawingEnabled();
            drawingView.setDrawingEnabled(enable);
            btnToggleDraw.setText(enable ? "Deshabilitar dibujo" : "Habilitar dibujo");
            Toast.makeText(this, enable ? "Dibujo activado" : "Dibujo desactivado", Toast.LENGTH_SHORT).show();
        });

        // Mantén presionado el botón para limpiar
        btnToggleDraw.setOnLongClickListener(v -> {
            drawingView.clear();
            Toast.makeText(this, R.string.lienzo_limpio, Toast.LENGTH_SHORT).show();
            return true;
        });
    }
}
