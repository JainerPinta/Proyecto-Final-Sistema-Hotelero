/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author pablo
 */
public class Reservacion {

    private Long id_reservacion;
    private String cliente;
    private String habitacion;
    private LocalDate fecha;
    private LocalDate fecha_entrada;
    private LocalDate fecha_salida;
    private Double costoTotal;
    
    /**
     * Devuelve el id_reservacion
     * @return Long
     */
    public Long getId_reservacion() {
        return id_reservacion;
    }

    /**
     * Setea id_reservacion
     * @param id_reservacion
     */
    public void setId_reservacion(Long id_reservacion) {
        this.id_reservacion = id_reservacion;
    }

    /**
     * Devuelve el cliente
     * @return String
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * Setea cliente
     * @param cliente
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * Devuelve la habitacion
     * @return String
     */
    public String getHabitacion() {
        return habitacion;
    }

    /**
     * Setea habitacion
     * @param habitacion
     */
    public void setHabitacion(String habitacion) {
        this.habitacion = habitacion;
    }

    /**
     * Devuelve la fecha de la reserva
     * @return LocalDate
     */
    public LocalDate getFecha() {
        return fecha;
    }

    /**
     * Setea la fecha de la reserva
     * @param fecha
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    /**
     * Devuelve la fecha_entrada de la reserva
     * @return LocalDate
     */
    public LocalDate getFecha_entrada() {
        return fecha_entrada;
    }

    /**
     * Setea fecha_entrada
     * @param fecha_entrada
     */
    public void setFecha_entrada(LocalDate fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    /**
     * Devuelve la fecha_salida de la reserva
     * @return LocalDate
     */
    public LocalDate getFecha_salida() {
        return fecha_salida;
    }

    /**
     * Setea la fecha_salida
     * @param fecha_salida
     */
    public void setFecha_salida(LocalDate fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    /**
     * Devuelve el costoTotal
     * @return Double
     */
    public Double getCostoTotal() {
        return costoTotal;
    }

    /**
     * Setea el costoTotal
     * @param costoTotal
     */
    public void setCostoTotal(Double costoTotal) {
        this.costoTotal = costoTotal;
    }

}
