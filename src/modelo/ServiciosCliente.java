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
public class ServiciosCliente extends Servicio{
    private String cliente;
    private int uso;

    /**
     * 
     * @return Cliente
     */
    public String getCliente() {
        return cliente;
    }

    /**
     * 
     * @param cliente 
     */
    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    /**
     * 
     * @return Uso
     */
    public int getUso() {
        return uso;
    }

    /**
     * 
     * @param uso 
     */
    public void setUso(int uso) {
        this.uso = uso;
    }
    
}
