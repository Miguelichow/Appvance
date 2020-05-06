package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;
import com.example.appvance.ui.weight_chart.WeightChartFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para realizar el seguimiento del peso
 * @author Miguel Prieto Horcajo
 */
public class WeightTrack extends AppCompatActivity {

    private RecyclerView recyclerWeight;
    private RecyclerViewAdapterWeight adapterWeight;

    String etWeight = WeightChartFragment.etWeight.getText().toString();
    ArrayList<String> weightData = new ArrayList<>();
    WeightModel wm;

    SQLiteHelperWeight sqliteHelperW;
    Cursor c;
    List<WeightModel> weight;
    Intent intent;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Registro de peso");
        setContentView(R.layout.activity_weight_track);

        recyclerWeight = findViewById(R.id.recyclerWeight);
        recyclerWeight.setLayoutManager(new LinearLayoutManager(this));

        weight = new ArrayList<>();

        sqliteHelperW = new SQLiteHelperWeight(this);

        c = sqliteHelperW.getData();

        adapterWeight = new RecyclerViewAdapterWeight(getData());
        recyclerWeight.setAdapter(adapterWeight);

        compareWeight();
    }

    /**
     * Crea un objeto WeightModel a través de los datos almacenados en la BD del peso
     * @return Lista de los objetos WeightModel creados
     */
    public List<WeightModel> getData() {
        while(c.moveToNext()){
            wm = new WeightModel(c.getString(0), c.getString(1), R.drawable.ic_weight);
            weight.add(wm);
            weightData.add(wm.getPeso());
        }
        return weight;
    }

    /**
     * Compara si el último peso es igual, menor o mayor que el anterior y establece la imagen en
     * base a ello
     */
    public void compareWeight() {
        if(weightData.size() == 1) {
            Toast.makeText(getApplication(), "Tu primer peso ha sido añadido", Toast.LENGTH_SHORT).show();
        } else {
            if(Double.parseDouble(weightData.get(weightData.size() - 2)) == Double.parseDouble(etWeight)) {
                Toast.makeText(getApplication(), "Has mantenido el peso", Toast.LENGTH_SHORT).show();
            } else if(Double.parseDouble(weightData.get(weightData.size() - 2)) > Double.parseDouble(etWeight)) {
                wm.setImgWeight(R.drawable.ic_weight_down);
                Toast.makeText(getApplication(), "Has bajado de peso", Toast.LENGTH_SHORT).show();
            } else if(Double.parseDouble(weightData.get(weightData.size() - 2)) < Double.parseDouble(etWeight)) {
                wm.setImgWeight(R.drawable.ic_weight_up);
                Toast.makeText(getApplication(), "Has subido de peso", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
