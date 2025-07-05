package Parciales;

import java.util.LinkedList;

import tp2.ejercicio1.BinaryTree;
import tp2.ejercicio1.Queue;

public class Main {
    public static void main(String[] args) {
        // Crear nodos individuales
        BinaryTree<Integer> n1 = new BinaryTree<>(1);
        BinaryTree<Integer> n2 = new BinaryTree<>(2);
        BinaryTree<Integer> n3 = new BinaryTree<>(3);
        BinaryTree<Integer> n4 = new BinaryTree<>(4);
        BinaryTree<Integer> n5 = new BinaryTree<>(5);
        BinaryTree<Integer> n6 = new BinaryTree<>(6);
        BinaryTree<Integer> n7 = new BinaryTree<>(7);

        // Armar el árbol según la estructura
        n1.addLeftChild(n2);
        n1.addRightChild(n3);

        n2.addLeftChild(n4);

        n3.addLeftChild(n5);
        n3.addRightChild(n6);

        n5.addLeftChild(n7);

        // Podés probar imprimir el árbol, o usar tus métodos
        System.out.println("Raíz del árbol: " + n1.getData());
        
        ParcialArboles_290624 arbol = new ParcialArboles_290624(n1);
        
        BinaryTree<Integer> nuevo = arbol.nuevoTree();
        
        n1.entreNiveles(0, 20);
        nuevo.entreNiveles(0, 20);
    }
}

