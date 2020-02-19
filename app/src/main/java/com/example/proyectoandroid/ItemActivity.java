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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemActivity extends AppCompatActivity {
    private ArrayList<Libros> libros;
    private ImageView imagen_item;
    private TextView titulo_item;
    private TextView volumen_item;
    private TextView autor_item;
    private TextView publicacion_item;
    private TextView sinopsis_item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        imagen_item = findViewById(R.id.imagen_item);
        titulo_item = findViewById(R.id.titulo_item);
        volumen_item = findViewById(R.id.volumen_item);
        autor_item = findViewById(R.id.autor_item);
        publicacion_item = findViewById(R.id.publicacion_item);
        sinopsis_item = findViewById(R.id.sinopsis_item);

        Intent i = getIntent();
        Bundle b = i.getExtras();

        Libros libro = (Libros) b.getSerializable("libro");

        Picasso.get().load(libro.getImagen()).into(imagen_item);
        titulo_item.setText(titulo_item.getText() + " " + libro.getTitulo());
        volumen_item.setText(volumen_item.getText() + " " + libro.getVolumen());
        autor_item.setText(autor_item.getText() + " " + libro.getAutor());
        publicacion_item.setText(publicacion_item.getText() + " " + libro.getPublicacion());
        sinopsis_item.setText(libro.getSinopsis());
        Log.i("item titulo", titulo_item.getText().toString());
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
                i = new Intent(ItemActivity.this, HomeActivity.class);
                i.putExtras(extras);
                startActivity(i);
                return true;
            case R.id.lista:
                extras.putSerializable("libros", libros);
                i = new Intent(ItemActivity.this, ListaActivity.class);
                i.putExtras(extras);
                startActivity(i);
                return true;
            case R.id.carrito:
                Log.i("ActionBar", "Settings!");
                return true;
            case R.id.user:
                extras.putSerializable("libros", libros);
                i = new Intent(ItemActivity.this, UsuarioActivity.class);
                i.putExtras(extras);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
