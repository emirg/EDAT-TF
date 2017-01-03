/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

/**
 *
 * @author grios
 */
public class NodoAdy {

    ////////////////////ATRIBUTOS///////////////////////////////
    private NodoVert vertice;
    private NodoAdy sigAdyacente;
    private int etiqueta;

    ////////////////////CONSTRUCTORES///////////////////////////
    public NodoAdy(NodoVert n, int etiq) {
        vertice = n;
        sigAdyacente = null;
        etiqueta = etiq;

    }
    
    public NodoAdy(NodoVert n, NodoAdy a, int etiq) {
        vertice = n;
        sigAdyacente = a;
        etiqueta = etiq;

    }
    
    /////////////////////METODOS////////////////////////////////

    public NodoVert getVertice() {
        return vertice;
    }

    public NodoAdy getSigAdyacente() {
        return sigAdyacente;
    }
    
    public int getEtiqueta(){
        return etiqueta;
    }  
    
    public void setVertice(NodoVert vertice) {
        this.vertice = vertice;
    }

    public void setSigAdyacente(NodoAdy sigAdyacente) {
        this.sigAdyacente = sigAdyacente;
    }
    
    public void setEtiqueta(int etiq){
        this.etiqueta = etiq;
    }
    
    

}
