

public class Q5Edge <T extends Comparable<T>>{
	Q5Vertex<T> toVertex;
	Q5Edge<T> nextEdge;
	
	public Q5Edge() {
		toVertex = null;
		nextEdge = null;
	}
	
	public Q5Edge(Q5Vertex<T> destination, Q5Edge<T> a) {
		toVertex = destination;
		nextEdge = a;
	}
}
