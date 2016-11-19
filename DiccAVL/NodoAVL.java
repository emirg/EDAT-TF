/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiccAVL;

import Puerto.Puerto;

/**
 *
 * @author grios
 */
public class NodoAVL {

    private String clave;
    private Puerto dato;
    private int altura;
    private NodoAVL izq, der;

    public NodoAVL() {
        this.clave = null;
        this.dato = null;
        this.altura = 0;
        this.izq = null;
        this.der = null;
    }

    public NodoAVL(String clave, Puerto dato) {
        this.clave = clave;
        this.dato = dato;
        this.izq = null;
        this.der = null;
        this.altura = 0;
    }

    public boolean tieneHijos() {
        return this.izq != null || this.der != null;
    }

    public NodoAVL getIzq() {
        return this.izq;
    }

    public NodoAVL getDer() {
        return this.der;
    }

    public int getAlt() {
        return this.altura;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public void setDato(Puerto dato) {
        this.dato = dato;
    }

    public Puerto getDato() {
        return this.dato;
    }

    public boolean setDer(NodoAVL der) {
        this.der = der;
        return true;
    }

    public boolean setIzq(NodoAVL izq) {
        this.izq = izq;
        return true;
    }

    public void setAlt(int altura) {
        this.altura = altura;
    }

    public int getBalance() {
        int alt1 = -1, alt2 = -1, bal = 0;
        if (this.getIzq() != null || this.getDer() != null) {
            if (this.getIzq() != null) {
                alt1 = this.getIzq().getAlt();
            }
            if (this.getDer() != null) {
                alt2 = this.getDer().getAlt();
            }
            bal = alt1 - alt2;
        }
        return bal;
    }

    public boolean esHoja() {
        boolean hoja = false;
        if (this.der == null && this.izq == null) {
            hoja = true;
        }
        return hoja;
    }

    public boolean tieneIzq() {
        return this.izq != null;
    }

    public boolean tieneDer() {
        return this.der != null;
    }
}
