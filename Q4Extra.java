


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author firzanahzaidi
 */
import java.util.*;

public class Q4Extra {
    public static void main(String[] args) {
        Q4Graph<Integer> graph = new Q4Graph<>();

        // Adding vertices to the graph
        for( int i = 0; i <=10; i++){
            graph.addVertex(i);
        }

        // Adding edges to the graph
        graph.addUndirectedEdge(1, 2, 10, "forest");
        graph.addUndirectedEdge(1, 3, 18, "flat road");
        graph.addUndirectedEdge(1, 6, 20, "flat road");
        graph.addUndirectedEdge(1, 10, 16, "flat road");
        graph.addUndirectedEdge(2, 4, 10, "swamp");
        graph.addEdge(3, 4, 12, "swamp");
        graph.addUndirectedEdge(3, 7, 28, "plank road");
        graph.addUndirectedEdge(4, 5, 12, "swamp");
        graph.addUndirectedEdge(5, 7, 10, "forest");
        graph.addUndirectedEdge(6, 5, 17, "flat road");
        graph.addUndirectedEdge(6, 7, 23, "forest");
        graph.addUndirectedEdge(6, 8, 35, "plank road");
        graph.addUndirectedEdge(7, 9, 17, "flat road");
        graph.addUndirectedEdge(8, 9, 7, "swamp");
        graph.addUndirectedEdge(10, 8, 12, "forest");
        graph.addUndirectedEdge(10, 9, 18, "flat road");
        
        // Set end as last destination
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the base camp for the enemy base camp: ");
        int end = scanner.nextInt();
        scanner.nextLine();

        // Find the shortest path from Node 1 to the end node
        Q4DijkstraPaths.dijkstra(graph, 1, end);        
        }
    }


