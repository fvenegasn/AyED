package tp2.ejercicio6;

import tp2.ejercicio1.BinaryTree;

/*Cree una clase Java llamada Transformacion que tenga como variable de 
instancia un árbol binario de números enteros y un método de instancia 
suma (): BinaryTree<Integer> el cuál devuelve el árbol en el que se 
reemplazó el valor de cada nodo por la suma de todos los elementos presentes
en su subárbol izquierdo y derecho. Asuma que los valores de los subárboles
vacíos son ceros.*/

/*¿Su solución recorre una única vez cada subárbol? 
 * En el caso que no, ¿Puede mejorarla para que
sí lo haga?*/

public class TransformacionTebi {
	
	private BinaryTree<Integer> arbol;
	
	public BinaryTree<Integer> suma(){
		if (!arbol.isEmpty()) {
			//llamar a metodo privado hace la recursion
			sumaRecursion(arbol);
			return arbol;
		} else {
			return null;  
		}
	}
	
	private int sumaRecursion(BinaryTree<Integer> ab){
		// caso base -> es hoja
		if (ab.isLeaf()) {
			int nroARetornar = ab.getData();
			ab.setData(0);
			return nroARetornar;
		}
		// no es hoja -> recorro
		int sumaHI = 0;
		int sumaHD = 0;
		// si tiene hijo izq
		if (ab.hasLeftChild()) {
			// llamo a la recursion con el izq
			sumaHI += sumaRecursion(ab.getLeftChild()); // nodo 1, vale 6
		}
		// si tiene hijo der
		if (ab.hasRightChild()) {
			sumaHD += sumaRecursion(ab.getRightChild()); // nodo 1, vale 29
		}
		// raiz -> retorno mi dato mas lo que sumé en mis hijos
		int valorARetornar = ab.getData();
		ab.setData(sumaHI+sumaHD); // 29 + 6
		return valorARetornar + sumaHI + sumaHD; // 35 + 1
	}

	public BinaryTree<Integer> getArbol() {
		return arbol;
	}

	public void setArbol(BinaryTree<Integer> arbol) {
		this.arbol = arbol;
	}
	
	public static void imprimirInorden(BinaryTree<Integer> nodo) {
	    if (nodo == null) return;

	    imprimirInorden(nodo.getLeftChild());
	    System.out.print(nodo.getData() + " ");
	    imprimirInorden(nodo.getRightChild());
	}
	
}
