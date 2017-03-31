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

    /*
    La clase NodoAdy es utilizada para crear una lista de nodos adyacentes a los nodos
    vertice de un grafo, donde los nodos adyacentes representaran un arco con el
    nodo vertice al cual pertenecen, con el nodo vertice el cual representan.
    */
    ////////////////////ATRIBUTOS///////////////////////////////
    private NodoVert vertice; //Este sera el nodo el cual conectara con el nodo vertice que contiene la lista de adyacencia
    private NodoAdy sigAdyacente; //Enlace con el siguiente nodo adyacente de la lista
    private int etiqueta; //Este entero representara la etiqueta del arco entre los dos nodos

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
