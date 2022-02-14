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
import java.sql.Statement;
import lista.controlador.Lista;
import modelo.Evento;

/**
 *
 * @author Usuario
 */
public class AdaptadorDaoEvento <T> implements InterfazDao<T>{
    
    private Class<T> clazz;
    private ConexionDB conexionDB = new ConexionDB();
    Connection conexion = conexionDB.conectar();
    private Lista<T> lista = new Lista<>();
    
    public AdaptadorDaoEvento(Class<T> clazz) {
        this.clazz = clazz;
        lista.setClazz(clazz);
    }

    /**
     * Guardara el dato dato Ingresado en la base de datos
     * @param dato
     * @return true si guarda el dato correctamente
     */
    @Override
    public boolean guardar(T dato) {
        Evento evento = (Evento) dato;
        try {
            PreparedStatement pst;
            pst = conexion.prepareStatement("INSERT INTO eventos (Tipo,Fecha,Precio,Duracion,Cliente,Jornada) values (?,?,?,?,?,?)");
            pst.setString(1, evento.getNombreServicio());
            pst.setString(2, evento.getFecha());
            pst.setDouble(3, evento.getPrecio());
            pst.setString(4, evento.getDuracion());
            pst.setString(5, evento.getCliente());
            pst.setString(6, evento.getJordana());
            pst.executeUpdate();
            pst.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error en el guardado de la base "+e);
            return false;
        }
    }

    @Override
    public boolean modificar(T dato, int ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(String dato, String ID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean modificar(T dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * 
     * @return Lista de Eventos
     */
    @Override
    public Lista<T> listar() {
        Statement st = null;
        ResultSet rs = null;
        lista = new Lista<>();
        try {
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM eventos");
            while(rs.next()){
                Evento eventos = new Evento();
                eventos.setIdServicio(rs.getLong("id"));
                eventos.setNombreServicio(rs.getString("Tipo"));
                eventos.setFecha(rs.getString("Fecha"));
                eventos.setPrecio(rs.getDouble("Precio"));
                eventos.setDuracion(rs.getString("Duracion"));
                eventos.setCliente(rs.getString("Cliente"));
                eventos.setJordana(rs.getString("Jornada"));
                lista.insertarNodo((T) eventos);
            }
            
        } catch (Exception e) {
            System.out.println("Error en listar "+e);
        }
        return (lista);
    }
    
}
