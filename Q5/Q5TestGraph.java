import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Q5TestGraph {
	public static void main(String[] args) {
		Q5Graph<Integer> graph1 = new Q5Graph<>();
		Scanner sc = new Scanner(System.in);
		
		for (int i = 1; i <= 10; i++) {
			graph1.addVertex(i);
		}

		addUndirectedEdge(graph1, 1, 3);
		addUndirectedEdge(graph1, 1, 10);
		addUndirectedEdge(graph1, 1, 2);
		addUndirectedEdge(graph1, 1, 6);

		addUndirectedEdge(graph1, 2, 4);

		addUndirectedEdge(graph1, 3, 4);
		graph1.addEdge(7, 3);

		addUndirectedEdge(graph1, 4, 5);

		addUndirectedEdge(graph1, 5, 6);
		addUndirectedEdge(graph1, 5, 7);

		addUndirectedEdge(graph1, 6, 7);
		addUndirectedEdge(graph1, 6, 8);

		addUndirectedEdge(graph1, 7, 9);

		addUndirectedEdge(graph1, 8, 7);
		addUndirectedEdge(graph1, 8, 9);
		addUndirectedEdge(graph1, 8, 10);

		addUndirectedEdge(graph1, 9, 10);

		
		System.out.print("Enter node without food : ");
		graph1.removeVertex(graph1.getIndex(sc.nextInt()));
		Q5HamiltonianCycle<Integer> hamCycle = new Q5HamiltonianCycle<>(graph1.head, graph1.getAllVertexObjects().size());
		hamCycle.findCycle();
	}

	public static <E extends Comparable<E>> void addUndirectedEdge(Q5Graph<E> graph, E source, E destination) {
		graph.addEdge(source, destination);
		graph.addEdge(destination, source);
	}

}
