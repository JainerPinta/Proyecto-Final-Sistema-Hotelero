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
public class Pila <T> implements Serializable{
    private Nodo cabecera;
    private int size;
    
    /**
     * Obtener tamaño de la pila
     * @return Tamaño de la pila
     */
    public int getSize() {
        return size;
    }

    /**
     * Setear tamaño de pila
     * @param size Tamaño de pila
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    /**
     * Construir pila con tamaño fijo
     * @param size Tamaño de la pila
     */
    public Pila(int size){
        this.size= size;
    }
    
    /**
     * Obtener cabecera de la pila
     * @return Dato de la cabecera
     */
    public Nodo getCabecera() {
        if(this.cabecera == null)
            this.cabecera = new Nodo();
        return cabecera;
    }
    
    /**
     * Setear cabecera en la pila
     * @param cabecera Dato a setear
     */
    public void setCabecera(Nodo cabecera) {
        this.cabecera = cabecera;
    }
    
    /**
     * Verifica si la pila esta vacia
     * @return true si la pila esta vacia
     */
    public boolean estaVacias(){
        return cabecera == null;
    }
    
    /**
     * Verifica si la pila esta llena
     * @return true si la pila esta llena
     */
    public boolean estaLlena(){
        return size == tamanio();
    }
    
    /**
     * Inserta un dato en la pila
     * @param dato Dato a insertar 
     * @return true si he insertado el dato
     */
    public boolean push(T dato){
        if (!estaLlena()) {
            Nodo temp = new Nodo();
            temp.setDato(dato);
            temp.setNodoSiguiente(cabecera);
            cabecera = temp;
            return true;
        }
        return false;
    }
    
    /**
     * Extrae un dato de la pila
     * @return true si el dato se ha extraido.
     */
    public boolean pop(){
        if (!estaVacias()) {
            //Nodo temp = new Nodo();
            Nodo temp = cabecera;
            cabecera = temp.getNodoSiguiente();
            return true;
        }
        return false;
    }
    
    /**
     * Obtiene la cantidad de datos en la pila
     * @return Cantidad de datos en la pila
     */
    public int tamanio(){
        Nodo tmp = cabecera;
        int contador = 0;
        while (!estaVacias() && tmp != null) {            
            contador++;
            tmp  = tmp.getNodoSiguiente();
        }
        return contador;
    }
    
    /**
     * Consultar un dato en la pila por posicion
     * @param posicion Posicion del dato
     * @return El dato a consultar
     */
    public T consultarDatoPosicion(int posicion){
        T dato = null;
        if (!estaVacias() && (posicion >= 0 && posicion <= tamanio()-1)) {       
            Nodo tmp = cabecera;        
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
     * Imprime los datos de la pila.
     */
    public void imprimir(){
        Nodo tmp = cabecera;
        while (!estaVacias() && tmp != null) {            
            System.out.println(tmp.getDato());
            tmp  = tmp.getNodoSiguiente();
        }
    }
}
