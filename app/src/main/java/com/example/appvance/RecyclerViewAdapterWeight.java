package com.example.appvance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

/**
 * Clase adaptadora del RecylcerView para el registro de pesos
 * @author Miguel Prieto Horcajo
 */
public class RecyclerViewAdapterWeight extends RecyclerView.Adapter<RecyclerViewAdapterWeight.ViewHolder> {

    /**
     * Clase que contendrá los datos a mostrar en los item de tipo peso
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvWeight;
        ImageView imageWeight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvWeight = itemView.findViewById(R.id.tvWeight);
            imageWeight = itemView.findViewById(R.id.imageWeight);
        }
    }

    public List <WeightModel> weightList;

    /**
     * Constructor de la clase
     * @param weightList Lista de tipo WeightModel
     */
    public RecyclerViewAdapterWeight(List<WeightModel> weightList) {
        this.weightList = weightList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weight, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDate.setText(weightList.get(position).getFecha());
        holder.tvWeight.setText(weightList.get(position).getPeso());
        holder.imageWeight.setImageResource(weightList.get(position).getImgWeight());
    }

    /**
     * Devuelve el tamaño de la lista de pesos
     * @return Tamaño lista de pesos
     */
    @Override
    public int getItemCount() {
        return weightList.size();
    }
}
