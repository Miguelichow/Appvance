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
 * Clase para realizar el seguimiento de las porciones
 * @author Miguel Prieto Horcajo
 */
public class PortionsTrack extends AppCompatActivity implements SwipeMenuListView.OnMenuItemClickListener {

    SQLiteHelperPortions sqliteHelperP;
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
        setTitle("Registro de porciones");
        setContentView(R.layout.activity_portions_track);

        smlv = findViewById(R.id.lista2);
        smlv.setOnMenuItemClickListener(this);
        sqliteHelperP = new SQLiteHelperPortions(this);

        Cursor c = sqliteHelperP.getPortions();

        datos = new ArrayList<>();

        while(c.moveToNext()){
            datos.add("\t\t" + c.getString(1)
                    + " - " + c.getString(0) + System.getProperty("line.separator")
                    + "\t\t\t- Proteína: " + c.getString(2) + System.getProperty("line.separator")
                    + "\t\t\t- Grasa: " + c.getString(3) + System.getProperty("line.separator")
                    + "\t\t\t- Carbohidratos: " + c.getString(4) + System.getProperty("line.separator"));
        }

        adaptador = new ArrayAdapter<>(this, R.layout.custom_text_swipe, datos);
        smlv.setAdapter(adaptador);

        SwipeMenuCreator creator = new SwipeMenuCreator() {
            /**
             * Controla el borradp de los componentes de la SwipeMenuListView
             * @param menu
             */
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
        sqliteHelperP.delete(position + 1);
        adaptador.notifyDataSetChanged();
        Toast.makeText(this,"Borrado correctamente", Toast.LENGTH_SHORT).show();
        return false;
    }
}
