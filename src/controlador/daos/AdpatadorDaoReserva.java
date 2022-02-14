/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import Controlador.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import javafx.beans.property.StringProperty;
import lista.controlador.Lista;
import modelo.Empleado;
import modelo.Reservacion;

/**
 *
 * @author pablo serrano
 */
public class AdpatadorDaoReserva<T> implements InterfazDao<T> {

    private Class<T> clazz;
    private ConexionDB conexionDB = new ConexionDB();
    private Lista<T> lista = new Lista<>();

    
    /**
     * Constructor de la clase AdaptadorDaoReserva
     * @param clazz class del tipo Lista
     */
    public AdpatadorDaoReserva(Class<T> clazz) {
        this.clazz = clazz;
        lista.setClazz(clazz);
    }

    /**
     * Guarda una reserva en la base de datos
     * @param dato Objeto de tipo Reservaion
     * @return True si se ha guardado la reserva
     */
    @Override
    public boolean guardar(T dato) {
        Reservacion reserva = (Reservacion) dato;
        try {
            Connection conexion = conexionDB.conectar();
            PreparedStatement pst;
            pst = conexion.prepareStatement("INSERT INTO reservas (cliente,habitacion,fecha_reserva,fecha_entrada,fecha_salida,costoTotal) values (?,?,?,?,?,?)");
            pst.setString(1, reserva.getCliente());
            pst.setString(2, reserva.getHabitacion());
            pst.setString(3, reserva.getFecha().toString());
            pst.setString(4, reserva.getFecha_entrada().toString());
            pst.setString(5, reserva.getFecha_salida().toString());
            pst.setDouble(6, reserva.getCostoTotal());
            int verificacion = pst.executeUpdate();
            pst.close();
            if (verificacion > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en el guardado de la base " + e);
            return false;
        }
    }

    /**
     * Elimina una reserva de la base de datos
     * @param id de la reserva
     * @return True si se ha elminado la reserva
     */
    public boolean eliminar(Integer id) {
        Connection conexion = conexionDB.conectar();
        try {
            PreparedStatement ps = conexion.prepareStatement("DELETE FROM reservas WHERE id_reservacion='" + id + "'");
            int verificacion = ps.executeUpdate();
            ps.close();
            if (verificacion > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    /**
     * Obtiene las reservas de la base de datos
     * @return Lista de tipo Reservacion.
     */
    @Override
    public Lista<T> listar() {
        Statement st = null;
        ResultSet rs = null;
        lista = new Lista<>();
        Connection conexion = conexionDB.conectar();
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM reservas");
            while (rs.next()) {
                Reservacion reserva = new Reservacion();
                reserva.setId_reservacion(rs.getLong("id_reservacion"));
                reserva.setCliente(rs.getString("cliente"));
                reserva.setHabitacion(rs.getString("habitacion"));
                reserva.setFecha(LocalDate.parse(rs.getString("fecha_reserva")));
                reserva.setFecha_entrada(LocalDate.parse(rs.getString("fecha_entrada")));
                reserva.setFecha_salida(LocalDate.parse(rs.getString("fecha_salida")));
                reserva.setCostoTotal(rs.getDouble("costoTotal"));
                lista.insertarNodo((T) reserva);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }
   

    @Override
    public boolean modificar(T dato, int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @param dato
     * @return True si se ha modificado la reserva
     */
    @Override
    public boolean modificar(T dato) {
        Connection conexion = conexionDB.conectar();
        Reservacion reserva = (Reservacion) dato;
        try {
            PreparedStatement ps = conexion.prepareStatement("UPDATE reservas SET cliente = '" + reserva.getCliente() + "', habitacion = '" + reserva.getHabitacion()
                    + "', fecha_reserva = '" + reserva.getFecha() + "', fecha_entrada = '" + reserva.getFecha_entrada() + "', fecha_salida = '" + reserva.getFecha_salida()
                    + "', costoTotal = '" + reserva.getCostoTotal() +"' WHERE id_reservacion = '"+ reserva.getId_reservacion()+"' ;");
            int verificacion = ps.executeUpdate();
            ps.close();
            if (verificacion > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(String dato, String ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
