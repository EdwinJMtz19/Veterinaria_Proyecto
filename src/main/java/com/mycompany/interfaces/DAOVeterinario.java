/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import com.mycompany.models.Veterinario;
import java.util.List;
/**
 *
 * @author 52951
 */
public interface DAOVeterinario {
    public int contarRegistros() throws Exception ;
    
    public void registrar(Veterinario vet) throws Exception;

    public void modificar(Veterinario vet, String nombre_usuario) throws Exception;

    public List<Veterinario> listar(String nombre,String correo,int page, int limit) throws Exception;

    public void eliminar(String nombre_usuario) throws Exception;
    
    
}
