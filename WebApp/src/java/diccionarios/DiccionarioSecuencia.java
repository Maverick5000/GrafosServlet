/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diccionarios;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Krauss
 */
public class DiccionarioSecuencia<Tkey, Tvalue> extends Diccionario<Tkey, Tvalue> {

    private Nodo<Tkey, Tvalue> primero;
    private Nodo<Tkey, Tvalue> ultimo;

    public DiccionarioSecuencia() {
        super();
    }

    public DiccionarioSecuencia(Comparador comparador) {
        super(comparador);
    }

    @Override
    public void insertar(Tkey key, Tvalue value) {

        cantidadElementos++;
        Nodo<Tkey, Tvalue> nuevoNodo = new Nodo<Tkey, Tvalue>(key, value);
        if (primero == null) {
            primero = nuevoNodo;
            ultimo = nuevoNodo;
            return;
        }

        nuevoNodo.setAnterior(ultimo);
        ultimo.setSiguiente(nuevoNodo);
        ultimo = nuevoNodo;
    }

    private Nodo<Tkey, Tvalue> getNodo(Tkey key) {
        Nodo<Tkey, Tvalue> nodo = primero;
        while (nodo != null) {
            if (comparador.esIgual(nodo.getKey(), key)) {
                return nodo;
            }
            nodo = nodo.getSiguiente();
        }
        return null;
    }

    @Override
    public Tvalue obtener(Tkey key) {
        Nodo<Tkey, Tvalue> nodo = getNodo(key);
        if (nodo != null) {
            return nodo.getValor();
        }
        return null;
    }

    @Override
    public Tvalue eliminar(Tkey key) {

        Nodo<Tkey, Tvalue> nodo = getNodo(key);
        if (nodo == null) {
            return null;
        }

        cantidadElementos--;
        Tvalue value = nodo.getValor();
        if (primero == ultimo && nodo == primero) {
            primero = null;
            ultimo = null;
            return value;
        }

        if (primero == nodo) {
            primero.getSiguiente().setAnterior(null);
            primero = primero.getSiguiente();
            nodo.setSiguiente(null);
            return value;
        }

        if (ultimo == nodo) {
            ultimo.getAnterior().setSiguiente(null);
            ultimo = ultimo.getAnterior();
            nodo.setAnterior(null);
            return value;
        }

        nodo.getAnterior().setSiguiente(nodo.getSiguiente());
        nodo.getSiguiente().setAnterior(nodo.getAnterior());
        nodo.setSiguiente(null);
        nodo.setAnterior(null);

        return value;
    }

    @Override
    public boolean contieneLlave(Tkey key) {
        return getNodo(key) != null;
    }

    @Override
    public boolean estaVacio() {
        return cantidadElementos == 0;
    }

    @Override
    public List<Tkey> getLlaves() {
        List lista = new LinkedList();

        if (primero == null) {
            return null;
        }

        Nodo aux = primero;
        for (int i = 0; i < cantidadElementos; i++) {
            lista.add(aux.getKey());
            aux = aux.getSiguiente();
        }
        return lista;
    }

    @Override
    public List<Tvalue> getValores() {
        LinkedList<Tvalue> valores = new LinkedList<>();
        Nodo<Tkey, Tvalue> auxNodo = primero;
        while (auxNodo != null) {
            valores.add(auxNodo.getValor());
            auxNodo = auxNodo.getSiguiente();
        }
        return valores;
    }

    @Override
    public String toString() {
        Nodo aux = primero;
        String k = "";
        String v = "";
        while (aux != null) {
            System.out.println(aux + "");
            aux = aux.getSiguiente();
        }
        return k + " " + v;
    }

    public Iterator<Nodo<Tkey, Tvalue>> iterator() {
        return new Inodo<>(primero);
    }
}
