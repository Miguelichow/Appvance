package com.example.appvance;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.Toast;
import com.google.android.material.textfield.TextInputEditText;
import androidx.appcompat.app.AppCompatActivity;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que maneja los ejercicios de espalda
 * @author Miguel Prieto Horcajo
 */
public class BackExercises extends AppCompatActivity implements View.OnClickListener {

    NumberPicker npBack, npSets, npReps;
    String nombre, sets, reps;
    SQLiteHelperExercises sqliteHelperEx;
    ImageButton ibCheck;
    String[] data = {" ", "Dominadas pronas", "Dominadas supinas", "Dominadas neutras", "Muscle up"};
    TextInputEditText etNew;
    DateFormat dateFormat = new SimpleDateFormat("dd/MMM");
    Date currentDate = new Date();
    String fecha = dateFormat.format(currentDate);

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ejercicios de espalda");
        setContentView(R.layout.activity_back_exercises);

        etNew = findViewById(R.id.etNew);

        npBack = findViewById(R.id.npBack);
        npBack.setMinValue(0);
        npBack.setMaxValue(data.length - 1);
        npBack.setWrapSelectorWheel(true);
        npBack.setDisplayedValues(data);

        npSets = findViewById(R.id.npSets);
        npSets.setMinValue(1);
        npSets.setMaxValue(10);

        npReps = findViewById(R.id.npReps);
        npReps.setMinValue(1);
        npReps.setMaxValue(20);

        ibCheck = findViewById(R.id.ibCheck);
        ibCheck.setOnClickListener(this);

        sqliteHelperEx = new SQLiteHelperExercises(this);
    }

    /**
     * Controla la función del click sobre los componentes
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        if(etNew.getText().toString().isEmpty()) {
            nombre = data[npBack.getValue()];
            sets = String.valueOf(npSets.getValue());
            reps = String.valueOf(npReps.getValue());

            if(nombre.equals(" ") || sets.equals("") || reps.equals("")) {
                Toast.makeText(getApplicationContext(), "Existe algún campo vacío", Toast.LENGTH_SHORT).show();
            } else {
                if(sqliteHelperEx.ifExists(fecha, nombre)) {
                    Toast.makeText(getApplicationContext(), "Hoy ya has registrado el ejercicio", Toast.LENGTH_SHORT).show();
                } else {
                    sqliteHelperEx.addExercise(new Exercise(nombre, sets, reps));
                    Toast.makeText(getApplicationContext(), "Ejercicio añadido exitosamente", Toast.LENGTH_SHORT).show();
                }
            }
        } else if(!etNew.getText().toString().isEmpty() && data[npBack.getValue()].equals(" ")){
            nombre = etNew.getText().toString();
            sets = String.valueOf(npSets.getValue());
            reps = String.valueOf(npReps.getValue());

            if(sets.isEmpty() || reps.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Existe algún campo vacío", Toast.LENGTH_SHORT).show();
            } else {
                if(sqliteHelperEx.ifExists(fecha, nombre)) {
                    Toast.makeText(getApplicationContext(), "Hoy ya has registrado el ejercicio", Toast.LENGTH_SHORT).show();
                } else {
                    sqliteHelperEx.addExercise(new Exercise(nombre, sets, reps));
                    Toast.makeText(getApplicationContext(), "Ejercicio añadido exitosamente", Toast.LENGTH_SHORT).show();
                }
            }
        } else if(!etNew.getText().toString().isEmpty() && !data[npBack.getValue()].equals(" ")) {
            Toast.makeText(getApplicationContext(), "Ambos campos para ejercicios están completos", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Por favor, borra uno de ellos para continuar", Toast.LENGTH_SHORT).show();
        }
    }
}
