package Sistema;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Barco.Barco;
import ColaPrioridadBarcos.ColaPrio;
import Puerto.Puerto;
import DiccAVL.DiccAVL;
import Grafos.Grafo;
import Grafos.ResultadoCamino;
import PuertosLista.Lista;
import java.util.Hashtable;

/**
 *
 * @author emiliano
 */
public class SistemaMaritimo {

    /*
  La clase SistemaMaritimo es la clase encargada de contener todas las estructuras de datos y
  la que tendra acceso a hacer cambios en estas estructuras. Esta clase permite un mejor manejo
  de la informacion, accesos y control de errores. Ademas permite un mayor nivel de abstraccion,
  no permitiendo a la clase Interfaz (clase que interactua con el usuario) a tener un acceso
  directo a las estructuras de datos.
  Esta clase sera la encargada de recibir informacion, actualizar o crear estructuras y de
  devolver informacion solicitada por la interfaz.
     */
    /////////////////////ATRIBUTOS////////////////////////////////
    private Hashtable<String, Barco> tablaBarcos; //Estructura con la informacion de los barcos cargados en el sistema
    private Grafo rutasMaritimas; //Estructura con la informacion de las rutas maritimas cargadas en el sistema, representadas mediante un grafo no dirigido
    private DiccAVL tablaPuertos; //Estructura con la informacion de los puertos cargados en el sistema

    /////////////////////CONSTRUCTORES////////////////////////////////
    public SistemaMaritimo() {
        this.tablaBarcos = new Hashtable();
        this.tablaPuertos = new DiccAVL();
        this.rutasMaritimas = new Grafo();

    }

    /////////////////////METODOS////////////////////////////////
    public void setTablaBarcos(Hashtable<String, Barco> tablaBarcos) {
        this.tablaBarcos = tablaBarcos;
    }

    public void setRutasMaritimas(Grafo rutasMaritimas) {
        this.rutasMaritimas = rutasMaritimas;
    }

    public void setTablaPuertos(DiccAVL tablaPuertos) {
        this.tablaPuertos = tablaPuertos;
    }

    public Hashtable<String, Barco> getTablaBarcos() {
        return tablaBarcos;
    }

    public Grafo getRutasMaritimas() {
        return rutasMaritimas;
    }

    public DiccAVL getTablaPuertos() {
        return tablaPuertos;
    }

    public boolean darAltaPuerto(String nombre, String pais, int cantDar) { //Dado un nombre, un pais y una cantidad de darsenas, se inicializa un puerto y se lo inserta en la tabla de puertos y en la estructura de rutas maritimas
        Puerto p = new Puerto(nombre.toUpperCase(), pais, cantDar);
        boolean exito = tablaPuertos.insertar(p.getNombre(), p); //Consultar sobre clave
        this.rutasMaritimas.insertarVertice(nombre.toUpperCase(), p); //REvisar

        return exito;
    }

    public boolean darAltaBarco(String nombre, String tipo, String pais, String puerto) { //Dado un nombre, tipo, pais y puerto se inicializa un barco con los datos dados y se lo coloca en el puerto enviado por parametro
        Puerto p = tablaPuertos.obtenerDato(puerto);
        boolean exito = false;
        if (p != null) {
            Barco b = new Barco(nombre.toUpperCase(), tipo, pais);
            tablaBarcos.put(nombre, b);
            tablaPuertos.obtenerDato(puerto).ponerBarco(b);
            exito = true;
        }
        return exito;
    }

    public boolean darBajaPuerto(String clave) { //Dada la clave (nombre del puerto), se elimina un puerto (correspondiente a la clave) de las estructuras de las rutas maritimas y de la tabla de puertos

        return tablaPuertos.eliminar(clave) && rutasMaritimas.eliminarVertice(clave);

    }

    public boolean darBajaRuta(String ini, String fin) { //Dados dos puertos, se elimina la ruta entre estas puertos (si existe)
        return this.rutasMaritimas.eliminarArco(ini, fin);

    }

    public String puertosAlfabeticamente() { //Metodo que retorna un String con todos los puertos registrados en la tabla de puertos en orden alfabetico
        String resultado = "";
        if (tablaPuertos.inOrden().equalsIgnoreCase("Arbol vacio")) {
            resultado = "No hay puertos registrados";
        } else {
            resultado = tablaPuertos.inOrden();
        }
        return resultado;
    }

    public String puertosConEspera() { //Metodo que retorna un String con todos los puertos que tienen barcos en lista de espera
        return tablaPuertos.tieneEspera();
    }

    public String puertosRango(String min, String max) { //Dados dos String, se devuelve un String con todos los puertos cuyos nombres esten alfabeticamente en el rango generado por los strings enviados
        Lista cadena = tablaPuertos.rangoAlfabetico(min, max);
        return cadena.toString();

    }

    public String estructuraPuertos() { //Metodo que retorna un String con la estructura de la tabla de puertos (Metodo con fines de debugging)
        return tablaPuertos.toString();
    }

    public String obtenerDatosBarco(String clave) { //Dada una clave se obtiene la informacion correspondiente a un barco con dicha clave como nombre
        String cadena = "No existe tal barco.";
        Barco b = tablaBarcos.get(clave.toUpperCase());
        if (b != null) {
            cadena = b.toString();
        }
        return cadena;
    }

    public String estadoDarsenas(String clave) { //Dada una clave, se devuelve un String con la informacion de las darsenas (su contenido) del puerto al que le corresponde dicha clave
        Puerto p = tablaPuertos.obtenerDato(clave);
        String cadena = "No existe el puerto " + clave + " en el sistema";
        if (p != null) {
            cadena = "";
            Barco[] b = p.getDarsenas();

            for (int i = 0; i < b.length; i++) {
                if (b[i] == null) {
                    cadena = cadena + "Darsena " + (i + 1) + ": " + "Vacia" + "\n";
                } else {
                    cadena = cadena + "Darsena " + (i + 1) + ": " + "Ocupada - Barco: " + b[i].getCodigo() + "\n";
                }
            }
        }
        return cadena;

    }

    public boolean ingresarADarsenas(String clave) { //Metodo que realiza el ingreso a darsenas de todos los barcos en lista de espera de un puerto dado (siempre y cuando dichas darsenas esten vacias)
        Puerto p = this.tablaPuertos.obtenerDato(clave);
        boolean exito = false;
        if (p != null) {
            ColaPrio temp = p.getEspera();
            Barco[] darsenas = p.getDarsenas();

            for (int i = 0; i < darsenas.length; i++) {
                if (!temp.estaVacia()) {
                    if (darsenas[i] == null) {
                        darsenas[i] = temp.recuperarFrente();
                        temp.eliminarFrente();
                        exito = true;
                    }
                }
            }
        }
        return exito;
    }

    public boolean transferirBarco(String barco, String puertoO, String puertoD) { //Metodo encargado de transferir un barco desde la darsena de un puerto a la cola de espera de otro puerto
        Barco b = this.tablaBarcos.get(barco.toUpperCase());
        boolean exito = false;
        Puerto p =this.tablaPuertos.obtenerDato(puertoO);
        if (b != null && p!= null && p.sacarBarcoDeDarsena(b)) {
            exito = this.tablaPuertos.obtenerDato(puertoD).ponerBarco(b);
        }
        return exito;
    }

    public String mostrarEspera(String puerto) { //Dado un puerto, retorna un String mostrando los barcos en lista de espera en el puerto
        Puerto p = this.tablaPuertos.obtenerDato(puerto);
        String resultado = "No existe puerto llamado " + puerto;
        if (p != null) {
            resultado = p.getEspera().toString();
        }
        return resultado;
    }

    public boolean existeCaminoMillas(String ini, String fin, int millas) { //Dados dos puertos y una cantidad de millas, se retornara un booleano indicando si existe o no un camino entre dichos puertos que no supere la cantidad de millas ingresadas
        boolean existe = false;
        ResultadoCamino res = rutasMaritimas.caminoMasCorto(ini, fin);
        if (res.getMillas() <= millas && res.getCamino() != null) {
            existe = true;
        }
        return existe;
    }

    public String caminoMenorPuertos(String ini, String fin) {  //Dados dos puertos, se retorna un String del camino entre dos puertos que tenga la menor cantidad de puertos intermedios (sin considerar la distancia en millas)
        ResultadoCamino res = rutasMaritimas.caminoMenorPuertos(ini, fin);
        String resultado = "No existe una ruta entre dichos puertos";
        if (res.getCamino() != null) {
            resultado = res.getCamino().toString() + " -- " + res.getMillas();
        }
        return resultado;
    }

    public String caminoSinPuertoN(String ini, String fin, String n) { //Dados tres puertos, se retorna un String del camino entre los dos primeros puertos, tal que dicho camino no contenga al tercer puerto
        Lista resultado = rutasMaritimas.caminoSinN(ini, fin, n);
        String res = "No existe un camino entre dichos puertos, o no existe camino que no contenga al puerto " + n;
        if (resultado.longitud() != 0) {
            res = resultado.toString();
        }
        return res;
    }

    public boolean darAltaRuta(String p1, String p2, int dist) { //Dados dos puertos y una distancia, se crea una ruta entre dichos puertos con la distancia dada
        return this.rutasMaritimas.insertarArco(p1, p2, dist);
    }

    public String estructuraGrafos() { //Metodo que retorna un String con la informacion de la estructura de las rutas maritimas (Metodo con fines de debugging)
        return this.rutasMaritimas.toString();
    }

    public String caminoMasCorto(String ini, String fin) { //Dados dos puertos, retornar un String con el camino entre dichos puertos de menor distancia (en millas)
        ResultadoCamino res = this.rutasMaritimas.caminoMasCorto(ini, fin);
        String resultado = "No existe camino entre los dos puertos dados";
        if (res.getCamino() != null) {
            resultado = res.getCamino().toString() + " -- " + res.getMillas();
        }
        return resultado;
    }

    public void cargaPorDefecto() { //Metodo que carga al sistema con ciertos puertos, barcos y rutas por defecto (Util a la hora de debugging)

        ////////////////PUERTOS////////////////
        Puerto p1 = new Puerto("MAR DEL PLATA", "ARGENTINA", 3);
        Puerto p2 = new Puerto("PUERTO MADRYN", "ARGENTINA", 5);
        Puerto p3 = new Puerto("PORTO ALEGRE", "BRASIL", 2);
        Puerto p4 = new Puerto("ARATU", "BRASIL", 6);
        Puerto p5 = new Puerto("CIUDAD DEL CABO", "SUDAFRICA", 7);
        Puerto p6 = new Puerto("ACCRA", "GHANA", 3);
        Puerto p7 = new Puerto("LISBOA", "PORTUGAL", 4);
        Puerto p8 = new Puerto("BARCELONA", "ESPAÑA", 8);
        Puerto p9 = new Puerto("GIJON", "ESPAÑA", 5);
        Puerto p10 = new Puerto("BORDEAUX", "FRANCIA", 3);
        Puerto p11 = new Puerto("BAR HARBOR", "ESTADOS UNIDOS", 10);
        Puerto p12 = new Puerto("AGUADILLA", "PUERTO RICO", 4);
        Puerto p13 = new Puerto("LONDRES", "INGLATERRA", 5);
        Puerto p14 = new Puerto("BORGHOLM", "SUECIA", 3);
        Puerto p15 = new Puerto("HELSINKI", "FINLANDIA", 4);
        Puerto p16 = new Puerto("COPENHAGUE", "DINAMARCA", 3);
        Puerto p17 = new Puerto("FLEKKEFJORD", "NORUEGA", 2);

        this.tablaPuertos.insertar("MAR DEL PLATA", p1);
        this.tablaPuertos.insertar("PUERTO MADRYN", p2);
        this.tablaPuertos.insertar("PORTO ALEGRE", p3);
        this.tablaPuertos.insertar("ARATU", p4);
        this.tablaPuertos.insertar("CIUDAD DEL CABO", p5);
        this.tablaPuertos.insertar("ACCRA", p6);
        this.tablaPuertos.insertar("LISBOA", p7);
        this.tablaPuertos.insertar("BARCELONA", p8);
        this.tablaPuertos.insertar("GIJON", p9);
        this.tablaPuertos.insertar("BORDEAUX", p10);
        this.tablaPuertos.insertar("BAR HARBOR", p11);
        this.tablaPuertos.insertar("AGUADILLA", p12);
        this.tablaPuertos.insertar("LONDRES", p13);
        this.tablaPuertos.insertar("BORGHOLM", p14);
        this.tablaPuertos.insertar("HELSINKI", p15);
        this.tablaPuertos.insertar("COPENHAGUE", p16);
        this.tablaPuertos.insertar("FLEKKEFJORD", p17);

        this.rutasMaritimas.insertarVertice("MAR DEL PLATA", p1);
        this.rutasMaritimas.insertarVertice("PUERTO MADRYN", p2);
        this.rutasMaritimas.insertarVertice("PORTO ALEGRE", p3);
        this.rutasMaritimas.insertarVertice("ARATU", p4);
        this.rutasMaritimas.insertarVertice("CIUDAD DEL CABO", p5);
        this.rutasMaritimas.insertarVertice("ACCRA", p6);
        this.rutasMaritimas.insertarVertice("LISBOA", p7);
        this.rutasMaritimas.insertarVertice("BARCELONA", p8);
        this.rutasMaritimas.insertarVertice("GIJON", p9);
        this.rutasMaritimas.insertarVertice("BORDEAUX", p10);
        this.rutasMaritimas.insertarVertice("BAR HARBOR", p11);
        this.rutasMaritimas.insertarVertice("AGUADILLA", p12);
        this.rutasMaritimas.insertarVertice("LONDRES", p13);
        this.rutasMaritimas.insertarVertice("BORGHOLM", p14);
        this.rutasMaritimas.insertarVertice("HELSINKI", p15);
        this.rutasMaritimas.insertarVertice("COPENHAGUE", p16);
        this.rutasMaritimas.insertarVertice("FLEKKEFJORD", p17);
        this.rutasMaritimas.insertarArco("MAR DEL PLATA", "PUERTO MADRYN", 578);
        this.rutasMaritimas.insertarArco("MAR DEL PLATA", "PORTO ALEGRE", 737);
        this.rutasMaritimas.insertarArco("PORTO ALEGRE", "CIUDAD DEL CABO", 4423);
        this.rutasMaritimas.insertarArco("PUERTO MADRYN", "CIUDAD DEL CABO", 4423);
        this.rutasMaritimas.insertarArco("CIUDAD DEL CABO", "ACCRA", 5082);
        this.rutasMaritimas.insertarArco("ACCRA", "LISBOA", 5425);
        this.rutasMaritimas.insertarArco("LISBOA", "BARCELONA", 965);
        this.rutasMaritimas.insertarArco("GIJON", "LISBOA", 538);
        this.rutasMaritimas.insertarArco("GIJON", "BORDEAUX", 332);
        this.rutasMaritimas.insertarArco("BAR HARBOR", "GIJON", 3115);
        this.rutasMaritimas.insertarArco("ARATU", "AGUADILLA", 3484);
        this.rutasMaritimas.insertarArco("AGUADILLA", "BAR HARBOR", 1822);
        this.rutasMaritimas.insertarArco("BORDEAUX", "LONDRES", 770);
        this.rutasMaritimas.insertarArco("COPENHAGUE", "LONDRES", 658);
        this.rutasMaritimas.insertarArco("FLEKKEFJORD", "COPENHAGUE", 354);
        this.rutasMaritimas.insertarArco("COPENHAGUE", "BORGHOLM", 239);
        this.rutasMaritimas.insertarArco("HELSINKI", "BORGHOLM", 392);
        this.rutasMaritimas.insertarArco("AGUADILLA", "LONDRES", 4451);
        this.rutasMaritimas.insertarArco("BAR HARBOR", "LONDRES", 3377);
        this.rutasMaritimas.insertarArco("PORTO ALEGRE", "ARATU", 1876);
        this.rutasMaritimas.insertarArco("ARATU", "ACCRA", 2963);
        this.rutasMaritimas.insertarArco("MAR DEL PLATA", "LISBOA", 6202);
        this.rutasMaritimas.insertarArco("CIUDAD DEL CABO", "BAR HARBOR", 7668);
        this.rutasMaritimas.insertarArco("AGUADILLA", "LISBOA", 3842);
        this.rutasMaritimas.insertarArco("BAR HARBOR", "BORDEAUX", 3311);

        ////////////////BARCOS////////////////
        Barco b1 = new Barco("ARGOFICIAL", "PESQUERO", "ARGENTINA");
        Barco b2 = new Barco("INGOFICIAL", "PASAJERO", "INGLATERRA");
        Barco b3 = new Barco("SUDAFRICAOFICIAL", "CARGA", "SUDAFRICA");
        Barco b4 = new Barco("ESPANAOFICIAL", "PESQUERO", "ESPAÑA");
        Barco b5 = new Barco("PRICOOFICIAL", "CARGA", "PUERTO RICO");

        this.tablaBarcos.put("ARGOFICIAL", b1);
        this.tablaBarcos.put("INGOFICIAL", b2);
        this.tablaBarcos.put("SUDAFRICAOFICIAL", b3);
        this.tablaBarcos.put("ESPANAOFICIAL", b4);
        this.tablaBarcos.put("PRICOOFICIAL", b5);

        this.tablaPuertos.obtenerDato("MAR DEL PLATA").ponerBarco(b1);
        this.tablaPuertos.obtenerDato("MAR DEL PLATA").ponerBarco(b2);
        this.tablaPuertos.obtenerDato("CIUDAD DEL CABO").ponerBarco(b3);
        this.tablaPuertos.obtenerDato("BARCELONA").ponerBarco(b4);
        this.tablaPuertos.obtenerDato("AGUADILLA").ponerBarco(b5);

    }
}
