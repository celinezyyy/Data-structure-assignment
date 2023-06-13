

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author firzanahzaidi
 */

import java.util.*;

public class Q4DijkstraPaths {

    // Dijkstra's algorithm to find the shortest path from start to end

    public static List<List<Integer>> dijkstra(Q4Graph<Integer> graph, int start, int end) {
        List<List<Integer>> Dpaths = new ArrayList<>(); // store all paths from start to end
        Queue<List<Integer>> queue = new LinkedList<>(); // store paths explored during Dijkstra's algorithm based on the shortest distance
        Integer startV = graph.getVertex(start); // initialize start vertex

        if (startV == null) { // check if starting vertex has a value or not
            return Dpaths;
        }

        queue.add(Arrays.asList(start)); // add list with starting vertex to the priority queue

        while (!queue.isEmpty()) { // loop until the priority queue is empty

            List<Integer> path = queue.poll(); // fetch the path with the shortest distance
            int node = path.get(path.size() - 1); // representing the last vertex in the path

            if (node == end) {
                Dpaths.add(path); // if the end vertex is reached, add the path to the list of paths
            } else {
                List<Integer> neighbors = graph.getNeighbours(node);
                if (neighbors != null) {
                    for (int neighbor : neighbors) {
                        if (!path.contains(neighbor)) {
                            List<Integer> newPath = new ArrayList<>(path);
                            newPath.add(neighbor);
                            queue.add(newPath);
                        }
                    }
                }
            }
        }
        String[] armyTypes = {"Calvary", "Archer", "Infantry"};
        for (String armyType : armyTypes) {
        
            // Find the shortest path specific to the army type
        List<Integer> shortestPath = null;
        double shortestTime = Double.POSITIVE_INFINITY;
        for (List<Integer> path : Dpaths) {
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
        return Dpaths;
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