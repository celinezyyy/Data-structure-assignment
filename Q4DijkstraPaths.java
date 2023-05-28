
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author firzanahzaidi
 */

public class Q4DijkstraPaths {

    // Dijkstra's algorithm to find the shortest path from start to end

    public static List<List<Integer>> dijkstra(Q4Graph<Integer> graph, int start, int end) {

        List<List<Integer>> Dpaths = new ArrayList<>(); // store all paths from start to end

        PriorityQueue<List<Integer>> queue = new PriorityQueue<>((a, b) -> {
            int distA = calculateDistance(graph, a);
            int distB = calculateDistance(graph, b);
            return Integer.compare(distA, distB);
        }); // store paths explored during Dijkstra's algorithm based on the shortest distance

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
        return Dpaths; // return all available paths
    }

    private static int calculateDistance(Q4Graph<Integer> graph, List<Integer> path) {
        int distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int u = path.get(i);
            int v = path.get(i + 1);
            int weight = graph.getEdgeWeight(u, v);
            distance += weight;
        }
        return distance;
    }
}


