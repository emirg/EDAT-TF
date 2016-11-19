/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiccAVL;

import Puerto.Puerto;

/**
 *
 * @author emiliano
 */
public class DiccAVL {

    ////////////////////ATRIBUTOS////////////////////
    private NodoAVL raiz;

    ////////////////////CONSTRUCTORES////////////////////
    public DiccAVL() {
        this.raiz = null;
    }

    public DiccAVL(NodoAVL n) {
        this.raiz = n;
    }

    ////////////////////METODOS PUBLICOS////////////////////
    public boolean insertar(String clave, Puerto dato) {
        boolean exito = false;
        if (this.raiz == null) {
            this.raiz = new NodoAVL(clave, dato);
            exito = true;
        } else {
            NodoAVL temp = insertar(this.raiz, clave, dato);
            if (temp != null) {
                this.raiz = temp;
                exito = true;
            }

        }

        return exito;

    }

    public boolean eliminar(String clave) {
        boolean exito = false;
        NodoAVL buscado = obtenerDato(this.raiz, clave);

        if (buscado != null) {
            eliminar(buscado);
            exito = true;
        }
        return exito;
    }

    public Puerto obtenerDato(String clave) {
        Puerto p=null;

        NodoAVL n = obtenerDato(this.raiz, clave);
        if (n != null) {
            p = n.getDato();
        }
        return p;
    }

    public String toString() {
        String cadena = "Arbol vacío";
        if (this.raiz != null) {
            cadena = this.toStringAux(raiz);
        }
        return cadena;
    }

    public String inOrden() {
        String cadena = "Arbol vacio";
        if (this.raiz != null) {
            cadena = this.inOrden(this.raiz);
        }
        return cadena;
    }

    ////////////////////METODOS PRIVADOS////////////////////
    private NodoAVL insertar(NodoAVL nodo, String clave, Puerto dato) {
        NodoAVL exito;
        NodoAVL aux;
        if (nodo.getClave().compareToIgnoreCase(clave) < 0) {
            if (nodo.getDer() == null) {
                nodo.setDer(new NodoAVL(clave, dato));
            } else {
                aux = insertar(nodo.getDer(), clave, dato);
                nodo.setDer(aux);
            }
        } else if (nodo.getClave().compareToIgnoreCase(clave) > 0) {
            if (nodo.getIzq() == null) {
                nodo.setIzq(new NodoAVL(clave, dato));
            } else {
                aux = insertar(nodo.getIzq(), clave, dato);
                nodo.setIzq(aux);
            }
        } else {
            nodo = null;
        }
        if (nodo != null) {
            nodo.setAlt(altSub(nodo));
            if (nodo.getBalance() > 1 || nodo.getBalance() < -1) {
                nodo = balancear(nodo);
            }
        }
        return nodo;
    }

    private NodoAVL eliminar(NodoAVL n) {
        if (n.equals(this.raiz)) {
            if (n.tieneHijos()) {
                NodoAVL candidato = buscarCandidato(n);
                String key = candidato.getClave();
                Puerto p = candidato.getDato();
                eliminar(candidato);
                n.setClave(key);
                n.setDato(p);
            } else {
                this.raiz = null;
            }
        } else {
            NodoAVL padre = buscarPadre(n);
            if (n.esHoja()) {//Caso 1 - Nodo a eliminar es una hoja
                if (padre.tieneDer() && padre.getDer().equals(n)) {
                    padre.setDer(null);
                } else {
                    padre.setIzq(null);
                }
            } else if (n.getDer() == null) { //Caso 2
                if (padre.tieneDer() && padre.getDer().equals(n)) {
                    padre.setDer(n.getIzq());
                } else {
                    padre.setIzq(n.getIzq());
                }
                n.setIzq(null);
            } else if (n.getIzq() == null) { //Caso 2 
                if (padre.tieneIzq() && padre.getIzq().equals(n)) {
                    padre.setIzq(n.getDer());
                } else {
                    padre.setDer(n.getDer());
                }
                n.setDer(null);
            } else { //Caso 3 
                NodoAVL candidato = buscarCandidato(n);
                String key = candidato.getClave();
                Puerto p = candidato.getDato();
                eliminar(candidato);
                n.setClave(key);
                n.setDato(p);

            }
        }
        if (this.raiz != null) {
            balancear(encontrarDesbalance(this.raiz));
        }
        return n;
    }

    private NodoAVL obtenerDato(NodoAVL n, String clave) {
        NodoAVL nodo = n;
        if (nodo != null) {
            if (nodo.getClave().equalsIgnoreCase(clave)) {
                nodo = this.raiz;
            } else if (n.getClave().compareToIgnoreCase(clave) < 0) {
                if (nodo.tieneDer() && nodo.getDer().getClave().equalsIgnoreCase(clave)) {
                    nodo = n.getDer();
                } else {
                    nodo = obtenerDato(n.getDer(), clave);
                }
            } else if (nodo.tieneIzq() && nodo.getIzq().getClave().equalsIgnoreCase(clave)) {
                nodo = n.getIzq();
            } else {
                nodo = obtenerDato(n.getIzq(), clave);
            }
        }
        return nodo;
    }

    private NodoAVL buscarPadre(NodoAVL elem) {

        NodoAVL aux = null;
        boolean cortar = true;
        if (this.raiz != null) {
            aux = this.raiz;
            while (aux != null && cortar) {
                if (aux.getDer() != null && aux.getDer().equals(elem)) {
                    cortar = false;
                } else if (aux.getIzq() != null && aux.getIzq().equals(elem)) {
                    cortar = false;
                } else if (aux.getClave().compareToIgnoreCase(elem.getClave()) > 0) {
                    aux = aux.getIzq();
                } else {
                    aux = aux.getDer();
                }

            }
        }
        return aux;
    }

    private NodoAVL buscarCandidato(NodoAVL n) { //El mayor elemento del subárbol izquierdo de N
        NodoAVL aux = n.getIzq();
        while (aux.getDer() != null) {
            aux = aux.getDer();
        }
        return aux;

    }

    private NodoAVL encontrarDesbalance(NodoAVL n) {
        boolean balan = false;
        NodoAVL aux = null;
        int balance = n.getBalance();
        if (balance == 2 || balance == -2) {
            aux = n;
        } else {
            if (n.getDer() != null) {
                aux = encontrarDesbalance(n.getDer());
            }
            if (n.getIzq() != null && (n.getBalance() != 2 || n.getBalance() != -2)) {
                aux = encontrarDesbalance(n.getIzq());
            }
        }

        return aux;

    }

    private NodoAVL balancear(NodoAVL n) {
        NodoAVL hijoDes, nuevaRaiz = null;
        if (n != null) {
            if (n.getBalance() == 2) { //Simple a derecha o Doble Izquierda-Derecha
                if (n.getIzq().getBalance() == -1) {//Doble 
                    hijoDes = rotarIzq(n.getIzq());
                    n.setIzq(hijoDes);
                    nuevaRaiz = rotarDer(n);
                } else {//Simple
                    nuevaRaiz = rotarDer(n);
                }
            } else //Simple a izquierda o Doble Derecha-Izquierda
             if (n.getDer().getBalance() == 1) {//Doble 
                    hijoDes = rotarDer(n.getDer());
                    n.setDer(hijoDes);
                    nuevaRaiz = rotarIzq(n);
                } else {//Simple
                    nuevaRaiz = rotarIzq(n);
                }
        }
        return nuevaRaiz;
    }

    private int altSub(NodoAVL n) {
        int alt = 0;
        if (n.getDer() != null && n.getIzq() != null) {
            alt = Math.max(n.getDer().getAlt(), n.getIzq().getAlt()) + 1;
        } else if (n.getIzq() == null && n.getDer() != null) {
            alt = (n.getDer().getAlt()) + 1;
        } else if (n.getIzq() != null && n.getDer() == null) {
            alt = (n.getIzq().getAlt()) + 1;
        }
        return alt;
    }

    private NodoAVL rotarIzq(NodoAVL n) { //Simple a izquierda
        NodoAVL h = n.getDer();
        NodoAVL temp = h.getIzq();
        h.setIzq(n);
        n.setDer(temp);
        n.setAlt(altSub(n));
        h.setAlt(altSub(h));
        return h;
    }

    private NodoAVL rotarDer(NodoAVL n) { //Simple a derecha
        NodoAVL h = n.getIzq();
        NodoAVL temp = h.getDer();
        h.setDer(n);
        n.setIzq(temp);
        n.setAlt(altSub(n));
        h.setAlt(altSub(h));
        return h;
    }

    private String toStringAux(NodoAVL nodo) {
        String cadena = "", aux1 = "No tiene", aux2 = "No tiene";
        if (nodo != null) {
            if (nodo.getIzq() != null) {
                aux1 = "" + nodo.getIzq().getClave();
            }
            if (nodo.getDer() != null) {
                aux2 = "" + nodo.getDer().getClave();
            }
            cadena = "Nodo: " + nodo.getClave() + " | H.I: " + aux1 + " | H.D: " + aux2 + " | Balance: " + nodo.getBalance() + "\n" + toStringAux(nodo.getIzq()) + toStringAux(nodo.getDer());
        }
        return cadena;
    }

    private String inOrden(NodoAVL nodo) {
        String cadena = "";
        if (nodo != null) {
            if (nodo.getIzq() != null) {
                cadena = cadena + inOrden(nodo.getIzq());
            }

            cadena = cadena  + nodo.getClave() + "\n";

            if (nodo.getDer() != null) {
                cadena = cadena + inOrden(nodo.getDer());
            }
        }

        return cadena;

    }

    public String rangoAlfabetico(String min, String max) {
        String cadena = "Rango vacio";
        if (this.raiz != null) {
            cadena = this.rangoAlfabetico(this.raiz, min, max);
        }
        return cadena;
    }

    private String rangoAlfabetico(NodoAVL nodo, String min, String max) {
        String cadena = "";
        if (nodo != null) {
            if (nodo.getIzq() != null) {
                cadena = cadena + rangoAlfabetico(nodo.getIzq(), min, max);
            }
            if (nodo.getClave().compareToIgnoreCase(min) >= 0 && nodo.getClave().compareToIgnoreCase(max) <= 0) {
                cadena = cadena + "\n" + nodo.getClave();
            }
            if (nodo.getDer() != null) {
                cadena = cadena + rangoAlfabetico(nodo.getDer(), min, max);
            }
        }

        return cadena;

    }

}
