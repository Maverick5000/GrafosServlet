/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Krauss
 */
public class Arista<Tkey, Tcost, Tvalue> {
    
    private Tcost costo;
    private Nodo<Tkey, Tcost, Tvalue> destino;

    public Arista(Tcost costo, Nodo<Tkey, Tcost, Tvalue> destino) {
        this.costo = costo;
        this.destino = destino;
    }
    
   
    public Tcost getCosto() {
        return costo;
    }

    public void setCosto(Tcost costo) {
        this.costo = costo;
    }

    public Nodo<Tkey, Tcost, Tvalue>  getDestino() {
        return destino;
    }

    public void setDestino(Nodo<Tkey, Tcost, Tvalue> destino) {
        this.destino = destino;
    }
    
    
    
}
