/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import java.util.ArrayList;
import model.Nodo;

/**
 *
 * @author Krauss
 */
public class Ruta<K, C, V> {

        ArrayList<Nodo<K, C, V>> list;
        int costo;

        public Ruta() {
            list = new ArrayList<>();
            costo = 0;
        }

        public Ruta(ArrayList<Nodo<K, C, V>> list, int costo) {
            this.list = list;
            this.costo = costo;
        }

        public ArrayList<Nodo<K, C, V>> getLista() {
            return list;
        }

        public void setLista(ArrayList<Nodo<K, C, V>> list) {
            this.list = list;
        }

        public int getCosto() {
            return costo;
        }

        public void setCosto(int costo) {
            this.costo = costo;
        }

        public void agregar(Nodo<K, C, V> nodo, int costo) {
            this.list.add(nodo);
            this.costo += costo;
        }

        public boolean contiene(Nodo<K, C, V> nodo) {
            for (Nodo<K, C, V> nodo1 : list) {
                if (nodo.getId() == nodo1.getId()) {
                    return true;
                }
            }
            return false;
        }

        public Nodo<K, C, V> ultima() {
            return list.get(list.size() - 1);
        }

        public Ruta getCopia() {
            Ruta camino = new Ruta();
            camino.setLista((ArrayList) this.getLista().clone());
            camino.setCosto(this.getCosto());
            return camino;
        }

        @Override
        public String toString() {
            return costo + "";
        }
    }
