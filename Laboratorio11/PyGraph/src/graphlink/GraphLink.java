package graphlink;

import java.util.ArrayList;

import linkedlist.*;

public class GraphLink<E> {
	protected LinkedList<Vertex<E>> listVertex;

	public GraphLink() {
		listVertex = new LinkedList<>(); // Inicializa lista de vértices
	}

	// Método para insertar un vértice
	public void insertVertex(E data) {
		Vertex<E> nuevoVertice = new Vertex<>(data);
		// Verificar si el vértice no está en la lista
		if (listVertex.search(nuevoVertice) == -1) {
			listVertex.insertLast(nuevoVertice);
		}
	}

	// Método para insertar una arista
	public void insertEdge(E verOri, E verDes) { // Data
		Vertex<E> verOrigen = null;
		Vertex<E> verDestino = null;
		// Creamos un aux para recorrer la lista
		Node<Vertex<E>> aux = listVertex.getFirst();
		// A partir de aquí obtenemos los vértices
		do {
			// aux.getData: Vertex<E> // aux.getData().getData: obtiene el dato almacenado
			// en el vértice
			if (aux.getData().getData().equals(verOri)) {
				verOrigen = aux.getData();
			}
			if (aux.getData().getData().equals(verDes)) {
				verDestino = aux.getData();
			}
			aux = aux.getNext(); // Pasamos al siguiente
		} while (aux != null); // Mientras aux sea diferente de null

		// Si ambos vértices existen
		if (verOrigen != null && verDestino != null) {
			Edge<E> nuevaArista1 = new Edge<E>(verDestino);
			Edge<E> nuevaArista2 = new Edge<E>(verOrigen);
			// Insertar arista si no existen
			if (verOrigen.listAdj.search(nuevaArista1) == -1) {
				verOrigen.listAdj.insertLast(nuevaArista1);
			}
			if (verDestino.listAdj.search(nuevaArista2) == -1) {
				verDestino.listAdj.insertLast(nuevaArista2);
			}
		}
	}

	// Método para buscar un vértice
	public boolean searchVertex(E data) {
		Vertex<E> busca = new Vertex<E>(data);
		if (listVertex.search(busca) != -1) {
			System.out.println("El vértice " + busca + " existe");
			return true;
		} else {
			System.out.println("El vértice " + busca + " no existe");
			return false;
		}
	}

	// Método para buscar una arista
	public boolean searchEdge(E verOri, E verDes) {
		// Busca el vértice de origen en la lista de vértices
		Vertex<E> rOri = listVertex.searchData(new Vertex<E>(verOri));
		// Busca el vértice de destino en la lista de vértices
		Vertex<E> rDes = listVertex.searchData(new Vertex<E>(verDes));
		// Si alguno de los vértices no se encuentra, retorna falso
		if (rOri == null || rDes == null) {
			return false;
		} else {
			// Si el vértice de destino está en la lista de
			// adyacencias del vértice de origen, retorna verdadero
			if (rOri.listAdj.search(new Edge<E>(rDes)) != -1) {
				return true;
			} else {
				return false;
			}
		}
	}

	public void removeVertex(E data) {
		// Crea un nuevo vértice con el dato
		Vertex<E> vertice = new Vertex<E>(data);
		// Busca el vértice en la lista de vértices
		Vertex<E> foundVertex = listVertex.searchData(vertice);

		if (foundVertex != null) {
			// Elimina todas las aristas desde y hacia este vértice
			Node<Edge<E>> currentEdge = foundVertex.listAdj.getFirst();
			while (currentEdge != null) {
				Vertex<E> destino = currentEdge.getData().getRefDest();
				destino.listAdj.removeNode(new Edge<E>(foundVertex));
				currentEdge = currentEdge.getNext();
			}
			// Elimina el vértice de la lista de vértices
			listVertex.removeNode(foundVertex);
		}
	}

	public void removeEdge(E verOri, E verDes) {
		Vertex<E> origen = listVertex.searchData(new Vertex<E>(verOri));
		Vertex<E> destino = listVertex.searchData(new Vertex<E>(verDes));

		// Verificar que ambos vértices existan
		if (origen != null && destino != null) {
			origen.listAdj.removeNode(new Edge<E>(destino)); // Eliminar la arista de origen a destino
			destino.listAdj.removeNode(new Edge<E>(origen)); // Eliminar la arista de destino a origen
		}
	}

	// Recorrido en profundidad a partir del vértice v del grafo
	public void dfs(E data) {
		// Crea un nuevo vértice con el dato
		Vertex<E> vertice = new Vertex<E>(data);
		// Busca el vértice en la lista de vértices
		Vertex<E> verticeOri = listVertex.searchData(vertice); // Obtiene el vértice de origen
		if (verticeOri == null) {
			System.out.println("Vértice no encontrado");
			return;
		}
		StackLink<Vertex<E>> pila = new StackLink<>(); // Crea una pila para el recorrido
		LinkedList<Vertex<E>> visited = new LinkedList<>(); // Lista de vértices visitados
		pila.push(verticeOri); // Apila el vértice de origen
		visited.insertLast(verticeOri); // Marca el vértice inicial como visitado

		while (!pila.isEmpty()) { // Mientras la pila no esté vacía
			try {
				Vertex<E> current = pila.pop(); // Desapila el vértice actual
				System.out.print(current.getData() + ", ");
				// Obtiene la primera arista del vértice actual
				Node<Edge<E>> aux = current.listAdj.getFirst();
				while (aux != null) {
					// Obtiene el vértice adyacente
					Vertex<E> verticeAdj = aux.getData().getRefDest();
					if (visited.search(verticeAdj) == -1) { // Si el vértice adyacente no ha sido visitado
						visited.insertLast(verticeAdj); // Marca como visitado
						pila.push(verticeAdj); // Apila el vértice adyacente
					}
					aux = aux.getNext(); // Pasamos a la siguiente arista
				}
			} catch (ExceptionIsEmpty e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	// Recorrido en anchura a partir del vértice v del grafo
	public void bfs(E data) {
		// Crea un nuevo vértice con el dato
		Vertex<E> vertice = new Vertex<E>(data);
		// Busca el vértice en la lista de vértices
		Vertex<E> verticeOri = listVertex.searchData(vertice); // Obtiene el vértice de origen
		if (verticeOri == null) {
			System.out.println("Vértice no encontrado");
			return;
		}
		QueueLink<Vertex<E>> cola = new QueueLink<>(); // Crea una cola para el recorrido
		LinkedList<Vertex<E>> visited = new LinkedList<>(); // Lista de vértices visitados
		cola.enqueue(verticeOri); // Encola el vértice de origen

		while (!cola.isEmpty()) { // Mientras la cola no esté vacía
			try {
				Vertex<E> current = cola.dequeue(); // Desencola el vértice actual
				if (visited.search(current) == -1) { // Si el vértice actual no ha sido visitado
					System.out.print(current.getData() + ", ");
					visited.insertLast(current); // Marca como visitado

					Node<Edge<E>> aux = current.listAdj.getFirst(); // Obtiene la primera arista del vértice actual
					while (aux != null) {
						Vertex<E> verticeAdj = aux.getData().getRefDest(); // Obtiene el vértice adyacente
						if (visited.search(verticeAdj) == -1) { // Si el vértice adyacente no ha sido visitado
							cola.enqueue(verticeAdj); // Encola el vértice adyacente
						}
						aux = aux.getNext(); // Pasa a la siguiente arista
					}
				}
			} catch (ExceptionIsEmpty e) {
				e.printStackTrace();
			}
		}
		System.out.println();
	}

	public void insertEdgeWeight(E x, E y, int w) {
		// Crea nuevos vértices con los datos
		Vertex<E> verticeOri = new Vertex<>(x);
		Vertex<E> verticeDest = new Vertex<>(y);
		// Busca los vértices en la lista de vértices
		verticeOri = listVertex.searchData(verticeOri);
		verticeDest = listVertex.searchData(verticeDest);

		// Verifica que existan ambos vértices
		if (verticeOri != null && verticeDest != null) {
			Edge<E> newEdge = new Edge<>(verticeDest, w);
			// Verifica que la arista no esté en la lista de adyacencias del vértice de
			// origen
			if (verticeOri.listAdj.search(newEdge) == -1) {
				verticeOri.listAdj.insertLast(newEdge);
			}
			// Arista inversa
			Edge<E> aristaInversa = new Edge<>(verticeOri, w);
			if (verticeDest.listAdj.search(aristaInversa) == -1) {
				verticeDest.listAdj.insertLast(aristaInversa);
			}
		} else {
			System.out.println("Error: vértices no encontrados");
		}
	}

	public ArrayList<Vertex<E>> bfsPath(Vertex<E> x, Vertex<E> y) {
		QueueLink<Vertex<E>> cola = new QueueLink<>();
		LinkedList<Vertex<E>> visited = new LinkedList<>();
		LinkedList<Vertex<E>> parents = new LinkedList<>(); // Predecesores
		cola.enqueue(x); // Encola el vértice inicial
		visited.insertLast(x); // Marca como visitado

		while (!cola.isEmpty()) { // Mientras la cola no esté vacía
			try {
				Vertex<E> current = cola.dequeue();
				if (current.equals(y)) { // Si se encuentra el vértice destino
					return reconstructPath(x, y, parents);
				}
				Node<Edge<E>> aux = current.listAdj.getFirst();
				while (aux != null) {
					Vertex<E> verticeAdj = aux.getData().getRefDest();
					if (visited.search(verticeAdj) == -1) { // Si el vértice adyacente no ha sido visitado
						cola.enqueue(verticeAdj);
						visited.insertLast(verticeAdj);
						parents.insertLast(current); // Guarda el padre del vértice adyacente
					}
					aux = aux.getNext();
				}
			} catch (ExceptionIsEmpty e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	private ArrayList<Vertex<E>> reconstructPath(Vertex<E> x, Vertex<E> y, LinkedList<Vertex<E>> parents) {
		ArrayList<Vertex<E>> path = new ArrayList<>(); // Crea una lista para almacenar el camino
		Vertex<E> current = y; // Empieza desde el vértice destino
		while (!current.equals(x)) { // Mientras no se haya alcanzado el vértice origen
			path.add(0, current); // Añade el vértice actual al principio del camino
			current = getParent(current, parents); // Obtiene el padre del vértice actual
		}
		path.add(0, x); // Añade el vértice origen al principio del camino
		return path;
	}

	private Vertex<E> getParent(Vertex<E> vertex, LinkedList<Vertex<E>> parents) {
		Node<Vertex<E>> aux = parents.getFirst(); // Empieza desde el primer nodo en la lista de padres
		while (aux != null) { // Recorre la lista de padres
			if (aux.getData().listAdj.search(new Edge<>(vertex)) != -1) {
				return aux.getData(); // Retorna el vértice si encuentra una arista hacia el vértice dado
			}
			aux = aux.getNext(); // Pasa al siguiente nodo en la lista
		}
		return null;
	}

	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		Node<Vertex<E>> current = listVertex.getFirst();
		while (current != null) {
			result.append(current.getData().toString());
			current = current.getNext();
		}
		return result.toString();
	}
}