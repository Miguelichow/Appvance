package com.example.appvance.ui.diet;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.appvance.Explanation;
import com.example.appvance.Portions;
import com.example.appvance.R;
import com.example.appvance.RegisterPortions;

/**
 * Clase que maneja el Fragment del apartado Dieta
 * @author Miguel Prieto Horcajo
 */
public class DietFragment extends Fragment implements View.OnClickListener {

    Intent intent;
    CardView cvRequirements, cvPortions, cvRegisterPortions;

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
        View v = inflater.inflate(R.layout.fragment_dieta, container, false);

        cvRequirements = v.findViewById(R.id.cvRequirements);
        cvRequirements.setOnClickListener(this);
        cvPortions = v.findViewById(R.id.cvPortions);
        cvPortions.setOnClickListener(this);
        cvRegisterPortions = v.findViewById(R.id.cvRegisterPortions);
        cvRegisterPortions.setOnClickListener(this);

        return v;
    }

    /**
     * Controla la función del click sobre los distintos componentes
     * @param view Vista
     */
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cvRequirements:
                intent = new Intent(getActivity(), Explanation.class);
                startActivity(intent);
                break;

            case R.id.cvPortions:
                intent = new Intent(getActivity(), Portions.class);
                startActivity(intent);
                break;

            case R.id.cvRegisterPortions:
                intent = new Intent(getActivity(), RegisterPortions.class);
                startActivity(intent);
                break;
        }
    }
}