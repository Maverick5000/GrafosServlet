/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package diccionarios;

import java.util.List;

/**
 *
 * @author Krauss
 */
public abstract class Diccionario <Tkey, Tvalue> {
    
    protected int cantidadElementos;
    Comparador<Tkey> comparador;
    
    public Diccionario() {
        this.comparador = new ComparadorGenerico<>();
    }
    
    public Diccionario(Comparador comparador){
        this.comparador = comparador;
    }
    
    public abstract void insertar(Tkey key, Tvalue value);
    public abstract Tvalue obtener(Tkey key);
    public abstract Tvalue eliminar(Tkey key);
    public abstract boolean contieneLlave(Tkey key);
    public abstract boolean estaVacio();
    public abstract List<Tkey> getLlaves();
    public abstract List<Tvalue> getValores();

    public int getCantidadElementos() {
        return cantidadElementos;
    }

    
}
