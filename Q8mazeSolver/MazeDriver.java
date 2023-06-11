package Q8mazeSolver;


import java.io.File;
import java.io.FileInputStream;
import java.util.List;
import java.util.Scanner;

public class MazeDriver {
    public static void main(String[] args) throws Exception {
        File maze1 = new File("C:\\Users\\user\\Downloads\\maze1.txt");
        
        print(maze1);
        execute(maze1);
    }

    private static void execute(File file) throws Exception {
        Maze maze = new Maze(file);
        //dfs(maze);
        bfs(maze);
    }
    
    private static void print(File file) throws Exception {
        System.out.println("Hua Rong Road: ");
        file = new File("C:\\Users\\user\\Downloads\\maze1.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()){
            System.out.println(sc.nextLine());
        }
        System.out.println("");
    }

    private static void bfs(Maze maze) {
        System.out.println("How Cao Cao might have retreated from Hua Rong Road: ");
        BFSMazeSolver bfs = new BFSMazeSolver();
        List<Coordinate> path = bfs.solve(maze);
        maze.printPath(path);
        maze.reset();
    }
}
