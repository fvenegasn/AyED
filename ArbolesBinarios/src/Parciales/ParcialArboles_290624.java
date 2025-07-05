package Parciales;

import tp2.ejercicio1.BinaryTree;

/* Defina una clase ParcialArboles con una unica variable de instancia de tipo BinaryTree<Integer>
 * y un metodo publico con la firma public BinaryTree<Integer> nuevoTree
 * El metodo debe devolver un nuevo arbol, construido de la siguiente forma
 * 	- Si el arbol dado tiene hijo izquierdo, el nuevo arbol tendra hijo izquierdo cuyo valor
 * 	  sera la suma del valor del hijo izquierdo y el valor del padre del arbol dado
 *  - Si el arbol dado no tiene hijo izquierdo, tampoco lo tendra el nuevo
 *  - Los hijos derechos del nuevo arbol son iguales que los del arbol dado
 *  - Las hojas del arbol dado seran hojas en el nuevo
*/

public class ParcialArboles_290624 {
	
	// una unica variable de instancia de tipo BinaryTree<Integer>
	private BinaryTree<Integer> ab;
	
	public ParcialArboles_290624(BinaryTree<Integer> ab) {
		this.ab = ab;
	}

	// un metodo publico con la siguiente firma
	public BinaryTree<Integer> nuevoTree(){
		// defino el arbol a retornar
		BinaryTree<Integer> sumaIzquierdos = new BinaryTree<Integer>();
		// si el arbol no es null y no esta vacio, arranco a procesar
		if ((ab != null) && (!ab.isEmpty())){
			// agrega la raiz
			sumaIzquierdos.setData(ab.getData());
			// llama con la raiz al metodo privado
			nuevoTreePrivado(ab, sumaIzquierdos);
		}
		// retorna el nuevo arbol
		return sumaIzquierdos;
	}
	
	private void nuevoTreePrivado(BinaryTree<Integer> arbol, BinaryTree<Integer> sumaIzquierdos) {
		// si tiene hijo izquierdo
		if (arbol.hasLeftChild()) {
			// agrega con el valor del hijo izquierdo + la raiz
			sumaIzquierdos.addLeftChild(new BinaryTree<Integer>(arbol.getData() + arbol.getLeftChild().getData()));
			// llama a procesar con el hijo izquierdo
			nuevoTreePrivado(arbol.getLeftChild(), sumaIzquierdos.getLeftChild());
		}
		// si tiene hijo derecho
		if (arbol.hasRightChild()) {
			// agrega con el mismo dato en el nuevo, sin sumas
			sumaIzquierdos.addRightChild(new BinaryTree<Integer>(arbol.getRightChild().getData()));
			// llama a procesar con el hijo derecho
			nuevoTreePrivado(arbol.getRightChild(), sumaIzquierdos.getRightChild());
		}
		
	}
}

