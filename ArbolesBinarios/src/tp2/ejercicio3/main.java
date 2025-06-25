package tp2.ejercicio3;

import tp2.ejercicio1.BinaryTree;

public class main {
    public static void main(String[] args) {
        // Creamos los nodos
        BinaryTree<Integer> a = new BinaryTree<>(10);
        BinaryTree<Integer> b = new BinaryTree<>(2);
        BinaryTree<Integer> c = new BinaryTree<>(3);
        BinaryTree<Integer> d = new BinaryTree<>(4);
        BinaryTree<Integer> e = new BinaryTree<>(5);
        BinaryTree<Integer> f = new BinaryTree<>(6);

        // Armamos el árbol
        a.addLeftChild(b);   // 1 -> 2 (izq)
        a.addRightChild(c);  // 1 -> 3 (der)
        b.addLeftChild(d);   // 2 -> 4 (izq)
        b.addRightChild(e);  // 2 -> 5 (der)
        c.addRightChild(f);  // 3 -> 6 (der)
        
/*      10
      /   \
     2     3
    / \     \
   4   5     6
*/


        
        ContadorArbol arbol = new ContadorArbol();
        arbol.setArbol(a);
        System.out.println(arbol.numerosParesInOrden()); // 4, 2, 10, 6
        System.out.println(arbol.numerosParesPostOrden()); // 4, 2, 6, 10
        
    }
}
