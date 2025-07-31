package parcial20250614;

import java.util.ArrayList;
import java.util.List;

import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;

// Condiciones ruta:
// 1. Suma total de pociones no debe superar a maxPociones
// 2. Se debe visitar la mayor cantidad posible de lugares distintos (camino maximo)
// 3. Si existen varias rutas que cumplen las condiciones con igual cantidad de lugares 
// * visitados, se puede devolver cualquiera
// 4. Si no existe ninguna ruta valida, devuelvo lista vacia

public class ParcialGrafos {

	public List<String> rutaOptimaDistribucion(Graph<String> reino, String castillo, String aldea, int maxPociones){
		// declaro lista a retornar
		List<String> camino = new ArrayList<String>();
		// el grafo no es null y no esta vacio?
		if (reino != null && !reino.isEmpty()) {
			// busco castillo y aldea
			Vertex<String> inicio = reino.search(castillo);
			Vertex<String> fin = reino.search(aldea);
			// si existen en mi grafo
			if (inicio != null && fin != null) {
				// declaro vector de visitados
				boolean[] visitados = new boolean[reino.getSize()];
				// declaro un camino actual
				List<String> caminoActual = new ArrayList<String>();
				// llamo recursivamente al privado
				rutaPrivada(reino, inicio, fin, visitados, maxPociones, 0, camino, caminoActual);
			}
		}
		return camino;
	}
	
	private void rutaPrivada(Graph<String> reino, Vertex<String> actual, Vertex<String> fin, boolean[] visitados, int maxPociones, int pocionesActuales, List<String> camino, List<String> caminoActual) {
		// marco como visitado el vertice
		visitados[actual.getPosition()]=true;
		// agrego el vertice actual al camino actual
		caminoActual.add(actual.getData());
		// si llegue y es el camino mas largo
		if (actual.getData().equals(fin.getData()) && caminoActual.size() > camino.size()) {
			// limpio el camino que ERA el maximo
			camino.clear();
			// agrego el camino actual como nuevo maximo
			camino.addAll(caminoActual);
		} else {
			// obtengo las adyacencias
			List<Edge<String>> edges = reino.getEdges(actual);
			// recorro los adyacentes
			for (Edge<String> e: edges) {
				// obtener el proximo vertice
				Vertex<String> proximoVertice = e.getTarget();
				// obtener el costo del proximo vertice
				int costo = e.getWeight(); 
				// si el costo del proximo + mi acumulado es menor al maximo Y no visite el proximo vertice
				if (costo + pocionesActuales < maxPociones && !visitados[proximoVertice.getPosition()]) {
					// llama recursivo
					rutaPrivada(reino, proximoVertice, fin, visitados, maxPociones, costo+pocionesActuales, camino, caminoActual);
				}
			}
		}
		// hago backtracking
		caminoActual.remove(caminoActual.size()-1);
		visitados[actual.getPosition()]=false;
	}
}
