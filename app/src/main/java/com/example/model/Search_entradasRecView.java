package com.example.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Search_entradasRecView extends RecyclerView.Adapter<Search_entradasRecView.EntradaViewHolder> implements View.OnClickListener {
    private View.OnClickListener listener;
    private ArrayList<Libros> entradas;

    public Search_entradasRecView(ArrayList<Libros> entradas) {
        this.entradas = entradas;
    }

    public ArrayList<Libros> getDatos() {
        return entradas;
    }

    public void setDatos(ArrayList<Libros> entradas) {
        this.entradas = entradas;
    }

    public static class EntradaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen_entrada;
        private TextView titulo_entrada;
        private TextView volumen_entrada;
        private TextView autor_entrada;
        private TextView publicacion_entrada;
        private TextView precio_entrada;

        public EntradaViewHolder(View itemView) {
            super(itemView);
            imagen_entrada = (ImageView) itemView.findViewById(R.id.imagenEntrada);
            titulo_entrada = (TextView) itemView.findViewById(R.id.tituloEntrada);
            volumen_entrada = (TextView) itemView.findViewById(R.id.volumenEntrada);
            autor_entrada = (TextView) itemView.findViewById(R.id.autorEntrada);
            publicacion_entrada = (TextView) itemView.findViewById(R.id.publicacionEntrada);
            precio_entrada = (TextView) itemView.findViewById(R.id.precioEntrada);
        }

        public void bindEntrada(Libros e) {
            Picasso.get().load(e.getImagen()).into(imagen_entrada);
            titulo_entrada.setText(titulo_entrada.getText() + " " + e.getTitulo());
            volumen_entrada.setText(volumen_entrada.getText() + " " + e.getVolumen());
            autor_entrada.setText(autor_entrada.getText() + " " + e.getAutor());
            publicacion_entrada.setText(publicacion_entrada.getText() + " " + e.getPublicacion());
            precio_entrada.setText(precio_entrada.getText());
        }
    }

    @Override
    public Search_entradasRecView.EntradaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_items_recview, parent, false);
        itemView.setOnClickListener(this);
        Search_entradasRecView.EntradaViewHolder tvh = new Search_entradasRecView.EntradaViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull Search_entradasRecView.EntradaViewHolder holder, int pos) {
        Libros item = entradas.get(pos);

        holder.bindEntrada(item);
    }

    @Override
    public int getItemCount() {
        return entradas.size();
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
