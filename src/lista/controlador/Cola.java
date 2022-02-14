/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lista.controlador;

import java.io.Serializable;
import lista.modelo.Nodo;

/**
 *
 * @author Jainer Pinta
 */
public class Cola <T> implements Serializable{
    private int size;
    private Nodo primero;
    private Nodo ultimo;
    
    /**
     * Construir cola con tamaño especifico
     * @param size int Tamaño de la cola
     */
    public Cola(int size){
        this.size = size;
    }
    
    /**
     * Obtener tamaño de la cola
     * @return int tamaño de cola
     */
    public int getSize() {
        return size;
    }
    
    /**
     * Setear tamaño de cola
     * @param size 
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * Obtener primer objeto de la cola
     * @return Primer objeto de la cola.
     */
    public Nodo getPrimero() {
        if (this.primero == null) {
            this.primero = new Nodo();
        }
        return primero;
    }
    
    /**
     * Setear primer objeto de la cola
     * @param primero Nodo
     */
    public void setPrimero(Nodo primero) {
        this.primero = primero;
    }
    
    /**
     * Obtener ultimo nodo
     * @return Nodo ultimo
     */
    public Nodo getUltimo() {
        if (this.ultimo == null) {
            this.ultimo = new Nodo();
        }       
        return ultimo;
    }
    
    /**
     * Setear ultimo nodo
     * @param ultimo Ultimo nodo
     */
    public void setUltimo(Nodo ultimo) {
        this.ultimo = ultimo;
    }
    
    /**
     * Verificar si una cola esta llena
     * @return true si la cola esta llena
     */
    public boolean estaLlena(){
        return size == tamanio();
    }
    
    /**
     * Verificar si la cola esta vacia
     * @return true si la cola esta vacia
     */
    public boolean estaVacia(){
        return this.primero == null;
    }
    
    /**
     * Insertar dato en cola
     * @param dato Dato cola
     * @return true si se puede insertar el dato
     */
    public boolean queue(T dato){
        if (!estaLlena()) {
            Nodo temp = new Nodo();
            temp.setDato(dato);
            temp.setNodoSiguiente(null);
            if (estaVacia()) {
                primero = temp;
            }else{
                ultimo.setNodoSiguiente(temp);
            }
            ultimo = temp;
            return true;
        }
        return false;
    }
    
    /**
     * Sacar un objeto de la cola
     * @return el objeto extraido, null si la cola esta vacia.
     */
    public T dequeue(){
        if (!estaVacia()) {
            Nodo temp = new Nodo();
            temp = getPrimero();
            T dato = (T) temp.getDato();
            if (getSize()==1) {
                setUltimo(null);
            }
            setPrimero(temp.getNodoSiguiente());
            return dato;
        }
        return null;
    }
    
    /**
     * Obtener la cantidad e elementos que existen en la cola
     * @return int número de elementos en cola.
     */
    public int tamanio(){
        Nodo tmp = primero;
        int contador = 0;
        while (!estaVacia() && tmp != null) {            
            contador++;
            tmp  = tmp.getNodoSiguiente();
        }
        return contador;
    }
    
    /**
     * Obtener un dato en la cola dada su posicion
     * @param posicion int posicion en cola
     * @return T dato en la posicion indicada
     */
    public T consultarDatoPosicion(int posicion){
        T dato = null;
        if (!estaVacia() && (posicion >= 0 && posicion <= tamanio()-1)) {       
            Nodo tmp = primero;        
            for (int i = 0; i < posicion; i++) {
                tmp = tmp.getNodoSiguiente();
                if (tmp == null)break;
            }
            if (tmp != null) {
                dato = (T)tmp.getDato();
            }
        }
        return dato;
    }
    
    /**
     * Imprime la informacion de la cola
     */
    public void imprimir(){
        Nodo tmp = primero;
        while (!estaVacia() && tmp != null) {            
            System.out.println(tmp.getDato());
            tmp  = tmp.getNodoSiguiente();
        }
    }
    
}
