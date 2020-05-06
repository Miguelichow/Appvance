package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Button;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Clase que maneja los requisitos del usuario
 * @author Miguel Prieto Horcajo
 */
public class Requirements extends AppCompatActivity implements View.OnClickListener {

    public static double totalKcal;
    NumberPicker np, np2, np3;
    public static int peso;
    FloatingActionButton btnNext;
    TextView tvIMC, tvIMCText;
    Button btnIMC;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Datos físicos");
        setContentView(R.layout.activity_requirements);

        np = findViewById(R.id.np);
        np.setMinValue(140);
        np.setMaxValue(210);

        np2 = findViewById(R.id.np2);
        np2.setMinValue(40);
        np2.setMaxValue(150);

        np3 = findViewById(R.id.np3);
        np3.setMinValue(1);
        np3.setMaxValue(100);

        btnNext = findViewById(R.id.btnNext);
        btnNext.setOnClickListener(this);
        btnIMC = findViewById(R.id.btnIMC);
        btnIMC.setOnClickListener(this);

        tvIMC = findViewById(R.id.tvIMC);
        tvIMCText = findViewById(R.id.tvIMCText);
    }

    /**
     * Controla la función del click sobre los distintos componentes
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnNext:
                totalKcal = (66 + (13.7 * np2.getValue()) + (5 * np.getValue()) - (6.8 * np3.getValue()));
                peso = np2.getValue();

                Intent intent = new Intent(this, ActivityRequirements.class);
                startActivity(intent);
                break;

            case R.id.btnIMC:
                double altura = ((double) np.getValue() / 100);
                double peso = (double) np2.getValue();
                double imc = Math.round(((peso / (altura * altura)) / 10) * 10);

                if(imc < 18.5) {
                    tvIMC.setTextColor(Color.YELLOW);
                    tvIMC.setText(String.valueOf(imc));
                    tvIMCText.setTextColor(Color.YELLOW);
                    tvIMCText.setText("Delgadez muy severa");
                } else if(imc >= 18.5 && imc <= 24.9) {
                    tvIMC.setTextColor(Color.rgb(0, 128, 0));
                    tvIMC.setText(String.valueOf(imc));
                    tvIMCText.setTextColor(Color.rgb(0, 128, 0));
                    tvIMCText.setText("Intervalo normal");
                } else if(imc >= 25 && imc <= 29.9) {
                    if(imc == 25) {
                        tvIMC.setTextColor(Color.rgb(255, 165, 0));
                        tvIMC.setText(String.valueOf(imc));
                        tvIMCText.setTextColor(Color.rgb(255, 165, 0));
                        tvIMCText.setText("Sobrepeso");
                    } else {
                        tvIMC.setTextColor(Color.rgb(255, 165, 0));
                        tvIMC.setText(String.valueOf(imc));
                        tvIMCText.setTextColor(Color.rgb(255, 165, 0));
                        tvIMCText.setText("Pre obesidad");
                    }
                } else {
                    if(imc == 30) {
                        tvIMC.setTextColor(Color.RED);
                        tvIMC.setText(String.valueOf(imc));
                        tvIMCText.setTextColor(Color.RED);
                        tvIMCText.setText("Obesidad");
                    } else if(imc >= 30 && imc <= 34.99){
                        tvIMC.setTextColor(Color.RED);
                        tvIMC.setText(String.valueOf(imc));
                        tvIMCText.setTextColor(Color.RED);
                        tvIMCText.setText("Obesidad de clase I");
                    } else if(imc == 35 && imc <= 39.99){
                        tvIMC.setTextColor(Color.RED);
                        tvIMC.setText(String.valueOf(imc));
                        tvIMCText.setTextColor(Color.RED);
                        tvIMCText.setText("Obesidad de clase II");
                    } else {
                        tvIMC.setTextColor(Color.RED);
                        tvIMC.setText(String.valueOf(imc));
                        tvIMCText.setTextColor(Color.RED);
                        tvIMCText.setText("Obesidad de clase III");
                    }
                }
        }
    }
}

