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
public interface Comparador <E> {
    
    public boolean esIgual(E objA, E objB);

    public int getHashCode(E KEY);
    
}
