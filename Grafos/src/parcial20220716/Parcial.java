package parcial20220716;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;

public class Parcial {

	public List<String> recorrido(Graph<String> grafo, int cantLocalidades, int cantNafta, List<String> localidadesExceptuadas){
		// defino la lista a retornar
		List<String> lista = new ArrayList<String>();
		// si el grafo no es nulo ni vacio
		if (grafo != null && !grafo.isEmpty()) {
			// busco la localidad de inicio
			Vertex<String> inicio = grafo.search("Mendoza");
			// si no es nulo
			if (inicio != null) {
				// defino listado de visitados
				boolean[] visitados = new boolean[grafo.getSize()];
				// marco como visitados los lugares exceptuados		
				marcarExceptuados(grafo, localidadesExceptuadas, visitados);
				// defino cantidad de localidades visitadas y nafta consumida
				int cantLocalidadesVisitadas = 0;
				int cantNaftaConsumida = 0;
				// llamo al metodo recursivo
				recorridoPrivado(grafo, inicio, cantLocalidadesVisitadas, cantNafta, cantNaftaConsumida, visitados, lista);
			}
		}
		
		// retorno la lista
		return lista;
	}
	
	private int recorridoPrivado(Graph<String> grafo, Vertex<String> actual, int cantLocalidadesVisitadas,
			int cantNafta, int cantNaftaConsumida, boolean[] visitados, List<String> camino) {
		// marco como visitado el grafo
		visitados[actual.getPosition()] = true;
		// lo agrego al camino recorrido 
		camino.add(actual.getData());
		// sumo localidades
		cantLocalidadesVisitadas++;
		// obtengo los adyacentes
		List<Edge<String>> adyacentes = grafo.getEdges(actual);
		// hago un iterator
		Iterator<Edge<String>> it = adyacentes.iterator();
		// mientras tenga adyacentes para recorrer, no haya alcanzado max nafta
		while (it.hasNext() && cantNaftaConsumida < cantNafta) {
			// obtengo el proximo nodo
			Edge<String> edge = it.next();
			Vertex<String> proximoVertice = edge.getTarget();
			// si no lo visite
			if (!visitados[proximoVertice.getPosition()]) {
				// obtengo la nafta que me cuesta ir
				int costo = edge.getWeight();
				// si el me alcanza la nafta
				if (costo + cantNaftaConsumida < cantNafta) {
					// llama recursivo
					cantLocalidadesVisitadas = recorridoPrivado(grafo, proximoVertice, cantLocalidadesVisitadas, cantNafta, costo+cantNaftaConsumida, visitados, camino);
				}
				
			}
		}
		// marco como visitado el grafo
		visitados[actual.getPosition()] = false;
		return cantLocalidadesVisitadas;
	}
	
	private void marcarExceptuados(Graph<String> grafo, List<String> localidadesExceptuadas, boolean[] visitados) {
		for (String localidad: localidadesExceptuadas) {
			Vertex<String> vertice = grafo.search(localidad);
			visitados[vertice.getPosition()] = true;
		}
	}
}
