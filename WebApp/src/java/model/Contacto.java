/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author josec
 */
public class Contacto {
    
    private int contactoId;
    private String nombre;
    private String direccion;
    private List<Telefono> telefonos;

    public Contacto() {
        this.telefonos = new LinkedList<>();
    }

    public Contacto(int contactoId, String nombre, String direccion) {
        this();
        this.contactoId = contactoId;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public int getContactoId() {
        return contactoId;
    }

    public void setContactoId(int contactoId) {
        this.contactoId = contactoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setTelefonos(List<Telefono> telefonos) {
        this.telefonos = telefonos;
    }

    
}
