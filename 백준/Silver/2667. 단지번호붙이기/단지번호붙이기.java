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
    
    static int N;
    static int[][] arr,visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static List<Integer> count = new ArrayList<>();
    static LinkedList<Node> queue = new LinkedList<>();
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new int[N][N];
        
        for(int i=0; i<N; i++) {
            String info = br.readLine();
            for(int j=0; j<N; j++) {
                arr[i][j] = Character.getNumericValue(info.charAt(j));
            }
        }
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(visited[i][j]==0 && arr[i][j]==1) {
                    count.add(bfs(i,j));
                }
            }
        }
        
        Collections.sort(count);
        
        System.out.println(count.size());
        for(int i=0; i<count.size(); i++) {
            System.out.println(count.get(i));
        }
    }
    
    static int bfs(int x, int y) {
        queue.offer(new Node(x,y));
        visited[x][y] = 1;
        int count = 1;
        
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = currentNode.x + dx[i];
                int ny = currentNode.y + dy[i];
                
                if(0<=nx && nx<N && 0<=ny && ny<N && visited[nx][ny]==0 && arr[nx][ny]==1) {
                    queue.offer(new Node(nx,ny));
                    visited[nx][ny] = 1;
                    count+=1;
                }
            }
        }
        
        return count;
    }
}