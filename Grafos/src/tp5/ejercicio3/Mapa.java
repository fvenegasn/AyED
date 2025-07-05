package tp5.ejercicio3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import tp5.ejercicio1.Edge;
import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;
import tp5.ejercicio1.adjList.AdjListGraph;

/* 1) devolverCamino (String ciudad1, String ciudad2): List<String>
Retorna la lista de ciudades que se deben atravesar para ir de ciudad1 a ciudad2 en caso de que se
pueda llegar, si no retorna la lista vacía. (Sin tener en cuenta el combustible).*/

public class Mapa {
	
	private Graph<String>mapaCiudades;
	
	public List<String> devolverCamino(String ciudad1,String ciudad2){
		ArrayList<String> camino = new ArrayList<String>();
		Vertex<String> vCiudad1 = mapaCiudades.search(ciudad1);
		Vertex<String> vCiudad2 = mapaCiudades.search(ciudad2);
		boolean[] vertices = new boolean[mapaCiudades.getSize()];
		if((vCiudad1 != null) && (vCiudad2 != null)) {
			devolverCaminoPrivado(vertices,camino,vCiudad1,vCiudad2); // consultar si es necesario el for desde aca y en que casos
/*			for (int i = 0; i < mapaCiudades.getSize(); i++) {
				devolverCaminoPrivado(vertices,camino,vCiudad1,vCiudad2);
			}*/
		}
		return camino;		
	}
	
	private boolean devolverCaminoPrivado(boolean[]vertices, ArrayList<String>camino, Vertex<String>vCiudad1, Vertex<String>vCiudad2){
		vertices[vCiudad1.getPosition()]=true;
		boolean encontre = false;
		camino.add(vCiudad1.getData());
		if(vCiudad1.getData().equals(vCiudad2.getData())){
			return encontre = true;
		}
		else {
			List<Edge<String>>adyacentes = mapaCiudades.getEdges(vCiudad1);
			Iterator <Edge<String>> it = adyacentes.iterator();
			while(it.hasNext() && !encontre) {
				Vertex<String> proximoVertice = it.next().getTarget();
				if(!vertices[proximoVertice.getPosition()]) {
					encontre = devolverCaminoPrivado(vertices, camino, proximoVertice, vCiudad2);
				}
			}	
		}
		if(!encontre) {
			camino.remove(vCiudad1.getData()); // consultar si se puede utilizar .clear
		}
		vertices[vCiudad1.getPosition()]=false;
		return encontre;
	}
	
	/*2. devolverCaminoExceptuando (String ciudad1, String ciudad2, List<String> ciudades): List<String>
	Retorna la lista de ciudades que forman un camino desde ciudad1 a ciudad2, sin pasar por las ciudades
	que están contenidas en la lista ciudades pasada por parámetro, si no existe camino retorna la lista
	vacía. (Sin tener en cuenta el combustible).*/
	
	public List<String> devolverCaminoExceptuando(String ciudad1, String ciudad2, List<String> ciudades){
		ArrayList<String> camino = new ArrayList<String>();
		// if grafo vacio
			//returna nada (camino vacio)
		// else
		Vertex<String> vCiudad1 = mapaCiudades.search(ciudad1);
		Vertex<String> vCiudad2 = mapaCiudades.search(ciudad2);
		if((vCiudad1 != null) && (vCiudad2 != null)) {
			boolean[] vertices = new boolean[mapaCiudades.getSize()];
			marcarCiudadesRestringidas(ciudades, vertices);
			if ((vertices[vCiudad1.getPosition()] != true) && (vertices[vCiudad2.getPosition()] != true)) {
				devolverCaminoPrivado(vertices,camino,vCiudad1,vCiudad2);
			}
			/*for (int i = 0; i < mapaCiudades.getSize(); i++) {
				if (vertices[i] != true) {
				}*/
		}
		return camino;
	}
	
	private void marcarCiudadesRestringidas(List<String> ciudades, boolean[] vertices) {
		for (String c: ciudades) {
			//vertices[mapaCiudades.search(c).getPosition()] = true;
			Vertex<String> vertice = mapaCiudades.search(c);
			if (vertice != null) {
				vertices[vertice.getPosition()] = true;
			}
		}
	}
	
	//private boolean devolverCaminoExceptuandoPrivado(boolean[]vertices, ArrayList<String>camino, Vertex<String>vCiudad1, Vertex<String>vCiudad2) {
	//	
	//}
	
	public static void main(String[] args) {
	    Graph<String> ciudades = new AdjListGraph<String>();
	    Mapa rec = new Mapa();
	    Vertex<String> v1 = ciudades.createVertex("Buenos Aires");
	    Vertex<String> v2 = ciudades.createVertex("Santiago");
	    Vertex<String> v3 = ciudades.createVertex("Asunción");
	    Vertex<String> v4 = ciudades.createVertex("Tokio");
	    Vertex<String> v5 = ciudades.createVertex("Roma");
	    Vertex<String> v6 = ciudades.createVertex("Paris");
	    Vertex<String> v7 = ciudades.createVertex("Madrid");
	    Vertex<String> v8 = ciudades.createVertex("Caracas");
	    ciudades.connect(v1, v2);
	    ciudades.connect(v1, v3);
	    ciudades.connect(v2, v5);
	    ciudades.connect(v3, v7);
	    ciudades.connect(v3, v8);
	    ciudades.connect(v8, v7);
	    ciudades.connect(v8, v4);
	    ciudades.connect(v5, v4);
	    ciudades.connect(v7, v4);
	    ciudades.connect(v6, v5);
	    ciudades.connect(v6, v7);
	    ciudades.connect(v6, v4);
	    ciudades.connect(v4, v1);
	    
	    List<String> places = Arrays.asList("Caracas");

	    
	    rec.mapaCiudades = ciudades;
	    List<String> lisDFS = rec.devolverCaminoExceptuando("Caracas","Tokio", places);
	    
	    System.out.print("Lista DFS: ");
	    for (String e: lisDFS){
	        System.out.print(e + " ~ ");
	    }
	    
	    System.out.println("");
	}
}