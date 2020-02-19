package com.example.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyectoandroid.R;

import java.util.ArrayList;

public class UsuarioComprasRecView extends RecyclerView.Adapter<UsuarioComprasRecView.ComprasViewHolder> implements View.OnClickListener {
    private View.OnClickListener listener;
    private ArrayList<Compra> comprasUsuario;

    public UsuarioComprasRecView(ArrayList<Compra> entradas) {
        this.comprasUsuario = entradas;
    }

    public ArrayList<Compra> getDatos() {
        return comprasUsuario;
    }

    public void setDatos(ArrayList<Compra> entradas) {
        this.comprasUsuario = entradas;
    }

    public static class ComprasViewHolder extends RecyclerView.ViewHolder {
        private TextView fecha_compra;
        private TextView numero_libros;

        public ComprasViewHolder(View itemView) {
            super(itemView);
            fecha_compra = (TextView) itemView.findViewById(R.id.fecha_compra);
            numero_libros = (TextView) itemView.findViewById(R.id.numero_libros);
        }

        public void bindEntrada(Compra e) {
            fecha_compra.setText(e.getFecha());
            numero_libros.setText("\t\t\tlibros comprados: "+Integer.toString(e.getItems()));
        }
    }

    @Override
    public ComprasViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.usuario_compras_recview, parent, false);
        itemView.setOnClickListener(this);
        ComprasViewHolder tvh = new ComprasViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(ComprasViewHolder holder, int pos) {
        Compra item = comprasUsuario.get(pos);

        holder.bindEntrada(item);
    }

    @Override
    public int getItemCount() {
        return comprasUsuario.size();
    }

    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener != null)
            listener.onClick(view);
    }
}
