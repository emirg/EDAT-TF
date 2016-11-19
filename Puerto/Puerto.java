/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Puerto;

import Barco.Barco;
import ColaPrioridadBarcos.ColaPrio;
import PuertosLista.Lista;

/**
 *
 * @author grios
 */
public class Puerto {

    private String nombre;
    private String pais;
    private int cantDar;
    private Barco[] darsenas;
    private ColaPrio barcosEspera;

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

    public boolean sacarBarcoDeDarsena(Barco b) {
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
