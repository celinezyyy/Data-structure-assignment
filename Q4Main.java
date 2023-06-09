

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author firzanahzaidi
 */
import java.util.*;

public class Q4Main {
    public static void main(String[] args) {
        
        Q4Graph<Integer> graph = new Q4Graph<>();
        
        for (int i = 0; i <= 10 ; i++){
            graph.addVertex(i);       
        }
        
        // Adding edges to the graph
        graph.addUndirectedEdge(1, 2, 0, null);
        graph.addUndirectedEdge(1, 3, 0,null);
        graph.addUndirectedEdge(1, 6, 0,null);
        graph.addUndirectedEdge(1, 10, 0,null);
        graph.addUndirectedEdge(2, 4, 0,null);
        graph.addUndirectedEdge(3, 4, 0,null);
        graph.addEdge(3, 7, 0,null);
        graph.addUndirectedEdge(4, 5, 0,null);
        graph.addUndirectedEdge(5, 6, 0,null);
        graph.addUndirectedEdge(5, 7, 0,null);
        graph.addUndirectedEdge(6, 7, 0,null);
        graph.addUndirectedEdge(6, 8, 0,null);
        graph.addUndirectedEdge(7, 8, 0,null);
        graph.addUndirectedEdge(7, 9, 0,null);
        graph.addUndirectedEdge(8, 9, 0,null);
        graph.addUndirectedEdge(8, 10, 0,null);
        graph.addUndirectedEdge(9, 10, 0,null);
        
        // get the end node from the user
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the base camp for the enemy base camp: ");
        int end = input.nextInt();    
        
        // find all paths from Node 1 to the end node
        List<List<Integer>> paths = Q4BFSPaths.bfs(graph, 1, end);
        
        // find the length of the shortest path
        int bestLength = Integer.MAX_VALUE;
        for (List<Integer> path : paths) {
            if (path.size() < bestLength) {
                bestLength = path.size();
            }
        }
        
        // print all the shortest paths
        System.out.println("Best path:");
        for (List<Integer> path : paths) {
            if (path.size() == bestLength) {
                for (int i = 0; i < path.size(); i++) {
                    System.out.print(path.get(i));
                    if (i < path.size() - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
        }
    }
}


