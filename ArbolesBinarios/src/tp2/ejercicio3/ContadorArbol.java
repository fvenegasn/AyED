package tp2.ejercicio3;

import java.util.ArrayList;

import tp2.ejercicio1.BinaryTree;

/*Defina una clase Java denominada ContadorArbol cuya función principal es proveer métodos de
validación sobre árboles binarios de enteros. Para ello la clase tiene como variable de instancia un
BinaryTree<Integer>. Implemente en dicha clase un método denominado numerosPares() que
devuelve en una estructura adecuada (sin ningún criterio de orden) todos los elementos pares del
árbol (divisibles por 2).
*/

public class ContadorArbol {

	private BinaryTree<Integer> arbol;
	
	// a) Implemente el método realizando un recorrido InOrden.
	public ArrayList<Integer> numerosParesInOrden(){
		// defino lista a retornar
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		this.inorden_private(arbol, resultado);
		return resultado;
	}
	
	private void inorden_private(BinaryTree<Integer> ab, ArrayList<Integer> resultado) {
		// si tiene hijo izq
		if (ab.hasLeftChild()) {
			// hijo izq.inorden()
			inorden_private(ab.getLeftChild(), resultado);
		}
		// raiz - nodo
		if ((ab.getData() % 2) == 0) {
			resultado.add(ab.getData());
		}
		// si tiene hijo der
		if (ab.hasRightChild()) {
			// hijo der.inorden()
			inorden_private(ab.getRightChild(), resultado);
		}
	}
	
	// b) Implemente el método realizando un recorrido PostOrden.
	public ArrayList<Integer> numerosParesPostOrden(){
		// defino lista a retornar
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		this.postorden_private(arbol, resultado);
		return resultado;
	}
	
	private void postorden_private(BinaryTree<Integer> ab, ArrayList<Integer> resultado) {
		// si tiene hijo izq
		if (ab.hasLeftChild()) {
			// hijo izq.inorden()
			postorden_private(ab.getLeftChild(), resultado);
		}
		// si tiene hijo der
		if (ab.hasRightChild()) {
			// hijo der.inorden()
			postorden_private(ab.getRightChild(), resultado);
		}
		// raiz - nodo
		if ((ab.getData() % 2) == 0) {
			resultado.add(ab.getData());
		}
	}

	public BinaryTree<Integer> getArbol() {
		return arbol;
	}

	public void setArbol(BinaryTree<Integer> arbol) {
		this.arbol = arbol;
	}
}
