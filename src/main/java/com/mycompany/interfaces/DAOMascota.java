/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

/**
 *
 * @author 52951
 */
import com.mycompany.models.Mascota;
import java.util.List;
public interface DAOMascota {
    
    public int contarRegistros() throws Exception ;
    
    public void registrar(Mascota mas) throws Exception;

    public void modificar(Mascota mas) throws Exception;

    public List<Mascota> listar(String nombre, int page, int limit) throws Exception;
    
    public Mascota regresarMascota (int idMascota) throws Exception;

    public void eliminar( int id_mascota) throws Exception;
}
