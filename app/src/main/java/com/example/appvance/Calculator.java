package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;

/**
 * Clase que maneja la calculadora de macronutrientes
 * @author Miguel Prieto Horcajo
 */
public class Calculator extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText etProtein, etFats, etCarbs;
    TextView tvProtein, tvFats, tvCarbs;
    Button button;
    int p, f, c;
    Intent intent;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Calculadora");
        setContentView(R.layout.activity_calculator);

        etProtein = findViewById(R.id.etProtein);
        etFats = findViewById(R.id.etFats);
        etCarbs = findViewById(R.id.etCarbs);

        tvProtein = findViewById(R.id.tvProtein);
        tvFats = findViewById(R.id.tvFats);
        tvCarbs = findViewById(R.id.tvCarbs);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        etCarbs.setOnEditorActionListener(editorActionListener);
    }

    /**
     * Controla la función de la tecla "intro" del teclado
     */
    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onClick(button);
                return true;
            }
            return false;
        }
    };

    /**
     * Controla la función del click sobre los distintos componentes
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        if(etProtein.getText().equals("") || etFats.getText().equals("") || etCarbs.getText().equals("")) {
            Toast.makeText(this, "Algún campo se encuentra vacío", Toast.LENGTH_SHORT).show();
        } else {
            p = (int) (Math.round(Double.parseDouble(etProtein.getText().toString()) / 5.0) * 5) / 20;
            f = (int) (Math.round(Double.parseDouble(etFats.getText().toString()) / 5.0) * 5) / 10;
            c = (int) (Math.round(Double.parseDouble(etCarbs.getText().toString()) / 5.0) * 5) / 20;

            tvProtein.setText(String.valueOf(p));
            tvFats.setText(String.valueOf(f));
            tvCarbs.setText(String.valueOf(c));
        }
    }
}
