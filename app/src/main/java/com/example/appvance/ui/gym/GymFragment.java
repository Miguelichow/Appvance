package com.example.appvance.ui.gym;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.appvance.ExercisesList;
import com.example.appvance.R;
import com.example.appvance.Technique;
import com.example.appvance.WorkoutTrack;

/**
 * Clase que maneja el Fragment del apartado Gimnasio
 * @author Miguel Prieto Horcajo
 */
public class GymFragment extends Fragment implements View.OnClickListener {

    Intent intent;
    CardView cvRegister, cvShow, cvTechnique;

    /**
     * Asocia la clase con su vista y relaciona sus componentes
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return Vista de la clase
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_gym, container, false);

        cvRegister = v.findViewById(R.id.cvRegister);
        cvRegister.setOnClickListener(this);
        cvShow = v.findViewById(R.id.cvShow);
        cvShow.setOnClickListener(this);
        cvTechnique = v.findViewById(R.id.cvTechnique);
        cvTechnique.setOnClickListener(this);

        return v;
    }

    /**
     * Controla la función del click sobre los distintos componentes
     * @param view Vista
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvRegister:
                intent = new Intent(getActivity(), ExercisesList.class);
                startActivity(intent);
                break;

            case R.id.cvShow:
                intent = new Intent(getActivity(), WorkoutTrack.class);
                startActivity(intent);
                break;

            case R.id.cvTechnique:
                intent = new Intent(getActivity(), Technique.class);
                startActivity(intent);
                break;
        }
    }
}