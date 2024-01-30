import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static int MIN = 100;
    static int MAX = 2;
    static int answer = 1;
    static int[][] arr,visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static LinkedList<int[]> queue;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        
        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                int num = Integer.parseInt(info[j]);
                if(num < MIN) {
                    MIN = num;
                }
                if(num > MAX) {
                    MAX = num;
                }
                arr[i][j] = num;
            }
        }
    
        for(int height=MIN; height<=MAX; height++) {
            visited = new int[N][N];
            int count = 0;
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(visited[i][j]==0 && arr[i][j]>height) {
                        bfs(i,j,height);
                        count+=1;
                    }
                }
            }
            answer = Math.max(answer, count);
        }
        System.out.println(answer);
    }
    
    
    static void bfs(int x, int y, int height) {
        visited[x][y] = 1;
        queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        while(!queue.isEmpty()) {
            int[] current = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];
                
                if(0<=nx && nx<N && 0<=ny && ny<N && visited[nx][ny]==0 && arr[nx][ny]>height) {
                    visited[nx][ny] = 1;
                    queue.offer(new int[]{nx,ny});
                }
            }
        }
    }
}