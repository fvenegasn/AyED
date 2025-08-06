package parcial2024SegundaFechaTema1;

import java.util.ArrayList;
import java.util.List;

import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Queue;
import tp5.ejercicio1.Vertex;

public class Parcial {

	public List<Invitado> invitacionMasterClass (Graph<String> red, String usuario, int distancia, int limite){
		// defino la lista a retornar
		List<Invitado> invitados = new ArrayList<Invitado>();
		// si el grafo no es nulo y no esta vacio
		if (red != null && !red.isEmpty()) {
			// busco el usuario que es punto de partida
			Vertex<String> inicio = red.search(usuario);
			// si el usuario existe en el grafo
			if (inicio != null) {
				// defino nodos visitados 
				boolean[] visitados = new boolean[red.getSize()];
				// defino variables para llevar el conteo de distancia recorrida y limite de invitados
				int distanciaActual = 0;
				int cantidadInvitados = 0;
				// recorro el grafo
				invitacionPrivada(red, inicio, distancia, limite, visitados, distanciaActual, cantidadInvitados, invitados);
			}
		}
		// retorno la lista de invitados
		return invitados;
	}
	
	public void invitacionPrivada(Graph<String> red, Vertex<String> inicio, int distancia, int limite, 
			boolean[] visitados, int distanciaActual, int cantidadInvitados, List<Invitado> invitados) {
		// implemento la colo barco
		Queue<Vertex<String>> cola = new Queue<Vertex<String>>();
		// encolo el nodo actual
		cola.enqueue(inicio);
		// lo marco como visitado
		visitados[inicio.getPosition()] = true;
		// encolo null
		cola.enqueue(null);
		// mientras la cola no este vacia, pueda seguir invitando gente y la distancia me alcanza
		while (!cola.isEmpty() && cantidadInvitados < limite && 
				distanciaActual < distancia) {
			// desencolo el ultimo elemento de la cola
			Vertex<String> actual = cola.dequeue();
			// si es un nodo valido
			if (actual != null) {
				// si el nodo actual no es el inicial
				if (!inicio.getData().equals(actual.getData())) {
					// lo agrego a mi listado de invitados
					invitados.add(new Invitado(actual.getData(), distanciaActual));
					cantidadInvitados++;
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
				if (!cola.isEmpty())
					// incremento la distancia
					distanciaActual++;
					// encolo null (nuevo nivel)
					cola.enqueue(null);
			}
		}
	}
}
