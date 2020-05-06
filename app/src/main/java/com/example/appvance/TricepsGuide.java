package com.example.appvance;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Clase que maneja la guía de ejercicios de tríceps
 * @author Miguel Prieto Horcajo
 */
public class TricepsGuide extends AppCompatActivity implements View.OnClickListener {

    TextView tv1, tv2, tv3, tv4;
    String url;
    Intent intent;

    /**
     * Asocia la clase a su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Guía de hombro");
        setContentView(R.layout.activity_shoulder_guide);

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
     * Controla la función del click sobre los distintos componentes
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv1:
                url = "https://www.youtube.com/watch?v=2z8JmcrW-As";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.tv2:
                url = "https://www.youtube.com/watch?v=wjUmnZH528Y";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.tv3:
                url = "https://www.youtube.com/watch?v=6kALZikXxLc";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;

            case R.id.tv4:
                url = "https://www.youtube.com/watch?v=J0DnG1_S92Ic";
                intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
                break;
        }
    }
}
