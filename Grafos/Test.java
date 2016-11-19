/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafos;

import Puerto.Puerto;

/**
 *
 * @author emiliano
 */
public class Test {

    public static void main(String[] args) {
        Grafo g = new Grafo();
        Puerto p1 = new Puerto("Puerto 1", "Arg", 5);
        Puerto p2 = new Puerto("Puerto 2", "Arg", 5);
        Puerto p3 = new Puerto("Puerto 3", "Arg", 5);
        g.insertarVertice(p1.getNombre(), p1);
        g.insertarVertice(p2.getNombre(), p2);
        g.insertarVertice(p3.getNombre(), p3);
        g.insertarArco("Puerto 1", "Puerto 2", 12);
        System.out.println(g.toString());
    }
}
