import java.io.*;
import java.util.*;

public class Main {
    
    static int N,M;
    static int[][] maze, visited;
    static LinkedList<Node> queue = new LinkedList<>();
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    
    static class Node {
        int x;
        int y;
        int count;
        
        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        
        maze = new int[N][M];
        visited = new int[N][M];
        
        for(int i=0; i<N; i++) {
            String inputMaze = br.readLine();
            for(int j=0; j<M; j++) {
                maze[i][j] = Character.getNumericValue(inputMaze.charAt(j));
            }
        }
        
        queue.offer(new Node(0,0,1));
        visited[0][0] = 1;
        
        System.out.println(bfs());
    }
    
    static int bfs() {
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if(currentNode.x == N-1 && currentNode.y == M-1) {
                return currentNode.count;
            }
            
            for(int i=0; i<4; i++) {
                int nx = currentNode.x + dx[i];
                int ny = currentNode.y + dy[i];
                
                if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny] == 0 && maze[nx][ny]==1) {
                    queue.offer(new Node(nx,ny,currentNode.count+1));
                    visited[nx][ny] = 1;
                }
            }
        }
        return -1;
    }
}
