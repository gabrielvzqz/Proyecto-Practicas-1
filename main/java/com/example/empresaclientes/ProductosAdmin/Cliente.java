package com.example.empresaclientes.ProductosAdmin;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String id;
    private String nombre;
    private String empresa;
    private String telefono;
    private String dniLetra;
    private String comunidad;
    private String provincia;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDniLetra() {
        return dniLetra;
    }

    public void setDniLetra(String dniLetra) {
        this.dniLetra = dniLetra;
    }

    public String getComunidad() {
        return comunidad;
    }

    public void setComunidad(String comunidad) {
        this.comunidad = comunidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Cliente(String id, String nombre, String empresa, String telefono, String dniLetra, String comunidad, String provincia) {
        this.id = id;
        this.nombre = nombre;
        this.empresa = empresa;
        this.telefono = telefono;
        this.dniLetra = dniLetra;
        this.comunidad = comunidad;
        this.provincia = provincia;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", empresa='" + empresa + '\'' +
                ", telefono='" + telefono + '\'' +
                ", dniLetra='" + dniLetra + '\'' +
                ", comunidad='" + comunidad + '\'' +
                ", provincia='" + provincia + '\'' +
                '}';
    }
}
