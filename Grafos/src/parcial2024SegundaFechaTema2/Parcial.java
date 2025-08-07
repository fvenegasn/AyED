package parcial2024SegundaFechaTema2;

import java.util.List;

import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Queue;
import tp5.ejercicio1.Vertex;

public class Parcial {
	public Popularidad nivelPopularidad(Graph<String> red, String usuario, int distancia, int umbral){
		// defino la popularidad a retornar
		Popularidad popu = new Popularidad();
		// si el grafo no es nulo ni vacio
		if (red != null && !red.isEmpty()) {
			// busco el usuario de partida
			Vertex<String> inicio = red.search(usuario);
			// si el usuario esta en la red
			if (inicio != null) {
				// declaro arreglo de visitados
				boolean[] visitados = new boolean[red.getSize()];
				// declaro distancia actual
				int distanciaActual = 0;
				// declaro umbral
				int umbralActual = 0;
				// llamo a funcion privada
				nivelPrivado(red, inicio, popu, distancia, distanciaActual, umbral, umbralActual, visitados);
				
			}
		}
		// retorno popularidad
		return popu;
	}
	
	public void nivelPrivado(Graph<String> red, Vertex<String> inicio, Popularidad popu, int distancia, int distanciaActual,
			int umbral, int umbralActual, boolean[] visitados) {
		// creo cola
		Queue<Vertex<String>> cola = new Queue<Vertex<String>>();
		// encolo 
		cola.enqueue(inicio);
		cola.enqueue(null);
		// marco como visitado
		visitados[inicio.getPosition()]=true;
		// mientras la cola no este vacia
		while (!cola.isEmpty() && distanciaActual < distancia) {
			// desencolo
			Vertex<String> actual = cola.dequeue();
			// si es un nodo valido
			if (actual != null) {
				// si el nivel no es 0
				if (distanciaActual != 0 && distanciaActual == distancia) {
					// acumulo amigos
					umbralActual++;
				}
				// obtengo los vecinos del nodo actual
				List<Edge<String>> adyacentes = red.getEdges(actual);
				// para cada vecino
				for (Edge<String> e: adyacentes) {
					// obtengo el proximo vertice
					Vertex<String> proximoVertice = e.getTarget();
					// si no lo visite
					if (!visitados[proximoVertice.getPosition()]) {
						// lo marco como visitado
						visitados[actual.getPosition()] = true;
						// lo encolo
						cola.enqueue(proximoVertice);
					}
				}
			} else {
				// si sali porque actual es null
				if (!cola.isEmpty()) {
					// sumo distancia y encolo null
					distanciaActual++;
					cola.enqueue(null);
				}
			}
		}
		// instancia el objeto a retornar
		if (umbralActual >= umbral) {
			popu.setCantUsuarios(umbralActual);
			popu.setPopular(true);
		} else {
			popu.setCantUsuarios(umbralActual);
			popu.setPopular(false);
		}
	}
}
