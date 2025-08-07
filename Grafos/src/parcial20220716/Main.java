package parcial20220716;

import java.util.ArrayList;
import java.util.List;

import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;
import tp5.ejercicio1.adjList.AdjListGraph;

public class Main {

    public static void main(String[] args) {
        Graph<String> mapa = new AdjListGraph<String>();

        // Crear vértices (ciudades)
        Vertex<String> mendoza = mapa.createVertex("Mendoza");
        Vertex<String> sanMartin = mapa.createVertex("San Martín");
        Vertex<String> tunuyan = mapa.createVertex("Tunuyán");
        Vertex<String> malargue = mapa.createVertex("Malargüe");
        Vertex<String> generalAlvear = mapa.createVertex("General Alvear");
        Vertex<String> sanRafael = mapa.createVertex("San Rafael");
        Vertex<String> laPaz = mapa.createVertex("La Paz");
        Vertex<String> santaRosa = mapa.createVertex("Santa Rosa");

        // Crear conexiones bidireccionales con sus respectivos litros
        mapa.connect(mendoza, sanMartin, 6);
        
        mapa.connect(mendoza, tunuyan, 10);
        
        mapa.connect(tunuyan, malargue, 12);
        
        mapa.connect(tunuyan, sanMartin, 10);
        
        mapa.connect(sanMartin, sanRafael, 13);
        
        mapa.connect(sanMartin, laPaz, 8);
        
        mapa.connect(laPaz, santaRosa, 2);
        
        mapa.connect(sanRafael, generalAlvear, 7);
        
        mapa.connect(generalAlvear, malargue, 6);
        
        mapa.connect(sanRafael, tunuyan, 11);

        List<String> lista = new ArrayList<String>();
        lista.add("General Alvear");
        lista.add("La Paz");
        
        
        // Ejemplo de uso del buscador
        Parcial buscador = new Parcial();
        List<String> recorridos = buscador.recorrido(mapa, 5, 80, lista);
        for (String camino : recorridos) {
            System.out.println(camino);
        }
    }
}

