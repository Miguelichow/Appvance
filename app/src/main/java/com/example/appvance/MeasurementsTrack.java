package com.example.appvance;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import com.example.appvance.ui.measurements.MeasurementsFragment;
import java.util.ArrayList;
import java.util.List;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Realiza el seguimiento de las medidas
 * @author Miguel Prieto Horcajo
 */
public class MeasurementsTrack extends AppCompatActivity {

    private RecyclerView recyclerMeasurements;
    private RecyclerViewAdapterMeasurements adapterMeasurements;

    String etHip = MeasurementsFragment.etHip.getText().toString();
    ArrayList<String> measurementsData = new ArrayList<>();
    MeasurementsModel mm;

    SQLiteHelperMeasurements sqliteHelperMeasurements;
    Cursor c;
    List<MeasurementsModel> measurements;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registro de perímetro abdominal");
        setContentView(R.layout.activity_measurements_track);

        recyclerMeasurements = findViewById(R.id.recyclerMeasurements);
        recyclerMeasurements.setLayoutManager(new LinearLayoutManager(this));

        measurements = new ArrayList<>();

        sqliteHelperMeasurements = new SQLiteHelperMeasurements(this);

        c = sqliteHelperMeasurements.getData();

        adapterMeasurements = new RecyclerViewAdapterMeasurements(getData());
        recyclerMeasurements.setAdapter(adapterMeasurements);

        compareWeight();
    }

    /**
     * Crea un objeto MeasurementsModel a través de los datos almacenados en la BD de las medidas
     * @return Lista de los objetos MeasurementsModel creados
     */
    public List<MeasurementsModel> getData() {
        while(c.moveToNext()){
            mm = new MeasurementsModel(c.getString(0), c.getString(1), R.drawable.ic_weight);
            measurements.add(mm);
            measurementsData.add(mm.getPeso());
        }
        return measurements;
    }

    /**
     * Compara si la última medida es igual, menor o mayor que el anterior y establece la imagen en
     * base a ello
     */
    public void compareWeight() {
        if(measurementsData.size() == 1) {
            Toast.makeText(getApplication(), "Tu primera medida ha sido añadida", Toast.LENGTH_SHORT).show();
        } else {
            if(Double.parseDouble(measurementsData.get(measurementsData.size() - 2)) == Double.parseDouble(etHip)) {
                Toast.makeText(getApplication(), "Has mantenido la medida de la tripa", Toast.LENGTH_SHORT).show();
            } else if(Double.parseDouble(measurementsData.get(measurementsData.size() - 2)) > Double.parseDouble(etHip)) {
                mm.setImgWeight(R.drawable.ic_weight_down);
                Toast.makeText(getApplication(), "Has bajado tripa", Toast.LENGTH_SHORT).show();
            } else if(Double.parseDouble(measurementsData.get(measurementsData.size() - 2)) < Double.parseDouble(etHip)) {
                mm.setImgWeight(R.drawable.ic_weight_up);
                Toast.makeText(getApplication(), "Has subido tripa", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
