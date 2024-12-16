/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import com.mycompany.db.Database;
import com.mycompany.interfaces.DAOCitas;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 52951
 */
public class DAOCitasImplements extends Database implements DAOCitas {

    public void registrar(Citas cit) throws Exception {
        try {
            this.Conectar();
            try (PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO citas(id_mascota, id_usuario, fecha, hora,nombre_usuario_vet,estado) VALUES(?,?,?, ?, ?, ?);")) {
                st.setInt(1, cit.getId_mascota());
                st.setInt(2, cit.getId_usuario());
                
// Convierte LocalDate a java.sql.Date
                st.setDate(3, java.sql.Date.valueOf(cit.getFecha()));

// Convierte LocalTime a java.sql.Time
                st.setTime(4, java.sql.Time.valueOf(cit.getHora()));
                st.setString(5, cit.getNombre_usuario_vet());
                st.setString(6, cit.getEstado());
                st.executeUpdate();
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void modificar(Citas cit) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE citas SET id_mascota= ?, id_usuario = ?, fecha = ?, hora = ?, estado = ? WHERE id_cita =  ?;");
            st.setInt(1, cit.getId_mascota());
            st.setInt(2, cit.getId_usuario());

// Convierte LocalDate a java.sql.Date
            st.setDate(3, java.sql.Date.valueOf(cit.getFecha()));

// Convierte LocalTime a java.sql.Time
            st.setTime(4, java.sql.Time.valueOf(cit.getHora()));

            st.setString(5, cit.getEstado());
            st.setInt(6, cit.getId_cita());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Citas> listar() throws Exception {
        this.Conectar();
        List<Citas> list = new ArrayList();
        String query = "SELECT * FROM citas;";

        try {
            PreparedStatement st = this.conexion.prepareStatement(query);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Citas cit = new Citas(
                        rs.getInt("id_cita"),
                        rs.getInt("id_mascota"), // Obtiene un int
                        rs.getInt("id_usuario"), // Obtiene un int
                        rs.getDate("fecha").toLocalDate(), // Convierte java.sql.Date a LocalDate
                        rs.getTime("hora").toLocalTime(), 
   // Convierte java.sql.Time a LocalTime
                        rs.getString("nombre_usuario_vet"),
                        rs.getString("estado") // Obtiene un String
                );
                list.add(cit);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return list;
    }

    public List<Citas> listar(int idCita) throws Exception {
        this.Conectar();
        List<Citas> list = new ArrayList();
        String query = "SELECT * FROM citas WHERE id_cita = ?";

        try {
            PreparedStatement st = this.conexion.prepareStatement(query);
            st.setInt(1, idCita);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Citas cit = new Citas(
                        rs.getInt("id_mascota"), // Obtiene un int
                        rs.getInt("id_usuario"), // Obtiene un int

                        // Cambiar getObject para fechas y horas a métodos específicos:
                        rs.getDate("fecha").toLocalDate(), // Convierte java.sql.Date a LocalDate
                        rs.getTime("hora").toLocalTime(), // Convierte java.sql.Time a LocalTime
                         rs.getString("nombre_usuario_vet"),
                        rs.getString("estado") // Obtiene un String
                );
                list.add(cit);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return list;
    }

    public void eliminar(int id_cita) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "DELETE FROM citas WHERE id_cita = ?"
            );
            st.setInt(1, id_cita);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

}
