package com.example.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FirebaseMethods {
    private DatabaseReference dbR;

    public HashMap<String, HashMap<String, String>> obtenerAlmacen() {
        dbR = FirebaseDatabase.getInstance().getReference().child("Almacen");
        final HashMap<String, HashMap<String, String>> listaAlmacen = new HashMap<String, HashMap<String, String>>();

        dbR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listaAlmacen.put(dataSnapshot.getKey(), new HashMap<String, String>());
                for (DataSnapshot valores : dataSnapshot.getChildren()) {
                    listaAlmacen.get(dataSnapshot.getKey()).put(valores.getKey(), valores.getValue().toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return listaAlmacen;

    }

    public HashMap<String, HashMap<String, String>> obtenerAutores() {
        dbR = FirebaseDatabase.getInstance().getReference().child("Autores");
        final HashMap<String, HashMap<String, String>> listaAutores = new HashMap<String, HashMap<String, String>>();
        dbR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listaAutores.put(dataSnapshot.getKey(), new HashMap<String, String>());
                for (DataSnapshot valores : dataSnapshot.getChildren()) {
                    listaAutores.get(dataSnapshot.getKey()).put(valores.getKey(), valores.getValue().toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return listaAutores;
    }

    public HashMap<String, HashMap<String, String>> obtenerLibros() {
        dbR = FirebaseDatabase.getInstance().getReference().child("libros");
        final HashMap<String, HashMap<String, String>> listaLibros = new HashMap<String, HashMap<String, String>>();
        dbR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listaLibros.put(dataSnapshot.getKey(), new HashMap<String, String>());
                for (DataSnapshot valores : dataSnapshot.getChildren()) {
                    listaLibros.get(dataSnapshot.getKey()).put(valores.getKey(), valores.getValue().toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return listaLibros;
    }

    public HashMap<String, HashMap<String, String>> obtenerNoticias() {
        dbR = FirebaseDatabase.getInstance().getReference().child("Noticias");
        final HashMap<String, HashMap<String, String>> listaNoticias = new HashMap<String, HashMap<String, String>>();

        dbR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listaNoticias.put(dataSnapshot.getKey(), new HashMap<String, String>());
                for (DataSnapshot valores : dataSnapshot.getChildren()) {
                    listaNoticias.get(dataSnapshot.getKey()).put(valores.getKey(), valores.getValue().toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        return listaNoticias;
    }

    public HashMap<String,HashMap<String,String>> obtenerCompras(){
        dbR = FirebaseDatabase.getInstance().getReference().child("Compras");
        final HashMap<String, HashMap<String, String>> listaCompras = new HashMap<String, HashMap<String, String>>();
        dbR.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                listaCompras.put(dataSnapshot.getKey(), new HashMap<String, String>());
                for (DataSnapshot valores : dataSnapshot.getChildren()) {
                    listaCompras.get(dataSnapshot.getKey()).put(valores.getKey(), valores.getValue().toString());
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return listaCompras;
    }
}
