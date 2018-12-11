/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author josec
 */
public class Telefono {
    private int telefonoId;
    private String tipo; //Casa, Personal, Trabajo, Otro
    private String nroTelefono;
    private int contactoId;

    public Telefono(int telefonoId, String tipo, String nroTelefono, int contactoId) {
        this.telefonoId = telefonoId;
        this.tipo = tipo;
        this.nroTelefono = nroTelefono;
        this.contactoId = contactoId;
    }

    public Telefono(String tipo, String nroTelefono) {
        this.tipo = tipo;
        this.nroTelefono = nroTelefono;
    }

    public Telefono() {

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNroTelefono() {
        return nroTelefono;
    }

    public void setNroTelefono(String nroTelefono) {
        this.nroTelefono = nroTelefono;
    }

    public int getTelefonoId() {
        return telefonoId;
    }

    public void setTelefonoId(int telefonoId) {
        this.telefonoId = telefonoId;
    }

    public int getContactoId() {
        return contactoId;
    }

    public void setContactoId(int contactoId) {
        this.contactoId = contactoId;
    }
}
