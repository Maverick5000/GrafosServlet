/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diccionarios;

/**
 *
 * @author Krauss
 */
public class Nodo<Tkey, Tvalue> {

    private Tkey key;
    private Tvalue valor;
    
    Nodo<Tkey, Tvalue> anterior;
    Nodo<Tkey, Tvalue> siguiente;

    public Nodo() {
    }

    public Nodo(Tkey key, Tvalue valor) {
        this.key = key;
        this.valor = valor;
    }

    public Tkey getKey() {
        return key;
    }

    public void setKey(Tkey key) {
        this.key = key;
    }

    public Tvalue getValor() {
        return valor;
    }

    public void setValor(Tvalue valor) {
        this.valor = valor;
    }
    
    public Nodo<Tkey, Tvalue> getAnterior() {
        return anterior;
    }

    public void setAnterior(Nodo<Tkey, Tvalue> anterior) {
        this.anterior = anterior;
    }

    public Nodo<Tkey, Tvalue> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<Tkey, Tvalue> siguiente) {
        this.siguiente = siguiente;
    }
    
    @Override
    public String toString(){
        return key + " " + valor;
    }
}
