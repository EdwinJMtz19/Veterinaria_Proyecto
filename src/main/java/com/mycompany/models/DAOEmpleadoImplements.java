/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.models;

import com.mycompany.db.Database;
import com.mycompany.interfaces.DAOEmpleado;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 52951
 */
public class DAOEmpleadoImplements extends Database implements DAOEmpleado {

    public int contarRegistros() throws Exception {
        int numeroRegistros = 0;
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("select count(*) from empleado");
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

    public void registrar(Empleado emp) throws Exception {

        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "INSERT INTO empleado(nombre_usuario, nombre, correo, rol,contraseña) VALUES(?,?,?,?,?);");
            st.setString(1, emp.getNombre_usuario());
            st.setString(2, emp.getNombre());
            st.setString(3, emp.getCorreo());
            st.setString(4, emp.getRol());
            st.setString(5, emp.getContraseña());
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.Cerrar();
        }
    }

    public void modificar(Empleado emp, String nombre_usuario) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement(
                    "UPDATE empleado SET nombre_usuario = ?, nombre = ?, correo = ?, rol = ?, contraseña = ? WHERE nombre_usuario LIKE ?;"
            );
            st.setString(1, emp.getNombre_usuario());
            st.setString(2, emp.getNombre());
            st.setString(3, emp.getCorreo());
            st.setString(4, emp.getRol());
            st.setString(5, emp.getContraseña());
            st.setString(6, "%" + nombre_usuario + "%"); // Sin comillas adicionales
            st.executeUpdate();
            st.close();
        } catch (Exception e) {
            throw e; // Lanza la excepción para manejarla en niveles superiores
        } finally {
            this.Cerrar(); // Asegúrate de cerrar la conexión
        }
    }

    public List<Empleado> listar(String nombre, String correo, int page, int limit) throws Exception {
        List<Empleado> list = new ArrayList();
        try {
            String query;
            this.Conectar();
            if ((nombre.isEmpty() || nombre.equals(""))
                    && (correo.isEmpty() || correo.equals("")) && page != 0 && limit != 0) {
                query = "SELECT * FROM empleado limit " + (page - 1) * limit + " ," + limit;
            } else if ((nombre.isEmpty() || nombre.equals(""))
                    && (correo.isEmpty() || correo.equals("")) && page == 0 && limit == 0) {
                query = "SELECT * FROM empleado";
            } else if (!nombre.isEmpty()) {
                query = "SELECT * FROM empleado WHERE nombre LIKE '%" + nombre + "%';";
            } else {
                query = "SELECT * FROM empleado WHERE correo LIKE '%" + correo + "%';";
            }

            PreparedStatement st = this.conexion.prepareStatement(query);

            list = new ArrayList();
            ResultSet rs = st.executeQuery();
            //String nombre_usuario, String nombre, String correo, int telefono
            while (rs.next()) {
                Empleado emp = new Empleado(
                        rs.getString("nombre"),
                        rs.getString("correo"),
                        rs.getString("rol"),
                        rs.getString("contraseña"));
                list.add(emp);
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
                    "DELETE FROM empleado WHERE nombre_usuario LIKE ?"
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
