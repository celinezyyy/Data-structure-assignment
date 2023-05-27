
import java.util.ArrayList;
import java.util.List;

public class Q5Vertex<T extends Comparable<T>> {
	T vertexInfo;
	int indeg;
	int outdeg;
	Q5Vertex<T> nextVertex;
	Q5Edge<T> firstEdge;
	boolean visited;
	List<Q5Vertex<T>> neighbors = new ArrayList<>();
	
	public Q5Vertex() {
		vertexInfo = null;
		indeg = 0;
		outdeg = 0;
		nextVertex = null;
		firstEdge = null;
		visited = false;
	}
	
	public Q5Vertex(T vInfo, Q5Vertex<T> next) {
		vertexInfo = vInfo;
		indeg = 0;
		outdeg = 0;
		nextVertex = next;
		firstEdge = null;
		visited = false;
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	
	public boolean getVisited() {
		return visited;
	}
}
