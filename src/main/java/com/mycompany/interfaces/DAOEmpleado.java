/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

/**
 *
 * @author 52951
 */
import com.mycompany.models.Empleado;
import java.util.List;
public interface DAOEmpleado {
    
    public int contarRegistros() throws Exception;
    
    public void registrar(Empleado emp) throws Exception;

    public void modificar(Empleado emp, String nombre_usuario) throws Exception;

    public List<Empleado> listar(String nombre,String correo,int page, int limit) throws Exception;

    public void eliminar(String nombre_usuario) throws Exception;
}
