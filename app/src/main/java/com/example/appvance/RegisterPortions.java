package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.shawnlin.numberpicker.NumberPicker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Registra las porciones en la BD
 * @author Miguel Prieto Horcajo
 */
public class RegisterPortions extends AppCompatActivity implements View.OnClickListener {

    RadioGroup radioGroup;
    String time;
    RadioButton radioButton;
    NumberPicker npP, npF, npC;
    SQLiteHelperPortions sqliteHelperPortions;
    SQLiteHelperMacros sqliteHelperMacros;
    int protein, fats, carbs;
    Button button;
    ListView lvMacros;
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
        setTitle("Registro de porciones");
        setContentView(R.layout.activity_register_portions);

        radioGroup = findViewById(R.id.radioGroup);

        lvMacros = findViewById(R.id.lvMacros);

        npP = findViewById(R.id.npP);
        npF = findViewById(R.id.npF);
        npC = findViewById(R.id.npC);

        npP = findViewById(R.id.npP);
        npP.setMinValue(1);
        npP.setMaxValue(25);

        npF = findViewById(R.id.npF);
        npF.setMinValue(1);
        npF.setMaxValue(25);

        npC = findViewById(R.id.npC);
        npC.setMinValue(1);
        npC.setMaxValue(50);

        button = findViewById(R.id.button);
        button.setOnClickListener(this);

        sqliteHelperPortions = new SQLiteHelperPortions(this);

        sqliteHelperMacros = new SQLiteHelperMacros(this);
        Cursor c = sqliteHelperMacros.getMacros();
        ArrayList<String> datos = new ArrayList<String>();

        while(c.moveToNext()) {
            datos.add("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tMacros totales diarios:" + System.getProperty("line.separator")
                    + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tP: " + c.getString(0) + "   G: " + c.getString(1)
                    + "   C: " + c.getString(2));
        }

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, R.layout.custom_text, datos);
        lvMacros.setAdapter(adaptador);
    }

    /**
     * Controla la función del click sobre los distintos componentes
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        checkButton(v);

        time = radioButton.getText().toString();
        protein = npP.getValue();
        fats = npF.getValue();
        carbs = npC.getValue();

        if(sqliteHelperPortions.ifExists(fecha, time)) {
            Toast.makeText(this, "La fecha y comida introducidas ya existen", Toast.LENGTH_SHORT).show();
        } else {
            sqliteHelperPortions.addPortions(new Portion(time, protein, fats, carbs));

            Intent intent = new Intent(this, PortionsTrack.class);
            startActivity(intent);
        }
    }

    /**
     * Obtiene el RadioButton seleccionado
     * @param v Vista
     */
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }
}
