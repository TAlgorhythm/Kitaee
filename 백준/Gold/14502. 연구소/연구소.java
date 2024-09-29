import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static int N,M;
    static int[][] graph;
    static String[] info;
    static List<int[]> safeAreaTarget = new ArrayList<>();
    static int[] arr;
    static int[] visited;
    static int[] result;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static LinkedList<int[]> queue;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new int[N][M];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
                if(graph[i][j] == 0) {
                    safeAreaTarget.add(new int[]{i,j});
                }
            }
        }

        arr = new int[safeAreaTarget.size()];
        visited = new int[safeAreaTarget.size()];
        result = new int[3];
        for(int i=0; i<arr.length; i++) {
            arr[i] = i;
        }
        dfs(0,0);
        System.out.println(answer);
    }

    static void simulation(int[][] graph, int[][] visited) {
        queue = new LinkedList<>();
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(graph[i][j] == 2) {
                    queue.offer(new int[]{i,j});
                }
            }
        }
        while(!queue.isEmpty()) {
            int[] virus = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = virus[0] + dx[i];
                int ny = virus[1] + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny]==0 && graph[nx][ny]!=1) {
                    visited[nx][ny] = 1;
                    graph[nx][ny] = 2;
                    queue.offer(new int[]{nx,ny});
                }
            }
        }
        checkSafeArea(graph);
    }

    static void dfs(int depth, int start) {
        if(depth == 3) {
            int[][] copyGraph = copyGraph(graph);
            for(int i=0; i<3; i++) {
                int[] wall = safeAreaTarget.get(result[i]);
                copyGraph[wall[0]][wall[1]] = 1;
            }
            simulation(copyGraph, new int[N][M]);
            return;
        }
        for(int i=start; i<arr.length; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                result[depth] = arr[i];
                dfs(depth+1, i+1);
                visited[i] = 0;
            }
        }
    }

    static int[][] copyGraph(int[][] graph) {
        int[][] copyGraph = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                copyGraph[i][j] = graph[i][j];
            }
        }
        return copyGraph;
    }

    static void checkSafeArea(int[][] targetGraph) {
        int count = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(targetGraph[i][j] == 0) {
                    count+=1;
                }
            }
        }
        answer = Math.max(answer, count);
    }
}