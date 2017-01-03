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
public class Interfaz { //Esta es la version mas reciente (18/11/16 - 21:15)

    public static void main(String[] args) {
        SistemaMaritimo unSistema = new SistemaMaritimo();
        int opcion, aux;
        String temp1, temp2, temp3;
        boolean parar, exito;
        do {
            System.out.println("");
            System.out.println("-----------Sistema de información para navegantes marítimos-----------" + "\n");
            System.out.println("Seleccione una de las siguientes opciones:" + "\n");
            System.out.println("1) Dar de alta un puerto" + "\n");
            System.out.println("2) Dar de alta una ruta marítima" + "\n");
            System.out.println("3) Dar de alta un barco" + "\n");
            System.out.println("4) Dar de baja un puerto" + "\n");
            System.out.println("5) Dar de baja una ruta marítima" + "\n");
            System.out.println("6) Mostrar puertos registrados ordenados alfabéticamente" + "\n");
            System.out.println("7) Transferir un barco dado de un puerto a otro" + "\n");
            // System.out.println("8) Verificar si existe camino entre dos puertos que no supere N millas" + "\n");
            //  System.out.println("9) Obtener ruta entre dos puertos dados, que posee la menor cantidad de puertos intermedios" + "\n");
            System.out.println("10) Dado un puerto, ingresar barcos en espera a dársenas libres" + "\n");
            System.out.println("11) Ver datos de un barco" + "\n");
            System.out.println("12) Dado un puerto, ver estado de darsenas" + "\n");
            //  System.out.println("13) Obtener ruta más corta entre dos puertos dados" + "\n");
            System.out.println("14) Ver puertos que se hallan alfabéticamente en un rango" + "\n");
            System.out.println("15) Mostrar cola de espera de un puerto dado" + "\n");
            System.out.println("16) Realizar carga de datos por defecto" + "\n");
            System.out.println("17) Ver estructura de AVL que almacena a puertos" + "\n");
            System.out.println("18) Ver estructura de grafo que almacena a rutas marítimas" + "\n");
            System.out.println("0) SALIR" + "\n");
            System.out.println("---------------------------------------------------------------------" + "\n");
            System.out.print("Opcion: ");
            opcion = TecladoIn.readLineInt();
            System.out.println("");
            switch (opcion) {
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
                    unSistema.darAltaRuta(temp1, temp2, aux);
                    break;

                case 3:
                    System.out.print("Ingrese el codigo/nombre del barco: ");
                    temp1 = TecladoIn.readLine();
                    System.out.print("Ingrese el tipo del barco: ");
                    temp2 = TecladoIn.readLine();
                    System.out.print("Ingrese el pais del barco: ");
                    temp3 = TecladoIn.readLine();
                    unSistema.darAltaBarco(temp1, temp2, temp3);

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
                    unSistema.darBajaRuta(temp1, temp2);

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
                case 8: //Falta implementar
                    break;
                case 9: //Falta implementar
                    break;
                case 10:
                    System.out.print("Ingrese el nombre del puerto: ");
                    temp1 = TecladoIn.readLine();
                    unSistema.ingresarADarsenas(temp1);

                    break;
                case 11:
                    System.out.print("Ingrese el codigo/nombre del barco a buscar: ");
                    temp1 = TecladoIn.readLine();
                    System.out.println(unSistema.obtenerDatosBarco(temp1));
                    break;
                case 12:
                    System.out.print("Ingrese el nombre del puerto: ");
                    temp1 = TecladoIn.readLine();
                    System.out.println(unSistema.estadoDarsenas(temp1));
                    break;
                case 13:
                    break;
                case 14:
                    System.out.print("Ingrese el min: ");
                    temp1 = TecladoIn.readLine();
                    System.out.print("Ingrese el max: ");
                    temp2 = TecladoIn.readLine();
                    System.out.println("Los puertos dentro del rango son: ");
                    System.out.println(unSistema.puertosRango(temp1, temp2));

                    break;
                case 15:
                    System.out.print("Ingrese el nombre del puerto: ");
                    temp1 = TecladoIn.readLine();
                    System.out.println(unSistema.mostrarEspera(temp1));
                    break;

                case 16:
                    unSistema.cargaPorDefecto();
                    System.out.println("Se ha realizado la carga por defecto!");
                    break;
                case 17:
                    System.out.println(unSistema.estructuraPuertos());
                    break;
                case 18:
                    System.out.println(unSistema.estructuraGrafos());
                    break;
                case 0:
                    break;
            }

        } while (opcion != 0);

    }
}
