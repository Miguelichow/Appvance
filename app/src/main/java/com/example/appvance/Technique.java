package com.example.appvance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase que maneja la técnica de los ejercicios
 * @author Miguel Prieto Horcajo
 */
public class Technique extends AppCompatActivity implements View.OnClickListener {

    LinearLayout ll1, ll2, ll3, ll4, ll5, ll6, ll7;
    Intent intent;

    /**
     * Asocia la clase a su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Glosario de ejercicios");
        setContentView(R.layout.activity_technique);

        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll3);
        ll4 = findViewById(R.id.ll4);
        ll5 = findViewById(R.id.ll5);
        ll6 = findViewById(R.id.ll6);
        ll7 = findViewById(R.id.ll7);

        ll1.setOnClickListener(this);
        ll2.setOnClickListener(this);
        ll3.setOnClickListener(this);
        ll4.setOnClickListener(this);
        ll5.setOnClickListener(this);
        ll6.setOnClickListener(this);
        ll7.setOnClickListener(this);
    }

    /**
     * Controla la función del click sobre los distintos componentes
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.ll1:
                intent = new Intent(this, ChestGuide.class);
                startActivity(intent);
                break;

            case R.id.ll2:
                intent = new Intent(this, ShoulderGuide.class);
                startActivity(intent);
                break;

            case R.id.ll3:
                intent = new Intent(this, BackGuide.class);
                startActivity(intent);
                break;

            case R.id.ll4:
                intent = new Intent(this, AbsGuide.class);
                startActivity(intent);
                break;

            case R.id.ll5:
                intent = new Intent(this, LegGuide.class);
                startActivity(intent);
                break;

            case R.id.ll6:
                intent = new Intent(this, BicepsGuide.class);
                startActivity(intent);
                break;

            case R.id.ll7:
                intent = new Intent(this, TricepsGuide.class);
                startActivity(intent);
                break;
        }
    }
}
