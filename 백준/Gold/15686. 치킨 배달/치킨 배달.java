import java.io.*;
import java.util.*;

public class Main {

    // input
    static int N,M;
    
    // logic
    static int answer = Integer.MAX_VALUE;
    static int[][] graph;
    static List<int[]> chickenIndex = new ArrayList<>();
    static List<int[]> homeIndex = new ArrayList<>();
    
    // combination
    static int[] visited;
    static int[] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new int[N][N];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
                if(graph[i][j] == 1) {
                    homeIndex.add(new int[]{i,j});
                } else if(graph[i][j] == 2) {
                    chickenIndex.add(new int[]{i,j});
                }
            }
        }

        result = new int[M];
        arr = new int[chickenIndex.size()];
        visited = new int[chickenIndex.size()];
        for(int i=0; i<arr.length; i++) {
            arr[i] = i;
        }

        combination(0, 0);

        System.out.println(answer);
    }

    static void setChicken() {
        int chickenDistance = 0;
        for(int[] home : homeIndex) {
            int distance = Integer.MAX_VALUE;
            for(int i=0; i<result.length; i++) {
                int[] chicken = chickenIndex.get(result[i]);
                distance = Math.min(distance, getDistance(home[0], home[1], chicken[0], chicken[1]));
            }
            chickenDistance += distance;
        }
        answer = Math.min(answer, chickenDistance);
    }

    static void combination(int depth, int start) {
        if(depth == M) {
            setChicken();
            return;
        }
        for(int i=start; i<arr.length; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                result[depth] = arr[i];
                combination(depth+1, i+1);
                visited[i] = 0;
            }
        }
    }

    static int getDistance(int x1, int y1, int x2, int y2) {
        return (Math.abs(x1-x2) + Math.abs(y1-y2));
    }
}