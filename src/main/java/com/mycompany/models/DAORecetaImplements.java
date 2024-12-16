/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import com.mycompany.db.Database;
import com.mycompany.interfaces.DAOReceta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 52951
 */
public class DAORecetaImplements extends Database implements DAOReceta{
    
    public List<Receta> listarReceta() throws Exception{
    List<Receta> list = new ArrayList<>();
    try {
        String query = "SELECT * FROM receta";
        this.Conectar();
        

        try (PreparedStatement st = this.conexion.prepareStatement(query); ResultSet rs = st.executeQuery()) {
            
            while (rs.next()) {
                Receta receta = new Receta(
                        rs.getInt("id_receta"),
                        rs.getString("detalles"),
                        rs.getInt("id_mascota")
                );
                list.add(receta);
            }
        }
    } catch (ClassNotFoundException | SQLException e) {
        throw e;
    } finally {
        this.Cerrar();
    }

    return list;
}
    
    
    public void registrar(Receta rec) throws Exception {
    try {
        this.Conectar();
        try (PreparedStatement st = this.conexion.prepareStatement(
                "INSERT INTO receta(detalles, id_mascota) VALUES(?, ?);")) {
            st.setString(1, rec.getDetalles());
            st.setInt(2, rec.getIdMascota());
            
            st.executeUpdate();
        }
    } catch (ClassNotFoundException | SQLException e) {
        throw e;
    } finally {
        this.Cerrar();
    }
}

    
    }

    
    

