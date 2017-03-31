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
    /*
    La clase NodoAVL sera utilizada por la clase DiccAVL la cual es una implementacion de un TDA diccionario
    utilizando la estructura de datos Arbol AVL. El NodoAVL sera el encargado de representar los nodos del
    arbol, conteniendo asi la clave por la cual se accedera a la informacion del puerto, un objeto del tipo
    puerto, un entero altura que indica a la altura en la que se encuentra el nodo dado y luego dos atributos
    del tipo NodoAVL que seran los nodos hijos del nodo.

    */

    /////////////////////ATRIBUTOS////////////////////////////////
    private String clave;
    private Puerto dato;
    private int altura;
    private NodoAVL izq, der;

    /////////////////////CONSTRUCTORES////////////////////////////////
    public NodoAVL() { //Constructor vacio
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

    /////////////////////METODOS////////////////////////////////
    public boolean tieneHijos() { //Metodo creado para una organizacion mas modular que me permite saber si un nodo tiene o no hijos
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

    public int getBalance() { //Metodo que retorna el balance de un nodo (Informacion util para comprobar si se requiere balancear el arbol)
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

    public boolean esHoja() { //Metodo creado para una organizacion mas modular que me permite saber si un nodo es hoja o no
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
