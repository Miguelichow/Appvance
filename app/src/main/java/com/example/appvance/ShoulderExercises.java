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
 * Clase que maneja los ejercicios de hombro
 * @author Miguel Prieto Horcajo
 */
public class ShoulderExercises extends AppCompatActivity implements View.OnClickListener {

    NumberPicker npShoulder, npSets, npReps;
    String nombre, sets, reps;
    ImageButton ibCheck;
    SQLiteHelperExercises sqliteHelperEx;
    String[] data = {" ", "Flexiones en pica", "Pseudo flexiones", "Flexiones en pino", "Fondos coreanos",
    "Pino libre", "Pino en pared"};
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
        setTitle("Ejercicios de hombro");
        setContentView(R.layout.activity_shoulder_exercises);

        etNew = findViewById(R.id.etNew);

        npShoulder = findViewById(R.id.npShoulder);
        npShoulder.setMinValue(0);
        npShoulder.setMaxValue(data.length - 1);
        npShoulder.setWrapSelectorWheel(true);
        npShoulder.setDisplayedValues(data);

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
     * Controla la función del click de los distintos componentes
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        if(etNew.getText().toString().isEmpty()) {
            nombre = data[npShoulder.getValue()];
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
        } else if(!etNew.getText().toString().isEmpty() && data[npShoulder.getValue()].equals(" ")){
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
        } else if(!etNew.getText().toString().isEmpty() && !data[npShoulder.getValue()].equals(" ")) {
            Toast.makeText(getApplicationContext(), "Ambos campos para ejercicios están completos", Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(), "Por favor, borra uno de ellos para continuar", Toast.LENGTH_SHORT).show();
        }
    }
}
