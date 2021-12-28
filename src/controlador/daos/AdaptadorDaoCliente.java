/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import controlador.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import lista.controlador.Lista;
import modelo.Empleado;
import modelo.Persona;

/**
 *
 * @author pablo
 */
public class AdaptadorDaoCliente<T> implements InterfazDao<T> {

    private Class<T> clazz;
    private ConexionDB conexionDB = new ConexionDB();
    private Lista<T> lista = new Lista<>();

    public AdaptadorDaoCliente(Class<T> clazz) {
        this.clazz = clazz;
        lista.setClazz(clazz);
    }

    @Override
    public boolean guardar(T dato) {
        Persona persona = (Persona) dato;
        Connection conexion = conexionDB.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement("INSERT INTO clientes(idCliente,Nombres,Apellidos,Cedula,Direccion,Telefono) VALUE(?,?,?,?,?,?)");
            ps.setLong(1, persona.getIdPersona());
            ps.setString(2, persona.getNombres());
            ps.setString(3, persona.getApellidos());
            ps.setString(4, persona.getCedula());
            ps.setString(5, persona.getDireccion());
            ps.setString(6, persona.getTelefono());
            int verificacion = ps.executeUpdate();
            ps.close();
            if (verificacion > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(String dato, String ID) {
        return false;
    }

    @Override
    public Lista<T> listar() {
        Statement st = null;
        ResultSet rs = null;
        lista = new Lista<>();
        Connection conexion = conexionDB.conectar();  
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM clientes");
            while (rs.next()) {    
                Persona cliente = new Persona();
                cliente.setIdPersona(rs.getLong("IdCliente"));
                cliente.setNombres(rs.getString("Nombres"));
                cliente.setApellidos(rs.getString("Apellidos"));
                cliente.setCedula(rs.getString("Cedula"));
                cliente.setTelefono(rs.getString("Telefono"));
                cliente.setDireccion(rs.getString("Direccion"));
                lista.insertarNodo((T) cliente);
            }            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }

}
