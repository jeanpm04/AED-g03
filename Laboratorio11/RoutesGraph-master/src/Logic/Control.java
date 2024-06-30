package Logic;

import Model.Routes;
import Model.referencePoints;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Control {
    // Lista de puntos que representan los vértices del grafo
    private List<referencePoints> points;

    // Lista de rutas que representan los bordes del grafo ponderado
    private List<Routes> routes;

    // Grafo ponderado que utiliza vértices de tipo referencePoints y bordes de tipo DefaultWeightedEdge
    private Graph<referencePoints, DefaultWeightedEdge> graph;

    // Instancia de DijkstraShortestPath para calcular el camino más corto en el grafo
    private DijkstraShortestPath<referencePoints, DefaultWeightedEdge> dijkstra;

    // Almacena el camino más corto calculado por el algoritmo de Dijkstra
    private GraphPath<referencePoints, DefaultWeightedEdge> shortestPath;

    /**
     * Constructor de la clase Control, inicializa los puntos de referencia y las posibles rutas
     * entre ellas
     */
    public Control() {
        Points();
        Routes();
        Graph();
    }

    private void Points() {
        points = new ArrayList<>(Arrays.asList(
            new referencePoints("A"),
            new referencePoints("B"),
            new referencePoints("C"),
            new referencePoints("D"),
            new referencePoints("E"),
            new referencePoints("F"),
            new referencePoints("G"),
            new referencePoints("H")
        ));
    }


        private void Routes() {
            routes = new ArrayList<>(Arrays.asList(
                new Routes(points.get(0), points.get(1), 3),
                new Routes(points.get(0), points.get(2), 5),
                new Routes(points.get(0), points.get(3), 2),
                new Routes(points.get(0), points.get(7), 10),
                new Routes(points.get(1), points.get(2), 5),
                new Routes(points.get(1), points.get(3), 8),
                new Routes(points.get(1), points.get(4), 4),
                new Routes(points.get(1), points.get(6), 6),
                new Routes(points.get(1), points.get(7), 6),
                new Routes(points.get(2), points.get(5), 7),
                new Routes(points.get(2), points.get(4), 1),
                new Routes(points.get(2), points.get(6), 9),
                new Routes(points.get(3), points.get(4), 8),
                new Routes(points.get(3), points.get(7), 14),
                new Routes(points.get(4), points.get(6), 15),
                new Routes(points.get(5), points.get(7), 9),
                new Routes(points.get(6), points.get(7), 3)
            ));
        }


    private void Graph() {
        // Crear un grafo ponderado utilizando la clase SimpleWeightedGraph de JGraphT
        graph = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

        // Añadir vértices al grafo utilizando la lista de referencePoints
        for (referencePoints points1 : points) {
            graph.addVertex(points1);
        }

        // Añadir bordes y pesos al grafo utilizando la lista de Routes
        for (Routes route : routes) {
            // Añadir un borde entre el punto de inicio y el punto de destino
            graph.addEdge(route.getStartPoint(), route.getEndPoint());
            // Establecer el peso del borde con la distancia de la ruta
            graph.setEdgeWeight(graph.getEdge(route.getStartPoint(), route.getEndPoint()), route.getDistance());
        }
    }



    /**
     * Método para encontrar la ruta más corta entre dos puntos refernciados utilizando el
     * algoritmo de Dijkstra.
     */
    public String searchRoute(int origin, int destiny) {
        this.dijkstra = new DijkstraShortestPath<>(graph);// Crear una instancia de DijkstraShortestPath utilizando el grafo
        this.shortestPath = dijkstra.getPath(points.get(origin), points.get(destiny));// Obtener el camino más corto entre el punto de origen y el punto de destino

        String out = "";

        // Verificar si se encontró un camino más corto
        if (shortestPath != null) {
            // Construir la cadena de salida para la ruta más corta
            out += "La ruta más corta es: " + "\n";
            List<referencePoints> shortRoute = shortestPath.getVertexList();
            double shortestDistance = shortestPath.getWeight();
            // Iterar a través de los puntos en el camino más corto
            for (int i = 0; i < shortRoute.size() - 1; i++) {
                out += shortRoute.get(i).getNamePoint() + " -> ";
            }
            out += shortRoute.get(shortRoute.size() - 1).getNamePoint();// Agregar el último punto en la ruta
            out += "\nLa distancia de la ruta es:" + shortestDistance + " metros";
        } else {
            out += "\nNo se encontró una ruta.";
        }
        return out;
    }
}
