package parcialPandemia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;

public class Parcial {
	
	public List<String> resolver(Graph<String>ciudades, String origen, String destino, List<String>pasandoPor){
		//creo lista a retornar
		List<String> camino = new ArrayList<String>();
		//chequeo que el grafo no sea ni null ni vacio
		if(ciudades !=null && !ciudades.isEmpty()) {
			//me fijo que origen y destino existan en el grafo
			Vertex<String> inicio = ciudades.search(origen);
			Vertex<String> fin = ciudades.search(destino);
			//chequeo que no sean null ambas ciudades
			if((inicio != null) && (fin != null)) {
				//creo vector de visitados
				boolean [] visitados = new boolean[ciudades.getSize()];
				resolverPrivado(ciudades,inicio,fin,pasandoPor,camino,visitados);
			}			
		}		
		//retorno la lista
		return camino;
	}
	
	private boolean resolverPrivado(Graph<String> ciudades, Vertex<String>actual,Vertex<String> destino,List<String>pasandoPor,List<String>camino,boolean[] visitados) {
		boolean ok=false;
		//marco como visitado mi nodo actual
		visitados[actual.getPosition()]=true;
		//agrego el nodo actual a la lista del camino actual
		camino.add(actual.getData());
		//
		if(ok == false && (actual.getData().equals(destino.getData()))) {
			//Se puede utilizar este metodo?
			if(camino.containsAll(pasandoPor)) {
				ok=true;
				return ok;				
			}
		}
		else {  // iterador para cortar la ejecucion al encontrar el primer camino posible
			List<Edge<String>> adyacentes = ciudades.getEdges(actual);
			Iterator<Edge<String>> it = adyacentes.iterator();
			while(it.hasNext() && ok==false) {
				Edge<String> edge = it.next();
				Vertex<String> proximoVertice = edge.getTarget();
				if(!visitados[proximoVertice.getPosition()]) { 
					ok = resolverPrivado(ciudades,proximoVertice,destino,pasandoPor,camino,visitados);
				}
			}
		}
		if(!ok) {
			camino.remove(camino.size()-1);
		}
		visitados[actual.getPosition()]=false;
		return ok;
	}
}
