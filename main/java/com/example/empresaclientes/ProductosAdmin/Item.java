package com.example.empresaclientes.ProductosAdmin;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String nombre;
    private String precio;
    private String cantidad;
    private String foto;
    private boolean isChecked;

    public Item(int imageView, String nombre, String precio, String cantidad,String foto, boolean isChecked) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
        this.foto = foto;
        this.isChecked = isChecked;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    @Override
    public String toString() {
        return "Item{" +
                ", nombre='" + nombre + '\'' +
                ", precio='" + precio + '\'' +
                ", cantidad='" + cantidad + '\'' +
                ", foto='" + foto + '\''+
                ", isChecked=" + isChecked +
                '}';
    }
}
