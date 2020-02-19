package com.example.model;

import java.io.Serializable;

public class Libros implements Serializable {
    private int codLibro;
    private String titulo;
    private String publicacion;
    private String autor;
    private String volumen;
    private String imagen;
    private String sinopsis;

    public Libros() {
    }

    public Libros(int codLibro, String titulo, String publicacion, String autor, String volumen, String imagen, String sinopsis) {
        this.codLibro = codLibro;
        this.titulo = titulo;
        this.publicacion = publicacion;
        this.autor = autor;
        this.volumen = volumen;
        this.imagen = imagen;
        this.sinopsis = sinopsis;
    }

    @Override
    public String toString() {
        return "Libros{" +
                "codLibro=" + codLibro +
                ", titulo='" + titulo + '\'' +
                ", publicacion='" + publicacion + '\'' +
                ", autor='" + autor + '\'' +
                ", volumen='" + volumen + '\'' +
                ", imagen='" + imagen + '\'' +
                ", sinopsis='" + sinopsis + '\'' +
                '}';
    }

    public int getCodLibro() {
        return codLibro;
    }

    public void setCodLibro(int codLibro) {
        this.codLibro = codLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
}
