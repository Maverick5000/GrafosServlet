/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

import diccionarios.Diccionario;
import model.Nodo;
import diccionarios.TablaHash;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import model.Arista;

/**
 *
 * @author Krauss
 */
public class Grafo<Tkey, Tcost, Tvalue> {

    private Diccionario<Tkey, Nodo<Tkey, Tcost, Tvalue>> vertices = new TablaHash<>();
    private int menor = Integer.MAX_VALUE;

    public Grafo() {
    }

    public void insertarVertice(Tkey key, Tvalue value) {
        if (key == null) {
            System.err.println("NO CONTIENE UN ID");
        }
        if (vertices.contieneLlave(key)) {
            System.err.println("EXISTE SIMILAR");
        }
        Nodo<Tkey, Tcost, Tvalue> nuevoVertice = new Nodo(key, value);
        vertices.insertar(key, nuevoVertice);
    }

    public Tvalue obtener(Tkey key) {
        if (key == null) {
            System.err.println("NO EXISTE ID");
        }
        if (!vertices.contieneLlave(key)) {
            System.err.println("NO CONTIENE LLAVE");
        }
        return vertices.obtener(key).getValor();
    }

    public Tvalue eliminarVertice(Tkey key) {
        if (key == null) {
            throw new IllegalArgumentException("El id del vertice no puede ser nulo");
        }

        if (!vertices.contieneLlave(key)) {
            throw new IllegalArgumentException("NO existe el vertice origen dentro del grafo");
        }

        List<Nodo<Tkey, Tcost, Tvalue>> nodos = vertices.getValores();
        for (Nodo<Tkey, Tcost, Tvalue> nodo : nodos) {
            if (nodo.contieneArista(key)) {
                nodo.eliminarArista(key);
            }
        }
        vertices.eliminar(key);
        return null;
    }

    public void insertarArista(Tkey origen, Tkey destino, Tcost costo) {
        if (!vertices.contieneLlave(origen)) {
            System.err.println("NO HAY LLAVE ORIGEN");
        }
        if (!vertices.contieneLlave(destino)) {
            System.err.println("NO HAY LLAVE DESTINO");
        }
        Nodo<Tkey, Tcost, Tvalue> $origen = vertices.obtener(origen);
        Nodo<Tkey, Tcost, Tvalue> $destino = vertices.obtener(destino);
        System.out.println("esta recibiendo de costo en grafo insertar: " + costo);
        $origen.insertarArista($destino, costo);
    }

    public void eliminarArista(Tkey origen, Tkey destino) {
        if (!vertices.contieneLlave(origen)) {
            System.err.println("NO HAY LLAVE ORIGEN PARA ELIMINAR");
        }
        if (!vertices.contieneLlave(destino)) {
            System.err.println("NO HAY LLAVE DESTINO PARA ELIMINAR");
        }
        Nodo<Tkey, Tcost, Tvalue> $origen = vertices.obtener(origen);
        $origen.eliminarArista(destino);
    }

    public List<Tkey> getIdsVertices() {
        return vertices.getLlaves();
    }

    public List<Nodo<Tkey, Tcost, Tvalue>> getValoresVertices() {
        return vertices.getValores();
    }

    public String mostrar() {
        String res = "";
        List<Nodo<Tkey, Tcost, Tvalue>> vertices = getValoresVertices();
        for (Nodo<Tkey, Tcost, Tvalue> vertice : vertices) {
            res += ("\n" + vertice.getId() + ": \n");
            List<Arista<Tkey, Tcost, Tvalue>> list = vertice.getAristas();
            for (Arista<Tkey, Tcost, Tvalue> nodo : list) {
                res += (" -> " + nodo.getDestino().getId().toString() + "(" + nodo.getCosto() + ")");
            }
            res += "\n";
        }
        return res;
    }

    public int getMenor() {
        return menor;
    }

    public void setMenor(int menor) {
        this.menor = menor;
    }

    public ArrayList<Ruta> getRutas(Tkey origen, Tkey destino) {
        ArrayList<Ruta> caminos = new ArrayList<>();
        Nodo<Tkey, Tcost, Tvalue> $origen = vertices.obtener(origen);
        Nodo<Tkey, Tcost, Tvalue> $destino = vertices.obtener(destino);
        Queue<Ruta<Tkey, Tcost, Tvalue>> cola = new LinkedList<>();
        Ruta<Tkey, Tcost, Tvalue> camino = new Ruta<>();
        camino.agregar($origen, 0);
        cola.add(camino);
        while (!cola.isEmpty()) {
            Ruta<Tkey, Tcost, Tvalue> actual = cola.poll();
            Nodo<Tkey, Tcost, Tvalue> ultimo = actual.ultima();
            if (ultimo.getId().equals($destino.getId())) {
                caminos.add(actual);
                continue;
            }

            List<Arista<Tkey, Tcost, Tvalue>> aristas = ultimo.getAristas();
            for (Arista<Tkey, Tcost, Tvalue> arista : aristas) {
                Nodo<Tkey, Tcost, Tvalue> destino$ = arista.getDestino();
                if (!actual.contiene(destino$)) {
                    Ruta<Tkey, Tcost, Tvalue> copia = actual.getCopia();
                    copia.agregar(arista.getDestino(), (Integer) arista.getCosto());
                    cola.add(copia);
                }
            }
        }

        return caminos;
    }

    public ArrayList<Nodo<Tkey, Tcost, Tvalue>> getMenor(ArrayList<Ruta> lista) {
        menor = Integer.MAX_VALUE;
        ArrayList<Nodo<Tkey, Tcost, Tvalue>> caminoMenor = new ArrayList<>();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getCosto() < menor) {
                caminoMenor = lista.get(i).getLista();
                menor = lista.get(i).getCosto();
            }
        }
        return caminoMenor;
    }
}