/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Puerto;

import Barco.Barco;
import ColaPrioridadBarcos.ColaPrio;


/**
 *
 * @author grios
 */
public class Puerto {
  /*
  La clase Puerto representara los puertos cargados en el sistema. De los puertos se almacenara
  su nombre, el pais donde se encuentran, la cantidad de darsenas que contiene, un arreglo
  que representan los barcos en cada darsena y una cola de prioridad que representa
  los barcos en espera para poder ingresar a las darsenas del puerto.
  La clase Puerto tambien sera utilizada en las rutas maritimas para poder representar
  los lugares de llegada y origen de los viajes.
  */

    ////////////////////ATRIBUTOS///////////////////////////////
    private String nombre;
    private String pais;
    private int cantDar;
    private Barco[] darsenas;
    private ColaPrio barcosEspera;

    ////////////////////CONSTRUCTORES///////////////////////////
    public Puerto() {
        nombre = null;
        pais = null;
        cantDar = 0;
        darsenas = null;
        barcosEspera = null;
    }

    public Puerto(String nom, String pas, int canDar, Barco[] dar, ColaPrio espera) {
        nombre = nom;
        pais = pas;
        cantDar = canDar;
        darsenas = dar;
        barcosEspera = espera;
    }

    public Puerto(String nom, String pas, int canDar) {
        nombre = nom;
        pais = pas;
        cantDar = canDar;
        darsenas = new Barco[cantDar];
        barcosEspera = new ColaPrio();
    }

    /////////////////////METODOS////////////////////////////////
    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public int getCantDar() {
        return cantDar;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public void setCantDar(int cantDar) {
        this.cantDar = cantDar;
    }

    public void setDarsenas(Barco[] darsenas) {
        this.darsenas = darsenas;
    }

    public void setBarcosEspera(ColaPrio barcosEspera) {
        this.barcosEspera = barcosEspera;
    }

    public Barco[] getDarsenas() {
        return this.darsenas;
    }

    public ColaPrio getEspera() {
        return this.barcosEspera;
    }

    public boolean ponerBarco(Barco b) { 
       return this.barcosEspera.insertar(b, b.getTipo());

    }

    public boolean sacarBarcoDeDarsena(Barco b) { //Saca un barco dado de la darsenas del puerto (Si esta en alguna)
        boolean exito = false;
        if (b != null) {
            for (int i = 0; i < darsenas.length; i++) {
                if (darsenas[i] != null) {
                    if (darsenas[i].equals(b)) {
                        exito = true;
                        darsenas[i] = null;
                    }
                }
            }
        }
        return exito;
    }

}
