package tp5.ejercicio4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;
import tp5.ejercicio1.adjList.AdjListGraph;

public class VisitaOslo {
	
	public List<String> paseoEnBici(Graph<String> lugares, String destino, int maxTiempo, List<String> lugaresRestringidos){
		List<String> paseo = new ArrayList<String>(); // lista a devolver
		if (!lugares.isEmpty()){
			Vertex<String> Inicio = lugares.search("Ayuntamiento");
			Vertex<String> Fin = lugares.search(destino);
			if((Inicio != null ) && (Fin != null)){
				boolean[] vertices = new boolean [lugares.getSize()];
				// aca se podria invocar a metodo que marca como visitados los lugares restringidos
				paseoEnBiciPrivado(vertices,lugares,maxTiempo,lugaresRestringidos,paseo,Inicio,Fin);
			}
		}
		return paseo;
	}
	
	private boolean paseoEnBiciPrivado(boolean[] vertice,Graph<String>lugares,int maxTiempo, List<String>lugaresRestringidos, List<String>paseo, Vertex<String>Inicio, Vertex<String>Fin) {
		boolean ok=false;
		vertice[Inicio.getPosition()]=true;
		paseo.add(Inicio.getData());
		if(Inicio.getData() == Fin.getData()) {
			return true;
		}
		else {
			List<Edge<String>> adyacentes = lugares.getEdges(Inicio);
			Iterator <Edge<String>> it = adyacentes.iterator();
			while((it.hasNext()) && (maxTiempo <= 120) && (!ok)) {
				Edge<String> arista = it.next();
				Vertex<String> proximoVertice = arista.getTarget();
				if((!vertice[proximoVertice.getPosition()]) && (!lugaresRestringidos.contains(proximoVertice.getData()))) {
					ok = paseoEnBiciPrivado(vertice,lugares,maxTiempo - arista.getWeight(),lugaresRestringidos,paseo,proximoVertice,Fin);
				}
			}
		}
		if(!ok) {
			paseo.remove(paseo.size()-1);
		}
		vertice[Inicio.getPosition()]=false;
		return ok;
	}
	
	public static void main(String[] args) {
        Graph<String> lugares = new AdjListGraph<String>();
        Vertex<String> v1 = lugares.createVertex("Holmenkollen");
        Vertex<String> v2 = lugares.createVertex("Parque Vigeland");
        Vertex<String> v3 = lugares.createVertex("Galería Nacional");
        Vertex<String> v4 = lugares.createVertex("Parque Botánico");
        Vertex<String> v5 = lugares.createVertex("Museo Munch");
        Vertex<String> v6 = lugares.createVertex("FolkMuseum");
        Vertex<String> v7 = lugares.createVertex("Palacio Real");
        Vertex<String> v8 = lugares.createVertex("Ayuntamiento");
        Vertex<String> v9 = lugares.createVertex("El Tigre");
        Vertex<String> v10 = lugares.createVertex("Akker Brigge");
        Vertex<String> v11 = lugares.createVertex("Museo Fram");
        Vertex<String> v12 = lugares.createVertex("Museo Vikingo");
        Vertex<String> v13 = lugares.createVertex("La Opera");
        Vertex<String> v14 = lugares.createVertex("Museo del Barco Polar");
        Vertex<String> v15 = lugares.createVertex("Fortaleza Akershus");   
        
        lugares.connect(v1, v2, 30);
        lugares.connect(v2, v1, 30);
        lugares.connect(v2, v3, 10);
        lugares.connect(v3, v2, 10);
        lugares.connect(v3, v4, 15);
        lugares.connect(v4, v3, 15);
        lugares.connect(v4, v5, 1);
        lugares.connect(v5, v4, 1);
        
        lugares.connect(v5, v9, 15);
        lugares.connect(v9, v5, 15);
        lugares.connect(v9, v13, 5);
        lugares.connect(v13, v9, 5);
        lugares.connect(v13, v15, 10);
        lugares.connect(v15, v13, 10);
        
        lugares.connect(v2, v6, 20);
        lugares.connect(v6, v2, 20);
        lugares.connect(v6, v7, 5);
        lugares.connect(v7, v6, 5);
        lugares.connect(v7, v8, 5);
        lugares.connect(v8, v7, 5);
        lugares.connect(v6, v10, 30);
        lugares.connect(v10, v6, 30);
        lugares.connect(v10, v8, 20);
        lugares.connect(v8, v10, 20);
        lugares.connect(v8, v4, 10);
        lugares.connect(v4, v8, 10);
        lugares.connect(v8, v9, 15);
        lugares.connect(v9, v8, 15);
        
        lugares.connect(v6, v11, 5);
        lugares.connect(v11, v6, 5);
        lugares.connect(v10, v12, 30);
        lugares.connect(v12, v10, 30);
        lugares.connect(v11, v14, 5);
        lugares.connect(v14, v11, 5);
        lugares.connect(v12, v14, 5);
        lugares.connect(v14, v12, 5);
        
        List<String> lugaresRestringidos = new LinkedList<String>();
        lugaresRestringidos.add("Akker Brigge");
        lugaresRestringidos.add("Palacio Real");
        VisitaOslo vis = new VisitaOslo();
        List<String> camino = vis.paseoEnBici(lugares, "Museo Vikingo", 120, lugaresRestringidos);
        
        System.out.println(camino);;} // rotisimo
}