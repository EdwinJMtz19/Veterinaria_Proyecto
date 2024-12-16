/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author 52951
 */
public class Citas {
    public int id_cita;
    public int id_mascota;
    public int id_usuario;
    
    public LocalDate fecha;
    public LocalTime hora;
    public String nombre_usuario_vet;
    public String estado;

    public Citas(int id_cita, int id_mascota, int id_usuario, LocalDate fecha, LocalTime hora, String nombre_usuario_vet, String estado) {
        this.id_cita = id_cita;
        this.id_mascota = id_mascota;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.nombre_usuario_vet = nombre_usuario_vet;
        this.estado = estado;
    }

    public Citas(int id_mascota, int id_usuario, LocalDate fecha, LocalTime hora, String nombre_usuario_vet, String estado) {
        this.id_mascota = id_mascota;
        this.id_usuario = id_usuario;
        this.fecha = fecha;
        this.hora = hora;
        this.nombre_usuario_vet = nombre_usuario_vet;
        this.estado = estado;
    }

   

    public String getNombre_usuario_vet() {
        return nombre_usuario_vet;
    }

    public void setNombre_usuario_vet(String nombre_usuario_vet) {
        this.nombre_usuario_vet = nombre_usuario_vet;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Citas{" + "id_cita=" + id_cita + ", id_mascota=" + id_mascota + ", id_usuario=" + id_usuario + ", fecha=" + fecha + ", hora=" + hora + '}';
    }
    
    
    
}
