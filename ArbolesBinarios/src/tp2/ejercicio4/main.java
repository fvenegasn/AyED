package tp2.ejercicio4;

import tp2.ejercicio1.BinaryTree;
import tp2.ejercicio3.ContadorArbol;

public class main {
    public static void main(String[] args) {
        BinaryTree<Integer> root = new BinaryTree<>(10);

        BinaryTree<Integer> n2 = new BinaryTree<>(2);
        BinaryTree<Integer> n3 = new BinaryTree<>(3);
        root.addLeftChild(n2);
        root.addRightChild(n3);

        BinaryTree<Integer> n5_1 = new BinaryTree<>(5);
        BinaryTree<Integer> n4 = new BinaryTree<>(4);
        n2.addLeftChild(n5_1);
        n2.addRightChild(n4);

        BinaryTree<Integer> n7 = new BinaryTree<>(7);
        BinaryTree<Integer> n8_1 = new BinaryTree<>(8);
        n5_1.addLeftChild(n7);
        n5_1.addRightChild(n8_1);

        BinaryTree<Integer> n5_2 = new BinaryTree<>(5);
        BinaryTree<Integer> n6 = new BinaryTree<>(6);
        n4.addLeftChild(n5_2);
        n4.addRightChild(n6);

        BinaryTree<Integer> n9 = new BinaryTree<>(9);
        BinaryTree<Integer> n8_2 = new BinaryTree<>(8);
        n3.addLeftChild(n9);
        n3.addRightChild(n8_2);

        BinaryTree<Integer> n12 = new BinaryTree<>(12);
        BinaryTree<Integer> n8_3 = new BinaryTree<>(8);
        n9.addLeftChild(n12);
        n9.addRightChild(n8_3);

        BinaryTree<Integer> n2_2 = new BinaryTree<>(200);
        BinaryTree<Integer> n1 = new BinaryTree<>(1);
        n8_2.addLeftChild(n2_2);
        n8_2.addRightChild(n1);

        // Prueba
        //System.out.println("Cantidad de hojas: " + root.contarHojas()); // Debería dar 8
        RedBinariaLlena arbol = new RedBinariaLlena();
        arbol.setArbol(root);
        System.out.println(arbol.retardoReenvio()); // 34
        //System.out.println(arbol.numerosParesInOrden()); // 4, 2, 10, 6
        //System.out.println(arbol.numerosParesPostOrden()); // 4, 2, 6, 10
    }
}