/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Barco;

/**
 *
 * @author emiliano
 */
public class Barco {

    private String codigo;
    private int tipo;
    /* 
     Tipo 0 = Pesquero
     Tipo 1 = Pasajero
     TIpo 2 = Carga    
     */
    private String pais;

    public Barco(String codigo, String tipo, String pais) {
        this.codigo = codigo;
        switch (tipo) { //Consultar por switch dentro de constructor
            case "Pesquero":
                this.tipo = 0;
                break;
            case "Pasajero":
                this.tipo = 1;
                break;
            case "Carga":
                this.tipo = 2;
                break;
        }
        this.pais = pais;

    }

    public Barco() {
        tipo = -1;
        pais = null;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getTipo() {
        return tipo;
    }

    public String getPais() {
        return pais;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String toString() {
        return "{Codigo: " + codigo + ", Tipo: " + tipo + ", Pais: " + pais + "}";
    }

}
