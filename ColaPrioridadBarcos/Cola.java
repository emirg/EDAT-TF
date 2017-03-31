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
public class Cola {

    /*
    La clase Cola sera utilizada en la implementacion de la clase ColaPrio, la cual contendra un
    arreglo de dimension 3 del tipo Cola. De esta forma, la cola contenida en la primera posicion
    del arreglo de colas sera la cola para los objetos de prioridad mas alta, y asi analogamente
    para las demas posiciones del arreglo.
    Consiste en 2 atributos del tipo NodoBarco los cuales indicaran el inicio y el fin de  la cola.
    Cada NodoBarco tendra un objeto Barco y un enlace al siguiente NodoBarco con el que se enlazara.
    De esta forma, la cola sera una coleccion de nodos enlazados.
     */
    /////////////////////ATRIBUTOS////////////////////////////////
    private NodoBarco frente; //NodoBarco inicial de la cola
    private NodoBarco fin; //NodoBarco final de la cola (El enlace de este nodo sera null)

    /////////////////////CONSTRUCTORES////////////////////////////////
    public Cola() { //Constructor vacio

        this.frente = null;
        this.fin = null;
        
    }

    /////////////////////METODOS////////////////////////////////
    public boolean poner(Barco elem) {
        /*
        Similar al metodo insertar de otras estructuras de datos. El metodo recibira un objeto Barco
        por parametro, creara un nuevo objeto NodoBarco el cual sera inicializado con el Barco enviado
        por parametro y se lo colocara al final de la cola.
        En el caso que la cola este vacia, es decir, que el NodoBarco frente sea null, el NodoBarco
        recien creado se volvera el frente de la cola
         */
        
        boolean exito = false;
        NodoBarco n1 = new NodoBarco(elem); //Se crea el NodoBarco a colocar

        if (this.frente == null) { //Si la cola esta vacia se lo coloca en el frente
            this.frente = n1;
            this.fin = n1;
            exito = true;
            
        } else { //En caso contrario, se lo coloca al final de la cola, cambiando asi el NodoBarco fin
            this.fin.setEnlace(n1);
            this.fin = n1;
            exito = true;
        }
        
        return exito;
    }
    
    public boolean sacar() {
        /*
      Metodo encargado de quitar el ultimo elemento de la cola. Justamente por la naturaleza del TDA Cola,
      no se necesita enviar por parametro el objeto de la cola que se desea sacar. Como la Cola es una lista
      FIFO (first-in first-out), se sabe que el proximo elemento a sacar sera el frente de la cola
         */
        
        if (this.frente == null) { //Si la cola esta vacia, entonces el metodo no podra sacar elementos de la misma
            return false; //Por lo que retornara false, denotando asi que la operacion no tuvo exito o cambio sobre la cola
        } else {
            this.frente = this.frente.getEnlace(); //Si la cola tiene un frente, entonces se cambia el enlace del NodoBarco frente, por el siguiente NodoBarco en la cola
            if (this.frente == null) { //Si el NodoBarco eliminado era el ultimo de la cola, se setea el NodoBarco fin a null tambien;
                this.fin = null;
            }
            return true;
        }
        
    }
    
    public Barco obtenerFrente() { //Retorna un objeto de tipo Barco el cual corresponde al elemento contenido por el NodoBarco frente
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
    
    public void vaciar() {  //Setea todos los elementos de la cola a null, de esta forma la estructura quedara vacia
        this.frente = null;
        this.fin = null;
    }
    
    public String toString() { //Retorna un String que representa el contenido de la Cola
        NodoBarco aux = this.frente;
        String col = " ";
        while (aux != null) {
            col = col + aux.getElem();
            aux = aux.getEnlace();
        }
        return col;
        
    }
    
    public Cola clonar() { //Retorna un objeto de tipo Cola, cuyos elementos representan los mismos que la cola sobre la cual fue aplicada el metodo
        Cola c2 = new Cola();
        NodoBarco puntero = this.frente.getEnlace();
        NodoBarco aux1 = new NodoBarco(this.frente.getElem());        
        c2.frente = aux1;
        c2.fin = aux1;
        while (puntero != null) {
            aux1 = new NodoBarco(puntero.getElem());
            c2.fin.setEnlace(aux1);
            c2.fin = aux1;
            puntero = puntero.getEnlace();
        }
        
        return c2;
    }
    
}
