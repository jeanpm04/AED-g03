package graphmatrix;

public class Test2 {
	public static void main(String[] args) {
		// Crear un grafo con 5 vértices
		GraphMat grafo = new GraphMat(5);

		// Insertar vértices
		for (int i = 0; i < 5; i++) {
			grafo.insertVertex(i);
		}

		// Insertar aristas
		grafo.insertEdge(0, 1);
		grafo.insertEdge(0, 2);
		grafo.insertEdge(1, 3);
		grafo.insertEdge(1, 4);
		grafo.insertEdge(2, 4);

		// Buscar vértices y aristas
		System.out.println("Vértice 3 en el grafo: " + grafo.searchVertex(3)); // Debería retornar true
		System.out.println("Arista entre 0 y 1: " + grafo.searchEdge(0, 1)); // Debería retornar true
		System.out.println("Arista entre 2 y 3: " + grafo.searchEdge(2, 3)); // Debería retornar false

		// Recorrido en profundidad (DFS)
		System.out.print("Recorrido DFS desde el vértice 0: ");
		grafo.dfs(0); // Debería imprimir algo como: 0 1 3 4 2

		for (int i = 0; i < grafo.getnVertices(); i++) {
			for (int j = 0; j < grafo.getnVertices(); j++) {
				System.out.print(grafo.getMatrizAdyacencia()[i][j] + " ");
			}
			System.out.println();
		}
	}
}