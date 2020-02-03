package com.emproducciones.entradaspapi.UI;

import androidx.recyclerview.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.emproducciones.entradaspapi.R;
import com.emproducciones.entradaspapi.db.entidad.*;
import java.util.ArrayList;
import java.util.List;


public class MyentradasRecyclerViewAdapter extends RecyclerView.Adapter<MyentradasRecyclerViewAdapter.ViewHolder> {

    private List<resumenNoches> entradas;
    private Context context;

    public MyentradasRecyclerViewAdapter(ArrayList<resumenNoches> items, Context context) {
        entradas = items;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_entradas, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.entrada = entradas.get(position);
        int temp = holder.entrada.getTotalEntradasNoche();
        holder.txtNroNoche.setText(holder.entrada.getId()+"");
        holder.txtCantidadEntradas.setText(holder.entrada.getTotalEntradasNoche()+"");
        holder.txtRecaudacion.setText("$ " + (temp*constantes.PRECIO_ENTRADA));
    }

    @Override
    public int getItemCount() {
        return entradas.size();
    }

    public void setNuevasEntradas(List<resumenNoches> nuevasEntradas){
        entradas=nuevasEntradas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView txtNroNoche;
        public final TextView txtCantidadEntradas;
        public final TextView txtRecaudacion;
        public resumenNoches entrada;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            txtNroNoche = view.findViewById(R.id.txtNroNoche);
            txtCantidadEntradas = view.findViewById(R.id.txtCantidadEntradas);
            txtRecaudacion = view.findViewById(R.id.txtValorRecaudacion);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + txtCantidadEntradas.getText() + "'";
        }
    }
}
