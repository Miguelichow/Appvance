package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Clase con la imagen de la tabla de las porciones
 * @author Miguel Prieto Horcajo
 */
public class Portions extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton btnReturn;

    /**
     * Asocia la clase con su vista
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Tabla de porciones");
        setContentView(R.layout.activity_portions);

        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);
    }

    /**
     * Controla la función del click sobre el componente
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
