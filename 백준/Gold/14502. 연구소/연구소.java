import java.io.*;
import java.util.*;

public class Main {
    
    static class Node {
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    static int N,M;
    static int[][] graph;
    static int[][] cloneGraph;
    static List<String> wall = new ArrayList<>();
    static int[] visited;
    static int[] result = new int[3];
    static int[] arr;
    static int answer = 0;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static LinkedList<Node> queue;
    static int[][] graphVisited;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        int wallCount = 0;
        
        graph = new int[N][M];
        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
                if(graph[i][j] == 0) {
                    wall.add(String.valueOf(i) + String.valueOf(j));
                }
            }
        }
        
        visited = new int[wall.size()];
        arr = new int[wall.size()];
        for(int i=0; i<arr.length; i++) {
            arr[i] = i;
        }
        
        combination(0, 0);
        
        System.out.println(answer);
    }
    
    static void combination(int depth, int start) {
        if(depth == 3) {
            setWall();
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
    
    static void setWall() {
        graphVisited = new int[N][M];
        queue = new LinkedList<>();
        cloneGraph = new int[N][M];
        
        for(int i=0; i<N; i++) {
            cloneGraph[i] = Arrays.copyOf(graph[i], graph[i].length);
        }
        
        for(int i=0; i<3; i++) {
            String index = wall.get(result[i]);
            cloneGraph[Character.getNumericValue(index.charAt(0))][Character.getNumericValue(index.charAt(1))] = 1;
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(graphVisited[i][j] == 0 && cloneGraph[i][j] == 2) {
                    bfs(i,j);
                }
            }
        }
        getMaxSafeArea();
    }
    
    static void bfs(int x, int y) {
        queue.offer(new Node(x,y));
        graphVisited[x][y] = 1;
        
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                
                if(0<=nx && nx<N && 0<=ny && ny<M && graphVisited[nx][ny]==0 && cloneGraph[nx][ny]==0) {
                    cloneGraph[nx][ny] = 2;
                    graphVisited[nx][ny] = 1;
                    queue.offer(new Node(nx,ny));
                }
            }
        }
    }
    
    static void getMaxSafeArea() {
        int count = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(cloneGraph[i][j] == 0) {
                    count += 1;
                }
            }
        }
        answer = Math.max(answer, count);
    }
}