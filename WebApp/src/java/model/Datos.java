/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josec
 */
public class Datos {
    
    
    
    private List<Contacto> contactos;
    private int currentId;
    private static Datos instancia;

    private Datos(){
        contactos = new ArrayList<>();

        Contacto temp = new Contacto(1, "Mariangela Cardona", "Av. Beni 2do Anillo");
        temp.getTelefonos().add(new Telefono("Trabajo","9484668486"));
        temp.getTelefonos().add(new Telefono("Movil","8484844"));

        contactos.add(temp);
        
        contactos.add(new Contacto(2, "Pancracio Peralta", "Av. Tres pasos al frente"));
        contactos.add(new Contacto(3, "Claudio Arrazola", "Av. Banzer"));
        contactos.add(new Contacto(4, "Carolina Pinto", "Av. Santos Doumont"));
        currentId = 4;
    }

    public static Datos getInstancia(){
        if(instancia == null)
            instancia = new Datos();
        return instancia;
    }

    public List<Contacto> getContactos() {
        return contactos;
    }

    public Contacto getContactoById(int id){
        if(id <= 0)
            return null;
        for (Contacto obj : contactos) {
            if(obj.getContactoId() == id)
                return  obj;
        }
        return null;
    }

    public void insertar(Contacto obj){
        currentId++;
        obj.setContactoId(currentId);
        contactos.add(obj);
    }

    public void eliminar(int contactoId){
        int index = 0;
        for (Contacto obj : contactos) {
            if(obj.getContactoId() == contactoId){
                contactos.remove(index);
                break;
            }
            index++;
        }
    }
}

