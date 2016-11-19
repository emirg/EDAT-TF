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

    private Barco elem;
    private NodoBarco enlace;

    
    
    public NodoBarco(Barco elem) {
        this.elem = elem;
        this.enlace = null;

    }

    public NodoBarco(Barco elem, NodoBarco enlace) {

        this.elem = elem;
        this.enlace = enlace;

    }

    //////////////////////////////////////
    public Barco getElem() {

        return elem;

    }

    public void setElem(Barco elem) {
        this.elem = elem;
    }

    public NodoBarco getEnlace() {
        return enlace;
    }

    public void setEnlace(NodoBarco enlace) {
        this.enlace = enlace;
    }
}
