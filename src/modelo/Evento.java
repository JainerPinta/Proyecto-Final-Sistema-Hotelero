/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Usuario
 */
public class Evento extends Servicio{
   private String cliente;
    private String fecha;
    private String Jordana;
    private String duracion;

    /**
     *  
     * @return Retorna la Fecha
     */
    public String getFecha() {
        return fecha;
    }

    /**
     * 
     * @param fecha 
     */
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    /**
     * 
     * @return String Jornada
     */
    public String getJordana() {
        return Jordana;
    }

    /**
     * 
     * @param Jordana 
     */
    public void setJordana(String Jordana) {
        this.Jordana = Jordana;
    }
    
    /**
     * 
     * @return La Duracion
     */
    public String getDuracion() {
        return duracion;
    }

    /**
     * 
     * @param duracion 
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    /**
     * 
     * @return Cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Setea un Objeto Cliente
     * @param cliente 
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    

    
}
