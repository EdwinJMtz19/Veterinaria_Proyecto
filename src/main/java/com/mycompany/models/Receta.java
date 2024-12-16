/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

/**
 *
 * @author 52951
 */
public class Receta {
    public int idReceta;
    public String detalles;
    public int idMascota;

    public Receta(String detalles, int idMascota) {
        this.detalles = detalles;
        this.idMascota = idMascota;
    }

    public Receta(int idReceta, String detalles, int idMascota) {
        this.idReceta = idReceta;
        this.detalles = detalles;
        this.idMascota = idMascota;
    }

    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }
    
    
}
