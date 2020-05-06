package com.example.appvance.ui.recipes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.appvance.Calculator;
import com.example.appvance.R;
import com.example.appvance.Recipes;

/**
 * Clase que maneja el Fragment del apartado Recetas
 * @author Miguel Prieto Horcajo
 */
public class RecipesFragment extends Fragment implements View.OnClickListener {

    Intent intent;
    CardView cvRecipes, cvCalculator;

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
        View v = inflater.inflate(R.layout.fragment_recipes, container, false);

        cvRecipes = v.findViewById(R.id.cvRecipes);
        cvRecipes.setOnClickListener(this);
        cvCalculator = v.findViewById(R.id.cvCalculator);
        cvCalculator.setOnClickListener(this);

        return v;
    }

    /**
     * Controla la función del click sobre los distintos componentes
     * @param v Vista
     */
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.cvRecipes:
                intent = new Intent(getActivity(), Recipes.class);
                startActivity(intent);
                break;

            case R.id.cvCalculator:
                intent = new Intent(getActivity(), Calculator.class);
                startActivity(intent);
                break;
        }
    }
}
