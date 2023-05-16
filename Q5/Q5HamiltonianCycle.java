import java.util.ArrayList;
import java.util.Stack;

public class Q5HamiltonianCycle <T extends Comparable<T>> {
	 //start (& end) vertex
	  Q5Vertex<T> start;
	  //stack used as list to store the path of the cycle
	  Stack<Q5Vertex<T>> cycle = new Stack<>();
	  Stack<Q5Vertex<T>> cloneStack = new Stack<>();
	  ArrayList<Integer> list = new ArrayList<>();
	  //number of vertices in the graph
	  int N;
	  //varibale to mark if graph has the cycle
	  boolean hasCycle = false;
	  boolean oneIteration = false;

	  //constructor
	  public Q5HamiltonianCycle(Q5Vertex<T> start, int N){
	    this.start = start;
	    this.N = N;
	  }

	  //method to inititate the search of the Hamiltonian cycle
	  public void findCycle(){
	    //add starting vertex to the list
	    cycle.push(start);

	    //start searching the path
	    solve(start);
	    
	    if(list.isEmpty())
	    	System.out.println("No path!");
	  }

	  private void solve(Q5Vertex<T> vertex){
	    //Base condition: if the vertex is the start vertex
	    //and all nodes have been visited (start vertex twice)
		if(oneIteration) {
			return;
		}
	    if(vertex == start && cycle.size() == N+1){
	      hasCycle = true;

	      //output the cycle
	      System.out.println("Path : ");
	      cloneStack = (Stack<Q5Vertex<T>>) cycle.clone();
	      while(!cloneStack.isEmpty()) {
	    	  if((Integer) cloneStack.peek().vertexInfo == 1 && !list.isEmpty())
	    		  System.out.print(cloneStack.peek().vertexInfo);
	    	  else
	    		  System.out.print(cloneStack.peek().vertexInfo + " --> ");
	    	  list.add((Integer) cloneStack.pop().vertexInfo);
	      }
	      oneIteration = true;
	      
	      //return to explore more hamiltonian cycles
	      return;
	    }

	    //iterate through the neighbor vertices
	    for(Q5Vertex<T> nbr : vertex.neighbors){
	      if(!nbr.visited){
	        //visit and add vertex to the cycle
	        nbr.visited = true;
	        cycle.push(nbr);

	        //Go to the neighbor vertex to find the cycle
	        solve(nbr);

	        //Backtrack
	        nbr.visited = false;
	        cycle.pop();
	      }
	    }
	  }
}
