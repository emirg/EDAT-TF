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

    private Puerto elem;
    private NodoPuerto enlace;

    
    
    public NodoPuerto(Puerto elem) {
        this.elem = elem;
        this.enlace = null;

    }

    public NodoPuerto(Puerto elem, NodoPuerto enlace) {

        this.elem = elem;
        this.enlace = enlace;

    }

    //////////////////////////////////////
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
