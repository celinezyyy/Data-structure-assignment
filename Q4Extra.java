
import java.util.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author firzanahzaidi
 */

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

        // Array of army types
        String[] armyTypes = {"Calvary", "Archer", "Infantry"};

        for (String armyType : armyTypes) {
        // Find the shortest path from Node 1 to the end node
        List<List<Integer>> paths = Q4DijkstraPaths.dijkstra(graph, 1, end);

        // Find the shortest path specific to the army type
        List<Integer> shortestPath = null;
        double shortestTime = Double.POSITIVE_INFINITY;
        for (List<Integer> path : paths) {
        double totalTime = calculateTotalTime(graph, path, armyType);
        if (totalTime < shortestTime) {
            shortestTime = totalTime;
            shortestPath = path;
        }
    }

        // Display the shortest path and total time for the army type
        if (shortestPath == null) {
            System.out.println("No paths found for " + armyType);
            }  
        else {
            System.out.println("Fastest path for General: " + armyType);
            for (int i = 0; i < shortestPath.size(); i++) {
                int vertex = shortestPath.get(i);
                System.out.print(vertex);
                if (i != shortestPath.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
            System.out.println("Total time = " + shortestTime);
        }
    }
    }

    private static double calculateTotalTime(Q4Graph<Integer> graph, List<Integer> path, String armyType) {
        double totalTime = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int u = path.get(i);
            int v = path.get(i + 1);
            int distance = graph.getEdgeWeight(u, v);
            String edgeType = graph.getEdgeLabel(u, v);
            double speed = calculateSpeed(armyType, edgeType);
            double time = distance / speed;
            totalTime += time;
        }
        return totalTime;
    }

    private static double calculateSpeed(String armyType, String edgeType) {
        double speed;
        switch (armyType) {
            case "Calvary":
                speed = 2.0;
                break;
            case "Archer":
                speed = 1.0;
                break;
            case "Infantry":
                speed = 1.0;
                break;
            default:
                speed = 1.0;
                break;
        }

        switch (edgeType) {
            case "flat road":
                if (armyType.equalsIgnoreCase("Calvary"))
                    speed *= 3.0;
                else if (armyType.equalsIgnoreCase("Archer"))
                    speed *= 2.0;
                else if (armyType.equalsIgnoreCase("Infantry"))
                    speed *= 2.0;
                break;
            case "forest":
                if (armyType.equalsIgnoreCase("Calvary"))
                    speed *= 0.8;
                else if (armyType.equalsIgnoreCase("Archer"))
                    speed *= 1.0;
                else if (armyType.equalsIgnoreCase("Infantry"))
                    speed *= 2.5;
                break;
            case "swamp":
                if (armyType.equalsIgnoreCase("Calvary"))
                    speed *= 0.3;
                else if (armyType.equalsIgnoreCase("Archer"))
                    speed *= 2.5;
                else if (armyType.equalsIgnoreCase("Infantry"))
                    speed *= 1.0;
                break;
            case "plank road":
                if (armyType.equalsIgnoreCase("Calvary"))
                    speed *= 0.5;
       
         else if (armyType.equalsIgnoreCase("Archer"))
                    speed *= 0.5;
                else if (armyType.equalsIgnoreCase("Infantry"))
                    speed *= 0.5;
                break;
            default:
                speed *= 1.0;
                break;
        }

        return speed;
    }
 }


