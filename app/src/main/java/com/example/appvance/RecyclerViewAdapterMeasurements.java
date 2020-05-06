package com.example.appvance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Clase adaptadora del RecylcerView para el registro de medidas
 * @author Miguel Prieto Horcajo
 */
public class RecyclerViewAdapterMeasurements extends RecyclerView.Adapter<RecyclerViewAdapterMeasurements.ViewHolder> {

    /**
     * Clase que contendrá los datos a mostrar en los item de tipo medidas
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDate, tvHip;
        ImageView imageWeight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDate = itemView.findViewById(R.id.tvDate);
            tvHip = itemView.findViewById(R.id.tvHip);
            imageWeight = itemView.findViewById(R.id.imageWeight);
        }
    }

    public List <MeasurementsModel> hipList;

    /**
     * Constructor de la clase
     * @param hipList Lista de tipo MeasurementsModel
     */
    public RecyclerViewAdapterMeasurements(List<MeasurementsModel> hipList) {
        this.hipList = hipList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_measurements, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvDate.setText(hipList.get(position).getFecha());
        holder.tvHip.setText(hipList.get(position).getPeso());
        holder.imageWeight.setImageResource(hipList.get(position).getImgWeight());
    }

    /**
     * Devuelve el tamaño de la lista de medidas
     * @return Tamaño lista de medidas
     */
    @Override
    public int getItemCount() {
        return hipList.size();
    }
}
