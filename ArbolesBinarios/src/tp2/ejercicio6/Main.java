package tp2.ejercicio6;

import tp2.ejercicio1.BinaryTree;

public class Main {
    public static void main(String[] args) {
        // Nodos hojas
        BinaryTree<Integer> nodo7 = new BinaryTree<>(7);
        BinaryTree<Integer> nodo8 = new BinaryTree<>(8);
        BinaryTree<Integer> nodo4 = new BinaryTree<>(4);
        BinaryTree<Integer> nodo6 = new BinaryTree<>(6);

        // Nodo 5 con hijos 7 y 8
        BinaryTree<Integer> nodo5 = new BinaryTree<>(5);
        nodo5.addLeftChild(nodo7);
        nodo5.addRightChild(nodo8);

        // Nodo 2 con hijo izquierdo 4
        BinaryTree<Integer> nodo2 = new BinaryTree<>(2);
        nodo2.addLeftChild(nodo4);

        // Nodo 3 con hijos 5 y 6
        BinaryTree<Integer> nodo3 = new BinaryTree<>(3);
        nodo3.addLeftChild(nodo5);
        nodo3.addRightChild(nodo6);

        // Raíz con hijos 2 y 3
        BinaryTree<Integer> raiz = new BinaryTree<>(1);
        raiz.addLeftChild(nodo2);
        raiz.addRightChild(nodo3);
        
        BinaryTree<Integer> raiz2 = raiz;

        // Acá podés pasar la raíz a la clase Transformacion
        /*TransformacionTebi t = new TransformacionTebi();
        t.setArbol(raiz);
        BinaryTree<Integer> nuevoArbolT = t.suma();
        
        System.out.println("Inorden:");
        TransformacionTebi.imprimirInorden(nuevoArbolT);
        System.out.println();*/ 

        
        TransformacionFran f = new TransformacionFran();
        f.setArbol(raiz);
        BinaryTree<Integer> nuevoArbolF = f.suma();
        
        System.out.println("Inorden:");
        TransformacionTebi.imprimirInorden(nuevoArbolF);
        System.out.println(); 

        // Si querés imprimirlo, agregá un método de recorrido (inorden, por ejemplo)
    }
}