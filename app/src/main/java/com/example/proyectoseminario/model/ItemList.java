package com.example.proyectoseminario.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ItemList implements Serializable {
    @SerializedName("title")
    private String titulo;
    @SerializedName("description")
    private String descripcion;
    @SerializedName("photo")
    private String imagen;

    public ItemList(String titulo, String descripcion, String imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public String getImagen() {
        return imagen;
    }


}
