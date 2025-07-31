package tp5.ejercicio6;

import java.util.ArrayList;
import java.util.List;

import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;

public class BuscadorDeCaminos {

	/*Su tarea es definir una clase llamada BuscadorDeCaminos, con una variable de instancia llamada "bosque" de
		tipo Graph, que representa el bosque descrito*/
	private Graph<String> bosque;
	
	// constructor para probar main
	public BuscadorDeCaminos(Graph<String> bosque) {
		this.bosque = bosque;
	}

	/*e implementar un método de instancia con la siguiente firma que devuelva un listado con TODOS 
	 * los caminos que cumplen con las condiciones mencionadas anteriormente.
	 * Nota: La casa de Caperucita debe ser buscada antes de comenzar a buscar el recorrido.*/
	public List<List<String>> recorridosMasSeguro(){
		// creo la lista de listas de caminos a retornar
		List<List<String>> caminos = new ArrayList<List<String>>();
		// valido que el grafo no sea nulo o vacío
		if ((bosque != null) && (!bosque.isEmpty())) {
			// busco la casa de Caperucita
			Vertex<String> casa = bosque.search("Casa Caperucita");
			// busco la casa de la abuela
			Vertex<String> abuela = bosque.search("Casa Abuelita");
			// si la casa de Caperucita y la casa de la abuelita existen (si bien el enunciado no 
			// pide que busque la de la abuelita la busque, si no existe no tiene sentido ir a buscar
			// caminos seguros porque nunca va a llegar
			if ((casa != null) && (abuela != null)){
				// inicio la lista de lugares visitados
				boolean[] visitados = new boolean[bosque.getSize()];
				// inicio un camino actual
				List<String> caminoActual = new ArrayList<String>();
				// llamo recursivamente buscando recorridos
				recorridosMasSeguroPrivado(casa, abuela, caminos, caminoActual, visitados);
			}
		}
		// retorno los caminos (llenos o vacíos)
		return caminos;
	}
	
	private void recorridosMasSeguroPrivado(Vertex<String> casa, Vertex<String> abuela, List<List<String>> caminos, List<String> caminoActual, boolean[] visitados) {
		// marco como visitado el nodo actual
		visitados[casa.getPosition()] = true;
		// agrego el nodo actual a los visitados
		caminoActual.add(casa.getData());
		// si llegue
		if (casa.getData().equals(abuela.getData())) {
			// agrego el camino actual a mi lista de caminos posibles
			caminos.add(new ArrayList<String>(caminoActual));
		} else { // si no llegue
			// busco los nodos adyacentes
			List<Edge<String>> adyacentes = bosque.getEdges(casa);
			// para cada adyacente
			for (Edge<String> edge: adyacentes) {
				// obtengo el próximo nodo a visitar
				Vertex<String> proximoVertice = edge.getTarget();
				// si no lo visite
				if (!visitados[proximoVertice.getPosition()]) {
					// obtengo la cantidad de frutales para ese camino
					int frutales = edge.getWeight();
					// si la cantidad de frutales es menor que 5 (CAMINO ANTI GOLOSO)
					if (frutales < 5) {
						// llamo recursivamente a ese camino
						recorridosMasSeguroPrivado(proximoVertice, abuela, caminos, caminoActual, visitados);
					}
				}
			}
		}
		// en todos los casos saco al lugar del camino y lo desmarco de los visitados
		caminoActual.remove(caminoActual.size()-1);
		visitados[casa.getPosition()] = false;
	}
}
