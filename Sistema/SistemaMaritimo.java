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
import java.util.Hashtable;

/**
 *
 * @author emiliano
 */
public class SistemaMaritimo {

    private Hashtable<String, Barco> tablaBarcos;
    private Grafo rutasMaritimas;
    private DiccAVL tablaPuertos;

    public SistemaMaritimo() {
        this.tablaBarcos = new Hashtable<String, Barco>();
        this.tablaPuertos = new DiccAVL();
        this.rutasMaritimas = new Grafo();

    }

    public boolean darAltaPuerto(String nombre, String pais, int cantDar) {
        Puerto p = new Puerto(nombre.toUpperCase(), pais, cantDar);
        boolean exito = tablaPuertos.insertar(p.getNombre(), p); //Consultar sobre clave
        //rutas.insertarVertice(p);

        return exito;
    }

    public boolean darAltaBarco(String nombre, String tipo, String pais) {
        Barco b = new Barco(nombre.toUpperCase(), tipo, pais);
        tablaBarcos.put(nombre, b);

        return true;
    }

    public boolean darBajaPuerto(String clave) {
        return tablaPuertos.eliminar(clave);

    }

    public String puertosAlfabeticamente() {
        return tablaPuertos.inOrden();
    }

    public void cargaPorDefecto() {
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
//        this.rutasMaritimas.insertarVertice("MAR DEL PLATA", p1);
//        this.rutasMaritimas.insertarVertice("PUERTO MADRYN", p2);
//        this.rutasMaritimas.insertarVertice("PORTO ALEGRE", p3);
//        this.rutasMaritimas.insertarVertice("ARATU", p4);
//        this.rutasMaritimas.insertarVertice("CIUDAD DEL CABO", p5);
//        this.rutasMaritimas.insertarVertice("ACCRA", p6);
//        this.rutasMaritimas.insertarVertice("LISBOA", p7);
//        this.rutasMaritimas.insertarVertice("BARCELONA", p8);
//        this.rutasMaritimas.insertarVertice("GIJON", p9);
//        this.rutasMaritimas.insertarVertice("BORDEAUX", p10);
//        this.rutasMaritimas.insertarVertice("BAR HARBOR", p11);
//        this.rutasMaritimas.insertarVertice("AGUADILLA", p12);
//        this.rutasMaritimas.insertarVertice("LONDRES", p13);
//        this.rutasMaritimas.insertarVertice("BORGHOLM", p14);
//        this.rutasMaritimas.insertarVertice("HELSINKI", p15);
//        this.rutasMaritimas.insertarVertice("COPENHAGUE", p16);
//        this.rutasMaritimas.insertarVertice("FLEKKEFJORD", p17);
//        this.rutasMaritimas.insertarArco("MAR DEL PLATA", "PUERTO MADRYN", 578);
//        this.rutasMaritimas.insertarArco("MAR DEL PLATA", "PORTO ALEGRE", 737);
//        this.rutasMaritimas.insertarArco("PORTO ALEGRE", "CIUDAD DEL CABO", 4423);
//        this.rutasMaritimas.insertarArco("PUERTO MADRYN", "CIUDAD DEL CABO", 4423);
//        this.rutasMaritimas.insertarArco("CIUDAD DEL CABO", "ACCRA", 5082);
//        this.rutasMaritimas.insertarArco("ACCRA", "LISBOA", 5425);
//        this.rutasMaritimas.insertarArco("LISBOA", "BARCELONA", 965);
//        this.rutasMaritimas.insertarArco("GIJON", "LISBOA", 538);
//        this.rutasMaritimas.insertarArco("GIJON", "BORDEAUX", 332);
//        this.rutasMaritimas.insertarArco("BAR HARBOR", "GIJON", 3115);
//        this.rutasMaritimas.insertarArco("ARATU", "AGUADILLA", 3484);
//        this.rutasMaritimas.insertarArco("AGUADILLA", "BAR HARBOR", 1822);
//        this.rutasMaritimas.insertarArco("BORDEAUX", "LONDRES", 770);
//        this.rutasMaritimas.insertarArco("COPENHAGUE", "LONDRES", 658);
//        this.rutasMaritimas.insertarArco("FLEKKEFJORD", "COPENHAGUE", 354);
//        this.rutasMaritimas.insertarArco("COPENHAGUE", "BORGHOLM", 239);
//        this.rutasMaritimas.insertarArco("HELSINKI", "BORGHOLM", 392);
//        this.rutasMaritimas.insertarArco("AGUADILLA", "LONDRES", 4451);
//        this.rutasMaritimas.insertarArco("BAR HARBOR", "LONDRES", 3377);
//        this.rutasMaritimas.insertarArco("PORTO ALEGRE", "ARATU", 1876);
//        this.rutasMaritimas.insertarArco("ARATU", "ACCRA", 2963);
//        this.rutasMaritimas.insertarArco("MAR DEL PLATA", "LISBOA", 6202);
//        this.rutasMaritimas.insertarArco("CIUDAD DEL CABO", "BAR HARBOR", 7668);
//        this.rutasMaritimas.insertarArco("AGUADILLA", "LISBOA", 3842);
//        this.rutasMaritimas.insertarArco("BAR HARBOR", "BORDEAUX", 3311);
        Barco b1 = new Barco("ARGOFICIAL", "PESQUERO", "ARGENTINA");
        Barco b2 = new Barco("INGOFICIAL", "PASAJERO", "INGLATERRA");
        Barco b3 = new Barco("SUDAFRICAOFICIAL", "CARGA", "SUDAFRICA");
        Barco b4 = new Barco("ESPAÑAOFICIAL", "PESQUERO", "ESPAÑA");
        Barco b5 = new Barco("PRICOOFICIAL", "CARGA", "PUERTO RICO");
        this.tablaBarcos.put("ARGOFICIAL", b1);
        this.tablaBarcos.put("INGOFICIAL", b2);
        this.tablaBarcos.put("SUDAFRICAOFICIAL", b3);
        this.tablaBarcos.put("ESPAÑAOFICIAL", b4);
        this.tablaBarcos.put("PRICOOFICIAL", b5);
        this.tablaPuertos.obtenerDato("MAR DEL PLATA").ponerBarco(b1);
        this.tablaPuertos.obtenerDato("MAR DEL PLATA").ponerBarco(b2);
        this.tablaPuertos.obtenerDato("CIUDAD DEL CABO").ponerBarco(b3);
        this.tablaPuertos.obtenerDato("BARCELONA").ponerBarco(b4);
        this.tablaPuertos.obtenerDato("AGUADILLA").ponerBarco(b5);

    }

    public String puertosRango(String min, String max) {
        return tablaPuertos.rangoAlfabetico(min, max);

    }

    public String estructuraPuertos() {
        return tablaPuertos.toString();
    }

    public String obtenerDatosBarco(String clave) {
        String cadena = "No existe tal barco.";
        Barco b = tablaBarcos.get(clave.toUpperCase());
        if (b != null) {
            cadena = b.toString();
        }
        return cadena;
    }

    public String estadoDarsenas(String clave) {
        Puerto p = tablaPuertos.obtenerDato(clave);
        Barco[] b = p.getDarsenas();
        String cadena = "";
        for (int i = 0; i < b.length; i++) {
            if (b[i] == null) {
                cadena = cadena + "Darsena " + (i + 1) + ": " + "Vacia" + "\n";
            } else {
                cadena = cadena + "Darsena " + (i + 1) + ": " + "Ocupada - Barco: " + b[i].getCodigo() + "\n";
            }
        }
        return cadena;

    }

    public void ingresarADarsenas(String clave) {
        Puerto p = this.tablaPuertos.obtenerDato(clave);
        ColaPrio temp = p.getEspera();
        Barco[] darsenas = p.getDarsenas();

        for (int i = 0; i < darsenas.length; i++) {
            if (!temp.estaVacia()) {
                if (darsenas[i] == null) {
                    darsenas[i] = temp.recuperarFrente();
                    temp.eliminarFrente();

                }
            }
        }

    }

    public boolean transferirBarco(String barco, String puertoO, String puertoD) {
        Barco b = this.tablaBarcos.get(barco.toUpperCase());
        boolean exito = false;
        if (this.tablaPuertos.obtenerDato(puertoO).sacarBarcoDeDarsena(b)) {
            exito = this.tablaPuertos.obtenerDato(puertoD).ponerBarco(b);
        }
        return exito;
    }

    public String mostrarEspera(String puerto) {
        Puerto p = this.tablaPuertos.obtenerDato(puerto);
        return p.getEspera().toString();
    }

//    public boolean darAltaRuta(String p1, String p2, int dist) {
//        return this.rutasMaritimas.insertarArco(p1, p2, dist);
//    }
}
