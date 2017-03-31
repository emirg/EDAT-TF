/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package PuertosLista;

import Puerto.Puerto;







/**
 *
 * @author grios
 */
public class NodoPuerto {
    /*
    La clase NodoPuerto sera utilizada por la clase Lista para enlazar distintos nodos y
    asi poder almacenar informacion de distintos puertos.
    El NodoPuerto contiene un objeto del tipo Puerto, el cual contendra la informacion de
    dicho puerto, y tambien tiene un atributo del tipo NodoPuerto le cual sera el enlace
    al siguiente nodo en la lista
    */

    /////////////////////ATRIBUTOS////////////////////////////////
    private Puerto elem;
    private NodoPuerto enlace;


    /////////////////////CONSTRUCTORES////////////////////////////////
    public NodoPuerto(Puerto elem) {
        this.elem = elem;
        this.enlace = null;

    }

    public NodoPuerto(Puerto elem, NodoPuerto enlace) {

        this.elem = elem;
        this.enlace = enlace;

    }

    /////////////////////METODOS////////////////////////////////
    public Puerto getElem() {

        return elem;

    }

    public void setElem(Puerto elem) {
        this.elem = elem;
    }

    public NodoPuerto getEnlace() {
        return enlace;
    }

    public void setEnlace(NodoPuerto enlace) {
        this.enlace = enlace;
    }
}
