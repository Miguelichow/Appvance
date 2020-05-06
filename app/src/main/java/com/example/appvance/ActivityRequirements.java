package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Clase que maneja los requisitos según actividades realizadas
 * @author Miguel Prieto Horcajo
 */
public class ActivityRequirements extends AppCompatActivity implements View.OnClickListener{

    public static double totalKcal = Requirements.totalKcal;
    RadioGroup rg;
    TextView tvKcal;
    FloatingActionButton btnNext;
    Button btnIMC;
    View selectedRadio;

    /**
     * Asocia la clase con su vista y relaciona sus componentes
     * Maneja el RadioButton que ha sido clicado
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Selecciona tu nivel de actividad");
        setContentView(R.layout.activity_final_requirements);

        selectedRadio = findViewById(R.id.rb1);

        rg = findViewById(R.id.rg);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int id = rg.getCheckedRadioButtonId();
                switch (id) {
                    case R.id.rb1:
                        totalKcal *= 1.0;
                        break;
                    case R.id.rb2:
                        totalKcal *= 1.2;
                        break;
                    case R.id.rb3:
                        totalKcal *= 1.4;
                        break;
                    case R.id.rb4:
                        totalKcal *= 1.6;
                        break;
                    case R.id.rb5:
                        totalKcal *= 1.8;
                        break;
                    case R.id.rb6:
                        totalKcal *= 2.0;
                        break;
                }
            }
        });
        tvKcal = findViewById(R.id.tvKcal);

        btnNext = findViewById(R.id.btnNext);
        btnIMC = findViewById(R.id.btnIMC);
    }

    /**
     * Identifica el RadioButton clicado
     * @param view Vista
     */
    public void checkButton(View view) {
        selectedRadio = view;
    }

    /**
     * Controla la función del click sobre el componente al que se asigna
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        totalKcal = Math.round((totalKcal /10.0) * 10);
        Intent intent = new Intent(this, Target.class);
        startActivity(intent);
    }
}
