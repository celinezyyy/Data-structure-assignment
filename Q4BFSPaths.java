import java.util.*;

public class BFSPaths {
    
    // adjacency list representation of the graph
    private static Map<Integer, List<Integer>> graph = new HashMap<>();
    
    // breadth-first search algorithm to find all paths from start to end
    public static List<List<Integer>> bfsPaths(int start, int end) {
        
        List<List<Integer>> paths = new ArrayList<>();
        Queue<List<Integer>> queue = new LinkedList<>();
        queue.add(Arrays.asList(start));
        
        while (!queue.isEmpty()) {
            
            List<Integer> path = queue.poll();
            int node = path.get(path.size() - 1);
            
            if (node == end) {
                paths.add(path);
            } else if (graph.containsKey(node)) {
                for (int neighbor : graph.get(node)) {
                    if (!path.contains(neighbor)) {
                        List<Integer> newPath = new ArrayList<>(path);
                        newPath.add(neighbor);
                        queue.add(newPath);
                    }
                }
            }
        }
        return paths;
    }
    
    public static void main(String[] args) {
        // initialize the graph
        graph.put(1, Arrays.asList(2, 3, 6, 10));
        graph.put(2, Arrays.asList(4));
        graph.put(3, Arrays.asList(4, 7));
        graph.put(4, Arrays.asList(5));
        graph.put(5, Arrays.asList(7));
        graph.put(6, Arrays.asList(5,7, 8));
        graph.put(7, Arrays.asList(9));
        graph.put(8, Arrays.asList(7, 9));
        graph.put(9, Arrays.asList(7));
        graph.put(10, Arrays.asList(8 ,9));
        
        // get the end node from the user
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the base camp for the enemy base camp: ");
        int end = input.nextInt();
        
        // find all paths from Node 1 to the end node
        List<List<Integer>> paths = bfsPaths(1, end);
        
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
                    System.out.print(path.get(0));
                    for (int i = 1; i < path.size(); i++) {
                        System.out.print(" -> " + path.get(i));
                    }
                    System.out.println();
                }
        }
    }
}


    





