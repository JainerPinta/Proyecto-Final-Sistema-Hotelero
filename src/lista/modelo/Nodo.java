/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.modelo;

import java.io.Serializable;

/**
 *
 * @author Jainer Pinta
 */
public class Nodo <T> implements Serializable{
    private T dato;
    private Nodo nodoSiguiente;
    
    /**
     * Constructor del nodo
     * @param dato Dato del nodo
     * @param nodoSiguiente Nodo siguiente
     */
    public Nodo(T dato, Nodo nodoSiguiente){
        this.dato = dato;
        this.nodoSiguiente = nodoSiguiente;
    }
    
    /**
     * Constructor por defecto del nodo.
     */
    public Nodo(){
        this.dato = null;
        this.nodoSiguiente = null;
    }
    /**
     * Obtiene el dato del nodo
     * @return Datod el nodo
     */
    public T getDato() {
        return dato;
    }
    
    /**
     * Setear el dato del nodo
     * @param dato Dato a setear
     */
    public void setDato(T dato) {
        this.dato = dato;
    }
    
    /**
     * Obtener nodo siguiente del nodo actual
     * @return Nodo siguiente.
     */
    public Nodo getNodoSiguiente() {
        return nodoSiguiente;
    }
    
    /**
     * Setear el nodo siguiente del nodo actual
     * @param nodoSiguiente Nodo siguiente a insertar.
     */
    public void setNodoSiguiente(Nodo nodoSiguiente) {
        this.nodoSiguiente = nodoSiguiente;
    }
    
}
