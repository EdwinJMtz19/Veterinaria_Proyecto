/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

import java.util.List;
import com.mycompany.models.Cliente;
/**
 *
 * @author 52951
 */
public interface DAOCliente {
    
    public int contarRegistros() throws Exception ;
    
    public void registrar(Cliente cli) throws Exception;

    public void modificar(Cliente cli) throws Exception;

    public List<Cliente> listar(String nombre,String correo,int page, int limit) throws Exception;
    
    public List<Cliente> listar(int id_usuario) throws Exception;
    
    public void eliminar(int id_usuario) throws Exception;
}
