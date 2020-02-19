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

import java.util.ArrayList;

public class Home_noticiasRecView extends RecyclerView.Adapter<Home_noticiasRecView.NoticiaViewHolder> implements View.OnClickListener {
    private View.OnClickListener listener;
    private ArrayList<Noticia> noticias;

    public Home_noticiasRecView(ArrayList<Noticia> noticia) {
        this.noticias = noticia;
    }

    public ArrayList<Noticia> getDatos() {
        return noticias;
    }

    public void setDatos(ArrayList<Noticia> noticia) {
        this.noticias = noticia;
    }

    public static class NoticiaViewHolder extends RecyclerView.ViewHolder {
        private ImageView imagen_noticia;
        private TextView titulo_noticia;
        private TextView texto_noticia;

        public NoticiaViewHolder(View itemView) {
            super(itemView);
            imagen_noticia = (ImageView) itemView.findViewById(R.id.imagen_noticia);
            titulo_noticia = (TextView) itemView.findViewById(R.id.titulo_noticia);
            texto_noticia = (TextView) itemView.findViewById(R.id.texto_noticia);
        }

        public void bindTitular(Noticia n) {
            Picasso.get().load(n.getImagen()).into(imagen_noticia);
            titulo_noticia.setText(n.getTitulo());
            texto_noticia.setText(n.getTexto());
        }
    }

    @Override
    public Home_noticiasRecView.NoticiaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_noticias_recview, parent, false);
        itemView.setOnClickListener(this);
        Home_noticiasRecView.NoticiaViewHolder tvh = new Home_noticiasRecView.NoticiaViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(@NonNull Home_noticiasRecView.NoticiaViewHolder holder, int pos) {
        Noticia item = noticias.get(pos);

        holder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return noticias.size();
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
