package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Clase que maneja las recetas
 * @author Miguel Prieto Horcajo
 */
public class Recipes extends AppCompatActivity implements View.OnClickListener {

    TextView tv1, tv2, tv3, tv4;
    String url;
    Intent intent;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Recetas");
        setContentView(R.layout.activity_recipes);

        tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(this);
        tv2 = findViewById(R.id.tv2);
        tv2.setOnClickListener(this);
        tv3 = findViewById(R.id.tv3);
        tv3.setOnClickListener(this);
        tv4 = findViewById(R.id.tv4);
        tv4.setOnClickListener(this);
    }

    /**
     * Controla la función dedl click sobre los distintos componentes
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                url = "https://www.iifym.com/iifym-recipes-and-articles/";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.tv2:
                url = "http://maddiefitness.com/category/iifym-recipes/";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.tv3:
                url = "https://healthyeater.com/protein-macro-friendly-meals";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.tv4:
                url = "https://www.purewow.com/food/macro-meal-prep-recipes";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
        }
    }
}
