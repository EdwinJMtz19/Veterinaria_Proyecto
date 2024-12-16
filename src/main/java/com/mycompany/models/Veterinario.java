/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

/**
 *
 * @author 52951
 */
public class Veterinario {
    public String nombre_usuario;
    public String nombre;
    public String correo;    
    public String contraseña;

    public Veterinario( String nombre, String correo, String contraseña) {
        String [] nom = nombre.split(" "); 
        String nombreUsuario =nom[0].toLowerCase()+nom[1].toLowerCase().charAt(0)+nom[2].toLowerCase().charAt(0)+"_vet";
        nombre_usuario = nombreUsuario.toLowerCase();
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        
    }
            

    

    public String getNombre_usuario() {
        return nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @Override
    public String toString() {
        return "Veterinario{" + "nombre_usuario=" + nombre_usuario + ", nombre=" + nombre + ", correo=" + correo + ", contrase\u00f1a=" + contraseña + '}';
    }

   
   
    
    
    
    
}
