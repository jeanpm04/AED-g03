package graphmatrix;

import java.util.ArrayList;

public class GraphMat {
	private int[][] matrizAdyacencia;
	private ArrayList<Integer> vertices;
	private int nVertices;

	public int[][] getMatrizAdyacencia() {
		return matrizAdyacencia;
	}

	public void setMatrizAdyacencia(int[][] matrizAdyacencia) {
		this.matrizAdyacencia = matrizAdyacencia;
	}

	public ArrayList<Integer> getVertices() {
		return vertices;
	}

	public void setVertices(ArrayList<Integer> vertices) {
		this.vertices = vertices;
	}

	public int getnVertices() {
		return nVertices;
	}

	public void setnVertices(int nVertices) {
		this.nVertices = nVertices;
	}

	// Constructor
	public GraphMat(int nVertices) {
		this.nVertices = nVertices;
		matrizAdyacencia = new int[nVertices][nVertices]; // Inicializa la matriz de adyacencia
		vertices = new ArrayList<>(); // Inicializa la lista de vértices
	}

	// Método para insertar vértices en la lista de vértices
	public void insertVertex(int vertice) {
		if (!vertices.contains(vertice)) { // Verifica si el vértice ya está en la lista
			vertices.add(vertice); // Si no está, lo agrega a la lista
		}
	}

	// Método para insertar una arista entre dos vértices
	public void insertEdge(int x, int y) {
		if (vertices.contains(x) && vertices.contains(y)) {
			matrizAdyacencia[x][y] = 1; // Marca la arista de x a y
			matrizAdyacencia[y][x] = 1; // Marca la arista de y a x
		}
	}

	// Método para buscar un vértice en la lista de vértices
	public boolean searchVertex(int vertice) {
		return vertices.contains(vertice); // Retorna true si el vértice está en la lista
	}

	// Método para buscar una arista entre dos vértices
	public boolean searchEdge(int x, int y) {
		if (vertices.contains(x) && vertices.contains(y)) {
			return matrizAdyacencia[x][y] == 1; // Retorna true si hay una arista entre x e y
		}
		return false;
	}

	// DFS: recorrido en profundidad
	public void dfs(int vertice) {
		boolean[] visited = new boolean[nVertices]; // Array para marcar vértices visitados
		dfsRecursivo(vertice, visited);
		System.out.println();
	}

	// Método recursivo para DFS
	private void dfsRecursivo(int vertice, boolean[] visited) {
		visited[vertice] = true; // Marca el vértice como visitado
		System.out.print(vertice + " "); // Imprime el vértice visitado
		for (int i = 0; i < nVertices; i++) {
			// Recorre todos los vértices adyacentes no visitados
			if (matrizAdyacencia[vertice][i] == 1 && !visited[i]) {
				dfsRecursivo(i, visited); // Llama recursivamente al DFS en el vértice adyacente
			}
		}
	}
}