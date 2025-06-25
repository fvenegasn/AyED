package tp2.ejercicio5;

import tp2.ejercicio1.BinaryTree;
import tp2.ejercicio1.Queue;

//Implemente una clase Java llamada ProfundidadDeArbolBinario que tiene como variable de
//instancia un árbol binario de números enteros y un método de instancia
//sumaElementosProfundidad (int p):int el cuál devuelve la suma de todos los nodos del árbol
//que se encuentren a la profundidad pasada como argumento.

public class ProfundidadDeArbolBinario {
  private BinaryTree<Integer> ab;

  public int SumaElementosProfundidad(int p) {
	  int nivel = 0;
      int suma = 0;

      Queue <BinaryTree<Integer>> cola = new Queue<BinaryTree<Integer>>();
      BinaryTree<Integer> nodo;

      cola.enqueue(ab);
      cola.enqueue(null);

      while(!cola.isEmpty() && nivel <= p) { // nivel <= p para que no entre a niveles despues del deseado
          nodo = cola.dequeue();
          if(ab != null) {
              if(nivel == p) {
                  suma+= nodo.getData();
              }
              if(ab.hasLeftChild()) {
                  cola.enqueue(ab.getLeftChild());
              }
              if(ab.hasRightChild()) {
                  cola.enqueue(ab.getRightChild());
              }
          }else if(!cola.isEmpty()) {
              cola.enqueue(null);
              nivel++;
          }
      }
      return suma;
  }
}
