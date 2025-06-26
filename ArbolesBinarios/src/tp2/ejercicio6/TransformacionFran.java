package tp2.ejercicio6;

import tp2.ejercicio1.BinaryTree;

public class TransformacionFran {
	
	private BinaryTree<Integer> arbol;
	
	public BinaryTree<Integer> suma(){
		if (!arbol.isEmpty()) {
			//llamar a metodo privado hace la recursion
			TransformacionAuxiliar arbolTransformado = new TransformacionAuxiliar();
			arbolTransformado = sumaRecursion(arbol);
			arbol = arbolTransformado.getArbol();
			return arbol;
		} else {
			return null;  
		}
	}
	
	private TransformacionAuxiliar sumaRecursion(BinaryTree<Integer> ab){
		TransformacionAuxiliar abTransformado = new TransformacionAuxiliar();
		// caso base -> es hoja
		if (ab.isLeaf()) {
			//int nroARetornar = ab.getData();
			abTransformado.setArbol(new BinaryTree<Integer>(0));
			//arbol.setData(sumaAcumulado);
			abTransformado.setSuma(ab.getData());
			return abTransformado;
		} else {
			// no es hoja -> recorro
			//int sumaHI = 0;
			TransformacionAuxiliar retornoHI = new TransformacionAuxiliar(); 
			//int sumaHD = 0;
			TransformacionAuxiliar retornoHD = new TransformacionAuxiliar(); 
			// si tiene hijo izq
			if (ab.hasLeftChild()) {
				// llamo a la recursion con el izq
				retornoHI = sumaRecursion(ab.getLeftChild()); // nodo 1, vale 6
			}
			// si tiene hijo der
			if (ab.hasRightChild()) {
				retornoHD = sumaRecursion(ab.getRightChild()); // nodo 1, vale 29
			}
			// raiz -> retorno mi dato mas lo que sumé en mis hijos
			//arbol.setData(sumaHI+sumaHD); // 29 + 6
			BinaryTree<Integer> reemplazo = new BinaryTree<Integer>(retornoHI.getSuma() + retornoHD.getSuma());
			reemplazo.addLeftChild(retornoHI.getArbol());
			reemplazo.addRightChild(retornoHD.getArbol());
			abTransformado.setArbol(reemplazo);
			abTransformado.setSuma(retornoHI.getSuma() + retornoHD.getSuma() + ab.getData());
			return abTransformado; // 35 + 1
		}
	}

	public BinaryTree<Integer> getArbol() {
		return arbol;
	}

	public void setArbol(BinaryTree<Integer> arbol) {
		this.arbol = arbol;
	}
	
	
}
