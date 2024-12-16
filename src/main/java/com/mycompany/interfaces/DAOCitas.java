/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.interfaces;

/**
 *
 * @author 52951
 */
import java.util.List;
import com.mycompany.models.Citas;
public interface DAOCitas {
    
    
    public void registrar(Citas cit) throws Exception;

    public void modificar(Citas cit) throws Exception;

    public List<Citas> listar(int idCita) throws Exception;
    
    public List<Citas> listar() throws Exception;

    public void eliminar(int id_cita) throws Exception;
}
