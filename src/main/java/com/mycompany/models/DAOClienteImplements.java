/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import com.mycompany.db.Database;
import com.mycompany.interfaces.DAOCliente;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 52951
 */
public class DAOClienteImplements extends Database implements DAOCliente {

    public int contarRegistros() throws Exception {
        int numeroRegistros = 0;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("select count(*) from cliente");
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

    public void registrar(Cliente cli) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO cliente(nombre, correo) VALUES(?,?);");
            st.setString(1, cli.getNombre());
            st.setString(2, cli.getCorreo());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void modificar(Cliente cli) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE cliente SET nombre = ?, correo = ? WHERE id_usuario =  ?;");
            st.setString(1, cli.getNombre());
            st.setString(2, cli.getCorreo());
            st.setInt(3, cli.getId_usuario());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public List<Cliente> listar(String nombre, String correo, int page, int limit) throws Exception {
        List<Cliente> list = new ArrayList();
        try {
            String query;
            this.Conectar();
            if ((nombre.isEmpty() || nombre.equals(""))
                    && (correo.isEmpty() || correo.equals("")) && page != 0 && limit != 0) {
                query = "SELECT * FROM cliente limit " + (page - 1) * limit + " ," + limit;
            } else if ((nombre.isEmpty() || nombre.equals(""))
                    && (correo.isEmpty() || correo.equals("")) && page == 0 && limit == 0) {
                query = "SELECT * FROM cliente";
            } else if (!nombre.isEmpty()) {
                query = "SELECT * FROM cliente WHERE nombre LIKE '%" + nombre + "%';";
            } else {
                query = "SELECT * FROM cliente WHERE correo LIKE '%" + correo + "%';";
            }

            PreparedStatement st = this.conexion.prepareStatement(query);

            list = new ArrayList();
            ResultSet rs = st.executeQuery();
            //String nombre_usuario, String nombre, String correo, int telefono
            while (rs.next()) {
                Cliente client = new Cliente(
                        rs.getString("nombre"),
                        rs.getInt("id_usuario"),
                        rs.getString("correo"));
                list.add(client);
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

    public List<Cliente> listar(int id_usuario) throws Exception {
        List<Cliente> list = new ArrayList();
        try {
            String query;
            this.Conectar();

            query = "SELECT * FROM cliente WHERE id_usuario =" + id_usuario + ";";

            PreparedStatement st = this.conexion.prepareStatement(query);

            list = new ArrayList();
            ResultSet rs = st.executeQuery();
            //String nombre_usuario, String nombre, String correo, int telefono
            while (rs.next()) {
                Cliente client = new Cliente(
                        rs.getString("nombre"),
                        rs.getInt("id_usuario"),
                        rs.getString("correo"));
                list.add(client);
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

   

    public void eliminar(int id_usuario) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "DELETE FROM cliente WHERE id_usuario = ?"
            );
            st.setInt(1, id_usuario);
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

}
