package tp2.ejercicio1;

import tp2.ejercicio1.Queue;

public class BinaryTree <T> {
	
	private T data;
	private BinaryTree<T> leftChild;   
	private BinaryTree<T> rightChild; 

	
	public BinaryTree() {
		super();
	}

	public BinaryTree(T data) {
		this.data = data;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	/**
	 * Preguntar antes de invocar si hasLeftChild()
	 * @return
	 */
	public BinaryTree<T> getLeftChild() {
		return leftChild;
	}
	/**
	 * Preguntar antes de invocar si hasRightChild()
	 * @return
	 */
	public BinaryTree<T> getRightChild() {
		return this.rightChild;
	}

	public void addLeftChild(BinaryTree<T> child) {
		this.leftChild = child;
	}

	public void addRightChild(BinaryTree<T> child) {
		this.rightChild = child;
	}

	public void removeLeftChild() {
		this.leftChild = null;
	}

	public void removeRightChild() {
		this.rightChild = null;
	}

	public boolean isEmpty(){
		return (this.isLeaf() && this.getData() == null);
	}

	public boolean isLeaf() {
		return (!this.hasLeftChild() && !this.hasRightChild());

	}
		
	public boolean hasLeftChild() {
		return this.leftChild!=null;
	}

	public boolean hasRightChild() {
		return this.rightChild!=null;
	}
	@Override
	public String toString() {
		return this.getData().toString();
	}

	// Devuelve la cantidad de árbol/subárbol hojas del árbol receptor
	public  int contarHojas() {
	       int cant=0;
	        if((!this.isEmpty())){
	           if(this.isLeaf()) {
	               cant++;
	           }
	           else {
	               if(this.hasLeftChild()) {
	                   cant+=this.getLeftChild().contarHojas();
	               }
	               if(this.hasRightChild()) {
	                   cant+=this.getRightChild().contarHojas();
	               }
	           }
	       }
	        return cant;
	    }

	    public BinaryTree<T> espejo(){
	        if((!this.isEmpty())){
	            BinaryTree<T> copia = new BinaryTree<T> (this.getData());
	            if(this.hasLeftChild()) {
	                copia.addRightChild(this.getLeftChild().espejo());
	            }
	            if(this.hasRightChild()) {
	                copia.addLeftChild(this.getRightChild().espejo());
	            }
	        return copia;
	        }
	    return null;
	    }

	    // 0<=n<=m
	    public void entreNiveles(int n, int m) {
	    	BinaryTree<T> nodo = null;
	    	
	    	// crea cola de BinaryTree
	    	Queue<BinaryTree<T>> cola = new Queue<BinaryTree<T>>();
	    	
	    	// encola this
	    	cola.enqueue(this);
	    	// encola null
	    	cola.enqueue(null);

	    	// inicializa nivel
	    	int nivel = 0;
	    	// mientras la cola no sea vacia y este en mis niveles
	    	while (!cola.isEmpty() && ((n <= nivel) && (nivel <= m))) {
	    		//desencolo data en nodo nuevo binarytree
	    		nodo = cola.dequeue();
	    		//si (nodo <> null)
	    		if (nodo != null) {
	    			// imprimo
	    			System.out.println(nodo.getData());
	    			//si (nodo tiene hijo izq)
	    			if(nodo.hasLeftChild()) {
	    				//encolo hijo izq
	    				cola.enqueue(nodo.getLeftChild());
	    			}
	    			//si (nodo tiene hijo der)
	    			if(nodo.hasRightChild()) {
	    				//encolo hijo der
	    				cola.enqueue(nodo.getRightChild());
	    			}
	    		} else {
	    			//sino (cola <> vacia)
	    			if (!cola.isEmpty()) {
	    				//encolo null -> (subo de nivel)
	    				cola.enqueue(null);
	    				// incremento nivel
	    				nivel++;
	    			}
	    		}
	    	}
	    }
}

