package tp2.ejercicio8;

import tp2.ejercicio1.BinaryTree;

public class ParcialArboles {
	
	public boolean esPrefijo(BinaryTree<Integer> arbol1, BinaryTree<Integer> arbol2) {
		
		if((arbol1 != null && !arbol1.isEmpty()) && (arbol2 != null && !arbol2.isEmpty())){
			return esPrefijoPrivada(arbol1,arbol2);
		}
		else {
			return false;
		}
	}
	
	private boolean esPrefijoPrivada(BinaryTree<Integer>ab1, BinaryTree<Integer>ab2) {
		if(ab1.getData().equals(ab2.getData())) {
			boolean boolIzq = true;
			boolean boolDer = true;
			
			if(ab1.hasLeftChild() ) {
				if (ab2.hasLeftChild()) {
					boolIzq = esPrefijoPrivada(ab1.getLeftChild(),ab2.getLeftChild());
				}
				else {
					return false;
				}
			}
			if(ab1.hasRightChild() ) {
				if (ab2.hasRightChild()) {
					boolDer = esPrefijoPrivada(ab1.getRightChild(),ab2.getRightChild());
				}
				else {
					return false;
				}
			}
			return boolIzq && boolDer;
		}
		else {
			return false;
		}
	}
}
