
import java.util.Stack;

public class Q5HamiltonianCycle<T extends Comparable<T>> {
	// start (& end) vertex
	Q5Vertex<T> start;

	// stack used as list to store the path of the cycle
	Stack<Q5Vertex<T>> cycle = new Stack<>();
	Stack<Q5Vertex<T>> cloneStack = new Stack<>();

	// number of vertices in the graph
	int N;
	String pathString = "";

	// variable to mark if graph has the cycle
	boolean hasCycle = false;

	int size = 0;

	public Q5HamiltonianCycle(Q5Vertex<T> start, int N) {
		this.start = start;
		this.N = N;
	}

	// method to initiate the search of the Hamiltonian cycle
	public int findCycle() {
		// add starting vertex to the list
		cycle.push(start);

		// start searching the path
		solve(start);

		return size;
	}

	@SuppressWarnings("unchecked")
	// use the recursive method to find the paths
	private void solve(Q5Vertex<T> vertex) {
		// Base condition: if the vertex is the start vertex
		// and all nodes have been visited (start vertex twice)
		if (vertex == start && cycle.size() == N + 1) {
			hasCycle = true;
			size = cycle.size();
			// output the cycle
			cloneStack = (Stack<Q5Vertex<T>>) cycle.clone();
			// print out the path
			while (!cloneStack.isEmpty()) {
				if ((Integer) cloneStack.peek().vertexInfo == 1 && cloneStack.size() == 1)
					pathString += cloneStack.peek().vertexInfo + "\n";
				else
					pathString += cloneStack.peek().vertexInfo + " --> ";
				cloneStack.pop();
			}

			// return to explore more hamiltonian cycles
			return;
		}

		// iterate through the neighbor vertices
		for (Q5Vertex<T> neighbors : vertex.getNeighbors()) {
			if (!neighbors.getVisited()) {
				// visit and add vertex to the cycle
				neighbors.setVisited(true);
				cycle.push(neighbors);

				// Go to the neighbor vertex to find the cycle
				solve(neighbors);

				// Backtrack
				neighbors.setVisited(false);
				cycle.pop();
			}
		}
	}
}
