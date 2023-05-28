
import java.util.List;
import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author firzanahzaidi
 */
public class Q4Compile {
    public static void main(String[] args) {
        Q4Graph<Integer> graph = new Q4Graph<>();

        // Adding vertices to the graph
        for(int i = 0; i <= 10; i++){
            graph.addVertex(i);
        }
        
        // Adding edges and weight to the graph
        graph.addUndirectedEdge(1, 2, 10, "forest");
        graph.addUndirectedEdge(1, 3, 18, "flat road");
        graph.addUndirectedEdge(1, 6, 20, "flat road");
        graph.addUndirectedEdge(1, 10, 16, "flat road");
        graph.addUndirectedEdge(2, 4, 10, "swamp");
        graph.addUndirectedEdge(3, 4, 12, "swamp");
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
        
        // user state the destination they want to go
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the base camp for the enemy base camp: ");
        int end = scanner.nextInt();
        scanner.nextLine();

        //NOT SURE PART
        // user stating the armyType
        System.out.print("Enter the army type (Calvary/Archer/Infantry): ");
        String armyType = scanner.nextLine();
        
        // finding bfs
        List<List<Integer>> bfspaths = Q4BFSPaths.bfs(graph, 1, end);

        // finding dijkstra
        List<List<Integer>> dijpaths = Q4DijkstraPaths.dijkstra(graph, 1, end);
        
        // finding the shortest length
        int bestLength = Integer.MAX_VALUE;
        for (List<Integer> path : bfspaths) {
            if (path.size() < bestLength) {
                bestLength = path.size();
            }
        }
        
        // print all the shortest paths
        System.out.println("Best path:");
        for (List<Integer> path : bfspaths) {
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

        // Display the shortest path
        if (dijpaths.isEmpty()) {
            System.out.println("No paths found.");
        } else {
            List<Integer> shortestPath = dijpaths.get(0);
            //double totalSpeed = calculateTotalSpeed(graph, shortestPath, armyType);
            double totalTime = calculateTotalTime(graph, shortestPath, armyType);

            System.out.println("Fastest path for General: "+ armyType);
            for (int i = 0; i < shortestPath.size(); i++) {
                int vertex = shortestPath.get(i);
                System.out.print(vertex);
                if (i != shortestPath.size() - 1) {
                    System.out.print(" -> ");
                }
            }
            System.out.println();
            //System.out.println("Total speed of the path = " + totalSpeed);
            System.out.println("Total time = " + totalTime);
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
