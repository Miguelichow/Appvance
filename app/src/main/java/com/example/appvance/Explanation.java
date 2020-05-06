package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Clase que maneja la explicación de la app
 * @author Miguel Prieto Horcajo
 */
public class Explanation extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton btnNext;
    Intent intent;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Explicación");
        setContentView(R.layout.activity_explanation);

        btnNext = findViewById(R.id.btnNext);
    }

    /**
     * Controla la función del click sobre el componente
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        intent = new Intent(this, Requirements.class);
        startActivity(intent);
    }
}
