package parcial20250705;

import java.util.ArrayList;
import java.util.List;

import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;

public class ParcialGrafos {

	public List<Camino> todosLosCaminosConPeligro(Graph<String> mapaCiudad, String refugio, String zonaSegura){
		// inicializo la lista de caminos a devolver
		List<Camino> caminosValidos = new ArrayList<Camino>();
		// si el grafo no es vacio y no es nulo
		if (mapaCiudad != null && !mapaCiudad.isEmpty()) {
			// busco los puntos de partida y llegada
			Vertex<String> inicio = mapaCiudad.search(refugio);
			Vertex<String> fin = mapaCiudad.search(zonaSegura);
			// si esos puntos existen en el grafo
			if (inicio != null && fin != null) {
				// inicializo un primer camino
				Camino caminoActual = new Camino(new ArrayList<String>(), 0);
				// inicializo nodos visitados
				boolean[] visitados = new boolean[mapaCiudad.getSize()];
				// declaro peligro que me cuesta arrancar
				int peligro = 0;
				// recorro buscando caminos
				caminosPrivado(mapaCiudad, inicio, fin, visitados, caminosValidos, caminoActual, peligro);
			}
		}
		return caminosValidos;
	}
	
	private void caminosPrivado(Graph<String> mapaCiudad, Vertex<String> inicio, Vertex<String> fin, boolean[] visitados, List<Camino> caminosValidos, Camino camino, int peligro) {
		// marco el nodo actual como visitado
		visitados[inicio.getPosition()] = true;
		// lo agrego al camino junto a su peligrosidad
		List<String> caminoRecorrido = camino.getCamino();
		caminoRecorrido.add(inicio.getData());
		camino.setCamino(caminoRecorrido);
		int peligroAcumulado = camino.getPeligro();
		camino.setPeligro(peligroAcumulado + peligro);
		// si llegue
		if (inicio.getData().equals(fin.getData())) {
			// agrego el camino actual a los caminos validos
			caminosValidos.add(camino);
		} else { // si no llegue
			// busco las adyacencias
			List<Edge<String>> edges = mapaCiudad.getEdges(inicio);
			// recorro las adyacencias
			for (Edge<String> e: edges) {
				// obtengo el proximo nodo
				Vertex<String> proximoVertice = e.getTarget();
				// si no visité el proximo nodo
				if (!visitados[proximoVertice.getPosition()]) {
					// obtengo el peligro que me sale ir al proximo nodo
					int peligroProximo = e.getWeight();
					// llamo recursivamente
					caminosPrivado(mapaCiudad, proximoVertice, fin, visitados, caminosValidos, camino, peligroProximo);
				}
			}
		}
		// cuando finalice el recorrido de ese nodo, hago backtracking
		visitados[inicio.getPosition()] = false;
		caminoRecorrido.remove(caminoRecorrido.size()-1);
		camino.setCamino(caminoRecorrido);
		camino.setPeligro(peligroAcumulado - peligro);
	}
}
