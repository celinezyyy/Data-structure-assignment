
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author firzanahzaidi
 */
public class Q4BFSPaths {
    
    // breadth-first search algorithm to find all paths from start to end
    
    //method where, list is inside a list
    public static List<List<Integer>> bfs(Q4Graph<Integer> graph, int start, int end) {
        
        List<List<Integer>> paths = new ArrayList<>(); //store all paths from start till end
        Queue<List<Integer>> queue = new LinkedList<>(); //store paths explored during the bfs.
        Integer startV = graph.getVertex(start);// initialise startvertex as start
        
        if (startV == null) { //check if starting vertex has value or not
            return paths;
        }
        
        queue.add(Arrays.asList(start)); //add list with starting vertex in the queue
        
        while (!queue.isEmpty()) { //loop until queue finished
            
            List<Integer> path = queue.poll(); //fetch new elemment in list by removing it from queue
            int node = path.get(path.size() - 1); //representing last value of path
            
            if (node == end) { 
                paths.add(path); //if dah finished a path, add to the list of paths
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
        return paths; //containing all paths available
    }
}
    
