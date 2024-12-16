/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 52951
 */
public class DAOMascotaImplements extends com.mycompany.db.Database implements com.mycompany.interfaces.DAOMascota {

    public void modificar(Mascota mas) throws Exception {
        String query = "UPDATE mascota SET nombre = ?, especie = ?, edad = ?, genero = ?, color_primario = ?, detalles = ? WHERE id_mascota = ?;";
        this.Conectar();
        PreparedStatement st = this.conexion.prepareStatement(query);
        st.setString(1, mas.getNombre());
        st.setString(2, mas.getEspecie());
        st.setInt(3, mas.getEdad());
        st.setString(4, mas.getGenero());
        st.setString(5, mas.getColor_primario());
        st.setString(6, mas.getDetalles());
        st.setInt(7, mas.getId_mascota());
        st.executeUpdate();

    }

    public List<Mascota> listar(String nombre, int page, int limit) throws Exception {

        List<Mascota> list = new ArrayList();
        try {
            String query = "SELECT * FROM mascota";
            this.Conectar();
            if ((nombre.isEmpty() || nombre.equals(""))
                    && page != 0 && limit != 0) {
                query = "SELECT * FROM mascota limit " + (page - 1) * limit + " ," + limit;
            } else if ((nombre.isEmpty() || nombre.equals(""))
                    && page == 0 && limit == 0) {
                query = "SELECT * FROM mascota";
            } else if (!nombre.isEmpty()) {
                query = "SELECT * FROM mascota WHERE nombre LIKE '%" + nombre + "%';";
            }

            PreparedStatement st = this.conexion.prepareStatement(query);

            list = new ArrayList();
            ResultSet rs = st.executeQuery();
            //String nombre_usuario, String nombre, String correo, int telefono
            while (rs.next()) {
                Mascota mas = new Mascota(
                        rs.getInt("id_mascota"),
                        rs.getString("nombre"),
                        rs.getInt("id_usuario"),
                        rs.getString("especie"),
                        rs.getInt("edad"),
                        rs.getString("genero"),
                        rs.getString("color_primario"),
                        rs.getString("detalles"));
                list.add(mas);
            }
            rs.close();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }

        return list;
    }

    public Mascota regresarMascota(int idMascota) throws Exception {
        try {
            this.Conectar();
            String query = "SELECT * FROM mascota WHERE id_mascota = ?";
            PreparedStatement st = this.conexion.prepareStatement(query);
            st.setInt(1, idMascota);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Mascota mas = new Mascota(
                        rs.getInt("id_mascota"),
                        rs.getString("nombre"),
                        rs.getInt("id_usuario"),
                        rs.getString("especie"),
                        rs.getInt("edad"),
                        rs.getString("genero"),
                        rs.getString("color_primario"),
                        rs.getString("detalles"));
                rs.close();
                st.close();

                return mas;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            this.Cerrar();
        }
        return null;
    }

    public void eliminar(int id_mascota) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "DELETE FROM mascota WHERE id_mascota = ?"
            );
            st.setInt(1, id_mascota);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void registrar(Mascota mas) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO mascota(nombre, id_usuario, especie, edad,genero,color_primario,detalles) VALUES(?,?,?,?,?,?,?);");
            st.setString(1, mas.getNombre());
            st.setInt(2, mas.getId_usuario());
            st.setString(3, mas.getEspecie());
            st.setInt(4, mas.getEdad());
            st.setString(5, mas.getGenero());
            st.setString(6, mas.getColor_primario());
            st.setString(7, mas.getDetalles());

            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public int contarRegistros() throws Exception {
        int numeroRegistros = 0;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("select count(*) from mascota");
            ResultSet r = st.executeQuery();
            if (r.first()) {
                numeroRegistros = r.getInt(1);
            }
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
        return numeroRegistros;
    }

}
