/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diccionarios;

import java.util.Iterator;

/**
 *
 * @author Krauss
 */
public class Inodo<Tkey, Tvalue> implements Iterator<Nodo<Tkey, Tvalue>> {
    private Nodo<Tkey, Tvalue> actual;

    public Inodo(Nodo<Tkey, Tvalue> inicial) {
        this.actual = inicial;
    }

    @Override
    public boolean hasNext() {
        return actual != null;
    }

    @Override
    public Nodo<Tkey, Tvalue> next() {
        Nodo<Tkey, Tvalue> nodo = actual;
        actual = actual.getSiguiente();
        return nodo;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
