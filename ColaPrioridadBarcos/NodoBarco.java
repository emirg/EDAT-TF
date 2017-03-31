/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ColaPrioridadBarcos;

import Barco.Barco;

/**
 *
 * @author grios
 */
public class NodoBarco {
    /*
    La clase NodoBarco sera utilizada por la clase Cola que contendra al objeto Barco
    que representa y un enlace al siguiente nodo en la cola.
    */

    /////////////////////ATRIBUTOS////////////////////////////////
    private Barco elem;
    private NodoBarco enlace;


    /////////////////////CONSTRUCTORES////////////////////////////////
    public NodoBarco(Barco elem) { //Constructor que recibe por parametro al Barco y no tiene un enlace (inicialmente)
        this.elem = elem;
        this.enlace = null;

    }

    public NodoBarco(Barco elem, NodoBarco enlace) { //Constructor que inicializa el Barco y el enlace del nodo

        this.elem = elem;
        this.enlace = enlace;

    }

    /////////////////////METODOS////////////////////////////////
    public Barco getElem() { //Retorna el elemento barco del nodo

        return elem;

    }

    public void setElem(Barco elem) { //Establece un nuevo elemento barco
        this.elem = elem;
    }

    public NodoBarco getEnlace() { //Obtiene el nodo al cual se enlaza
        return enlace;
    }

    public void setEnlace(NodoBarco enlace) { //Establece el enlace con un nodo, deshaciendose del enlace previo (si existia)
        this.enlace = enlace;
    }
}
