package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Objetivo del usuario
 * @author Miguel Prieto Horcajo
 */
public class Target extends AppCompatActivity implements View.OnClickListener {

    public static double totalKcal = ActivityRequirements.totalKcal;
    RadioGroup rg;
    TextView tvKcal;
    FloatingActionButton btnNext;
    View selectedRadio;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Selecciona tu objetivo");
        setContentView(R.layout.activity_target);

        selectedRadio = findViewById(R.id.rb1);

        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = rg.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rb1:
                        totalKcal *= 1.1;
                        break;
                    case R.id.rb2:
                        totalKcal *= 1;
                        break;
                    case R.id.rb3:
                        totalKcal *= 0.8;
                        break;
                }
            }
        });
        tvKcal = findViewById(R.id.tvKcal);

        btnNext = findViewById(R.id.btnNext);
    }

    /**
     * Conprueba el RadioButton seleccionado
     * @param view Vista
     */
    public void checkButton(View view) {
        selectedRadio = view;
    }

    /**
     * Controla la función del click sobre el componente
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        totalKcal = Math.round((totalKcal /10.0) * 10);
        Intent intent = new Intent(this, Macros.class);
        startActivity(intent);
    }
}
