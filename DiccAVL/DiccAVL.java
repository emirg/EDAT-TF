/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DiccAVL;

import Puerto.Puerto;
import PuertosLista.Lista;

/**
 *
 * @author emiliano
 */
public class DiccAVL {

    /*
    La clase DiccAVL es una estructura creada para el almacenamiento y manejo
    de datos acerca de los puertos registrados en el sistema.
    Funciona a traves de un arbol AVL, es decir, un arbol balanceado, que
    si se inserta o elimina un nodo, y el arbol se desbalance, el arbol
    automaticamente reposiciona los nodos de la forma necesaria para que el arbol
    vuelva a estar balacenado
    Cada nodo del arbol contiene una clave y un objeto puerto del que almacena
    su informacion. Para acceder a la informacion del puerto basta con conocer la
    clave del nodo que tiene dicha informacion (La clave sera el nombre del puerto)
     */
    ////////////////////ATRIBUTOS////////////////////
    private NodoAVL raiz; //Nodo raiz del arbol

    ////////////////////CONSTRUCTORES////////////////////
    public DiccAVL() {
        this.raiz = null;
    }

    public DiccAVL(NodoAVL n) {
        this.raiz = n;
    }

    ////////////////////METODOS PUBLICOS////////////////////
    public boolean insertar(String clave, Puerto dato) {
        /*
      Metodo que permite la inserccion de nodos al diccionario
      El metodo se encarga de insertarlo de manera tal que el arbol quede balanceado
         */
        boolean exito = false;
        if (this.raiz == null) { //En caso de que el arbol este vacio el nodo a insertar sera el nodo raiz
            this.raiz = new NodoAVL(clave, dato);
            exito = true;
        } else { //En caso contrario, se creara el nodo y se utilizara un metodo privado recursivo el cual se encargara de insertar el nodo de manera balanceada
            NodoAVL temp = insertarAux(this.raiz, clave, dato); //Metodo recursivo (Mismo nombre de metodo pero con sobrecarga parametrica)
            if (temp != null) {
                this.raiz = temp;
                exito = true;
            }

        }

        return exito;

    }

    public boolean eliminar(String clave) { //MODIFICADO
        /*
        Metodo encargado de eliminar un nodo de la estructura en base a la clave enviada
        por parametro.
        Internamente trabaja con un metodo privado, que sera el encargado de la eliminacion
        en si del nodo y del rebalance del arbol
         */
        boolean exito;

        this.raiz = eliminarAux(this.raiz, clave); //Metodo privado con sobrecarga parametrica encargado de la eliminacion en si

        exito = true;

        return exito;
    }

    public Puerto obtenerDato(String clave) {
        /*
        Dada una clave en formato String, el metodo devolvera un objeto puerto
        cuya informacion corresponde al nodo el cual contiene la clave enviada
        por parametro
         */
        Puerto p = null;

        NodoAVL n = obtenerDato(this.raiz, clave); //Obtengo el nodo a traves de un metodo recursivo
        if (n != null) {
            p = n.getDato(); //En el caso que el nodo exista en el arbol, entonces devuelvo la informacion del puerto
        }
        return p;
    }

    public String toString() {
        /*
        Metodo con finalidad de depuracion del programa
        Devuelve un String con la informacion relevante del arbol instanciado
        al momento de llamar al metodo
         */
        String cadena = "Arbol vacío"; //Inicialmente se considera al arbol vacio
        if (this.raiz != null) { //Si compruebo que el arbol no esta vacio
            cadena = this.toStringAux(raiz); //Utilizo un metodo auxiliar recursivo que me ayude a recorrer el arbol
        }
        return cadena;
    }

    public String inOrden() {
        /*
        Metodo que devuelve un String cuyo contenido es el recorrido del arbol
        acorde al algoritmo de InOrden (Izq -> Raiz -> Der).
         */
        String cadena = "Arbol vacio";
        if (this.raiz != null) {
            cadena = this.inOrden(this.raiz); //Recorro el arbol mediante un metodo recursivo
        }
        return cadena;
    }

    public Lista rangoAlfabetico(String min, String max) { //Dados dos strings min y max, el metodo retornara un string con los puertos cuyas claves/nombres esten dentro del rango alfabetico
        Lista cadena = new Lista();
        if (this.raiz != null) {
            rangoAlfabetico(this.raiz, min, max, cadena);
        }
        return cadena;
    }

    public String tieneEspera() { //Metodo que retornara un string con los puertos que tienen barcos en lista de espera
        String cadena = "";
        if (this.raiz != null) {
            cadena = this.tieneEspera(this.raiz);
        }
        return cadena;
    }

    ////////////////////METODOS PRIVADOS////////////////////
    private NodoAVL insertarAux(NodoAVL nodo, String clave, Puerto dato) { //Metodo privado recursivo encargado de recorrer el arbol hasta el punto adecuado donde insertar el nuevo nodo
        NodoAVL aux;
        if (nodo.getClave().compareToIgnoreCase(clave) != 0) {
            if (nodo.getClave().compareToIgnoreCase(clave) < 0) { //Verifico en que rama deberia ir el nuevo nodo
                if (nodo.getDer() == null) { //Si hay lugar en la rama derecha lo inserto como hijo derecho
                    nodo.setDer(new NodoAVL(clave, dato));
                } else { //Si no hay lugar, reposiciono el nodo derecho
                    aux = insertarAux(nodo.getDer(), clave, dato);
                    nodo.setDer(aux);
                }
            } else if (nodo.getClave().compareToIgnoreCase(clave) > 0) { //Verifico en que rama deberia ir el nuevo nodo
                if (nodo.getIzq() == null) { //Si hay lugar en la rama izquierda lo inserto como hijo izquierdo
                    nodo.setIzq(new NodoAVL(clave, dato));
                } else { //Si no hay lugar, reposiciono el nodo izquierdo
                    aux = insertarAux(nodo.getIzq(), clave, dato);
                    nodo.setIzq(aux);
                }
            } else {
                nodo = null;
            }
            if (nodo != null) {
                nodo.setAlt(calcularAltura(nodo));
                if (nodo.getBalance() > 1 || nodo.getBalance() < -1) { //Compruebo el balance del arbol una vez insertado el nodo y lo balanceo en caso de ser necesario
                    nodo = balancear(nodo);
                }
            }
        }
        return nodo;
    }

    private NodoAVL eliminarAux(NodoAVL n, String clave) {
        NodoAVL aux, resultado = n;
        Puerto candidato;
        int balance;
        if (n != null) {
            if (n.getClave().compareToIgnoreCase(clave) > 0) { //Si esta para la izquierda
                aux = eliminarAux(n.getIzq(), clave);
                n.setIzq(aux);
                n.setAlt(calcularAltura(n));
                balance = n.getBalance();
                if (balance > 1 || balance < -1) {
                    resultado = balancear(n);
                }

            } else {
                if (n.getClave().compareToIgnoreCase(clave) < 0) { //Si esta para la derecha...
                    aux = eliminarAux(n.getDer(), clave);
                    n.setDer(aux);
                    n.setAlt(calcularAltura(n));
                    balance = n.getBalance();
                    if (balance > 1 || balance < -1) {
                        resultado = balancear(n);
                    }

                } else { //Encontre el nodo a eliminar
                    NodoAVL hijoDer = n.getDer(), hijoIzq = n.getIzq();
                    if (hijoDer == null && hijoIzq == null) {//CASO 1
                        resultado = null;
                    } else {
                        if (hijoDer != null && hijoIzq != null) { //CASO 3
                            candidato = buscarCandidato(n);
                            n.setDato(candidato);
                            n.setClave(candidato.getNombre());
                            aux = eliminarAux(n.getIzq(), candidato.getNombre());
                            n.setIzq(aux);
                        } else { //CASO 2
                            if (hijoDer != null) {
                                resultado = hijoDer;
                            } else {
                                resultado = hijoIzq;
                            }
                        }
                    }
                }
            }

        }
        return resultado;

    }

    private NodoAVL obtenerDato(NodoAVL n, String clave) { //Dada una clave, el metodo retornara el nodo que contiene la informacion correspondiente a la clave enviada por parametro
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

    private Puerto buscarCandidato(NodoAVL n) { //El metodo se encarga de buscar el nodo candidato para reemplazar a un nodo a eliminar
        //El nodo candidato sera el mayor elemento del subárbol izquierdo de N
        NodoAVL aux = n.getIzq();
        while (aux.getDer() != null) {
            aux = aux.getDer();
        }
        return aux.getDato();

    }

    private NodoAVL balancear(NodoAVL n) { //Metodo encargado de balancear el arbol dado un nodo que causa el desbalance del mismo
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

    private int calcularAltura(NodoAVL n) { //Dado un nodo n, el metodo se encargara de devolver un entero que indicara la altura del subarbol a partir del nodo n
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
        n.setAlt(calcularAltura(n));
        h.setAlt(calcularAltura(h));
        return h;
    }

    private NodoAVL rotarDer(NodoAVL n) { //Simple a derecha
        NodoAVL h = n.getIzq();
        NodoAVL temp = h.getDer();
        h.setDer(n);
        n.setIzq(temp);
        n.setAlt(calcularAltura(n));
        h.setAlt(calcularAltura(h));
        return h;
    }

    private String toStringAux(NodoAVL nodo) { //Metodo privado encargado de recorrer la estructura y retornar un string con la informacion de la estructura
        String cadena = "", aux1 = "No tiene", aux2 = "No tiene";
        if (nodo != null) {
            if (nodo.getIzq() != null) {
                aux1 = "" + nodo.getIzq().getClave();
            }
            if (nodo.getDer() != null) {
                aux2 = "" + nodo.getDer().getClave();
            }
            cadena = "Nodo: " + nodo.getClave() + " | H.I: " + aux1 + " | H.D: " + aux2 + " | Balance: " + nodo.getBalance() + " | Altura: " + nodo.getAlt() + "\n" + toStringAux(nodo.getIzq()) + toStringAux(nodo.getDer());
        }
        return cadena;
    }

    private String inOrden(NodoAVL nodo) { //Metodo privado encargado de recorrer el arbol acorde al algoritmo inOrden
        String cadena = "";
        if (nodo != null) {
            if (nodo.getIzq() != null) {
                cadena = cadena + inOrden(nodo.getIzq());
            }

            cadena = cadena + nodo.getClave() + "\n";

            if (nodo.getDer() != null) {
                cadena = cadena + inOrden(nodo.getDer());
            }
        }

        return cadena;

    }

    private String tieneEspera(NodoAVL nodo) { //Metodo que retornara un String con los puertos que tienen barcos en lista de espera (Se recorre la estructura en InOrden)
        String cadena = "";
        if (nodo != null) {
            if (nodo.getIzq() != null) {
                cadena = cadena + tieneEspera(nodo.getIzq());
            }

            if (!nodo.getDato().getEspera().estaVacia()) {
                cadena = cadena + nodo.getClave() + "\n";
            }

            if (nodo.getDer() != null) {
                cadena = cadena + tieneEspera(nodo.getDer());
            }
        }

        return cadena;

    }

    private void rangoAlfabetico(NodoAVL nodo, String min, String max, Lista res) { //Metodo encargado de recorrer el arbol en inOrden y retornar un string con los puertos que se encuentran en el rango especificado por los strings min y max
        //El recorrido es similar al del metodo StringAux, pero en este caso, solo se concatenaran los puertos que cumplan con el rango
        if (nodo != null) {
            if (nodo.getClave().compareToIgnoreCase(min) >= 0) {
                if (nodo.getClave().compareToIgnoreCase(min) > 0) {
                    rangoAlfabetico(nodo.getIzq(), min, max, res);
                }
                if (nodo.getClave().compareToIgnoreCase(max) <= 0) {
                    res.insertar(nodo.getDato());
                    rangoAlfabetico(nodo.getDer(), min, max, res);
                }
            } else {
                if (nodo.getClave().compareToIgnoreCase(max) < 0) {
                    rangoAlfabetico(nodo.getDer(), min, max, res);
                }
            }
        }
    }
}
