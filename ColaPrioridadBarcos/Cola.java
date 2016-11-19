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

    private NodoBarco frente;
    private NodoBarco fin;

    public Cola() {

        this.frente = null;
        this.fin = null;

    }

    public boolean poner(Barco elem) {

        NodoBarco n1 = new NodoBarco(elem);

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

    public Barco obtenerFrente() {
        return this.frente.getElem();
    }

    public boolean esVacia() {
        boolean vacia;
        if (this.frente == null && this.fin == null) {
            vacia = true;
        } else {
            vacia = false;
        }

        return vacia;
    }

    public void vaciar() {
        NodoBarco aux = this.frente;

        while (aux != null) {
            this.sacar();
            aux = aux.getEnlace();

        }
    }

    public String toString() {
        NodoBarco aux = this.frente;
        String col = " ";
        while (aux != null) {
            col = col + aux.getElem();
            aux = aux.getEnlace();
        }
        return col;

    }

    public Cola clonar() {
        Cola c2 = new Cola();
        NodoBarco aux1 = this.frente;
        while (aux1 != null) {
            c2.poner(aux1.getElem());
            aux1 = aux1.getEnlace();
        }

        return c2;
    }

}
