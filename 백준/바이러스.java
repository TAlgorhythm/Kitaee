import java.io.*;
import java.util.*;

public class Main {
    
    static int N,M,count;
    static int[] visited;
    static int[][] graph;
    static LinkedList<Integer> queue = new LinkedList<>();
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        count = 0;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new int[N+1][N+1];
        visited = new int[N+1];
        
        
        for(int i=0; i<M; i++) {
            String[] info = br.readLine().split(" ");
            int v1 = Integer.parseInt(info[0]);
            int v2 = Integer.parseInt(info[1]);
            graph[v1][v2] = graph[v2][v1] = 1;
        }
        
        queue.offer(1);
        visited[1] = 1;
        bfs();
        
        System.out.print(count);
    }
    
    static void bfs() {
        while(!queue.isEmpty()) {
            int currentId = queue.poll();
            for(int i=1; i<N+1; i++) {
                if(graph[currentId][i] == 1 && visited[i] == 0) {
                    queue.offer(i);
                    count+=1;
                    visited[i] = 1;        
                }
            }
        }
    }
}
