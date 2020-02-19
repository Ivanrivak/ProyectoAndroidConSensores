package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.model.Libros;
import com.example.model.Noticia;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class NewsActivity extends AppCompatActivity {
    private ImageView imagen_noticia;
    private TextView titulo_noticia;
    private TextView texto_noticia;
    private ArrayList<Libros> libros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        imagen_noticia = findViewById(R.id.imagen_noticia);
        titulo_noticia = findViewById(R.id.titulo_noticia);
        texto_noticia = findViewById(R.id.texto_noticia);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        Noticia noticia = (Noticia) b.getSerializable("noticia");

        Picasso.get().load(noticia.getImagen()).into(imagen_noticia);
        titulo_noticia.setText(noticia.getTitulo());
        texto_noticia.setText(noticia.getTexto());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i;
        Bundle extras = this.getIntent().getExtras();
        HashMap<String, HashMap<String, HashMap<String, String>>> listado = (HashMap<String, HashMap<String, HashMap<String, String>>>) extras.getSerializable("listado");
        extras = new Bundle();
        extras.putSerializable("listado", listado);

        switch (item.getItemId()) {
            case R.id.inicio:
                extras.putSerializable("libros", libros);
                i = new Intent(NewsActivity.this, HomeActivity.class);
                i.putExtras(extras);
                startActivity(i);
                return true;
            case R.id.lista:
                extras.putSerializable("libros", libros);
                i = new Intent(NewsActivity.this, ListaActivity.class);
                i.putExtras(extras);
                startActivity(i);
                return true;
            case R.id.carrito:
                //i = new Intent(NewsActivity.this, ListaActivity.class);
                //startActivity(i);
                return true;
            case R.id.user:
                extras.putSerializable("libros", libros);
                i = new Intent(NewsActivity.this, UsuarioActivity.class);
                i.putExtras(extras);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
