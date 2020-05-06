package com.example.appvance;

import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import java.util.ArrayList;

/**
 * Clase para realizar el seguimiento de los entrenamientos
 * @author Miguel Prieto Horcajo
 */
public class WorkoutTrack extends AppCompatActivity implements SwipeMenuListView.OnMenuItemClickListener {

    SQLiteHelperExercises sqliteHelperEx;
    SwipeMenuListView smlv;
    ArrayAdapter<String> adaptador;
    ArrayList<String> datos;

    /**
     * Asocia la clase con su vista y relaciona los componentes
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Diario de entrenamientos");
        setContentView(R.layout.activity_workout_track);

        smlv = findViewById(R.id.lista);
        smlv.setOnMenuItemClickListener(this);

        sqliteHelperEx = new SQLiteHelperExercises(this);

        Cursor c = sqliteHelperEx.getExercises();

        datos = new ArrayList<>();

        while(c.moveToNext()){
            if(c.getString(1).equals("Correr") || c.getString(1).equals("Andar") || c.getString(1).equals("Bici")
                    || c.getString(1).equals("Natación") || c.getString(1).equals("Remo") || c.getString(1).equals("Hollow body")) {
                datos.add("\t\t- " + c.getString(1) + "\t- " + c.getString(0) + System.getProperty("line.separator")
                        + "\t\t\t" + c.getString(2) + "h. " + c.getString(3) + "min."
                        + System.getProperty("line.separator"));
            } else {
                datos.add("\t\t- " + c.getString(1)
                        + " - " + c.getString(0) + System.getProperty("line.separator")
                        + "\t\t\t Sets: " + c.getString(2) + " x Reps: " + c.getString(3)
                        + System.getProperty("line.separator"));
            }
        }

        adaptador = new ArrayAdapter<>(this, R.layout.custom_text_swipe, datos);
        smlv.setAdapter(adaptador);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
                deleteItem.setBackground(new ColorDrawable(getResources().getColor(R.color.red)));
                deleteItem.setWidth(250);
                deleteItem.setIcon(R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };
        smlv.setMenuCreator(creator);
    }

    /**
     * Controla el borrado de los componentes del ArrayList que se muestra en la SwipeMenuListView
     * Borra también el componente de la BD
     * @param position Posición del elemento a borrar
     * @param menu
     * @param index Índice
     * @return Verdadero o falso dependiendo de su resultado
     */
    @Override
    public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
        datos.remove(position);
        sqliteHelperEx.delete(position + 1);
        adaptador.notifyDataSetChanged();
        Toast.makeText(this,"Borrado correctamente", Toast.LENGTH_SHORT).show();
        return false;
    }
}
