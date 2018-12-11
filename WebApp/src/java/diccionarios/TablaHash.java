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
public class TablaHash<Tkey, Tvalue> extends Diccionario<Tkey, Tvalue> {

    private DiccionarioSecuencia[] elementos;
    private float factorCarga;
    private int limite;
    private int cantidadElementos;

    public TablaHash(Comparador c) {
        super(c);
        this.init();
    }

    public TablaHash() {
        super();
        this.init();
    }

    private void init() {
        this.elementos = new DiccionarioSecuencia[11];
        this.factorCarga = 0.75f;
        this.limite = (int) (elementos.length * factorCarga);
        this.cantidadElementos = 0;
    }

    private int getPosicion(Tkey key, int n) {
        int hash;
        hash = comparador.getHashCode(key);
        return (hash & 0x7FFFFFFF) % n;
    }

    @Override
    public boolean contieneLlave(Tkey key) {

        if (key == null) {
            throw new IllegalArgumentException("La llave no puede ser nula");
        }

        int posicion = getPosicion(key, elementos.length);
        if (elementos[posicion] == null) {
            return false;
        }

        return elementos[posicion].contieneLlave(key);
    }

    @Override
    public void insertar(Tkey key, Tvalue value) {
        if (key == null) {
            throw new NullPointerException("La llave no puede ser nula");
        }
        int codigoHash = comparador.getHashCode(key);
        int posicion = getPosicion(codigoHash, elementos.length);
        if (contieneLlave(key)) {
            elementos[posicion].insertar(key, value);
            cantidadElementos++;
            return;
        }
        if (cantidadElementos >= limite) {
            rehash();
        }
        posicion = getPosicion(codigoHash, elementos.length);
        if (elementos[posicion] == null) {
            elementos[posicion] = new DiccionarioSecuencia<>(comparador);
        }
        elementos[posicion].insertar(key, value);
        cantidadElementos++;
    }

    @Override
    public Tvalue obtener(Tkey key) {
        if (key == null) {
            throw new IllegalArgumentException("La llave no puede ser nula");
        }

        int posicion = getPosicion(key, elementos.length);
        if (elementos[posicion] == null) {
            return null;
        }

        return (Tvalue) elementos[posicion].obtener(key);
    }

    @Override
    public Tvalue eliminar(Tkey key) {
        if (key == null) {
            throw new NullPointerException("La llave no puede ser nula");
        }
        int codigoHash = comparador.getHashCode(key);
        int laPosicion = getPosicion(codigoHash, elementos.length);
        if (elementos[laPosicion] == null) {
            return null;
        }
        Tvalue valor = (Tvalue) elementos[laPosicion].eliminar(key);
        cantidadElementos--;
        if (elementos[laPosicion].estaVacio()) {
            elementos[laPosicion] = null;
        }
        return valor;
    }

    @Override
    public boolean estaVacio() {
        return cantidadElementos == 0;
    }

    @Override
    public List<Tkey> getLlaves() {
        List<Tkey> lista = new LinkedList<>();
        for (DiccionarioSecuencia<Tkey, Tvalue> elemento : elementos) {
            if (elemento == null) {
                continue;
            }

            lista.addAll(elemento.getLlaves());
        }
        return lista;
    }

    @Override
    public List<Tvalue> getValores() {
        List<Tvalue> lista = new LinkedList<>();
        for (DiccionarioSecuencia<Tkey, Tvalue> elemento : elementos) {
            if (elemento == null) {
                continue;
            }
            lista.addAll(elemento.getValores());
        }
        return lista;
    }

    private int getPosicion(int hash, int capacidad) {
        return (hash & Integer.MAX_VALUE) % capacidad;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
    private void rehash() {
        int newCap = elementos.length * 2 + 1;
        limite = (int) (newCap * factorCarga);
        DiccionarioSecuencia<Tkey, Tvalue>[] list = new DiccionarioSecuencia[newCap];
        for (DiccionarioSecuencia<Tkey, Tvalue> tabla : elementos) {
            if (tabla == null) {
                continue;
            }
            Iterator<Nodo<Tkey, Tvalue>> auxIterator = tabla.iterator();
            while (auxIterator.hasNext()) {
                Nodo<Tkey, Tvalue> nodo = auxIterator.next();
                int hash = comparador.getHashCode(nodo.getKey());
                int posicion = getPosicion(hash, list.length);
                if (list[posicion] == null) {
                    list[posicion] = new DiccionarioSecuencia<>(comparador);
                }
                list[posicion].insertar(nodo.getKey(), nodo.getValor());
            }
        }
        this.elementos = list;
    }
}
