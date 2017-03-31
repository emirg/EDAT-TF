/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import PuertosLista.Lista;

/**
 *
 * @author emiliano
 */
public class ResultadoCamino {

    /*
    La clase ResultadoCamino es utilizada en los metodos de busqueda de caminos
    para poder devolver un elemento que contenga tanto una lista que representara
    el camino obtenido por el metodo, como un entero el cual representara una distancia
    en millas o una cantidad de puertos intermedios (dependera del metodo en el cual
    se utilice)
     */
    ////////////////////ATRIBUTOS///////////////////////////////
    private Lista camino; //Lista que representara el camino entre dos nodos
    private int cantPuertos; //Entero utilizado para representar una distancia en millas o una cantidad de puertos intermedios (Dependera de su uso en el metodo)
    private double millas;

    ////////////////////CONSTRUCTORES///////////////////////////
    public ResultadoCamino() {
        camino = null;
        cantPuertos = 0;
        millas = 0;
    }

    public ResultadoCamino(Lista camino, int cant) {
        this.camino = camino;
        this.cantPuertos = cant;

    }

    public ResultadoCamino(Lista camino, double millas) {
        this.camino = camino;
        this.millas = millas;
    }

    public ResultadoCamino(Lista camino, int cant, double millas) {
        this.camino = camino;
        this.cantPuertos = cant;
        this.millas = millas;

    }

    /////////////////////METODOS////////////////////////////////
    public Lista getCamino() {
        return camino;
    }

    public void setCamino(Lista camino) {
        this.camino = camino;
    }

    public int getCantPuertos() {
        return cantPuertos;
    }

    public void setCantPuertos(int cant) {
        this.cantPuertos = cant;
    }

    public double getMillas() {
        return millas;
    }

    public void setMillas(double millas) {
        this.millas = millas;
    }

}
