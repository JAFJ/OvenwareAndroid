package com.example.pandroid.json;

import java.io.Serializable;

public class Jsonsito implements Serializable{

    /*variables*/

    private String usuario;
    private String nombre;
    private String contraseña;
    private String correoelec;
    private Boolean tipopago;
    private String fecha;
    private String numerotel;
    private String estado;

    private String[] clien_vend;
    private Boolean booleador;

    /*Publics*/

    public Boolean getBooleador() {
        return booleador;
    }

    public void setBooleador(Boolean booleador) {
        this.booleador = booleador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    public Jsonsito() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getCorreoelec() {
        return correoelec;
    }

    public void setCorreoelec(String correoelec) {
        this.correoelec = correoelec;
    }

    public String[] getClien_vend() {
        return clien_vend;
    }

    public void setClien_vend(String[] clien_vend) {
        this.clien_vend = clien_vend;
    }

    public Boolean getTipopago() {
        return tipopago;
    }

    public void setTipopago(Boolean tipopago) {
        this.tipopago = tipopago;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNumerotel() {
        return numerotel;
    }

    public void setNumerotel(String numerotel) {
        this.numerotel = numerotel;
    }

}
