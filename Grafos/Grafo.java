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
        if (n1 != null && n2 != null && !existeArco(vert1, vert2)) {
            n1.setPrimerAdy(new NodoAdy(n2, n1.getPrimerAdy(), etiqueta));
            n2.setPrimerAdy(new NodoAdy(n1, n2.getPrimerAdy(), etiqueta));
        }

        return exito;
    }

//    public boolean eliminarVertice
    public boolean eliminarArco(String vert1, String vert2) { //Elimina solo uno de los arcos dirigidos (en caso de querer eliminar un arco no dirigido se debera llamar nuevamente con argumentos invertidos)
        boolean exito = false;

        NodoVert ini = this.ubicarVertice(vert1), fin = this.ubicarVertice(vert2);

        if (existeArco(vert1, vert2)) {
            NodoAdy enlaceIni = this.enlace(ini, fin);
            NodoAdy pre = this.preEnlace(ini, fin);
            if (enlaceIni == ini.getPrimerAdy()) {
                ini.setPrimerAdy(enlaceIni.getSigAdyacente());
                enlaceIni.setSigAdyacente(null);
                exito = true;
            } else {
                pre.setSigAdyacente(enlaceIni.getSigAdyacente());
                enlaceIni.setSigAdyacente(null);
                exito = true;
            }
        }

        return exito;
    }

//    public boolean existeVertice
    public boolean existeArco(String v1, String v2) {
        boolean existe = false;
        NodoVert ini = this.ubicarVertice(v1), fin = this.ubicarVertice(v2);

        NodoAdy sig = ini.getPrimerAdy();
        while (sig != null) {
            if (sig.getVertice() == fin) {
                existe = true;
            }
            sig = sig.getSigAdyacente();
        }
        return existe;
    }

    @Override
    public String toString() {
        String res = "";
        NodoVert ini = this.inicio;
        while (ini != null) {
            res = res + ini.getElem().getNombre() + " --> ";
            NodoAdy iniA = ini.getPrimerAdy();
            while (iniA != null) {
                res = res + iniA.getVertice().getElem().getNombre() + " / ";
                iniA = iniA.getSigAdyacente();
            }
            res = res + "\n";

            ini = ini.getSigVertice();

        }
        return res;
    }

    // public boolean existeCamino(){}
    // public Lista caminoMasCorto(){}
    // public Lista caminoMasLargo(){}
    // public Lista busquedaEnProfundidad(){}
    // public Lista busquedaEnAnchura(){}
    // public boolean esVacio(){}
    // public Grafo clonar(){}
    
    ///////////////////METODOS PRIVADOS/////////////////////////
    private NodoVert ubicarVertice(String buscado) {
        NodoVert aux = inicio;
        while (aux != null && !aux.getElem().getNombre().equalsIgnoreCase(buscado)) {
            aux = aux.getSigVertice();
        }
        return aux;

    }

    private NodoAdy enlace(NodoVert ini, NodoVert fin) {
        boolean existe = false;
        NodoAdy sig = ini.getPrimerAdy();
        while (sig != null && sig.getVertice() != fin) {
            sig = sig.getSigAdyacente();
        }
        return sig;
    }

    private NodoAdy preEnlace(NodoVert ini, NodoVert fin) { //Parece ser que funciona....
        NodoAdy enlace = this.enlace(ini, fin);
        NodoAdy pre = ini.getPrimerAdy();
        if (enlace == ini.getPrimerAdy()) {
            pre = null; //Si el nodo anterior es el vertice cabecera entonces devuelve null
        } else {
            while (enlace != null && pre.getSigAdyacente() != enlace) {
                pre = pre.getSigAdyacente();
            }
        }
        return pre;
    }
}
