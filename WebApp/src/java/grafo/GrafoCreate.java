/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grafo;

/**
 *
 * @author Krauss
 */
public class GrafoCreate {
    
    private static Grafo<String,Integer,String> cost;
    private static Grafo<String,Integer,String> dist;
    
    public static Grafo<String, Integer, String>  cost (){
        if(cost == null){
            cost =  new Grafo<>();
        }
        return cost;
    }
    
    public static Grafo<String, Integer, String>  dist (){
        if(dist == null){
            dist =  new Grafo<>();
        }
        return dist;
    }
}
