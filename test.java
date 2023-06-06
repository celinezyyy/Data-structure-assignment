/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package data.struc.assignment;
import java.util.*;

public class Q7Extra {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter size of forest: ");
        int m = sc.nextInt();
        int n = sc.nextInt();
        int matrix[][] = new int[m][n];

        System.out.println("Enter the whole matrix");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }

        List<List<int[]>> clusterPoints = findClusters(matrix);
        List<List<int[]>> optimalCoordinatesByCluster = new ArrayList<>();
        List<Integer> timeTakenByPoint = new ArrayList<>();

        for (List<int[]> cluster : clusterPoints) {
            int minTime = Integer.MAX_VALUE;
            List<int[]> bestPoints = new ArrayList<>();
            int timeTaken = 0;

            for (int[] point : cluster) {
                int time = calculateTime(m, n, matrix, point[0], point[1]);

                if (time < minTime) {
                    minTime = time;
                    bestPoints.clear();
                    bestPoints.add(point);
                    timeTaken = time;
                } else if (time == minTime) {
                    bestPoints.add(point);
                }
            }

            optimalCoordinatesByCluster.add(bestPoints);
            timeTakenByPoint.add(timeTaken);
        }

        System.out.println("Cluster points:");
        int clusterIndex = 1;

        for (int i = 0; i < clusterPoints.size(); i++) {
            List<int[]> cluster = clusterPoints.get(i);
            List<int[]> bestPoints = optimalCoordinatesByCluster.get(i);

            System.out.println("Cluster " + clusterIndex + ":");
            for (int[] point : cluster) {
                System.out.println("(" + point[0] + ", " + point[1] + ")");
            }

            System.out.println("Best Points for Cluster " + clusterIndex + ":");
            for (int j = 0; j < bestPoints.size(); j++) {
                int[] point = bestPoints.get(j);
                int timeTaken = timeTakenByPoint.get(i);

                System.out.println("(" + point[0] + ", " + point[1] + ") - Time Taken: " + timeTaken);
            }

            clusterIndex++;
        }
    }

    private static List<List<int[]>> findClusters(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        List<List<int[]>> clusters = new ArrayList<>();
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 1 && !visited[i][j]) {
                    List<int[]> cluster = new ArrayList<>();
                    DFS(matrix, i, j, m, n, visited, cluster);
                    clusters.add(cluster);
                }
            }
        }

        return clusters;
    }

    public static void DFS(int[][] M, int i, int j, int ROW, int COL, boolean[][] visited, List<int[]> cluster) {
        if (i < 0 || j < 0 || i > ROW - 1 || j > COL - 1 || M[i][j] != 1 || visited[i][j]) {
            return;
        }

        visited[i][j] = true;
        cluster.add(new int[]{i, j});

        DFS(M, i + 1, j, ROW, COL, visited, cluster);
        DFS(M, i - 1, j, ROW, COL, visited, cluster);
        DFS(M, i, j + 1, ROW, COL, visited, cluster);
        DFS(M, i, j - 1, ROW, COL, visited, cluster);
        DFS(M, i + 1, j + 1, ROW, COL, visited, cluster);
        DFS(M, i - 1, j - 1, ROW, COL, visited, cluster);
        DFS(M, i + 1, j - 1, ROW, COL, visited, cluster);
        DFS(M, i - 1, j + 1, ROW, COL, visited, cluster);
    }

   private static int calculateTime(int m, int n, int[][] matrix, int X, int Y) {
    int min = 0, total = 0;
    boolean checked[][] = new boolean[m][n];
    Queue<Integer> q1 = new LinkedList<>();
    Queue<Integer> q2 = new LinkedList<>();
    q1.add(X);
    q2.add(Y);
    checked[X][Y] = true;
    int dx[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    int dy[] = {-1, 0, 1, -1, 1, -1, 0, 1};

    while (!q1.isEmpty() && !q2.isEmpty()) {
        int size = q1.size();

        for (int k = 0; k < size; k++) {
            int x1 = q1.remove();
            int y1 = q2.remove();

            if (matrix[x1][y1] == 1) {
                total++;
            }

            for (int i = 0; i < 8; i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];

                if (nx >= 0 && nx < m && ny >= 0 && ny < n && matrix[nx][ny] == 1 && !checked[nx][ny]) {
                    q1.add(nx);
                    q2.add(ny);
                    checked[nx][ny] = true;
                }
            }
        }

        if (total == m * n) {
            break;
        }

        if (size > 0) {
            min++;
        }
    }

    if (!checked[X][Y]) {
        return Integer.MAX_VALUE; // Return a large value to indicate that the initial point is unreachable
    }

    return min-1;
}
}
