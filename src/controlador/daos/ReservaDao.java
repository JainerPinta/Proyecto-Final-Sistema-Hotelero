/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import modelo.Reservacion;

/**
 *
 * @author pablo
 */
public class ReservaDao extends AdpatadorDaoReserva<Reservacion> {

    private Reservacion reserva;

    /**
     * Constructor de ReservaDao
     */
    public ReservaDao() {
        super(Reservacion.class);
    }

    /**
     * Obtiene un objeto Reservacion de la clase ReservaDao
     * @return Un objeto de tipo Reservacion
     */
    public Reservacion getReserva() {
        if(reserva == null){
            reserva = new Reservacion();
        }
        return reserva;
    }

    /**
     * Setea un objeto de tipo Reservacion
     * @param reserva Objeto de tipo Reservacion
     */
    public void setReserva(Reservacion reserva) {
        this.reserva = reserva;
    }

    /**
     * Guarda el objeto Reservacion en la base de datos
     * @return true si se ha guardado la reservacion
     */
    public boolean guardar() {
        return guardar(reserva);
    }
    
    /**
     * Modifica el objeto Reservacion en la base de datos
     * @return true si se ha modificado la reservacion
     */
    public boolean modificar() {
        return modificar(reserva);
    }


}
