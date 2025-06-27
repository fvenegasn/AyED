package tp5.ejercicio2;

import java.util.List;

import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;
import tp5.ejercicio1.adjList.AdjListGraph;

public class main {
	public static void main(String[] args) {
        Graph<Integer> ciudades = new AdjListGraph<Integer>();
        Recorridos<Integer> rec = new Recorridos<Integer>();
        Vertex<Integer> v1 = ciudades.createVertex(10);
        Vertex<Integer> v2 = ciudades.createVertex(12);
        Vertex<Integer> v3 = ciudades.createVertex(15);
        Vertex<Integer> v4 = ciudades.createVertex(88);
        Vertex<Integer> v5 = ciudades.createVertex(4);
        Vertex<Integer> v6 = ciudades.createVertex(9);
        Vertex<Integer> v7 = ciudades.createVertex(23);
        Vertex<Integer> v8 = ciudades.createVertex(58);
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
        
        List<Integer> lisDFS = rec.dfs(ciudades);
        List<Integer> lisBFS = rec.bfs(ciudades);
        
        System.out.print("Lista DFS: ");
        for (Integer e: lisDFS){
            System.out.print(e + " ~ ");
        }
        // imprime :Lista DFS: 10 ~ 12 ~ 4 ~ 88 ~ 15 ~ 23 ~ 58 ~ 9 ~ 
        // esta bien que llegue al 9?
        
        System.out.println("");
        
        System.out.print("Lista BFS: ");
        for (Integer e: lisBFS){
            System.out.print(e + " ~ ");
        }
        
        // imprime: Lista BFS: 10 ~ 12 ~ 15 ~ 4 ~ 23 ~ 58 ~ 88 ~ 9 ~
        // esta bien que llegue al 9?
    }
}

