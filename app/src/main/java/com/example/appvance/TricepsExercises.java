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
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que maneja los ejercicios de tríceps
 * @author Miguel Prieto Horcajo
 */
public class TricepsExercises extends AppCompatActivity implements View.OnClickListener {

    NumberPicker npTriceps, npSets, npReps;
    public static ArrayList<String> tExercises = new ArrayList<>();
    String nombre, sets, reps;
    SQLiteHelperExercises sqliteHelperEx;
    ImageButton ibCheck;
    String[] data = {" ", "Fondos de tríceps", "Fondos cerrados en barra", "Fondos en banco", "Flexiones diamante",
    "Flexiones de tigre"};
    TextInputEditText etNew;
    DateFormat dateFormat = new SimpleDateFormat("dd/MMM");
    Date currentDate = new Date();
    String fecha = dateFormat.format(currentDate);

    /**
     * Asocia la claser a su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Ejercicios de tríceps");
        setContentView(R.layout.activity_triceps_exercises);

        etNew = findViewById(R.id.etNew);

        npTriceps = findViewById(R.id.npTriceps);
        npTriceps.setMinValue(0);
        npTriceps.setMaxValue(tExercises.size() - 1);
        npTriceps.setWrapSelectorWheel(true);
        npTriceps.setDisplayedValues(data);

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
     * Controla la función del click sobre los distintos componentes
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        if(etNew.getText().toString().isEmpty()) {
            nombre = data[npTriceps.getValue()];
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
        } else if(!etNew.getText().toString().isEmpty() && data[npTriceps.getValue()].equals(" ")){
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
        } else if(!etNew.getText().toString().isEmpty() && !data[npTriceps.getValue()].equals(" ")) {
            Toast.makeText(getApplicationContext(), "Ambos campos para ejercicios están completos", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Por favor, borra uno de ellos para continuar", Toast.LENGTH_SHORT).show();
        }
    }
}
