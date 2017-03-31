/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import ColaPuerto.ColaPuerto;
import Puerto.Puerto;
import PuertosLista.Lista;

/**
 *
 * @author grios
 */
public class Grafo {

    /*
    La clase Grafo sera utilizada para representar las rutas maritimas existentes entre distintos puertos
    Se utiliza un enfoque dinamico de la clase Grafo, siendo implementada mediante listas de adyacencia, ya
    que el sistema tiene que permitir el alta y baja de rutas y puertos, por lo que no se conoce un tope de
    puertos y rutas que pueden existir en el sistema
    La clase tiene un unico atributo que es un elemento de la clase NodoVert (Nodo Vertice), este luego estara enlazado
    con los siguientes nodos vertice y con su lista de adyacencia.
    La lista de nodos vertice representan los nodos del grafo, y que en este caso, representa los puertos que existen
    registrados en el sistema
    La lista de nodos adyacentes representan los arcos del grafo, que representaran las rutas entre dos puertos.
    Los arcos son con arcos etiquetados, donde la etiqueta representa la distancia de la ruta entre los dos puertos dados.
    Se tiene que tener en cuenta que que es un grafo no dirigido, es decir, a la hora de dar de alta una ruta, no
    habra que dar de alta a la ruta con los puertos origen y destino intercambiados, ya que el sistema se encargara
    de que si existe una ruta con un puerto origen y un puerto destino, entonces tambien existira la misma ruta
    de forma inversa.
     */
    ////////////////////ATRIBUTOS///////////////////////////////
    private NodoVert inicio;

    ////////////////////CONSTRUCTORES///////////////////////////
    public Grafo() {
        inicio = null;
    }

    /////////////////////METODOS////////////////////////////////
    public NodoVert getInicio() { //Retorna el nodo vertice inicial del grafo
        return inicio;
    }

    public void setInicio(NodoVert inicio) { //Establece el nodo vertice inicial del grafo. Hay que tener en cuenta que si se establece un nodo vertice con enlaces ya establecidos, estos enlances continuaran y formaran parte del grafo
        this.inicio = inicio;
    }

    public boolean insertarVertice(String clave, Puerto p) { //Inserta un Nodo vertice (Nodo/Puerto) al principio de la lista
        boolean exito = false;
        NodoVert n = this.ubicarVertice(clave); //Comprueba que el puerto no este ya cargado en el sistema de rutas
        if (n == null) { //Si no se encuentra tal puerto, entonces lo coloca
            this.inicio = new NodoVert(p, this.inicio); //Se crea el nuevo objeto y se lo establece como nodo inicio
            exito = true;
        }

        return exito;
    }

    public boolean insertarArco(String vert1, String vert2, int etiqueta) { //Inserta un arco (Es decir, Nodos adyacentes a dos nodos vertices)
        boolean exito = false;
        NodoVert n1 = this.ubicarVertice(vert1); //Obetengo el objeto Nodo Vertice que le corresponde a la clave ingreseada por parametro
        NodoVert n2 = this.ubicarVertice(vert2); //Obtengo el segundo objeto Nodo Vertice que corresponde a la segunda clave ingresada
        if (n1 != null && n2 != null && !existeArco(vert1, vert2)) { //Verifico que ambos Nodos (Puertos) existan en el sistema y compruebo que no existe ya un arco entre los dos puertos (No permito que se cree mas de una ruta entre dos puertos)
            n1.setPrimerAdy(new NodoAdy(n2, n1.getPrimerAdy(), etiqueta)); //Si no existe arco tal entre los dos puertos, y estos existen, entonces paso a añadirlos a las listas de adyacencia de dichos puerots
            n2.setPrimerAdy(new NodoAdy(n1, n2.getPrimerAdy(), etiqueta));
        }

        return exito;
    }

    public boolean eliminarArco(String vert1, String vert2) { //Elimina un arco (Ruta) entre dos puertos, dadas sus claves
        boolean exito = false;
        NodoVert ini = this.ubicarVertice(vert1), fin = this.ubicarVertice(vert2); //Ubico los Nodos Vertice para poder trabajar directamente con los objetos
        int i = 0;
        if (existeArco(vert1, vert2)) { //Si el arco efectivamente existe, procedo a eliminarlo
            while (i < 2) { //Como se establecio que toda ruta esta representada mediante dos arcos (ida y vuelta), procedo a eliminarlos de forma iterativa
                NodoAdy enlaceIni = this.enlace(ini, fin); //Obtengo el Nodo Adyacente que corresponde al primer arco entre los dos puertos
                NodoAdy pre = this.preEnlace(ini, fin); //Tambien obtengo el Nodo Adyacente previo al Nodo Adyacente que representa el arco, esto es para poder establecer el enlace una vez que sea eliminado el arco
                if (enlaceIni == ini.getPrimerAdy()) { //Si el enlace es el primer nodo de la lista de adyacencia, bastara con establece como nueva cabecera de la lista de adyacencia al segundo nodo de adyacencia
                    ini.setPrimerAdy(enlaceIni.getSigAdyacente()); //Como sabemos que el enlace a sacar es el primero de la lista, establecemos el inicial como el siguiente del enlace a eliminar
                    enlaceIni.setSigAdyacente(null); //Me aseguro que el enlace a borrar no tenga enlace con otros nodos adyacentes para que luego sea eliminado por no referenciar a un objeto
                    exito = true;
                } else {
                    pre.setSigAdyacente(enlaceIni.getSigAdyacente()); //Como el enlace a eliminar no es el primero, obtengo el enlace anterior y cambio su enlace con el siguiente
                    enlaceIni.setSigAdyacente(null); //Borro los enlaces con el enlace a borrar para asegurarme que no hara referencia a ningun objeto
                    exito = true;
                }
                i++;

                fin = this.ubicarVertice(vert1); //Intercambio los valores de los nodo ini y fin para asegurarme que borro el doble arco
                ini = this.ubicarVertice(vert2);
            }

        }

        return exito;
    }

    public boolean eliminarVertice(String vert) { //Elimina un Nodo Vertice (Puerto) de la lista de Nodos vertices del grafo recibiendo la clave del nodo por parametro
        NodoVert n = this.ubicarVertice(vert); //Ubico el objeto correspondiente a la clave del parametro
        NodoVert temp = this.inicio; //Creo una variable temporal con el nodo vertice inicio para luego iterar por la lista y borrar el que corresponad (si es que necesito iterar)
        NodoAdy ady = n.getPrimerAdy(); //Obtengo el primer nodo adyacente del nodo vertice para borrar los arcos del mismo
        boolean exito = false;
        while (ady != null) { //Itero en la lista de adyacencia y borro todos los arcos que le corresponden al nodo vertice
            this.eliminarArco(vert, ady.getVertice().getElem().getNombre()); //Llamo al metodo eliminarArco con el nodo vertice a eliminar y con los elementos de la lista de adyacencia para asegurarme que borro todos los dobles arcos
            ady = n.getPrimerAdy();
        }

        if (n == this.inicio) { //Si el nodo vertice a borrar es el inicial, basta con cambiar la referencia del nodo inicio
            this.inicio = n.getSigVertice();
            exito = true;
        } else {
            while (temp.getSigVertice() != n) { //Busco el nodo vertice directamente anterior al nodo vertice a borrar
                temp = temp.getSigVertice();

            }

            if (n.getSigVertice() != null) { //Si el nodo vertice no es el ultimo de la lista, cambio la referencia al siguiente nodo vertice
                temp.setSigVertice(n.getSigVertice());
                exito = true;
            } else { //Si el nodo vertice es el ultimo, basta con referenciar el anteultimo nodo vertice al nulo
                temp.setSigVertice(null);
                exito = true;
            }
        }

        return exito;

    }

    public boolean existeVertice(String vert) { //Comprueba que existe un nodo vertice (Puerto) con el nombre clave pasado por parametro
        NodoVert buscado = this.ubicarVertice(vert); //Ubico el vertice en la lista (Si el nodo no esta, devolvera nulo, lo que ya me dice si existe o no el nodo vertice)

//      NodoVert actual = this.inicio; //Creo un nodo para iterar en la lista de nodos vertice
//        while (buscado != actual && actual.getSigVertice() != null) {
//            actual = actual.getSigVertice();
//        }
//        if (actual == buscado) {
//            exito = true;
//        }
        return (buscado != null);

    }

    public boolean existeArco(String v1, String v2) { //Comprueba mediante los nombres claves de dos puertos, si existe un arco entre ellos
        //Este metodo comprobara solamente la existencia de uno de los dos arcos que existen para representar las rutas maritimas
        //Esto se debe a que la forma en la que se plantearon las inserciones, no podria existir solo uno de los dos arcos, es decir, no podria existir un arco de ini a fin, y no un arco de fin a ini, ambos existen simultaneamente
        boolean existe = false;
        NodoVert ini = this.ubicarVertice(v1), fin = this.ubicarVertice(v2); //Ubico los nodos vertices con los que voy a trabajar para allar sus nodos de adyacencia
        if (ini != null) {
            NodoAdy sig = ini.getPrimerAdy(); //Creo un nodo de adyacencia para iterar en la lista de nodos adyacentes
            while (sig != null) {
                if (sig.getVertice() == fin) { //Si llego a un nodo adyacente que representa el arco con el nodo vertice de destino, entonces se que existe el arco
                    existe = true;
                }
                sig = sig.getSigAdyacente();
            }
        }
        return existe;
    }

    @Override
    public String toString() { //Devuelve un elemento String el cual contiene toda la informacion de la estructura instanciada del grafo
        String res = "Grafo vacio";
        NodoVert ini = this.inicio;
        if (ini != null) {
            res = "";
        }
        while (ini != null) {
            res = res + ini.getElem().getNombre() + " --> ";
            NodoAdy iniA = ini.getPrimerAdy();
            while (iniA != null) {
                res = res + iniA.getVertice().getElem().getNombre() + " / ";
                iniA = iniA.getSigAdyacente();
            }
            res = res + "\n";

            ini = ini.getSigVertice();

        }
        return res;
    }

    public boolean esVacio() { //Devuelve un booleano para conocer si la estructura del grafo esta vacia o no
        return this.inicio == null; //Para esto solo basta con comprobar si el nodo inicial es nulo o no
    }

    public void vaciar() { //Metodo utilizado para vaciar toda la estructura 
        this.inicio = null;
    }

    public boolean existeCamino(String ini, String fin) { //Comprueba que existe un camino para llegar desde un nodo vertice a otro, dadas las claves pasadas por parametros
        boolean exito = false;
        NodoVert origen = this.ubicarVertice(ini); //Obtengo el nodo vertice que corresponde al nombre clave ini
        NodoVert destino = this.ubicarVertice(fin); //Obtengo el nodo vertice que corresponde al nombre clave fin
        if (origen != null && destino != null) { //En el caso que ambos puertos estan registrados en la estructura procedo
            Lista vis = new Lista(); //Creo una lista vis, la cual sera una lista dinamica para manejar los nodos visitados del grafo y asi no entrar en posibles bucles durante el recorrido del grafo
            exito = existeCaminoAux(origen, destino.getElem(), vis); //Para iterar sobre el grafo utilizo un metodo privado recursivo
        }
        return exito;
    }

    public ResultadoCamino caminoMasCorto(String ini, String fin) {
        /*
        Retorna un elemento del tipo ResultadoCamino (el cual contiene una lista que representa un camino y un entero)
        donde devuelve el camino mas corto (en millas, acorde a la etiqueta de los arcos) entre dos puertos
        En este caso, el atributo entero del objeto ResultadoCamino contendra la distancia en millas
         */

        ResultadoCamino resultado = new ResultadoCamino(); //Inicializo el objeto resultado del tipo ResultadoCamino para luego mandarselo a un metodo recursivo el cual computara la solucion
        NodoVert origen = this.ubicarVertice(ini); //Obtengo el nodo vertice que corresponde al nombre clave ini
        NodoVert destino = this.ubicarVertice(fin); //Obtengo el nodo vertice que corresponde al nombre clave fin
        if (origen != null && destino != null) { //En el caso que ambos puertos estan registrados en la estructura procedo
            Lista vis = new Lista(); //Creo una lista vis, la cual sera una lista dinamica para manejar los nodos visitados del grafo y asi no entrar en posibles bucles durante el recorrido del grafo
            caminoMasCortoAux(origen, destino, vis, 0, resultado); //Para iterar sobre el grafo utilizo un metodo privado recursivo
        }
        return resultado;
    }

    public ResultadoCamino caminoMasLargo(String ini, String fin) {
        /*
        Retorna un elemento del tipo ResultadoCamino (el cual contiene una lista que representa un camino y un entero)
        donde devuelve el camino mas largo (en millas, acorde a la etiqueta de los arcos) entre dos puertos.
        En este caso, el atributo entero del objeto ResultadoCamino contendra la distancia en millas
         */
        ResultadoCamino resultado = new ResultadoCamino(); //Inicializo el objeto resultado del tipo ResultadoCamino para luego mandarselo a un metodo recursivo el cual computara la solucion
        NodoVert origen = this.ubicarVertice(ini); //Obtengo el nodo vertice que corresponde al nombre clave ini
        NodoVert destino = this.ubicarVertice(fin); //Obtengo el nodo vertice que corresponde al nombre clave fin
        if (origen != null && destino != null) { //En el caso que ambos puertos estan registrados en la estructura procedo
            Lista vis = new Lista(); //Creo una lista vis, la cual sera una lista dinamica para manejar los nodos visitados del grafo y asi no entrar en posibles bucles durante el recorrido del grafo
            caminoMasLargoAux(origen, destino, vis, 0, resultado); //Para iterar sobre el grafo utilizo un metodo privado recursivo
        }
        return resultado;
    }

    public Lista listarEnProfundidad() { //TESTEAR
        //Devuelvo una lista la cual contendra todos los nodos del grafo acorde al algoritmo de recorrido en profundidad
        Lista visitados = new Lista(); //Inicializo una estructura de control que me permita controlar que nodos ya fueron visitados y no deben ser enlistados nuevamente
        NodoVert comienzo = this.inicio; //Inicializo un NodoVert con el nodo inicial del grafo para poder iterar sobre el
        while (comienzo != null) { //Debo iterar sobre cada nodo vertice por lo que creo una estructura iterativa sobre el nodo vertice comienzo
            if (visitados.localizar(comienzo.getElem()) < 0) { //Si el nodo vertice todavia no fue visitado
                profundidadDesde(comienzo, visitados); //Entonces llamo a un metodo privado recursivo que se encargue de recorrer el nodo vertice y sus adyacentes
            }
            comienzo = comienzo.getSigVertice();
        }
        return visitados;
    }

    public Lista listarEnAnchura() { //TESTEAR
        //Devuelvo una lista la cual contendra todos los nodos del grafo acorde al algoritmo de recorrido en anchura
        Lista visitados = new Lista(); //Inicializo una estructura de control que me permita controlar que nodos ya fueron visitados y no deben ser enlistados nuevamente
        NodoVert comienzo = this.inicio; //Inicializo un NodoVert con el nodo inicial del grafo para poder iterar sobre el
        while (comienzo != null) { //Debo iterar sobre cada nodo vertice por lo que creo una estructura iterativa sobre el nodo vertice comienzo
            if (visitados.localizar(comienzo.getElem()) < 0) { //Si el nodo vertice todavia no fue visitado
                anchuraDesde(comienzo, visitados); //Entonces llamo a un metodo privado recursivo que se encargue de recorrer el nodo vertice y sus adyacentes
            }
            comienzo = comienzo.getSigVertice();
        }
        return visitados;
    }

    public ResultadoCamino caminoMenorPuertos(String ini, String fin) {
        /*
        Devuelvo un objeto del tipo ResultadoCamino el cual contendra una lista que representara
        el camino entre dos puertos y un entero el cual representara la cantidad de puertos intermedios
        entre los dos puertos dados.
        El metodo se encarga de hallar el camino con la menor cantidad de puertos intermedios, sin importar
        la distancia en millas
         */

        ResultadoCamino resultado = new ResultadoCamino();  //Inicializo el objeto resultado del tipo ResultadoCamino para luego mandarselo a un metodo recursivo el cual computara la solucion
        NodoVert origen = this.ubicarVertice(ini); //Obtengo el nodo vertice que corresponde al nombre clave ini
        NodoVert destino = this.ubicarVertice(fin); //Obtengo el nodo vertice que corresponde al nombre clave fin

        if (origen != null && destino != null) { //En el caso que ambos puertos estan registrados en la estructura procedo
            Lista visitados = new Lista(); //Creo una lista vis, la cual sera una lista dinamica para manejar los nodos visitados del grafo y asi no entrar en posibles bucles durante el recorrido del grafo
            caminoMenorPuertosAux(origen, destino, visitados, 0, resultado); //Para iterar sobre el grafo utilizo un metodo privado recursivo
        }

        return resultado;
    }

    public Lista caminoSinN(String ini, String fin, String n) {
        /*
        Devuelvo una lista que representara un camino entre dos puertos.
        El metodo se encarga de encontrar un camino el cual tenga origen en el primer puerto dado
        y destino en el segundo puerto dado, pero este camino no pasara por el puerto n dado por
        parametro (tercer argumento)
         */

        NodoVert origen = this.ubicarVertice(ini); //Obtengo el nodo vertice que corresponde al nombre clave ini
        NodoVert destino = this.ubicarVertice(fin); //Obtengo el nodo vertice que corresponde al nombre clave fin
        NodoVert auxN = this.ubicarVertice(n); //Ubico el nodo vertice que representa el puerto por el cual no puede contener el camino
        Lista resultado = new Lista(); //Inicializo la lista que contendra el camino a retornar
        if (origen != null && destino != null) { //En el caso que ambos puertos estan registrados en la estructura procedo. Notese que no compruebo que el puerto n este en la estructura, ya que si no esta, entonces cualquier camino no contendra al puerto n
            Lista visitados = new Lista(); //Creo una lista vis, la cual sera una lista dinamica para manejar los nodos visitados del grafo y asi no entrar en posibles bucles durante el recorrido del grafo
            caminoSinNAux(origen, destino, auxN, visitados, resultado); //Para iterar sobre el grafo utilizo un metodo privado recursivo
        }

        return resultado;
    }

    public Grafo clonar() {
        /*
        Dado un objeto Grafo, retorna un objeto de la clase Grafo, el cual tiene
        la misma informacion y representa el mismo grafo que el grafo inicial
         */

        Grafo g = new Grafo(); //Inicializo la estructura a retornar
        NodoVert n = new NodoVert(this.inicio.getElem(),null,null); //Inicializo un nodo vertice n, el cual luego sera utilizado para iterar sobre el grafo inicial
        g.setInicio(n);
        NodoVert puntero = this.inicio.getSigVertice();

        while (puntero != null) { //Creo una estructura iterativa para insertar todos los nodos vertice al nuevo grafo
            n.setSigVertice(new NodoVert(puntero.getElem(),null,null));
            n = n.getSigVertice();
            puntero=puntero.getSigVertice();

        }

        puntero = this.inicio;
        NodoAdy a1; //Creo un nodo adyacente el cual me ayudara a iterar sobre las listas de adyacencia del grafo inicial, para luego insertarlos en el nuevo grafo

        
        while (puntero != null) {
            a1 = puntero.getPrimerAdy();
            while (a1 != null) { //De forma iterativa inserto los arcos correspondientes a los nodos vertice del grafo inicial
                g.insertarArco(puntero.getElem().getNombre(), a1.getVertice().getElem().getNombre(), a1.getEtiqueta());
                a1 = a1.getSigAdyacente();
               
            }
            puntero = puntero.getSigVertice();

        }
        return g;

    }

    ///////////////////METODOS PRIVADOS/////////////////////////
    private NodoVert ubicarVertice(String buscado) {
        /*
        Metodo privado que se encarga de devolver un NodoVert tal que coincida con
        la clave enviada por parametro.
        Si no existe un nodo tal entonces se devuelve null (Util para saber si existe o no un nodo en el grafo).
        La clave enviada por parametro no sera sensible a mayusculas.
         */

        NodoVert aux = inicio; //Tomo como inicio el primer nodo vertice del grafo
        while (aux != null && !aux.getElem().getNombre().equalsIgnoreCase(buscado)) { //Mientras que el nodo no sea nulo y no sea el buscado, itero sobre la lista de nodos vertices
            aux = aux.getSigVertice();
        }
        return aux; //Devuelvo el nodo vertice que corresponde a la clave o null si no se encuentra

    }

    private NodoAdy enlace(NodoVert ini, NodoVert fin) {
        /*
        Metodo privado que devuelve el nodo adyacente que enlace dos nodos vertices para representar un arco.
        El metodo es muy util a la hora de insertar o eliminar nodos, por lo que su implementacion es sumamente util.
        En el caso que no exista un enlace tal entre dos nodos enviados por parametros, el metodo devuelve null.
         */

        NodoAdy sig = ini.getPrimerAdy(); //Comienzo por el primer nodo adyacente de la lista de adyacencia del nodo vertice ini enviado por parametro
        while (sig != null && sig.getVertice() != fin) { //Itero hasta encontrar el nodo adyacente tal que su nodo vertice sea el mismo que fin o hasta que el nodo adyacente sea nulo
            sig = sig.getSigAdyacente();
        }
        return sig; //Devuelvo el nodo adyacente que genera el enlace entre los dos nodos vertices o null si no existe un enlace tal
    }

    private NodoAdy preEnlace(NodoVert ini, NodoVert fin) {
        /*
        Metodo privado que se encarga de encontrar el nodo adyacente anterior al nodo adyacente que representa
        el enlace entre dos nodos vertices dados por parametros
        En caso que el nodo adyacente que representa el enlace entre los dos nodos vertices sea el primero de la lista
        de adyacencia, el metodo devolvera null

         */

        NodoAdy enlace = this.enlace(ini, fin); //Obtengo el nodo adyacente que representa el enlace entre los dos nodos dados
        NodoAdy pre = ini.getPrimerAdy(); //Establezco como preenlace el primer nodo adyacente de la lista de adyacencia del nodo ini
        if (enlace == pre || enlace == null) {
            /*
            Verifico que si enlace es igual a preenlace, entonces preenlace deberia ser el nodo vertice, y como no es el
            objetivo devolver un nodo vertice, lo seteo a null. Si el enlace directamente no existe, entonces tambien deberia devolver null
             */
            pre = null;
        } else { //En caso contrario, busco el preenlace iterativamente sobre la lista de adyacencia del nodo ini
            while (pre.getSigAdyacente() != enlace) {
                pre = pre.getSigAdyacente();
            }
        }
        return pre;
    }

    private boolean existeCaminoAux(NodoVert ini, Puerto fin, Lista visitados) {
        /*
      Metodo privado recursivo utilizado por el metodo publico existeCamino.
      Este metodo se encarga de recorrer el grafo de una manera recursiva
      para manejar de manera mas controlada la lista de nodos visitados y asi
      no entrar en bucles.
      El metodo devolvera un booleano para saber si existe un camino desde el nodo vertice ini
      al nodo vertice que contiene el puerto fin
         */
        boolean exito = false;
        if (ini != null) { //Me aseguro que el nodo ini enviado por parametro no sea nulo
            if (ini.getElem() == fin) { //Si el elemento del nodo ini es el mismo que el puerto buscado, entonces no necesito iterar, y llegue a mi caso base
                exito = true;
            } else { //En caso que no sea el buscado, tendre que iterar
                visitados.insertar(ini.getElem(), visitados.longitud() + 1); //Agrego el elemento a la lista visitados para saber que ya lo visite
                NodoAdy ady = ini.getPrimerAdy();
                while (!exito && ady != null) { //Itero hasta encontrar el puerto destino o hasta que se me termine la lista de adyacencias
                    if (visitados.localizar(ady.getVertice().getElem()) < 0) { //Si el puerto todavia no lo visite, entonces hago una llamada recursiva del metodo existeCaminoAux
                        exito = existeCaminoAux(ady.getVertice(), fin, visitados); //Hago la llamada con el nodo vertice que le corresponder el nodo adyacente
                    }
                    ady = ady.getSigAdyacente(); //Una vez que vuelvo de la llamada recursiva, obtengo el siguiente nodo adyacente y pruebo un nuevo camino
                }
            }

        }
        return exito;
    }

    private void profundidadDesde(NodoVert n, Lista vis) {
        /*
      Metodo privado recursivo que servira para recorrer el grafo segun el algoritmo de recorrido en profundidad (Depth-First Search)
      El metodo no tendra que devolver ninguna lista ya que trabajara sobre la lista enviada por parametros, ya que la lista de visitados (una vez recorrida toda la estructura)
      coincidira con el recorrido en profundidad
      El nodo vertice enviado por parametro indica desde donde empezara el recorrido, aunque suele ser desde el inicial, esto se puede cambiar en el metodo publico que le utiliza este metodo
         */
        if (n != null) { //Compruebo que el vector enviado por parametro no sea nulo
            vis.insertar(n.getElem(), vis.longitud() + 1); //Inserto el elemento del nodo vertice en la lista vis, para asegurarme que este nodo ya fue visitado
            NodoAdy ady = n.getPrimerAdy();
            while (ady != null) { //Itero para cada elemento de la lista de adyacencia
                if (vis.localizar(ady.getVertice().getElem()) < 0) { //Si el elemento del nodo de la lista de adyacencia no se encuentra en la lista de visitados, entonces hago una llamda recursiva de este mismo metodo
                    profundidadDesde(ady.getVertice(), vis); //Realizo la llamada recursiva con el nodo vertice que le corresponde al nodo adyacente
                }
                ady = ady.getSigAdyacente(); //Una vez que vuelvo de la llamada recursiva, obtengo el siguiente nodo adyacente y vuelvo a iterar para encontrar nodos no visitados
            }
        }
    }

    private void anchuraDesde(NodoVert n, Lista vis) { 
        /*
      Metodo privado recursivo que servira para recorrer el grafo segun el algoritmo de recorrido en anchura (Breadth First Search)
      El metodo no tendra que devolver ninguna lista ya que trabajara sobre la lista enviada por parametros, ya que la lista de visitados (una vez recorrida toda la estructura)
      coincidira con el recorrido en anchura
      El nodo vertice enviado por parametro indica desde donde empezara el recorrido, aunque suele ser desde el inicial, esto se puede cambiar en el metodo publico que le utiliza este metodo
         */
        ColaPuerto c = new ColaPuerto();
        Puerto u = new Puerto();
        if (n != null) {
            c.poner(n.getElem());
            while (!c.esVacia()) {
                u = c.obtenerFrente();
                c.sacar();
                vis.insertar(u, vis.longitud() + 1);
                NodoAdy ady = n.getPrimerAdy();
                while (ady != null) {
                    if (vis.localizar(u) < 0) {
                        c.poner(u);
                    }
                    ady = ady.getSigAdyacente();
                }
            }

        }
    }

    private void caminoMasCortoAux(NodoVert ini, NodoVert fin, Lista visitados, int millas, ResultadoCamino res) {
        /*
      Metodo privado recursivo utilizado por el metodo publico caminoMasCorto para por recorrer la estructura de forma recursiva
      y asi hallar el camino que existe desde el nodo ini al nodo fin con la menor cantidad de millas posibles
      El metodo no retorna nada ya que trabaja sobre el ultimo parametro que es un objeto ResultadoCamino el cual contendra
      el camino y cantidad de millas, por lo que dicho objeto sera modificado a traves de las distintas llamadas recursivas
         */
        if (ini != null) { //Compruebo que el nodo ini es no nulo
            if (millas < res.getMillas() || res.getMillas() == 0) {  //Ahora hay que comprobar que dicho camino es el de menor millas posibles, si lo es, procedo a actualizar los datos de res
                visitados.insertar(ini.getElem(), visitados.longitud() + 1); //Inserto el nodo en la lista de visitados para luego no tenerlo en cuenta a la hora de buscar el recorrido

                if (ini == fin) { //Si el nodo ini es igual al nodo fin, quiere decir que encontre mi caso base en la llamada recursiva, es decir, un camino entre los dos nodos iniciales

                    res.setCamino(visitados.clonar());
                    res.setMillas(millas);

                } else { //Si fin no es igual a ini, significa que todavia no encontre un camino entre los dos nodos iniciales
                    NodoAdy ady = ini.getPrimerAdy();

                    while (ady != null) { //Procedo a iterar sobre la lista de adyacentes del nodo vertice ini
                        if (visitados.localizar(ady.getVertice().getElem()) < 0) { //Si el nodo todavia no fue visitado
                            caminoMasCortoAux(ady.getVertice(), fin, visitados, (millas + ady.getEtiqueta()), res); //Entonces hago una llamada recursiva al nodo con la cantidad de millas mas la cantidad del arco que representa
                        }
                        ady = ady.getSigAdyacente();
                    }

                }

                visitados.eliminar(visitados.longitud()); //Elimino el ultimo nodo agregado para proceder con otros nodos de la estructura
            }
        }
    }

    private void caminoMasLargoAux(NodoVert ini, NodoVert fin, Lista visitados, int millas, ResultadoCamino res) {
        /*
      Metodo privado recursivo que analogamente al metodo caminoMasCortoAux, se encarga de recorrer la estructura de forma recursiva
      para hallar el camino que existe entre los dos nodos iniciales enviados por parametro, pero dicho camino sera el de mayor
      longitud posible
         */
        if (ini != null) { //Compruebo que el nodo ini es no nulo
            visitados.insertar(ini.getElem(), visitados.longitud() + 1); //Inserto el nodo en la lista de visitados para luego no tenerlo en cuenta a la hora de buscar el recorrido
            if (ini == fin) {  //Si el nodo ini es igual al nodo fin, quiere decir que encontre mi caso base en la llamada recursiva, es decir, un camino entre los dos nodos iniciales
                if (millas > res.getMillas()) {  //Ahora hay que comprobar que dicho camino es el de mayor millas posibles, si lo es, procedo a actualizar los datos de res
                    res.setCamino(visitados.clonar());
                    res.setMillas(millas);
                }
            } else { //Si fin no es igual a ini, significa que todavia no encontre un camino entre los dos nodos iniciales
                NodoAdy ady = ini.getPrimerAdy();
                while (ady != null) { //Procedo a iterar sobre la lista de adyacentes del nodo vertice ini
                    if (visitados.localizar(ady.getVertice().getElem()) < 0) { //Si el nodo todavia no fue visitado
                        caminoMasLargoAux(ady.getVertice(), fin, visitados, (millas + ady.getEtiqueta()), res); //Entonces hago una llamada recursiva al nodo con la cantidad de millas mas la cantidad del arco que representa
                    }
                    ady = ady.getSigAdyacente();
                }

            }
            visitados.eliminar(visitados.longitud()); //Elimino el ultimo nodo agregado para proceder con otros nodos de la estructura
        }
    }

    private void caminoMenorPuertosAux(NodoVert ini, NodoVert fin, Lista visitados, int cantPuertos, ResultadoCamino resultado) {
        /*
      Metodo privado recursivo, que al igual que los dos metodos anteriores, se encarga de recorrer la estructura de forma recursiva para
      hallar un camino entre los dos nodos iniciales dados, pero esta vez no se considera la distancia en millas del camino, sino, sera
      el camino entre ambos nodos pero con la menor cantidad de nodos intermedios
         */
        if (ini != null) { //Compruebo que el nodo ini es no nulo
            if (cantPuertos < resultado.getCantPuertos() || resultado.getCantPuertos() == 0) {
                visitados.insertar(ini.getElem(), visitados.longitud() + 1); //Inserto el nodo en la lista de visitados para luego no tenerlo en cuenta a la hora de buscar el recorrido

                if (ini == fin) { //Si el nodo ini es igual al nodo fin, quiere decir que encontre mi caso base en la llamada recursiva, es decir, un camino entre los dos nodos iniciales
                    //Si la cantidad de nodos intermedios es menor que a la del camino previamente encontrado o si es el primer camino encontrado, entonces se actualiza el resultado a los nuevos valores
                    resultado.setCamino(visitados.clonar());
                    resultado.setCantPuertos(cantPuertos);

                } else { //Si fin no es igual a ini, significa que todavia no encontre un camino entre los dos nodos iniciales
                    NodoAdy ady = ini.getPrimerAdy();
                    while (ady != null) { //Procedo a iterar sobre la lista de adyacentes del nodo vertice ini
                        if (visitados.localizar(ady.getVertice().getElem()) < 0) {  //Si el nodo todavia no fue visitado
                            caminoMenorPuertosAux(ady.getVertice(), fin, visitados, cantPuertos + 1, resultado); //Realizo la llamada recursiva con el siguiente nodo y incremento en uno la cantidad de puertos, ya que se que si el siguiente camino es valido, entonces tendra un nodo intermedio mas
                        }
                        ady = ady.getSigAdyacente();
                    }

                }

                visitados.eliminar(visitados.longitud()); //Elimino el ultimo nodo agregado para proceder con otros nodos de la estructura
                cantPuertos--; //Como saco un elemento de la lista de visitados, se que la proxima llamda recursiva tendra un nodo menos, por lo que decremento en uno el conteo de los puertos intermedios
            }
        }

    }

    private void caminoSinNAux(NodoVert ini, NodoVert fin, NodoVert n, Lista visitados, Lista resultado) {
        /*
      Metodo privado recursivo que se encargara de recorrer la estructura de forma recursiva buscando un camino
      entre los dos nodos iniciales dados por parametros, pero tendra la particularidad que si el camino encontrado
      tiene el nodo n (tercer parametro), entonces este camino no sera valido, y tendra que encontrar otro camino
      Si no existe un camino entre los dos nodos, pero que tal camino no contenga a n, entonces el metodo entregara
      una lista vacia
         */
        if (ini != null) { //Compruebo que el nodo ini es no nulo
            if (ini != n && (visitados.longitud() < resultado.longitud() || resultado.longitud() == 0)) {
                visitados.insertar(ini.getElem(), visitados.longitud() + 1); //Inserto el nodo en la lista de visitados para luego no tenerlo en cuenta a la hora de buscar el recorrido
                //Compruebo que el nodo ini no sea n, ya que a la hora de las llamadas recursivas, este nodo puede ser un nodo intermedio que sea igual a n, y por lo tanto no seria valido para el camino
                //Como se que el nodo n no esta en el camino, procedo a analizar el camino actual
                if (ini == fin) { //Si llego a un camino que vaya de ini a fin y que tenga una distancia menor a un camino previamente hallado (o que no se haya encontrado otro camino valido)
                    resultado.setLista(visitados.clonar()); //Establezco la nueva lista que representa un camino de ini a fin, sin el nodo n

                } else { //En caso de que no haya encontrado todavia el camino, tengo que seguir buscando uno
                    NodoAdy ady = ini.getPrimerAdy();
                    while (ady != null) { //Itero sobre los distintos nodos adyacentes del nodo ini
                        if (visitados.localizar(ady.getVertice().getElem()) < 0) { //Si el nodo todavia no fue visitado, procedo a hacer una llamada recursiva con el mismo
                            caminoSinNAux(ady.getVertice(), fin, n, visitados, resultado); //Realizo la llamada recursiva con el siguiente nodo a comprobar
                        }
                        ady = ady.getSigAdyacente();
                    }

                }

                visitados.eliminar(visitados.longitud());  //Elimino el ultimo nodo agregado para proceder con otros nodos de la estructura
            }
        }

    }

}
