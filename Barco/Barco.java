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

    /*Esta clase es la encargada de representar a los barcos, estos contienen un codigo univoco el cual
    los distingue de otros barcos. Tambien estan categorizados en tres tipos de barcos: Pesquero, pasajero y carga.
    Los barcos pesqueros tienen una prioridad alta a la hora de ingresa a un puerto ya que necesitan descargar cuanto
    antes para prevenir el deterioro de su carga
    Los barcos de pasajero tienen una prioridad media ya que no es de suma urgencia que el mismo desembarque, pero se
    prioriza el hecho que los pasajeros no tengan que esperar tanto tiempo
    Los barcos de carga son de prioridad baja ya que su carga no es considerada perecedera y por lo tanto no afecta
    el hecho que esperen un poco mas de tiempo para entrar al puerto*/

    /////////////////////ATRIBUTOS////////////////////////////////
    private String codigo; //EL codigo es representado con un String y suele ser el nombre de la embarcacion
    private int tipo; //El tipo es representado con un int ya que son solo 3 posibles embarcaciones, y debajo se detalla que numero representa cada categoria
    /*
     Tipo 0 = Pesquero
     Tipo 1 = Pasajero
     TIpo 2 = Carga
     */
    private String pais; //Se documenta tambien el origen del bote (no el origen de su viaje)

    /////////////////////CONSTRUCTORES////////////////////////////////
    public Barco(String codigo, String tipo, String pais) {
        this.codigo = codigo;
        tipo = tipo.toLowerCase();
        switch (tipo) { //Consultar por switch dentro de constructor
            case "pesquero":
                this.tipo = 0;
                break;
            case "pasajero":
                this.tipo = 1;
                break;
            case "carga":
                this.tipo = 2;
                break;
            default: //Si a la hora de crear el barco, el tipo ingresado no es valido, se considerara al barco como un barco de carga
                this.tipo = 2;
                break;
        }
        this.pais = pais;

    }

    public Barco() { //El constructor nulo establece el tipo como -1, para saber que no es un objeto que represente una entidad real, solo esta instanciado
        tipo = -1;
        pais = null;
    }

    /////////////////////METODOS////////////////////////////////
    public String getCodigo() { //Devuelve un String con el codigo del barco
        return codigo;
    }

    public int getTipo() { //Devuelve un entero con el tipo del barc
        return tipo;
    }

    public String getPais() { //Devuelve un String con el pais de origen del barco
        return pais;
    }

    public void setCodigo(String codigo) { //Establece el codigo del barco
        this.codigo = codigo;
    }

    public void setTipo(int tipo) { //Establece el tipo del barco
        this.tipo = tipo;
    }

    public void setPais(String pais) { //Establece el pais de origen del barco
        this.pais = pais;
    }

    public String toString() { //Se sobreescribe el metodo toString() para poder devolver un String que contenga la informacion relacionada al barco instanciado
        return "{Codigo: " + codigo + ", Tipo: " + tipo + ", Pais: " + pais + "}";
    }

}
