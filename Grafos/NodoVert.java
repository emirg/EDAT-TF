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

    /*
    La clase NodoVert representara los nodos vertices de la clase Grafo.
    Los nodos vertices son aquellos que representan los nodos (Puertos) de un grafo,
    por lo tanto tendran un atributo para el elemento que representan y luego contiene
    dos enlaces, uno hacia el siguiente nodo vertice que le sigue en la lista, y otro enlace
    para enlazar la lista de nodos adyacentes que representaran los arcos del grafo
     */

    ////////////////////ATRIBUTOS///////////////////////////////
    private Puerto elem; //Elemento que contiene el nodo para representar que un nodo es un puerto
    private NodoVert sigVertice; //Enlace con el siguiente vertice
    private NodoAdy primerAdy; //Enlace con el primer nodo adyacente

    ////////////////////CONSTRUCTORES///////////////////////////
    public NodoVert(Puerto elemento, NodoVert siguiente,NodoAdy primer) {
        elem = elemento;
        sigVertice = siguiente;
        primerAdy = primer;

    }
    
    public NodoVert(Puerto elemento, NodoVert siguiente) {
        elem = elemento;
        sigVertice = siguiente;
        primerAdy = null;

    }

    public NodoVert(Puerto elemento) {
        elem = elemento;
        sigVertice = null;
        primerAdy = null;
    }

    public NodoVert() {

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
