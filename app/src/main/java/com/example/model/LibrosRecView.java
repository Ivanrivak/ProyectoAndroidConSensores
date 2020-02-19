package com.example.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectoandroid.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class LibrosRecView extends RecyclerView.Adapter<LibrosRecView.LibrosViewHolder> implements View.OnClickListener{

    private View.OnClickListener listener;
    private ArrayList<Libros> libros;

    public LibrosRecView(ArrayList<Libros> libros){ this.libros = libros;}

    public ArrayList<Libros> getDatos(){return libros;}

    public void setDatos(ArrayList<Libros> libros){this.libros = libros;}

    public static class LibrosViewHolder extends RecyclerView.ViewHolder{

        private ImageView imagen_libro;
        private TextView titulo_libro;
        private TextView volumen_libro;

        public LibrosViewHolder(View itemView){
            super(itemView);
            imagen_libro = (ImageView) itemView.findViewById(R.id.imagen_entrada);
            titulo_libro = (TextView) itemView.findViewById(R.id.titulo_entrada);
            volumen_libro = (TextView) itemView.findViewById(R.id.volumen_entrada);

        }

        public void bindLibro(Libros e){
            Picasso.get().load(e.getImagen()).into(imagen_libro);
            titulo_libro.setText(e.getTitulo());
            volumen_libro.setText("Volumen "+e.getVolumen());
        }
    }
    @Override
    public LibrosViewHolder onCreateViewHolder(ViewGroup parent,int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_entradas_recview,parent,false);
        itemView.setOnClickListener(this);
        LibrosViewHolder libViewHold = new LibrosViewHolder(itemView);

        return libViewHold;
    }
    @Override
    public void onBindViewHolder(LibrosViewHolder holder,int pos){

        Libros libro = libros.get(pos);
        holder.bindLibro(libro);
    }
    @Override
    public int getItemCount() {return libros.size();}


    public void setOnClickListener(View.OnClickListener listener) {this.listener = listener;}
    @Override
    public void onClick(View view){
        if(listener != null)
            listener.onClick(view);

    }
}
