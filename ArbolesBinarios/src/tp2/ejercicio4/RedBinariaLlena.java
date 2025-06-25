package tp2.ejercicio4;
import tp2.ejercicio1.BinaryTree;


/*Una red binaria es una red que posee una topología de árbol binario lleno
Los nodos que conforman una red binaria llena tiene la particularidad de que todos ellos conocen
cuál es su retardo de reenvío. El retardo de reenvío se define como el período comprendido entre
que un nodo recibe un mensaje y lo reenvía a sus dos hijos.
Su tarea es calcular el mayor retardo posible, en el camino que realiza un mensaje desde la raíz
hasta llegar a las hojas en una red binaria llena. En el ejemplo, debería retornar 10+3+9+12=34
(Si hay más de un máximo retorne el último valor hallado).
Nota: asuma que cada nodo tiene el dato de retardo de reenvío expresado en cantidad de
segundos.
a) Indique qué estrategia (recorrido en profundidad o por niveles) utilizará para resolver el
problema: recorrido en profundidad

b) Cree una clase Java llamada RedBinariaLlena donde implementará lo solicitado en el
método retardoReenvio():int
 */

public class RedBinariaLlena {
	
	private BinaryTree<Integer> arbol; // consultar si tiene la variable de instancia o si el metodo recibe al
									   // arbol como parametro
	
	public int retardoReenvio() {
		if (this.arbol != null && !this.arbol.isEmpty()) { // si no lo pongo me desaprueban (el not null, y la posición de las condiciones en el and)?
			return retardoReenvio_private(arbol);
		} else {
			return 0;
		}		
	}
	
	private int retardoReenvio_private(BinaryTree<Integer> arbol) {
		// caso base -> llegue a la hoja
		if (arbol.isLeaf()) {
			return arbol.getData();
		} else {
			int retardoIzq = 0;
			int retardoDer = 0;
			// si tiene hijo izq
			if (arbol.hasLeftChild()) {
				// entro a procesar el hijo izquierdo
				retardoIzq += retardoReenvio_private(arbol.getLeftChild());
			}
			// si tiene hijo der
			if (arbol.hasRightChild()) {
				// entro a procesar el hijo derecho
				retardoDer += retardoReenvio_private(arbol.getRightChild());
			}
			// sumar la raiz-nodo
			return Math.max(retardoIzq, retardoDer) + arbol.getData();
			
		}
	}

	public BinaryTree<Integer> getArbol() {
		return arbol;
	}

	public void setArbol(BinaryTree<Integer> arbol) {
		this.arbol = arbol;
	}
	
}
