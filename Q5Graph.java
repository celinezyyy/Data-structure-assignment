
import java.util.ArrayList;

public class Q5Graph<T extends Comparable<T>> {
	Q5Vertex<T> head;
	int size;

	public Q5Graph() {
		head = null;
		size = 0;
	}

	public int getSize() {
		return this.size;
	}

	public boolean hasVertex(T v) {
		if (head == null)
			return false;
		Q5Vertex<T> temp = head;
		while (temp != null) {
			if (temp.vertexInfo.compareTo(v) == 0)
				return true;
			temp = temp.nextVertex;
		}
		return false;
	}

	public boolean addVertex(T v) {
		// if vertex is not on the graph
		if (hasVertex(v) == false) {
			Q5Vertex<T> temp = head;
			Q5Vertex<T> newVertex = new Q5Vertex<>(v, null);
			// if graph is empty, add the vertex to head
			if (head == null) {
				head = newVertex;
			}
			// else add vertex to the last in the list
			else {
				// traverse to the last vertex
				Q5Vertex<T> previous = head;
				while (temp != null) {
					previous = temp;
					temp = temp.nextVertex;
				}
				// add vertex to the last point
				previous.nextVertex = newVertex;
			}
			size++;
			return true;
		}
		// if vertex is in the graph don't add the vertex and return false
		else
			return false;
	}

	public int getIndex(T v) {
		Q5Vertex<T> temp = head;
		int pos = 0;
		while (temp != null) {
			if (temp.vertexInfo.compareTo(v) == 0)
				return pos;
			temp = temp.nextVertex;
			pos += 1;
		}
		return -1;
	}								

	public ArrayList<T> getAllVertexObjects() {
		ArrayList<T> list = new ArrayList<>();
		Q5Vertex<T> temp = head;
		while (temp != null) {
			list.add(temp.vertexInfo);
			temp = temp.nextVertex;
		}
		return list;
	}

	public ArrayList<Q5Vertex<T>> getAllVertex() {
		ArrayList<Q5Vertex<T>> list = new ArrayList<>();
		Q5Vertex<T> temp = head;
		while (temp != null) {
			list.add(temp);
			temp = temp.nextVertex;
		}
		return list;
	}

	public T removeVertex(int index) {
		if (index < 0 || index >= size)
			return null; // try to delete index of node not in the range
		else {
			removeVertexNeighbors(index);
			Q5Vertex<T> previous = head; // Set head to be previous

			for (int i = 1; i < index; i++) {
				previous = previous.nextVertex; // try to stop at previous before index that want to be deleted
			}
			Q5Vertex<T> current = previous.nextVertex; // copy previous.next to current
			previous.nextVertex = current.nextVertex; // set new point to from previous.next to current.next
			size--; // reduce size
			return current.vertexInfo;
		}

	}

	public void removeVertexNeighbors(int index) {
		Q5Vertex<T> temp = head;
		while (temp != null) {
			temp.getNeighbors().remove(getVertex(index));
			temp = temp.nextVertex;
		}
	}

	public Q5Vertex<T> getVertex(int pos) {
		if (pos > size - 1 || pos < 0)
			return null;
		Q5Vertex<T> temp = head;
		for (int i = 0; i < pos; i++)
			temp = temp.nextVertex;
		return temp;
	}

	public boolean hasEdge(T source, T destination) {
		if (head == null)
			return false;
		if (!hasVertex(source) || !hasVertex(destination))
			return false;
		Q5Vertex<T> sourceVertex = head;
		while (sourceVertex != null) {
			if (sourceVertex.vertexInfo.compareTo(source) == 0) {
				Q5Edge<T> currentEdge = sourceVertex.firstEdge;
				while (currentEdge != null) {
					if (currentEdge.toVertex.vertexInfo.compareTo(destination) == 0)
						return true;
					currentEdge = currentEdge.nextEdge;
				}
			}
			sourceVertex = sourceVertex.nextVertex;
		}
		return false;
	}

	public boolean addEdge(T source, T destination) {
		if (head == null)
			return false;
		if (!hasVertex(source) || !hasVertex(destination))
			return false;
		Q5Vertex<T> sourceVertex = head;
		while (sourceVertex != null) {
			if (sourceVertex.vertexInfo.compareTo(source) == 0) {
				Q5Vertex<T> destinationVertex = head;
				while (destinationVertex != null) {
					if (destinationVertex.vertexInfo.compareTo(destination) == 0) {
						Q5Edge<T> currentEdge = sourceVertex.firstEdge;
						Q5Edge<T> newEdge = new Q5Edge<>(destinationVertex, currentEdge);
						sourceVertex.firstEdge = newEdge;
						sourceVertex.neighbors.add(destinationVertex);
						return true;
					}
					destinationVertex = destinationVertex.nextVertex;
				}
			}
			sourceVertex = sourceVertex.nextVertex;
		}
		return false;
	}
	
}
