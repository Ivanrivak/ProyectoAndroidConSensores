package com.example.model;

import java.io.Serializable;

public class Noticia implements Serializable {
    private int codnoticia;
    private String imagen;
    private String titulo;
    private String texto;

    public Noticia(int codnoticia, String imagen, String titulo, String texto) {
        this.codnoticia = codnoticia;
        this.imagen = imagen;
        this.titulo = titulo;
        this.texto = texto;
    }

    public int getCodnoticia() {
        return codnoticia;
    }

    public String getImagen() {
        return imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTexto() {
        return texto;
    }
}
