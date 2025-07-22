package Parciales;

import java.util.ArrayList;
import java.util.List;

import tp2.ejercicio1.BinaryTree;

/* Implemente en la clase Parcial el metodo resolver
 * que recibe un arbol binario de enteros positivos
 * y un numero entero y devuelve un camino que cumple
 * con la siguiente condicion: la cantidad de numeros
 * pares que contenga dicho camino debe ser mayor o
 * igual al parametro "min". Si existen varios caminos
 * que cumplen la condicion, el metodo debe devolver
 * el primer camino que encuentre
*/

public class Parcial {
	
	public List<Integer> resolver(BinaryTree<Integer> ab, int min){
		// creo la lista del camino a retornar
		List<Integer> camino = new ArrayList<Integer>();
		// si el arbol no es null ni esta vacio
		if ((ab != null) && (!ab.isEmpty())){
			// declaro la cantidad de pares que encontre (0)
			int pares =0;
			// declara un camino actual
			List<Integer> caminoAct = new ArrayList<Integer>();
			// llamo a metodo recursivo
			caminoDePares(ab, min, camino, caminoAct, pares, false);
		}
		// retorna el camino
		return camino;
	}
	
	private void caminoDePares(BinaryTree<Integer> ab, int min, List<Integer> camino, List<Integer> caminoAct, int pares, boolean caminoValido) {
		// agrega el nodo actual
		caminoAct.add(ab.getData()); // 7-56-38-87
		// se fija si es par
		if (ab.getData() % 2 == 0) {
			pares++; // 2
		}
		// si es una hoja
		if (ab.isLeaf() && !caminoValido) {
			if (pares == min) {
				caminoValido = true;
				camino.clear();
				camino.addAll(caminoAct);
			}
		} else { // no es una hoja
			if (!caminoValido) {
				// si tiene hijo izquierdo
				if (ab.hasLeftChild()) {
					// llama con el izquierdo
					caminoDePares(ab.getLeftChild(), min, camino, caminoAct, pares, caminoValido);
				}
				// si tiene hijo derecho
				if (ab.hasRightChild()) {
					caminoDePares(ab.getRightChild(), min, camino, caminoAct, pares, caminoValido);
				}
				// backtracking
				caminoAct.remove(caminoAct.size()-1);
			}
			
		}
	}
}
