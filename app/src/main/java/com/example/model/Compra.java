package com.example.model;

public class Compra {
    private Integer idCompra;
    private String fecha;
    private String idUsuario;
    private int items;

    public Compra() {
    }

    public Compra(Integer idCompra, String fecha, String idUsuario, int items) {
        this.idCompra = idCompra;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.items = items;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "idCompra=" + idCompra +
                ", fecha='" + fecha + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", items=" + items +
                '}';
    }

    public Integer getIdCompra() {
        return idCompra;
    }

    public void setIdCompra(Integer idCompra) {
        this.idCompra = idCompra;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }
}
