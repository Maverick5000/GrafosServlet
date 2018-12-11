/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import diccionarios.Diccionario;
import diccionarios.TablaHash;
import java.util.List;

/**
 *
 * @author Krauss
 */
public class Nodo<Tkey, Tcost, Tvalue> {

    private Tkey key;
    private Tvalue valor;
    private Diccionario<Tkey, Arista<Tkey, Tcost, Tvalue>> aristas = new TablaHash<>();

    public Nodo(Tkey key, Tvalue valor) {
        this.key = key;
        this.valor = valor;
    }

    public Tkey getId() {
        return key;
    }

    public void setId(Tkey key) {
        this.key = key;
    }

    public Tvalue getValor() {
        return valor;
    }

    public void setValor(Tvalue valor) {
        this.valor = valor;
    }

    public void insertarArista(Nodo<Tkey, Tcost, Tvalue> destino,Tcost costo) {
        if(destino == null){
            throw new IllegalArgumentException("El nodo destino no puede ser nulo");
        }
        aristas.insertar(key,new Arista<>(costo, destino));
    }

    public void eliminarArista(Tkey key) {
        if (key == null) {
            System.err.println("key null en eliminar Arista");
        }
        if (!contieneArista(key)) {
            System.err.println("no contiene arista en eliminar Arista");
        }
        aristas.eliminar(key);
    }

    public boolean contieneArista(Tkey key) {
        return aristas.contieneLlave(key);
    }

    public List<Arista<Tkey, Tcost, Tvalue>> getAristas() {
        return aristas.getValores();
    }

    @Override
    public String toString() {
        return key.toString() + valor.toString() + ""; //To change body of generated methods, choose Tools | Templates.
    }

}
