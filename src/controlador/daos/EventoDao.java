/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador.daos;

import modelo.Evento;

/**
 *
 * @author Usuario
 */
public class EventoDao extends AdaptadorDaoEvento<Evento>{
    private Evento evento;
    
    
    public EventoDao() {
        super(Evento.class);
    }
    
    /**
     * 
     * @return Objeto Evento
     */
    public Evento getEvento() {
        if(evento == null)
            evento = new Evento();
        return evento;
    }

    /**
     * Setea el objeto Evento
     * @param evento 
     */
    public void setEvento(Evento evento) {
        this.evento = evento;
    }
    
    /**
     * 
     * @return true si guardo correctamente
     */
    public boolean guardar(){
        return guardar(evento);
    }
}
