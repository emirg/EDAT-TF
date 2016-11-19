/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import Puerto.Puerto;

/**
 *
 * @author grios
 */
public class NodoVert {

    ////////////////////ATRIBUTOS///////////////////////////////
    private Puerto elem;
    private NodoVert sigVertice;
    private NodoAdy primerAdy;

    ////////////////////CONSTRUCTORES///////////////////////////
    public NodoVert(Puerto elemento,NodoVert siguiente) {
        elem = elemento;
        sigVertice = siguiente;
        primerAdy = null;

    }

    /////////////////////METODOS////////////////////////////////
    public Puerto getElem() {
        return elem;
    }

    public NodoVert getSigVertice() {
        return sigVertice;
    }

    public NodoAdy getPrimerAdy() {
        return primerAdy;
    }

    public void setElem(Puerto elem) {
        this.elem = elem;
    }

    public void setSigVertice(NodoVert sigVertice) {
        this.sigVertice = sigVertice;
    }

    public void setPrimerAdy(NodoAdy primerAdy) {
        this.primerAdy = primerAdy;
    }

}
