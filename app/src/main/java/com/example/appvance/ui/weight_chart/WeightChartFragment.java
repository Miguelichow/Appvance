package com.example.appvance.ui.weight_chart;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.appvance.R;
import com.example.appvance.SQLiteHelperWeight;
import com.example.appvance.WeightTrack;
import com.google.android.material.textfield.TextInputEditText;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que maneja el Fragment del apartado Peso
 * @author Miguel Prieto Horcajo
 */
public class WeightChartFragment extends Fragment implements View.OnClickListener {

    public static TextInputEditText etWeight;
    Button btnInsert;
    SQLiteHelperWeight sqliteHelperWeight;
    CalendarView calendarView;
    String date = " ";
    Intent intent;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM");
    Date currentDate = new Date();
    String fecha = dateFormat.format(currentDate);

    /**
     * Asocia la clase con su vista y relaciona sus componentes
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return Vista de la clase
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_weight_chart, container, false);

        btnInsert = v.findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);
        btnInsert.setOnEditorActionListener(editorActionListener);

        etWeight = v.findViewById(R.id.etWeight);
        etWeight.setOnEditorActionListener(editorActionListener);

        calendarView = v.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + (month + 1);
            }
        });

        sqliteHelperWeight = new SQLiteHelperWeight(getActivity());

        return v;
    }

    /**
     * Controla la función de la tecla "intro" del teclado
     */
    private TextView.OnEditorActionListener editorActionListener = new TextView.OnEditorActionListener() {
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                onClick(btnInsert);
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
        String weight = etWeight.getText().toString();

        if (weight.equals(" ") || weight.isEmpty()) {
            Toast.makeText(getActivity(), "Existe algún campo vacío", Toast.LENGTH_SHORT).show();
        } else {
            if(date.equals(" ")) {
                if(sqliteHelperWeight.ifExists(fecha)) {
                    Toast.makeText(getActivity(), "La fecha introducida ya existe", Toast.LENGTH_SHORT).show();
                } else {
                    sqliteHelperWeight.addData(weight, fecha);
                    Toast.makeText(getActivity(), "Peso registrado correctamente", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), WeightTrack.class);
                    startActivity(intent);
                }
            } else {
                if(!date.equals(fecha)){
                    Toast.makeText(getActivity(), "No puedes registrar una fecha distinta a la actual", Toast.LENGTH_SHORT).show();
                } else if(sqliteHelperWeight.ifExists(date)) {
                    Toast.makeText(getActivity(), "La fecha introducida ya existe", Toast.LENGTH_SHORT).show();
                } else {
                    sqliteHelperWeight.addData(weight, date);
                    Toast.makeText(getActivity(), "Peso registrado correctamente", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), WeightTrack.class);
                    startActivity(intent);
                }
            }
        }
    }
}