import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int[][] graph;
    static int[][] visited;
    static String[] info;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new int[N][M];
        visited = new int[N][M];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                dfs(0,0,i,j);
                edgeCase(i,j);
            }
        }

        System.out.println(answer);
    }

    static void dfs(int depth, int count, int x, int y) {
        if(depth == 4) {
            answer = Math.max(answer, count);
            return;
        }
        for(int i=0; i<4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;
            if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny]==0) {
                visited[nx][ny]=1;
                dfs(depth+1, count+graph[nx][ny], nx, ny);
                visited[nx][ny]=0;
            }
        }
    }

    static void edgeCase(int x, int y) {
        // ㅜ, ㅓ, ㅗ, ㅏ (dfs로 안되는 모양)
        if(y+2<M && x+1<N) {    // ㅜ
            answer = Math.max(answer, graph[x][y]+graph[x][y+1]+graph[x][y+2]+graph[x+1][y+1]);
        }
        if(y-1>=0 && x+2<N) {   // ㅓ
            answer = Math.max(answer, graph[x][y]+graph[x+1][y]+graph[x+2][y]+graph[x+1][y-1]);
        }
        if(y-1>=0 && y+1<M && x+1<N) {  // ㅗ
            answer = Math.max(answer, graph[x][y]+graph[x+1][y-1]+graph[x+1][y]+graph[x+1][y+1]);
        }
        if(y+1<M && x+2<N) {    // ㅏ
            answer = Math.max(answer, graph[x][y]+graph[x+1][y]+graph[x+1][y+1]+graph[x+2][y]);
        }
    }
}
