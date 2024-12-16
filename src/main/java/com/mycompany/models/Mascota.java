/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

/**
 *
 * @author 52951
 */
public class Mascota {
    public int id_mascota;
    public String nombre;
    public int id_usuario;
    public String especie;
    public int edad;
    public String color_primario;
    public String detalles;
    public String genero;

    public Mascota(int id_mascota, String nombre, int id_usuario, String especie, int edad, String genero, String color_primario, String detalles) {
        this.id_mascota = id_mascota;
        this.nombre = nombre;
        this.id_usuario = id_usuario;
        this.especie = especie;
        this.edad = edad;
        this.color_primario = color_primario;
        this.detalles = detalles;
        this.genero = genero;
    }

    public Mascota(String nombre, int id_usuario, String especie, int edad, String color_primario, String detalles, String genero) {
        this.nombre = nombre;
        this.id_usuario = id_usuario;
        this.especie = especie;
        this.edad = edad;
        this.color_primario = color_primario;
        this.detalles = detalles;
        this.genero = genero;
    }
    
    

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getColor_primario() {
        return color_primario;
    }

    public void setColor_primario(String color_primario) {
        this.color_primario = color_primario;
    }

    public String getDetalles() {
        return detalles;
    }

    public void setDetalles(String detalles) {
        this.detalles = detalles;
    }

    @Override
    public String toString() {
        return "Mascota{" + "id_mascota=" + id_mascota + ", nombre=" + nombre + ", id_usuario=" + id_usuario + ", especie=" + especie + ", edad=" + edad + ", color_primario=" + color_primario + ", detalles=" + detalles + ", genero=" + genero + '}';
    }

    
    

    
}
