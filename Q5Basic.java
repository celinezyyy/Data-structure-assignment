import java.util.Scanner;

public class Q5Basic {
	static Q5Graph<Integer> graph = new Q5Graph<>();

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String path;

		initGraph();
		
		Q5HamiltonianCycle<Integer> hamCycle1 = new Q5HamiltonianCycle<>(graph.head, graph.getAllVertexObjects().size());
		hamCycle1.findCycle();
		
		path = hamCycle1.pathString;

		System.out.print("Enter node without food : ");
		int index = sc.nextInt();
		
		graph.removeVertex(graph.getIndex(index));

		Q5HamiltonianCycle<Integer> hamCycle2 = new Q5HamiltonianCycle<>(graph.head, graph.getAllVertexObjects().size());
		hamCycle2.findCycle();
		
		System.out.println("Path : ");
		if(!hamCycle2.hasCycle) {
			System.out.println(path);
			System.out.println("Node " + index + " has to be connected!");
		}
		else {
			System.out.println(hamCycle2.pathString);
		}
	}

	public static void initGraph() {
		for (int i = 1; i <= 10; i++) {
			graph.addVertex(i);
		}

		addUndirectedEdge(1, 3);
		addUndirectedEdge(1, 10);
		addUndirectedEdge(1, 2);
		addUndirectedEdge(1, 6);

		addUndirectedEdge(2, 4);

		addUndirectedEdge(3, 4);
		graph.addEdge(7, 3);

		addUndirectedEdge(4, 5);

		addUndirectedEdge(5, 6);
		addUndirectedEdge(5, 7);

		addUndirectedEdge(6, 7);
		addUndirectedEdge(6, 8);

		addUndirectedEdge(7, 9);

		addUndirectedEdge(8, 7);
		addUndirectedEdge(8, 9);
		addUndirectedEdge(8, 10);

		addUndirectedEdge(9, 10);
	}

	public static void addUndirectedEdge(int source, int destination) {
		graph.addEdge(source, destination);
		graph.addEdge(destination, source);
	}
}
