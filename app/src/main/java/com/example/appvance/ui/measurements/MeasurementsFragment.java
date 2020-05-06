package com.example.appvance.ui.measurements;

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
import com.example.appvance.MeasurementsTrack;
import com.example.appvance.R;
import com.example.appvance.SQLiteHelperMeasurements;
import com.google.android.material.textfield.TextInputEditText;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase que maneja el Fragment del apartado Medidas
 * @author Miguel Prieto Horcajo
 */
public class MeasurementsFragment extends Fragment implements View.OnClickListener {

    public static TextInputEditText etHip;
    Button btnInsert;
    SQLiteHelperMeasurements sqliteHelperMeasurements;
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
        View v = inflater.inflate(R.layout.activity_hip, container, false);

        btnInsert = v.findViewById(R.id.btnInsert);
        btnInsert.setOnClickListener(this);
        btnInsert.setOnEditorActionListener(editorActionListener);

        etHip = v.findViewById(R.id.etHip);
        etHip.setOnEditorActionListener(editorActionListener);

        calendarView = v.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = dayOfMonth + "/" + (month + 1);
            }
        });

        sqliteHelperMeasurements = new SQLiteHelperMeasurements(getActivity());

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
     * @param v VIsta
     */
    @Override
    public void onClick(View v) {
        String hip = etHip.getText().toString();

        if (hip.equals(" ") || hip.isEmpty()) {
            Toast.makeText(getActivity(), "Existe algún campo vacío", Toast.LENGTH_SHORT).show();
        } else {
            if(date.equals(" ")) {
                if(sqliteHelperMeasurements.ifExists(fecha)) {
                    Toast.makeText(getActivity(), "La fecha introducida ya existe", Toast.LENGTH_SHORT).show();
                } else {
                    sqliteHelperMeasurements.addData(hip, fecha);
                    Toast.makeText(getActivity(), "Peso registrado correctamente", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), MeasurementsTrack.class);
                    startActivity(intent);
                }
            } else {
                if(!date.equals(fecha)){
                    Toast.makeText(getActivity(), "No puedes registrar una fecha distinta a la actual", Toast.LENGTH_SHORT).show();
                } else if(sqliteHelperMeasurements.ifExists(date)) {
                    Toast.makeText(getActivity(), "La fecha introducida ya existe", Toast.LENGTH_SHORT).show();
                } else {
                    sqliteHelperMeasurements.addData(hip, date);
                    Toast.makeText(getActivity(), "Peso registrado correctamente", Toast.LENGTH_SHORT).show();
                    intent = new Intent(getActivity(), MeasurementsTrack.class);
                    startActivity(intent);
                }
            }
        }
    }
}