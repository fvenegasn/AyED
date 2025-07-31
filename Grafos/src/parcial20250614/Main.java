package parcial20250614;

import java.util.List;

import tp5.ejercicio1.Graph;
import tp5.ejercicio1.Vertex;
import tp5.ejercicio1.adjList.AdjListGraph;

public class Main {

    public static void main(String[] args) {
        Graph<String> bosque = new AdjListGraph<String>();

        Vertex<String> castillo = bosque.createVertex("Castillo");
        Vertex<String> cruceNorte = bosque.createVertex("Cruce Norte");
        Vertex<String> bosqueSombrio = bosque.createVertex("Bosque Sombrio");
        Vertex<String> aldeaLago = bosque.createVertex("Aldea del Lago");
        Vertex<String> campamentoDruidas = bosque.createVertex("Campamento de Druidas");
        Vertex<String> cuevaEncantada = bosque.createVertex("Cueva Encantada");
        Vertex<String> desfiladeroVientos = bosque.createVertex("Desfiladero de los Vientos");
        Vertex<String> aldea = bosque.createVertex("Aldea");

        // Conexiones (doble para simular no-dirigido)
        bosque.connect(castillo, cruceNorte, 10);
        bosque.connect(cruceNorte, castillo, 10);

        bosque.connect(castillo, bosqueSombrio, 20);
        bosque.connect(bosqueSombrio, castillo, 20);

        bosque.connect(castillo, aldeaLago, 15);
        bosque.connect(aldeaLago, castillo, 15);

        bosque.connect(castillo, cuevaEncantada, 6);
        bosque.connect(cuevaEncantada, castillo, 6);

        bosque.connect(cruceNorte, bosqueSombrio, 5);
        bosque.connect(bosqueSombrio, cruceNorte, 5);

        bosque.connect(bosqueSombrio, aldeaLago, 6);
        bosque.connect(aldeaLago, bosqueSombrio, 6);

        bosque.connect(bosqueSombrio, campamentoDruidas, 3);
        bosque.connect(campamentoDruidas, bosqueSombrio, 3);

        bosque.connect(campamentoDruidas, desfiladeroVientos, 7);
        bosque.connect(desfiladeroVientos, campamentoDruidas, 7);

        bosque.connect(desfiladeroVientos, aldea, 15);
        bosque.connect(aldea, desfiladeroVientos, 15);

        bosque.connect(campamentoDruidas, aldea, 35);
        bosque.connect(aldea, campamentoDruidas, 35);

        bosque.connect(aldeaLago, cuevaEncantada, 50);
        bosque.connect(cuevaEncantada, aldeaLago, 50);

        bosque.connect(aldeaLago, campamentoDruidas, 30);
        bosque.connect(campamentoDruidas, aldeaLago, 30);

        bosque.connect(cuevaEncantada, desfiladeroVientos, 45);
        bosque.connect(desfiladeroVientos, cuevaEncantada, 45);

        // Ejecutar búsqueda de caminos
        ParcialGrafos buscador = new ParcialGrafos();
        List<String> camino = buscador.rutaOptimaDistribucion(bosque, "Castillo", "Aldea", 70);
        
        System.out.println(camino);

    }
}