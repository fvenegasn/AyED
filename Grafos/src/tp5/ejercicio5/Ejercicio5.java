package tp5.ejercicio5;

import java.util.ArrayList;
import java.util.List;

import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Queue;
import tp5.ejercicio1.Vertex;

/*Se necesita armar la cartera de jubilados para cada empleado repartidor del banco, incluyendo en cada lista, los
jubilados que vivan un radio cercano a su casa y no hayan percibido la jubilación del mes.
Para ello, implemente un algoritmo que dado un Grafo<Persona> retorne una lista de jubilados que se
encuentren a una distancia menor a un valor dado del empleado Itaú (grado de separación del empleado Itaú).
El método recibirá un Grafo<Persona>, un empleado y un grado de separación/distancia y debe retornar una
lista de hasta 40 jubilados que no hayan percibido la jubilación del mes y se encuentre a una distancia menor a
recibido como parámetro.*/

public class Ejercicio5 {

	public List<Persona> quedateEnCasa(Graph<Persona> grafo, Persona empleado, int distancia){
		// declaro el listado de jubilados cerca
		List<Persona> jubiladosAVisitar= new ArrayList<Persona>();
		// si el grafo no es nulo y no esta vacio
		if (grafo != null && !grafo.isEmpty()) {
			// busco al empleado
			Vertex<Persona> empleadoInicial = grafo.search(empleado);
			// hago un arreglo de grafos visitados
			boolean[] visitados = new boolean[grafo.getSize()];
			// si el empleado no es null
			if (empleadoInicial != null) {
				// recorro en bfs
				quedateEnCasaPrivado(grafo, empleadoInicial, distancia, 0, jubiladosAVisitar, visitados);
			}
		}
		// retorno el listado de jubilados a visitar
		return jubiladosAVisitar;
	}
	
	private void quedateEnCasaPrivado(Graph<Persona> grafo, Vertex<Persona> empleado, int distancia, int distanciaRecorrida, List<Persona>jubiladosAVisitar, boolean[] visitados) {
		// crea una cola para los vertices
		Queue<Vertex<Persona>> cola = new Queue<Vertex<Persona>>(); 
		// encolo el vertice actual
		cola.enqueue(empleado);
		// encolo null
		cola.enqueue(null);
		// marco el vertice como visitado
		visitados[empleado.getPosition()] = true;
		
		// mientras la cola no este vacia
		while (!cola.isEmpty()) {
			// saca de la cola el vertice
			Vertex<Persona> verticeActual = cola.dequeue();
			if (verticeActual != null) {
				// es jubilado y es acorde a mi distancia?
				if ((verticeActual.getData().getTipo().equals("Jubilado")) && (distancia > distanciaRecorrida)) {
					// agrego al jubilado a mi lista de gente que voy a visitar
					jubiladosAVisitar.add(verticeActual.getData());
				}
				// para todos los vecinos del vertice actual
				List<Edge<Persona>> adyacentes = grafo.getEdges(verticeActual);
				for (Edge<Persona> p: adyacentes) {
					// obtengo el proximo vertice
					int proximoVertice = p.getTarget().getPosition();
					// si no lo visite y no llegue al maximo de jubilados que puedo visitar
					if (!visitados[proximoVertice] && jubiladosAVisitar.size() <= 40) {
						// lo marco como visitado
						visitados[proximoVertice] = true;
						cola.enqueue(p.getTarget());
					}
				}
			// si la cola no esta vacia (osea que avanzo en el nivel)
			} else if (!cola.isEmpty()) {
				// aumento un nivel de distancia
				distanciaRecorrida++;
				// encolo null
				cola.enqueue(null);
			}
			
		}
		
	}
}
