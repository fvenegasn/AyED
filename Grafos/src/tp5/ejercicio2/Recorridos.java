package tp5.ejercicio2;

import java.util.ArrayList;
import java.util.List;

import tp5.ejercicio1.Queue;
import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;

/*Ejercicio 2
a. Implemente en JAVA una clase llamada Recorridos ubicada dentro del paquete ejercicio2 cumpliendo
la siguiente especificación:
b. Estimar el orden de ejecución de los métodos anteriores.
	dfs = 
	bfs = 
*/

public class Recorridos<T> {

	
	// Retorna una lista con los datos de los vértices, con el recorrido en profundidad del grafo recibido como parámetro.
	public List<Integer> dfs(Graph<Integer> grafo){
		// hago un arreglo de booleanos para marcar los grafos visitados
		boolean[] vertices = new boolean[grafo.getSize()];
		// armo una lista en la que voy a retornar los datos de los vértices
		List<Integer> data = new ArrayList<Integer>();
		// para la totalidad de vertices que tengo
		for (int i=0; i<grafo.getSize();i++){
			// hago recursividad con dfs privado SI no visite ese vertice aun
			if (vertices[i] != true) {
				dfs_privado(i, grafo, vertices, data);
			}
		}
		// retorno la lista
		return data;
	}
	
	private void dfs_privado(int i, Graph<Integer> grafo, boolean[] vertices, List<Integer> data) {
		// marca que el vertice fue visitado
		vertices[i] = true;
		// me quedo con el vertice en el que estoy parado (actual)
		Vertex<Integer> vertice = grafo.getVertex(i);
		System.out.println(vertice);
		// agrego el vertice a mi listado de datos
		data.add(vertice.getData());
		// obtiene el listado de vertices adyacentes
		List<Edge<Integer>> adyacentes = grafo.getEdges(vertice);
		// recorre los adyacentes
		for (Edge<Integer> e: adyacentes) {
			// obtiene el proximo vertice
			int proximoVertice = e.getTarget().getPosition();
			// si no fue visitado el proximo vertice
			if(!vertices[proximoVertice]) {
				// lo visita
				dfs_privado(proximoVertice, grafo, vertices, data);
			}
		}
	}
	
	// Retorna una lista con los datos de vértices, con el recorrido en amplitud del grafo recibido como parámetro.
	public List<Integer> bfs(Graph<Integer> grafo){
		// hago un arreglo de booleanos para marcar los grafos visitados
		boolean[] vertices = new boolean[grafo.getSize()];
		// armo una lista en la que voy a retornar los datos de los vértices
		List<Integer> data = new ArrayList<Integer>();
		// para la totalidad de vertices que tengo
		for (int i=0; i<grafo.getSize();i++){
			// hago recursividad con bfs privado SI no visite ese vertice aun
			if (!vertices[i]) {
				bfs_privado(i, grafo, vertices, data);
			}
		}
		// retorno la lista
		return data;
	}
	
	public void bfs_privado(int i, Graph<Integer> grafo, boolean[] vertices, List<Integer> data) {
		// crea una cola para los vertices
		Queue<Vertex<Integer>> cola = new Queue<Vertex<Integer>>();
		// encola el vertice con el que entre al metodo
		cola.enqueue(grafo.getVertex(i));
		// marca que el vertice fue visitado		
		vertices[i]=true;
		
		// mientras la cola no es vacía
		while(!cola.isEmpty()) {
			// saca de la cola el vertice
			Vertex<Integer> verticeActual = cola.dequeue();
			System.out.println(verticeActual);
			data.add(verticeActual.getData());
			// para todos los vecinos del vertice actual (todas las adyacencias)
			List<Edge<Integer>> adyacentes = grafo.getEdges(verticeActual);
			for (Edge<Integer> e: adyacentes) {
				// obtengo el proximo vertice
				int proximoVertice = e.getTarget().getPosition();
				// si no lo visite, lo visito y encolo el proximo
				if (!vertices[proximoVertice]) {
					vertices[proximoVertice] = true;
					cola.enqueue(e.getTarget());
				}
			}
		}
	}

}
