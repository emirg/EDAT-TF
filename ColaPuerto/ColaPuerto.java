/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColaPuerto;

import Puerto.Puerto;
import PuertosLista.NodoPuerto;

/**
 *
 * @author emiliano
 */
public class ColaPuerto {

    /*
    La clase ColaPuerto se utiliza durante la implementacion del metodo anchuraDesde, el cual
    es un metodo recursivo utilizado por el metodo listarEnAnchura.
    El metodo anchuraDesde necesita la utilizacion de una estructura del tipo Cola por lo
    que esta clase es utilizada exclusivamente por la clase Grafo y es solo una Cola
    pero de Puertos, en vez de Barcos (Como se vio previamente).
    */
    /////////////////////ATRIBUTOS////////////////////////////////
    private NodoPuerto frente;
    private NodoPuerto fin;

    /////////////////////CONSTRUCTORES////////////////////////////////
    public ColaPuerto() { //Constructor vacio

        this.frente = null;
        this.fin = null;

    }

    /////////////////////METODOS////////////////////////////////
    public boolean poner(Puerto elem) {
      /*
      Similar al metodo insertar de otras estructuras de datos. El metodo recibira un objeto Puerto
      por parametro, creara un nuevo objeto NodoPuerto el cual sera inicializado con el Puerto enviado
      por parametro y se lo colocara al final de la cola.
      En el caso que la cola este vacia, es decir, que el NodoPuerto frente sea null, el NodoPuerto
      recien creado se volvera el frente de la cola
      */

        NodoPuerto n1 = new NodoPuerto(elem);

        if (this.frente == null) {
            this.frente = n1;
            this.fin = n1;

        } else {
            this.fin.setEnlace(n1);
            this.fin = n1;

        }

        return true;
    }

    public boolean sacar() {
      /*
      Metodo encargado de quitar el ultimo elemento de la cola. Justamente por la naturaleza del TDA Cola,
      no se necesita enviar por parametro el objeto de la cola que se desea sacar. Como la Cola es una lista
      FIFO (first-in first-out), se sabe que el proximo elemento a sacar sera el frente de la cola
      */

        if (this.frente == null) {
            return false;

        } else {

            this.frente = this.frente.getEnlace();
            if (this.frente == null) {
                this.fin = null;
            }
            return true;
        }

    }

     public Puerto obtenerFrente() { //Retorna un objeto de tipo Puerto el cual corresponde al elemento contenido por el NodoPuerto frente
        return this.frente.getElem();
    }

    public boolean esVacia() { //Retorna un booleano el cual indicara si la cola esta vacias, es decir, si su frente y fin son null
        boolean vacia;
        if (this.frente == null && this.fin == null) {
            vacia = true;
        } else {
            vacia = false;
        }

        return vacia;
    }

    public void vaciar() { //Setea todos los elementos de la cola a null, de esta forma la estructura quedara vacia
        this.frente = null;
        this.fin = null;
    }

    public String toString() { //Retorna un String que representa el contenido de la Cola
        NodoPuerto aux = this.frente;
        String col = " ";
        while (aux != null) {
            col = col + aux.getElem();
            aux = aux.getEnlace();
        }
        return col;

    }

    public ColaPuerto clonar() { //Retorna un objeto de tipo Cola, cuyos elementos representan los mismos que la cola sobre la cual fue aplicada el metodo
        ColaPuerto c2 = new ColaPuerto();
        NodoPuerto aux1 = this.frente;
        while (aux1 != null) {
            c2.poner(aux1.getElem());
            aux1 = aux1.getEnlace();
        }

        return c2;
    }

}
