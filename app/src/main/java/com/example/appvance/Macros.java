package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Clase que maneja los macronutrientes del usuario
 * @author Miguel Prieto Horcajo
 */
public class Macros extends AppCompatActivity implements View.OnClickListener {

    public static int p, g, ch;
    int peso = Requirements.peso;
    int totalKcal = (int) Target.totalKcal;
    TextView tvP, tvG, tvCH;
    FloatingActionButton btnReturn;
    SQLiteHelperMacros sqLiteHelperMacros;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Macronutrientes");
        setContentView(R.layout.activity_macros);

        sqLiteHelperMacros = new SQLiteHelperMacros(this);

        tvP = findViewById(R.id.tvP);
        tvG = findViewById(R.id.tvG);
        tvCH = findViewById(R.id.tvCH);

        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(this);

        p = (int) (peso * 2.2);
        int pRound = (int) (Math.round(p / 5.0) * 5);
        tvP.setText(String.valueOf(Math.round(pRound / 20)) + " porciones");

        g = peso;
        int gRound = (int) (Math.round((g)/ 5.0) * 5);
        tvG.setText(String.valueOf(Math.round(gRound / 10)) + " porciones");

        ch = ((totalKcal / 100) - (Math.round(pRound / 20)) - (Math.round(gRound / 10)));
        if(ch <= 0) {
            tvCH.setText(String.valueOf("0 porciones"));
        } else {
            tvCH.setText(String.valueOf(ch  + " porciones"));
        }

        sqLiteHelperMacros.addMacros(new Portion(Math.round(pRound / 20), Math.round(gRound / 10), ch));
    }

    /**
     * Controla la función del click sobre el componente
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Portions.class);
        startActivity(intent);
    }
}
