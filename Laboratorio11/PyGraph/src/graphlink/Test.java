package graphlink;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Crear una nueva instancia de GraphLink
		GraphLink<String> graph = new GraphLink<>();

		// Insertar vértices en el grafo
		graph.insertVertex("A");
		graph.insertVertex("B");
		graph.insertVertex("C");
		graph.insertVertex("F");

		graph.insertEdge("A", "B");
		graph.insertEdge("B", "C");
		graph.insertEdge("C", "F");

		System.out.println(graph);

		// graph.searchVertex("A");
		// graph.searchVertex("F");
		// graph.searchVertex("X");

		System.out.println(graph.searchEdge("A", "B"));
		System.out.println(graph.searchEdge("A", "C"));

		graph.removeVertex("F");
		System.out.println(graph);

		graph.removeEdge("A", "B");
		System.out.println(graph);

		System.out.println("Recorrido DFS");
		graph.dfs("B");

		GraphLink<Integer> graph1 = new GraphLink<>();
		graph1.insertVertex(1);
		graph1.insertVertex(2);
		graph1.insertVertex(3);
		graph1.insertVertex(6);
		graph1.insertVertex(7);
		graph1.insertVertex(8);
		graph1.insertEdge(1, 2);
		graph1.insertEdge(1, 3);
		graph1.insertEdge(2, 6);
		graph1.insertEdge(2, 7);
		graph1.insertEdge(3, 7);
		graph1.insertEdge(7, 8);
		System.out.println(graph1);
		// graph1.removeVertex(2);
		// System.out.println(graph1);
		// graph1.removeEdge(2, 7);
		// System.out.println(graph1);
		graph1.bfs(1);

		Vertex<Integer> verticeOrigen = graph1.listVertex.getNth(0); // Vértice 1
		Vertex<Integer> verticeDestino = graph1.listVertex.getNth(3); // Vértice 6
		ArrayList<Vertex<Integer>> camino = graph1.bfsPath(verticeOrigen, verticeDestino);
		// Imprimir el camino encontrado
		if (camino != null) {
			System.out.print("Camino: ");
			for (Vertex<Integer> vertex : camino) {
				System.out.print(vertex.getData() + " ");
			}
			System.out.println();
		} else {
			System.out.println("No existe un camino entre los vertices");
		}
	}
}