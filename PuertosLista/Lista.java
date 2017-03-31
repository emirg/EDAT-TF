/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuertosLista;

import Grafos.Grafo;
import Grafos.NodoVert;
import Puerto.Puerto;

/**
 *
 * @author grios
 */
public class Lista {

    /*
    La clase Lista sera utilizada principalmente en la clase Grafo, ya que seran muy recurrentes
    las situaciones en las que se necesite retornar o mostrar mas de un puerto por metodo. Por lo tanto
    la estructura mas adecuada y que nos permitira una buena administracion de los puertos a retornar
    sera la lista, ya que como no tendremos una cantidad fija de puertos a retornar por metodo, la lista
    nos da esa posibilidad de dinamismo.
    Tambien sera utilizada por clases de menor impacto, como la clase ResultadoCamino que devolvera
    una lista de puertos indicando un camino.
    La lista consiste de un NodoPuerto el cual contendra la informacion del puerto y el enlace al
    siguiente nodo de la lista. Por cuestiones de eficiencia, tambien se tiene un atributo del tipo
    entero, el cual mantendra la longitud de la lista. En este caso se sacrifica seguridad y robustez
    por eficiencia, ya que existe la posibilidad que haya problemas en la actulizacion del atributo
    longitud y que no corresponda a la verdadera longitud, por lo que se debera ser minucioso a la hora
    de hacer cambios en la lista.
     */

    /////////////////////ATRIBUTOS////////////////////////////////
    private NodoPuerto cabecera;
    private int longitud;

    /////////////////////CONSTRUCTORES////////////////////////////////
    public Lista() {

        cabecera = null;
        longitud = 0;

    }

    /////////////////////METODOS////////////////////////////////
    public boolean insertar(Puerto nuevoElem, int pos) { //Dada una posicion en la lista, inserta el objeto Puerto enviado por parametro, dentro de un nodo en la lista
        boolean exito = false;
        if (pos == 1) { //La primer posicion de la lista es 1, no se comienza la lista en la posicion 0, esta es una posicion no valida
            NodoPuerto n1 = new NodoPuerto(nuevoElem, this.cabecera);
            cabecera = n1; //Como es la posicion inicial, el nuevo nodo sera la nueva cabecera
            longitud++;
            exito = true;
        }
        if (pos > 1 && pos <= longitud + 1) { //En caso que se inserte en una posicion mayor a la inicial o menor a la longitud + 1 (Se debe considera el caso en el que elemento sea el nuevo final de la lista)

            NodoPuerto temp = cabecera;
            int i = 1;
            while (i < pos - 1) { //Busco el nodo en la posicion directamente anterior a la posicion a insertar
                temp = temp.getEnlace();
                i++;
            }
            NodoPuerto n1 = new NodoPuerto(nuevoElem, temp.getEnlace()); //Creo el nuevo nodo y lo enlazo a los nodos siguientes en la lista
            temp.setEnlace(n1);
            longitud++;
            exito = true;
        }

        return exito;
    }

    public boolean insertar(Puerto nuevoElem) {
        return insertar(nuevoElem, longitud + 1);
    }

    public boolean eliminar(int pos) { //Dada una posicion en la lista, elimino el elemento en dicha posicion
        boolean exito = false;
        NodoPuerto temp = cabecera;
        if (pos > 1 && pos <= this.longitud) { //En caso que se inserte en una posicion mayor a la inicial o menor/igual a la longitud
            int i = 1;
            while (i < pos - 1) { //Busco el nodo directamente anterior al nodo a eliminar
                temp = temp.getEnlace();
                i++;

            }
            temp.setEnlace(temp.getEnlace().getEnlace());  //Establezco el enlace del nodo anterior con el nodo siguiente al nodo a eliminar, de esta forma, el nodo a eliminar queda sin enlaces, por lo que sera eliminado por el garbage collector
            longitud--;
            exito = true;

        }
        if (pos == 1) { //Si el elemento a eliminar es el primero de la lista, el proceso es mas facil
            cabecera = cabecera.getEnlace(); //Solo habra que establecer la cabecera como el segundo nodo de la lista (o null en caso que haya un solo nodo en la lista)
            longitud--;
            exito = true;
        }

        return exito;
    }

    @Override
    public String toString() { //Metodo que retorna un String con una representacion de la estructura
        String lista = "[";
        NodoPuerto aux = cabecera;
        int i = 0;
        while (i <= this.longitud - 1 && this.cabecera != null) {
            lista = lista + aux.getElem().getNombre() + " , ";
            aux = aux.getEnlace();
            i++;
        }
        lista = lista + "]";
        return lista;
    }

    public boolean esVacia() { //Retorna un booleano que indicara si la lista esta vacia o no (True = Lista vacia)
        if (cabecera == null) {
            return true;
        } else {
            return false;
        }
    }

    public void vaciar() { //Vacia la lista
        cabecera = null;
    }

    public Puerto recuperar(int pos) { //Dada una posicion, retorna el objeto Puerto contenido por el NodoPuerto en la posicion pos
        int i = 1;
        NodoPuerto temp = cabecera;
        Puerto recuperado = null;
        if (temp != null) { //Comprueba que la lista no este vacia, para evitar una excepcion del tipo NullPointer
            while (i < pos) {
                temp = temp.getEnlace();
                i++;
            }
            recuperado = temp.getElem();
        }
        return recuperado;

    }

    public int localizar(Puerto elem) { //Dado un elemento del tipo Puerto, busca en que NodoPuerto se encuentra y retorna la posicion de dicho NodoPuerto
        int i = 1;
        Puerto elemloc = null;
        NodoPuerto temp = cabecera;
        if (temp != null) { //Comprueba que la lista no este vacia, para evitar una excepcion del tipo NullPointer
            while (elemloc != elem && i <= this.longitud) {
                elemloc = temp.getElem();
                temp = temp.getEnlace();
                i++;
            }
        }

        if (elemloc != elem) {
            i = 0;
        }

        return i - 1;

    }

    public int longitud() { //Retorna la longtud de la lista acorde al atributo longitud
        return this.longitud;
    }

    public Lista clonar() { //Retorna un objeto del tipo lista el cual representa la misma lista a clonar
        int i = 1;

        Lista l = new Lista(); //Inicializo la estructura a retornar
        NodoPuerto n = new NodoPuerto(this.cabecera.getElem()); //Inicializo un nodo vertice n, el cual luego sera utilizado para iterar sobre el grafo inicial
        l.setCabecera(n);
        l.longitud++;
        NodoPuerto puntero = this.cabecera.getEnlace();

        while (puntero != null) { //Creo una estructura iterativa para insertar todos los nodos vertice al nuevo grafo
            n.setEnlace(new NodoPuerto(puntero.getElem()));
            l.longitud++;
            n = n.getEnlace();
            puntero = puntero.getEnlace();

        }
        return l;

    }

    public void setLista(Lista l) { //Dada una lista l enviada por parametro, setea el contenido de la lista actual a la lista l
        this.cabecera = l.cabecera;
        this.longitud = l.longitud;
    }
    
    public void setCabecera(NodoPuerto n){
        this.cabecera = n;
    }

}
