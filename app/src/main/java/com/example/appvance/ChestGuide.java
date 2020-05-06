package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Clase que maneja la guía de ejercicios de pecho
 * @author Miguel Prieto Horcajo
 */
public class ChestGuide extends AppCompatActivity implements View.OnClickListener {

    TextView tv1, tv2, tv3, tv4, tv5;
    String url;
    Intent intent;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Guía de pecho");
        setContentView(R.layout.activity_chest_guide);

        tv1 = findViewById(R.id.tv1);
        tv1.setOnClickListener(this);
        tv2 = findViewById(R.id.tv2);
        tv2.setOnClickListener(this);
        tv3 = findViewById(R.id.tv3);
        tv3.setOnClickListener(this);
        tv4 = findViewById(R.id.tv4);
        tv4.setOnClickListener(this);
        tv5 = findViewById(R.id.tv5);
        tv5.setOnClickListener(this);
    }

    /**
     * Controla la función del click sobre los distintos componentes
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                url = "https://www.youtube.com/watch?v=IODxDxX7oi4";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.tv2:
                url = "https://www.youtube.com/watch?v=SKPab2YC8BE";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.tv3:
                url = "https://www.youtube.com/watch?v=Me9bHFAxnCs";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.tv4:
                url = "https://www.youtube.com/watch?v=2z8JmcrW-As";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.tv5:
                url = "https://www.youtube.com/watch?v=TSQP6rZqjiM";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
        }
    }
}
