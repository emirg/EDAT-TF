/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PuertosLista;

import Puerto.Puerto;

/**
 *
 * @author grios
 */
public class Lista {

    private NodoPuerto cabecera;
    private int longitud;

    public Lista() {

        cabecera = null;
        longitud = 0;

    }

    public boolean insertar(Puerto nuevoElem, int pos) {
        boolean exito = true;
        if (pos == 1) {
            NodoPuerto n1 = new NodoPuerto(nuevoElem, this.cabecera);
            cabecera = n1;
            longitud++;
        }
        if (pos > 1 && pos <= longitud + 1) {

            NodoPuerto temp = cabecera;
            int i = 1;
            while (i < pos - 1) {
                temp = temp.getEnlace();
                i++;
            }
            NodoPuerto n1 = new NodoPuerto(nuevoElem, temp.getEnlace());
            temp.setEnlace(n1);
            longitud++;
        }

        return exito;
    }

    public boolean eliminar(int pos) {
        boolean exito = true;
        NodoPuerto temp = cabecera;
        if (pos > 1 && pos <= this.longitud) {
            int i = 1;
            while (i < pos - 1) {
                temp = temp.getEnlace();
                i++;

            }
            temp.setEnlace(temp.getEnlace().getEnlace());
            longitud--;

        }
        if (pos == 1) {
            cabecera = cabecera.getEnlace();
            longitud--;
        }

        return exito;
    }

    public String toString() {
        String lista = "[";
        NodoPuerto aux = cabecera;
        int i = 0;
        while (i <= this.longitud - 1 && this.cabecera != null) {
            lista = lista + aux.getElem()+ " , ";
            aux = aux.getEnlace();
            i++;
        }
        lista = lista + "]";
        return lista;
    }

    public boolean esVacia() {
        if (cabecera == null) {
            return true;
        } else {
            return false;
        }
    }

    public void vaciar() {
        cabecera = null;
    }

    public Puerto recuperar(int pos) {
        int i = 1;
        NodoPuerto temp = cabecera;
        while (i < pos) {
            temp = temp.getEnlace();
            i++;
        }
        return temp.getElem();

    }

    /*public int localizar(int elem) {
        int i = 1;
        Puerto elemloc = null;
        NodoPuerto temp = cabecera;
        while (elemloc != elem && i <= this.longitud) {
            elemloc = temp.getElem();
            temp = temp.getEnlace();
            i++;
        }

        return i - 1;

    }*/

    public int longitud() {
        return this.longitud;
    }

    public Lista clonar() {
        int i = 1;

        Lista l2 = new Lista();
        while (i <= this.longitud) {
            l2.insertar(this.recuperar(i), i);
            i++;

        }
        return l2;

    }

}
