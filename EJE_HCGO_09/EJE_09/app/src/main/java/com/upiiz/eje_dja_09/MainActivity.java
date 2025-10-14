package com.upiiz.eje_dja_09;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnBasicListView, btnCustomListView, btnBasicRecycleView, btnCustomRecycleView, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnBasicListView      = findViewById(R.id.btnBasicListView);
        btnCustomListView     = findViewById(R.id.btnCustomListView);
        btnBasicRecycleView   = findViewById(R.id.btnBasicRecycleView);
        btnCustomRecycleView  = findViewById(R.id.btnCustomRecycleView);
        btnSalir              = findViewById(R.id.btnExit);


        btnBasicListView.setOnClickListener(this);
        btnCustomListView.setOnClickListener(this);
        btnBasicRecycleView.setOnClickListener(this);
        btnCustomRecycleView.setOnClickListener(this);
        btnSalir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == btnBasicListView.getId()) {
            intent = new Intent(this, BasicListViewActivity.class);
            startActivity(intent);
        } else if (v.getId() == btnCustomListView.getId()) {
            intent = new Intent(this, CustomListViewActivity.class);
            startActivity(intent);
        } else if (v.getId() == btnBasicRecycleView.getId()) {
            intent = new Intent(this, BasicRecycleViewActivity.class);
            startActivity(intent);
        } else if (v.getId() == btnCustomRecycleView.getId()) { //
            intent = new Intent(this, CustomRecycleViewActivity.class);
            startActivity(intent);
        } else if (v.getId() == btnSalir.getId()) {
            finish();
        }
    }
}