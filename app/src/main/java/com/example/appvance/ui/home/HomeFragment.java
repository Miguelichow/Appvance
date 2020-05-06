package com.example.appvance.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.appvance.ExercisesList;
import com.example.appvance.PortionsTrack;
import com.example.appvance.R;
import com.example.appvance.RegisterPortions;
import com.example.appvance.WorkoutTrack;
import com.github.clans.fab.FloatingActionButton;

/**
 * Clase que maneja el Fragment del apartado Home
 * @author Miguel Prieto Horcajo
 */
public class HomeFragment extends Fragment implements View.OnClickListener {

    Button btnDiet, btnWorkout, btnDiet2, btnWorkout2;
    FloatingActionButton fbaContact, fbaShare;
    Intent intent;
    String shareBody, shareSub;

    /**
     * Asocia la clase con su vista y relaciona sus componentes
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return Vista de la clase
     */
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        fbaContact = v.findViewById(R.id.fbaContact);
        fbaContact.setOnClickListener(this);
        fbaShare = v.findViewById(R.id.fbaShare);
        fbaShare.setOnClickListener(this);
        btnDiet = v.findViewById(R.id.btnDiet);
        btnDiet.setOnClickListener(this);
        btnWorkout = v.findViewById(R.id.btnWorkout);
        btnWorkout.setOnClickListener(this);
        btnDiet2 = v.findViewById(R.id.btnDiet2);
        btnDiet2.setOnClickListener(this);
        btnWorkout2 = v.findViewById(R.id.btnWorkout2);
        btnWorkout2.setOnClickListener(this);

        return v;
    }

    /**
     * Controla la función del click sobre los distintos componentes
     * @param view Vista
     */
    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.fbaContact:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/email");
                String[] s = {"appvance@contacto.com"};
                intent.putExtra(Intent.EXTRA_EMAIL, s);
                intent.putExtra(Intent.EXTRA_SUBJECT,"Posible mejora de Appvance");
                intent.putExtra(Intent.EXTRA_TEXT, "Hola Miguel, te escribo sobre Appvance");
                startActivity(Intent.createChooser(intent, "Contactar a través de:"));
                break;

            case R.id.fbaShare:
                intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                shareBody = "Compartir Appvance";
                shareSub = "Compartir aplicación Appvance";
                intent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                intent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(intent, "Compartir a través de:"));
                break;

            case R.id.btnDiet:
                intent = new Intent(getActivity(), RegisterPortions.class);
                startActivity(intent);
                break;

            case R.id.btnWorkout:
                intent = new Intent(getActivity(), ExercisesList.class);
                startActivity(intent);
                break;

            case R.id.btnDiet2:
                intent = new Intent(getActivity(), PortionsTrack.class);
                startActivity(intent);
                break;

            case R.id.btnWorkout2:
                intent = new Intent(getActivity(), WorkoutTrack.class);
                startActivity(intent);
                break;
        }
    }
}