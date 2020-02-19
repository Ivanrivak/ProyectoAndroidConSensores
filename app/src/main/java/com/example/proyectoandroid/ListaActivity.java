package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.model.Libros;
import com.example.model.Search_entradasRecView;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaActivity extends AppCompatActivity {
    private RecyclerView listaBusqueda;
    private ArrayList<Libros> libros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Bundle extras = this.getIntent().getExtras();
        libros = (ArrayList<Libros>) extras.getSerializable("libros");
        listaBusqueda = (RecyclerView) findViewById(R.id.search_items_recview);
        listaBusqueda.setHasFixedSize(true);
        final Search_entradasRecView listaEntrada = new Search_entradasRecView(libros);

        listaEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsada la entrada " + listaBusqueda.getChildAdapterPosition(v));

            }
        });
        listaBusqueda.setAdapter(listaEntrada);
        listaBusqueda.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
        HashMap<String,HashMap<String, HashMap<String, String>>> listado = (HashMap<String, HashMap<String, HashMap<String, String>>>) extras.getSerializable("listado");
        extras = new Bundle();
        extras.putSerializable("listado", listado);
        switch (item.getItemId()) {
            case R.id.inicio:
                extras.putSerializable("libros", libros);
                i = new Intent(ListaActivity.this, HomeActivity.class);
                i.putExtras(extras);
                startActivity(i);
                return true;
            case R.id.lista:
                return true;
            case R.id.carrito:
                Log.i("ActionBar", "Settings!");
                return true;
            case R.id.user:
                extras.putSerializable("libros", libros);
                i = new Intent(ListaActivity.this, UsuarioActivity.class);
                i.putExtras(extras);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
