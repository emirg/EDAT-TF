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
public class Grafo {

    ////////////////////ATRIBUTOS///////////////////////////////
    private NodoVert inicio;

    ////////////////////CONSTRUCTORES///////////////////////////
    public Grafo() {
        inicio = null;

    }

    /////////////////////METODOS////////////////////////////////
    public boolean insertarVertice(String clave, Puerto p) {
        boolean exito = false;
        NodoVert n = this.ubicarVertice(clave);
        if (n == null) {
            this.inicio = new NodoVert(p, this.inicio);
            exito = true;
        }

        return exito;
    }

    public boolean insertarArco(String vert1, String vert2, int etiqueta) {
        boolean exito = false;
        NodoVert n1 = this.ubicarVertice(vert1);
        NodoVert n2 = this.ubicarVertice(vert2);
        NodoAdy n = new NodoAdy(n2, etiqueta);
        NodoAdy n3 = new NodoAdy(n1, etiqueta);
        NodoAdy ultAdy = null;

        if (n1.getPrimerAdy() == null) {
            n1.setPrimerAdy(n);

        } else {
            ultAdy = n1.getPrimerAdy();
            while (ultAdy.getSigAdyacente() != null) {
                ultAdy = ultAdy.getSigAdyacente();
            }

            ultAdy.setSigAdyacente(n);

        }
        if (n2.getPrimerAdy() == null) {
            n2.setPrimerAdy(n3);
            exito = true;
        } else {
            ultAdy = n2.getPrimerAdy();
            while (ultAdy.getSigAdyacente() != null) {
                ultAdy = ultAdy.getSigAdyacente();
            }

            ultAdy.setSigAdyacente(n);

            exito = true;
        }

        return exito;
    }

    public boolean existeCamino(String vert1, String vert2) {
        boolean existe = false;
        NodoVert n1 = ubicarVertice(vert1);
        NodoVert n2 = ubicarVertice(vert2);

        return existe;
    }
  
    ///////////////////METODOS PRIVADOS/////////////////////////
    private NodoVert ubicarVertice(String buscado) {
        NodoVert aux = inicio;
        while (aux != null && !aux.getElem().getNombre().equalsIgnoreCase(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;

    }

}
