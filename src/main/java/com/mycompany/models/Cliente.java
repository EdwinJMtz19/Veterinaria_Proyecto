/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

/**
 *
 * @author 52951
 */
public class Cliente {
    public String nombre;
    public int id_usuario;
    public String correo;

    public Cliente(String nombre, int id_usuario, String correo) {
        this.nombre = nombre;
        this.id_usuario = id_usuario;
        this.correo = correo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", id_usuario=" + id_usuario + ", correo=" + correo + '}';
    }
    
    
}
