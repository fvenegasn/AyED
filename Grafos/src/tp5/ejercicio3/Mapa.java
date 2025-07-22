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
	
	/*. caminoMasCorto(String ciudad1, String ciudad2): List<String>
	Retorna la lista de ciudades que forman el camino más corto para llegar de ciudad1 a ciudad2, si no
	existe camino retorna la lista vacía. (Las rutas poseen la distancia).*/
	
	public List<String> caminoMasCorto(String ciudad1, String ciudad2){
		List<String>caminoActual = new ArrayList<String>();
		List<String>caminoCorto = new ArrayList<String>();
		Vertex<String> vCiudad1 = mapaCiudades.search(ciudad1);
		Vertex<String> vCiudad2 = mapaCiudades.search(ciudad2);
		boolean[] vertices = new boolean[mapaCiudades.getSize()];
		if((vCiudad1 != null) && (vCiudad2 != null)) {
			for (int i = 0; i < mapaCiudades.getSize(); i++) {
				devolverCaminoMasCorto(vertices,caminoActual,caminoCorto,vCiudad1,vCiudad2, 99999, 0);
			}			
		}
		return caminoCorto;
	}
	private void devolverCaminoMasCorto(boolean[]vertices,List<String>caminoActual, List<String>caminoCorto, Vertex<String>ciudad1, Vertex<String>ciudad2, int costoMasCorto, int costoActual) {
		vertices[ciudad1.getPosition()]=true;
		caminoActual.add(ciudad1.getData());
		if((ciudad1.getData().equals(ciudad2.getData())) && (costoActual < costoMasCorto)) {
			caminoCorto.clear();
			caminoCorto.addAll(caminoActual);
			costoMasCorto = costoActual;
		}else {
			List<Edge<String>> adyacentes = mapaCiudades.getEdges(ciudad1);
			for (Edge<String> edge : adyacentes) {
				Vertex<String> proximoVertice = edge.getTarget();
				if (!vertices[proximoVertice.getPosition()]) {
					int costo = edge.getWeight();
					if (costo + costoActual < costoMasCorto) {
						devolverCaminoMasCorto(vertices, caminoActual, caminoCorto, proximoVertice, ciudad2, costoMasCorto, costo + costoActual);
					}
				}
			}
		}
		caminoActual.remove(caminoActual.size()-1);
		vertices[ciudad1.getPosition()] = false;
	}

	// Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a ciudad2. El auto no debe
	// quedarse sin combustible y no puede cargar. Si no existe camino retorna la lista vacía.
	public List<String> caminoSinCargarCombustible(String ciudad1, String ciudad2, int tanqueAuto){
		List<String>camino = new ArrayList<String>();
		Vertex<String> vCiudad1 = mapaCiudades.search(ciudad1);
		Vertex<String> vCiudad2 = mapaCiudades.search(ciudad2);
		boolean[] vertices = new boolean[mapaCiudades.getSize()];
		if((vCiudad1 != null) && (vCiudad2 != null)) {
			devolverCaminoSinCombustible(vertices,camino,vCiudad1,vCiudad2, tanqueAuto, 0);
		}
		return camino;
	}
	
	private boolean devolverCaminoSinCombustible(boolean[]vertices, List<String>camino, Vertex<String>ciudad1, Vertex<String>ciudad2, int tanqueAuto, int tanqueActual) {
		boolean ok = false;
		vertices[ciudad1.getPosition()]=true;
		camino.add(ciudad1.getData());
		System.out.println("act"+tanqueActual);
		System.out.println("auto"+tanqueAuto);
		if((ciudad1.getData().equals(ciudad2.getData())) && (tanqueActual <= tanqueAuto)) {
			System.out.println("ok");
			return true;
		}else {
			List<Edge<String>> adyacentes = mapaCiudades.getEdges(ciudad1);
			Iterator<Edge<String>> it = adyacentes.iterator();
			while (it.hasNext() && !ok) {
				Edge<String> arista = it.next();
				Vertex<String> proximoVertice = arista.getTarget();
				if (!vertices[proximoVertice.getPosition()]) {
					int costo = arista.getWeight(); // 80
					if (costo + tanqueActual <= tanqueAuto) {
						ok = devolverCaminoSinCombustible(vertices, camino, proximoVertice, ciudad2, tanqueAuto, costo + tanqueActual);
					}

				}
			}
			if (!ok) {
				camino.remove(camino.size()-1);
			}
		}
		vertices[ciudad1.getPosition()] = false;
		return ok;
	}
	
	
	// Retorna la lista de ciudades que forman un camino para llegar de ciudad1 a ciudad2 teniendo en cuenta
	// que el auto debe cargar la menor cantidad de veces. El auto no se debe quedar sin combustible en
	// medio de una ruta, además puede completar su tanque al llegar a cualquier ciudad. Si no existe camino
	// retorna la lista vacía.
	
	public List<String> caminoConMenorCargaDeCombustible (String ciudad1, String ciudad2, int tanqueAuto){
		List<String>caminoActual = new ArrayList<String>();
		List<String>caminoCorto = new ArrayList<String>();
		Vertex<String> vCiudad1 = mapaCiudades.search(ciudad1);
		Vertex<String> vCiudad2 = mapaCiudades.search(ciudad2);
		boolean[] vertices = new boolean[mapaCiudades.getSize()];
		if((vCiudad1 != null) && (vCiudad2 != null)) {
			devolverCaminoConMenorCargaDeCombustible(vertices,caminoActual, caminoCorto,vCiudad1,vCiudad2, tanqueAuto, 9999, 0);
		}
		return caminoCorto;
	}
	
	private void devolverCaminoConMenorCargaDeCombustible(boolean[]vertices, List<String>caminoActual, List<String>caminoCorto, Vertex<String>ciudad1, Vertex<String>ciudad2, int tanqueAuto, int menorCantidadDeCombustible, int combustibleActual) {
		//boolean ok = false;
		vertices[ciudad1.getPosition()]=true;
		caminoActual.add(ciudad1.getData());
		if((ciudad1.getData().equals(ciudad2.getData())) && (combustibleActual < menorCantidadDeCombustible)){
			caminoCorto.clear();
			caminoCorto.addAll(caminoActual);
			menorCantidadDeCombustible = combustibleActual;
			//return true;
		} else {
			List<Edge<String>> adyacentes = mapaCiudades.getEdges(ciudad1);
			//Iterator<Edge<String>> it = adyacentes.iterator();
			for (Edge<String> edge : adyacentes) {
				//Edge<String> arista = it.next();
				Vertex<String> proximoVertice = edge.getTarget();
				if (!vertices[proximoVertice.getPosition()]) {
					int costo = edge.getWeight(); 
					if (costo < tanqueAuto) {
						devolverCaminoConMenorCargaDeCombustible(vertices, caminoActual, caminoCorto, proximoVertice, ciudad2, tanqueAuto, menorCantidadDeCombustible, costo + combustibleActual);
					}

				}
			}
		}
		caminoActual.remove(caminoActual.size()-1);
		vertices[ciudad1.getPosition()] = false;
//		return ok;
	}
	
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
	    ciudades.connect(v1, v2, 3);
	    ciudades.connect(v1, v3, 6);
	    ciudades.connect(v2, v5, 4);
	    ciudades.connect(v3, v7, 10);
	    ciudades.connect(v3, v8, 2);
	    ciudades.connect(v8, v7, 2);
	    ciudades.connect(v8, v4, 20);
	    ciudades.connect(v5, v4, 80);
	    ciudades.connect(v7, v4, 60);
	    ciudades.connect(v6, v5, 4);
	    ciudades.connect(v6, v7, 65);
	    ciudades.connect(v6, v4, 35);
	    ciudades.connect(v4, v1, 10);
	    
	    List<String> places = Arrays.asList("Caracas");

	    
	    rec.mapaCiudades = ciudades;
	    List<String> lisDFS = rec.caminoConMenorCargaDeCombustible("Paris","Madrid", 50);
	    
	    System.out.print("Lista DFS: ");
	    for (String e: lisDFS){
	        System.out.print(e + " ~ ");
	    }
	    
	    System.out.println("");
	}
}