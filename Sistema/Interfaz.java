/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sistema;

/**
 *
 * @author emiliano
 */

 /*
TRABAJO FINAL - ESTRUCTURAS DE DATOS
CURSADA 2015 - PUERTOS
ALUMNO: EMILIANO RIOS GAVAGNIN - FAI 1113
 */
 
public class Interfaz {

    public static void main(String[] args) {
        SistemaMaritimo unSistema = new SistemaMaritimo();
        int opcion;
        do {
            menu();
            opcion = TecladoIn.readLineInt();
            opciones(opcion, unSistema);
        } while (opcion != 0);

    }

    public static void opciones(int opcion, SistemaMaritimo unSistema) {

        int aux; //Opcion sera la variable utilizada para elegir la siguiente accion / Aux sera una variable auxiliar para el ingreso de datos en formato int
        String temp1, temp2, temp3, temp4; //Variables temporales que usare para el ingreso de datos en formato String
        boolean exito;
        switch (opcion) { //Acorde al menu, se ingresara una opcion y el switch se encargara de llamar al metodo apropiado

            case 1:
                System.out.print("Ingrese el nombre del puerto: ");
                temp1 = TecladoIn.readLine();
                System.out.print("Ingrese el pais del puerto: ");
                temp2 = TecladoIn.readLine();
                System.out.print("Ingrese la cantidad de darsenas: ");
                aux = TecladoIn.readLineInt();
                exito = unSistema.darAltaPuerto(temp1, temp2, aux);
                if (!exito) {
                    System.out.println("Ya existe un puerto con ese nombre");
                } else {
                    System.out.println("Se ha ingresado el nuevo puerto con exito!");
                }
                break;

            case 2:
                System.out.println("Ingrese el nombre del primer puerto: ");
                temp1 = TecladoIn.readLine();
                System.out.println("Ingrese el nombre del segundo puerto: ");
                temp2 = TecladoIn.readLine();
                System.out.println("Ingrese la distancia entre los puertos: ");
                aux = TecladoIn.readLineInt();
                if (unSistema.darAltaRuta(temp1, temp2, aux)) {
                    System.out.println("Ruta cargada en el sistema con exito!");
                } else {
                    System.out.println("Hubo algun problema durante la carga de la ruta");
                    System.out.println("Intentelo de nuevo y verifique los datos ingresados");
                }
                break;

            case 3:
                System.out.print("Ingrese el codigo/nombre del barco: ");
                temp1 = TecladoIn.readLine();
                System.out.print("Ingrese el tipo del barco: ");
                temp2 = TecladoIn.readLine();
                System.out.print("Ingrese el pais del barco: ");
                temp3 = TecladoIn.readLine();
                System.out.print("Ingrese el puerto donde se encuentra el barco: ");
                temp4 = TecladoIn.readLine();
                exito = unSistema.darAltaBarco(temp1, temp2, temp3, temp4);
                if (exito) {
                    System.out.println("Barco ingresado con exito!");
                } else {
                    System.out.println("Hubo un problema con la carga de datos");
                    System.out.println("Asegurese que el puerto dado exista en el sistema");
                    System.out.println("Recuerde que si la categoria no es valida, al barco sera considerado de carga por defecto");
                }
                break;

            case 4:
                System.out.print("Ingrese la clave del puerto: ");
                temp1 = TecladoIn.readLine();
                exito = unSistema.darBajaPuerto(temp1);
                if (!exito) {
                    System.out.println("No se ha podido eliminar el puerto. Verifique que exite");
                } else {
                    System.out.println("Se ha eliminado el puerto con exito!");
                }
                break;

            case 5:
                System.out.println("Ingrese el puerto origen: ");
                temp1 = TecladoIn.readLine();
                System.out.println("Ingrese el puerto destino: ");
                temp2 = TecladoIn.readLine();
                if (unSistema.darBajaRuta(temp1, temp2)) {
                    System.out.println("Ruta dada de baja con exito!");
                } else {
                    System.out.println("Hubo algun problema durante la baja de la ruta");
                    System.out.println("Verifique que los datos ingresados son correctos y que la ruta exista");
                }
                break;

            case 6:
                System.out.println("Los puertos registrados son los siguientes: ");
                System.out.println(unSistema.puertosAlfabeticamente());
                break;

            case 7:
                System.out.print("Ingrese el nombre/codigo del barco: ");
                temp1 = TecladoIn.readLine();
                System.out.print("Ingrese el nombre del puerto de origen: ");
                temp2 = TecladoIn.readLine();
                System.out.print("Ingrese el nombre del puerto de traslado: ");
                temp3 = TecladoIn.readLine();
                if (unSistema.transferirBarco(temp1, temp2, temp3)) {
                    System.out.println("Barco transferido con exito!");
                } else {
                    System.out.println("Hubo algun problema en el traslado del barco");
                }
                break;

            case 8:
                System.out.println("Ingrese el nombre del puerto origen: ");
                temp1 = TecladoIn.readLine();
                System.out.println("Ingrese el nombre del puerto destino: ");
                temp2 = TecladoIn.readLine();
                System.out.println("Ingrese la cantidad de millas que no puede superar el camino: ");
                aux = TecladoIn.readLineInt();
                if (unSistema.existeCaminoMillas(temp1, temp2, aux)) {
                    System.out.println("Existe un camino que no supera " + aux + " millas");
                } else {
                    System.out.println("No existe un camino que no supere " + aux + " millas");
                    System.out.println("O no existe camino entre los puertos dados");
                }
                break;

            case 9:
                System.out.println("Ingrese el nombre del puerto origen: ");
                temp1 = TecladoIn.readLine();
                System.out.println("Ingrese el nombre del puerto destino: ");
                temp2 = TecladoIn.readLine();
                System.out.println(unSistema.caminoMenorPuertos(temp1, temp2));
                break;

            case 10:
                System.out.println("Ingrese el nombre del puerto origen: ");
                temp1 = TecladoIn.readLine();
                System.out.println("Ingrese el nombre del puerto destino: ");
                temp2 = TecladoIn.readLine();
                System.out.println("Ingrese el nombre del puerto por el cual no puede pasar: ");
                temp3 = TecladoIn.readLine();
                System.out.println("\n" + unSistema.caminoSinPuertoN(temp1, temp2, temp3));
                break;

            case 11:
                System.out.print("Ingrese el nombre del puerto: ");
                temp1 = TecladoIn.readLine();
                if (unSistema.ingresarADarsenas(temp1)) {
                    System.out.println("Las darsenas vacias fueron ocupadas con exito!");
                } else {
                    System.out.println("No hubo cambios en el sistema");
                    System.out.println("Puede que no haya darsenas libres, el puerto no exista o no hayan barcos en espera en el puerto dado");
                }
                break;

            case 12:
                System.out.print("Ingrese el codigo/nombre del barco a buscar: ");
                temp1 = TecladoIn.readLine();
                System.out.println(unSistema.obtenerDatosBarco(temp1));
                break;

            case 13:
                System.out.print("Ingrese el nombre del puerto: ");
                temp1 = TecladoIn.readLine();
                System.out.println(unSistema.estadoDarsenas(temp1));
                break;

            case 14:
                System.out.println("Ingrese el nombre del puerto origen: ");
                temp1 = TecladoIn.readLine();
                System.out.println("Ingrese el nombre del puerto destino: ");
                temp2 = TecladoIn.readLine();
                System.out.println(unSistema.caminoMasCorto(temp1, temp2));
                break;

            case 15:
                System.out.print("Ingrese el min: ");
                temp1 = TecladoIn.readLine();
                System.out.print("Ingrese el max: ");
                temp2 = TecladoIn.readLine();
                System.out.println("Los puertos dentro del rango son: ");
                System.out.println(unSistema.puertosRango(temp1, temp2));
                break;

            case 16:
                System.out.print("Ingrese el nombre del puerto: ");
                temp1 = TecladoIn.readLine();
                System.out.println(unSistema.mostrarEspera(temp1));
                break;

            case 17:
                System.out.println("Los puertos con barcos esperando para ingresar a las darsenas son:");
                System.out.println(unSistema.puertosConEspera());
                break;

            case 18:
                unSistema.cargaPorDefecto();
                System.out.println("Se ha realizado la carga por defecto!");
                break;

            case 19:
                System.out.println(unSistema.estructuraPuertos());
                break;

            case 20:
                System.out.println(unSistema.estructuraGrafos());
                break;

            case 0:
                System.out.println("Seguro desea cerrar el sistema? ");
                System.out.print("S/N: ");
                temp1 = TecladoIn.readLine();
                if (temp1.equalsIgnoreCase("S")) {
                    System.out.println("Cerrando...");
                } else {
                    if (temp1.equalsIgnoreCase("N")) {
                        opcion = -1;
                    }
                }

                break;

            default:
                System.out.println("Verifique su opcion" + "\n");
                System.out.println("Recuede que debe ser un numero del 0 al 20" + "\n");
                System.out.println("Ademas debe ingresar sola y exclusivamente el numero de la opcion. Ej: 'Opcion: 18'" + "\n");
                break;
        }

    }

    public static void menu() {

        System.out.println("");
        System.out.print("-----------Sistema de información para navegantes marítimos-----------" + "\n");
        System.out.print("Seleccione una de las siguientes opciones:" + "\n");
        System.out.print("1) Dar de alta un puerto" + "\n");
        System.out.print("2) Dar de alta una ruta marítima" + "\n");
        System.out.print("3) Dar de alta un barco" + "\n");
        System.out.print("4) Dar de baja un puerto" + "\n");
        System.out.print("5) Dar de baja una ruta marítima" + "\n");
        System.out.print("6) Mostrar puertos registrados ordenados alfabéticamente" + "\n");
        System.out.print("7) Transferir un barco dado de un puerto a otro" + "\n");
        System.out.print("8) Verificar si existe camino entre dos puertos que no supere N millas" + "\n");
        System.out.print("9) Obtener ruta entre dos puertos dados, que posee la menor cantidad de puertos intermedios" + "\n");
        System.out.print("10) Camino entre dos puertos que no pase por un puerto N" + "\n");
        System.out.print("11) Dado un puerto, ingresar barcos en espera a dársenas libres" + "\n");
        System.out.print("12) Ver datos de un barco" + "\n");
        System.out.print("13) Dado un puerto, ver estado de darsenas" + "\n");
        System.out.print("14) Obtener ruta más corta entre dos puertos dados" + "\n");
        System.out.print("15) Ver puertos que se hallan alfabéticamente en un rango" + "\n");
        System.out.print("16) Mostrar cola de espera de un puerto dado" + "\n");
        System.out.print("17) Mostrar puertos con barcos en espera" + "\n");
        System.out.print("18) Realizar carga de datos por defecto" + "\n");
        System.out.print("19) Ver estructura de AVL que almacena a puertos" + "\n");
        System.out.print("20) Ver estructura de grafo que almacena a rutas marítimas" + "\n");

        System.out.print("0) SALIR" + "\n"); //Si se ingresa 0 se verificara que el usuario desea salir del sistema antes de salir
        System.out.print("---------------------------------------------------------------------" + "\n");
        System.out.print("Opcion: ");

    }
}
