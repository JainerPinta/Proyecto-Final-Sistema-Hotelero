/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author pablo
 */
public class Habitacion {
    private Long id_habitacion;
    private Long numero;
    private String tipo;
    private Long estado;

    public Long getId_habitacion() {
        return id_habitacion;
    }

    public void setId_habitacion(Long id_habitacion) {
        this.id_habitacion = id_habitacion;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }
    
    
}
