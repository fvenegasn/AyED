package tp2.ejercicio8;

import tp2.ejercicio1.BinaryTree;

public class Main {
    public static void main(String[] args) {
        ParcialArboles parcial = new ParcialArboles();

        // Primer caso: arbol1 ES prefijo de arbol2
        BinaryTree<Integer> a1_1 = new BinaryTree<>(65);
        a1_1.addLeftChild(new BinaryTree<>(37));
        a1_1.addRightChild(new BinaryTree<>(81));
        a1_1.getLeftChild().addLeftChild(new BinaryTree<>(47));

        BinaryTree<Integer> a2_1 = new BinaryTree<>(65);
        a2_1.addLeftChild(new BinaryTree<>(37));
        a2_1.addRightChild(new BinaryTree<>(81));
        a2_1.getLeftChild().addLeftChild(new BinaryTree<>(47));
        a2_1.getLeftChild().addRightChild(new BinaryTree<>(76));
        a2_1.getLeftChild().getLeftChild().addLeftChild(new BinaryTree<>(22));
        a2_1.getLeftChild().getLeftChild().addRightChild(new BinaryTree<>(47));
        a2_1.getLeftChild().getRightChild().addLeftChild(new BinaryTree<>(85));
        a2_1.getLeftChild().getRightChild().addRightChild(new BinaryTree<>(94));
        a2_1.getLeftChild().getLeftChild().getLeftChild().addLeftChild(new BinaryTree<>(11));
        a2_1.getLeftChild().getLeftChild().getLeftChild().addRightChild(new BinaryTree<>(29));

        System.out.println("1. arbol1 es prefijo de arbol2? " + parcial.esPrefijo(a1_1, a2_1)); // true

        // Segundo caso: arbol1 NO es prefijo (subárbol con raíz 93 no está en arbol2)
        BinaryTree<Integer> a1_2 = new BinaryTree<>(65);
        a1_2.addLeftChild(new BinaryTree<>(37));
        a1_2.addRightChild(new BinaryTree<>(81));
        a1_2.getLeftChild().addLeftChild(new BinaryTree<>(47));
        a1_2.getLeftChild().addRightChild(new BinaryTree<>(93));

        BinaryTree<Integer> a2_2 = a2_1; // Reutilizamos el anterior
        System.out.println("2. arbol1 es prefijo de arbol2? " + parcial.esPrefijo(a1_2, a2_2)); // false

        // Tercer caso: arbol1 NO es prefijo (contenido distinto: raíz 37 vs 62)
        BinaryTree<Integer> a1_3 = a1_2; // Igual que el anterior

        BinaryTree<Integer> a2_3 = new BinaryTree<>(65);
        a2_3.addLeftChild(new BinaryTree<>(62));
        a2_3.addRightChild(new BinaryTree<>(81));
        a2_3.getLeftChild().addLeftChild(new BinaryTree<>(22));
        a2_3.getLeftChild().addRightChild(new BinaryTree<>(47));
        a2_3.getRightChild().addLeftChild(new BinaryTree<>(76));
        a2_3.getRightChild().addRightChild(new BinaryTree<>(93));
        a2_3.getLeftChild().getLeftChild().addLeftChild(new BinaryTree<>(11));
        a2_3.getLeftChild().getLeftChild().addRightChild(new BinaryTree<>(29));
        a2_3.getRightChild().getLeftChild().addLeftChild(new BinaryTree<>(85));
        a2_3.getRightChild().getLeftChild().addRightChild(new BinaryTree<>(94));

        System.out.println("3. arbol1 es prefijo de arbol2? " + parcial.esPrefijo(a1_3, a2_3)); // false
    }
}
