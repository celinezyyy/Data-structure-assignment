import java.util.Scanner;

public class Q5Basic {
	static Q5Graph<Integer> graph = new Q5Graph<>();

	public static void main(String[] args) {
		//init
		Scanner sc = new Scanner(System.in);
		String path;

		//init graph
		initGraph();
		
		//find 1st cycle to backup if the node removed later will produce an acyclic graph
		Q5HamiltonianCycle<Integer> hamCycle1 = new Q5HamiltonianCycle<>(graph.head, graph.getAllVertexObjects().size());
		hamCycle1.findCycle();
		
		path = hamCycle1.pathString;

		System.out.print("Enter node without food : ");
		int index = sc.nextInt();
		
		//removing vertex based on user prompt
		graph.removeVertex(graph.getIndex(index));

		//finding the path after the node have been removed
		Q5HamiltonianCycle<Integer> hamCycle2 = new Q5HamiltonianCycle<>(graph.head, graph.getAllVertexObjects().size());
		hamCycle2.findCycle();
		
		//print out path
		System.out.println("Path : ");
		//if graph is acyclic after removing, then print out backup
		if(!hamCycle2.hasCycle) {
			System.out.println(path);
			System.out.println("Node " + index + " has to be connected!");
		}
		//else continue printing
		else {
			System.out.println(hamCycle2.pathString);
		}
	}

	public static void initGraph() {
		
		//this method is to initialize the graph
		
		for (int i = 1; i <= 10; i++) {
			graph.addVertex(i);
		}

		addUndirectedEdge(1, 3);
		addUndirectedEdge(1, 10);
		addUndirectedEdge(1, 2);
		addUndirectedEdge(1, 6);

		addUndirectedEdge(2, 4);

		addUndirectedEdge(3, 4);
		graph.addEdge(3, 7);

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
		
		//this method is for adding undirected edges
		
		graph.addEdge(source, destination);
		graph.addEdge(destination, source);
	}
}
