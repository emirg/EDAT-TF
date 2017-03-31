/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColaPrioridadBarcos;

import Barco.Barco;

/**
 *
 * @author grios
 */
public class ColaPrio {

    /*
    La clase ColaPrio (Cola de prioridad) sera utilizada para mantener una cola en cada puerto pero donde
    se considerara la prioridad que tiene un barco para poder entrar a una darsena
    del puerto. En este caso, como se sabe que los barcos pueden ser de 3 categorias no mas,
    la ColaPrio consistira de un arreglo de dimension 3 de tipo Cola, es decir, el arreglo
    contendra 3 colas las cuales se encargaran de mantener el orden de las 3 categorias
    (1 cola por categoria).
    La cola de prioridad recibira un objeto del tipo Barco y lo colocara en la cola
    que le corresponda segun su categoria
     */
    /////////////////////ATRIBUTOS////////////////////////////////
    private final int TAM = 3;
    private final Cola[] prioridades = new Cola[TAM]; //Arreglo que contendra las 3 colas de barcos

    /////////////////////CONSTRUCTORES////////////////////////////////
    public ColaPrio() { //Constructor
        for (int i = 0; i < TAM; i++) {
            prioridades[i] = new Cola();
        }

    }

    /////////////////////METODOS////////////////////////////////
    public boolean insertar(Barco elem, int prioridad) {
        /*
      Metodo que recibe por parametro un objeto Barco y un entero el cual representa la prioridad
      que tiene el elemento dentro de la cola. Inicialmente se compara el entero ingresado
      por parametro con la prioridad del barco, para asegurarnos que coincide (Por definicion,
      la implementacion del TDA Cola Prioridad recibe la prioridad por parametro, por lo que
      la recibo a pesar de que podria obtener la prioridad directamente del objeto Barco).
      Una vez que me aseguro que las prioridades coinciden, procedo a verificar en que
      cola deberia ser colocado el barco
         */

        boolean exito = false;
        if (prioridad == elem.getTipo()) { //Compruebo que coincidan las prioridades
            prioridades[prioridad].poner(elem);
        }
        return exito;
    }

    public boolean eliminarFrente() {
        /*
      Metodo encargado de eliminar el primer elemento en la cola de prioridad. Para esto hay que tener en cuenta
      que la cola de prioridad esta compuesta por un arreglo de colas, por lo que hay que verificar que eliminar
      el frente de la primer cola no vacia en el arreglo.
         */
        int i = 0;
        boolean exito = false;
        while (i < TAM && prioridades[i].esVacia()) {
            i++;
        }
        if (i < TAM) {
            exito = prioridades[i].sacar();
        }
        return exito; //Si todas las colas estaban vacias, el metodo devolvera false indicando que la operacion fallo o no tuvo cambios sobre la cola de prioridad
    }

    public Barco recuperarFrente() {
        /*
      Metodo encargado de obtener el frente de la primer cola no vacia, es decir, el primer elemento de la cola de prioridad
         */

        Barco devuelve = null;
        int i = 0;
        while (i < TAM && prioridades[i].esVacia()) {
            i++;
        }
        if (i < TAM) {
            devuelve = prioridades[i].obtenerFrente();
        }
        return devuelve;
    }

    public boolean estaVacia() {
        /*
      Metodo que devuelve un boolean indicando si la cola de prioridad esta o no vacia
      Para esto bastara con comprobar si todas la colas del arreglo estan vacias
         */
        boolean vacia = true;
        for (int i = 0; i < TAM; i++) {
            if (!prioridades[i].esVacia()) {
                vacia = false;
                i = TAM;
            }
        }

        return vacia;
    }

    public void vaciar() { //Metodo encargado de vaciar todas las colas
        for (int i = 0; i < 3; i++) {
            prioridades[i].vaciar();
        }
    }

    public String toString() {
        /*
      Metodo que retorna un String conteniendo la informacion de la cola de prioridad (Prioridades y elementos correspondientes a cada prioridad)

         */
        String cadena = "";
        cadena = "PRIORIDAD ALTA: " + prioridades[0].toString() + "\n";
        cadena = cadena + "PRIORIDAD MEDIA: " + prioridades[1].toString() + "\n";
        cadena = cadena + "PRIORIDAD BAJA: " + prioridades[2].toString() + "\n";

        return cadena;
    }

}
