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

    private final Cola[] prioridades = new Cola[3];

    public ColaPrio() {
        int i;
        for (i = 0; i < 3; i++) {
            prioridades[i] = new Cola();
        }

    }

    public boolean insertar(Barco elem, int prioridad) {
        boolean exito = false;
        switch (prioridad) {
            case 0:
                prioridades[0].poner(elem);
                exito = true;
                break;
            case 1:
                prioridades[1].poner(elem);
                exito = true;
                break;
            case 2:
                prioridades[2].poner(elem);
                exito = true;
                break;
            default:
                exito = false;
                break;
        }

        return exito;
    }

    public boolean eliminarFrente() {
        boolean exito = false;
        if (prioridades[0] != null) {
            prioridades[0].sacar();
            exito = true;
        } else if (prioridades[1] != null) {
            prioridades[1].sacar();
            exito = true;
        } else if (prioridades[2] != null) {
            prioridades[2].sacar();
            exito = true;
        }

        return exito;
    }

    public Barco recuperarFrente() {

        Barco devuelve = null;
        if (prioridades[0] != null) {
            devuelve = prioridades[0].obtenerFrente();

        } else if (prioridades[1] != null) {
            devuelve = prioridades[1].obtenerFrente();

        } else if (prioridades[2] != null) {
            devuelve = prioridades[2].obtenerFrente();

        }
        return devuelve;
    }

    public boolean estaVacia() {
        boolean vacia = false;
        if (prioridades[0].esVacia() && prioridades[1].esVacia() && prioridades[2].esVacia()) {
            vacia = true;
        }
        return vacia;
    }

    public String toString() {
        String cadena = "";
        cadena = "PRIORIDAD ALTA: " + prioridades[0].toString() + "\n";
        cadena = cadena + "PRIORIDAD MEDIA: " +  prioridades[1].toString() + "\n";
        cadena = cadena +"PRIORIDAD BAJA: " + prioridades[2].toString() + "\n";

        return cadena;
    }

}
