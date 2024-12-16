/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import com.mycompany.interfaces.DAOVeterinario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mycompany.db.Database;

/**
 *
 * @author 52951
 */
public class DAOVeterinarioImplements extends Database implements DAOVeterinario {

    public void registrar(Veterinario vet) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO veterinario(nombre_usuario, nombre, correo,contraseña) VALUES(?,?,?,?);");
            st.setString(1, vet.getNombre_usuario());
            st.setString(2, vet.getNombre());
            st.setString(3, vet.getCorreo());

            st.setString(4, vet.getContraseña());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void modificar(Veterinario vet, String nombre_usuario) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    " UPDATE veterinario "
                    + "SET nombre_usuario = ?, "
                    + "    nombre = ?, "
                    + "    correo = ?, "
                    + "    contraseña = ? "
                    + "WHERE nombre_usuario = ?;"
            );
            st.setString(1, vet.getNombre_usuario());
            st.setString(2, vet.getNombre());
            st.setString(3, vet.getCorreo());
            st.setString(4, vet.getContraseña());
            st.setString(5, nombre_usuario); // Sin comillas adicionales
            st.executeUpdate();

            st.close();
        } catch (Exception e) {
            throw e; // Lanza la excepción para su manejo en niveles superiores
        } finally {
            this.Cerrar(); // Cierra siempre la conexión
        }
    }

    public int contarRegistros() throws Exception {
        int numeroRegistros = 0;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("select count(*) from veterinario");
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

    public List<Veterinario> listar(String nombre, String correo, int page, int limit) throws Exception {
        List<Veterinario> list = new ArrayList();

        try {
            String query;
            this.Conectar();
            if ((nombre.isEmpty() || nombre.equals(""))
                    && (correo.isEmpty() || correo.equals("")) && page != 0 && limit != 0) {
                query = "SELECT * FROM veterinario limit " + (page - 1) * limit + " ," + limit;
            } else if ((nombre.isEmpty() || nombre.equals(""))
                    && (correo.isEmpty() || correo.equals("")) && page == 0 && limit == 0) {
                query = "SELECT * FROM veterinario";
            } else if (!nombre.isEmpty()) {
                query = "SELECT * FROM veterinario WHERE nombre LIKE '%" + nombre + "%';";
            } else {
                query = "SELECT * FROM veterinario WHERE correo LIKE '%" + correo + "%';";
            }

            PreparedStatement st = this.conexion.prepareStatement(query);

            list = new ArrayList();
            ResultSet rs = st.executeQuery();
            //String nombre_usuario, String nombre, String correo, int telefono
            while (rs.next()) {
                Veterinario vet = new Veterinario(
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("contraseña"));
                list.add(vet);
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

    public void eliminar(String nombre_usuario) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "DELETE FROM veterinario WHERE nombre_usuario LIKE ?"
            );
            st.setString(1, "%" + nombre_usuario + "%");
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }
}
