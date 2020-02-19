package com.example.proyectoandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.example.model.Compra;
import com.example.model.Libros;
import com.example.model.UsuarioComprasRecView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UsuarioActivity extends AppCompatActivity {
    private RecyclerView listadoCompras;
    private HashMap<String, HashMap<String, HashMap<String, String>>> listado;
    private ArrayList<Libros> libros;
    private HashMap<String, HashMap<String, String>> listaCompras;
    private ArrayList<Compra> listaCompraUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        Bundle extras = this.getIntent().getExtras();
        listado = (HashMap<String, HashMap<String, HashMap<String, String>>>) extras.getSerializable("listado");
        libros = (ArrayList<Libros>) extras.getSerializable("libros");

        listaCompraUsuario = new ArrayList<Compra>();
        listaCompras = listado.get("listaCompras");
        for (Map.Entry<String, HashMap<String, String>> compras : listaCompras.entrySet()) {
            String idUsuario = listaCompras.get(compras.getKey()).get("idUsuario");
            String uid = user.getUid();
            if (idUsuario.equals(user.getUid())) {
                Compra compra = new Compra();
                Integer numeroLibros = obtenerItems(listaCompras.get(compras.getKey()).get("items"));
                compra.setFecha(listaCompras.get(compras.getKey()).get("fecha"));
                compra.setItems(numeroLibros);
                compra.setIdUsuario(idUsuario);
                compra.setIdCompra(Integer.parseInt(listaCompras.get(compras.getKey()).get("idcompra")));
                listaCompraUsuario.add(compra);
            }
        }
        /*
        formato fecha
        String pattern = "E, d MMM,yyyy HH:mm:ss z";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        */
        listadoCompras = (RecyclerView) findViewById(R.id.usuario_compras_recview);
        listadoCompras.setHasFixedSize(true);
        final UsuarioComprasRecView listaEntrada = new UsuarioComprasRecView(listaCompraUsuario);

        listadoCompras.setAdapter(listaEntrada);
        listadoCompras.setLayoutManager(
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

    }

    private Integer obtenerItems(String items) {
        Integer libroscompra = 0;
        String[] listaitems = items.split(",");
        for (int i = 0; i < listaitems.length; i++) {
            libroscompra++;
        }
        return libroscompra;
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
        listado = (HashMap<String, HashMap<String, HashMap<String, String>>>) extras.getSerializable("listado");
        extras = new Bundle();
        extras.putSerializable("listado", listado);

        switch (item.getItemId()) {
            case R.id.inicio:
                i = new Intent(UsuarioActivity.this, HomeActivity.class);
                i.putExtras(extras);
                startActivity(i);
                return true;
            case R.id.lista:
                extras.putSerializable("libros", libros);
                i = new Intent(UsuarioActivity.this, ListaActivity.class);
                i.putExtras(extras);
                startActivity(i);
                return true;
            case R.id.carrito:
                //i = new Intent(NewsActivity.this, ListaActivity.class);
                //startActivity(i);
                return true;
            case R.id.user:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
