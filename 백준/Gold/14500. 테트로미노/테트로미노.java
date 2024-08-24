import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int[][] paper;
    static int[][] visited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int answer = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        paper = new int[N][M];
        visited = new int[N][M];
        
        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                paper[i][j] = Integer.parseInt(info[j]);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(visited[i][j] == 0) {
                    visited[i][j] = 1;
                    dfs(1, paper[i][j], i, j);
                    visited[i][j] = 0;
                }
                leftCheck(i,j);
            }
        }
        
        System.out.println(answer);
    }

    static void dfs(int depth, int sum, int x, int y) {
        if(depth == 4) {
            answer = Math.max(answer, sum);
            return;
        }
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny]==0) {
                visited[nx][ny] = 1;
                dfs(depth+1, sum+paper[nx][ny], nx, ny);
                visited[nx][ny] = 0;
            }
        }
    }

    static void leftCheck(int x, int y) {
        if(x+1<N && y+2<M) {   
            answer = Math.max(answer, paper[x][y]+paper[x][y+1]+paper[x][y+2]+paper[x+1][y+1]);
        }
        if(x+2<N && y+1<M) {   
            answer = Math.max(answer, paper[x][y]+paper[x+1][y]+paper[x+2][y]+paper[x+1][y+1]);
        }
        if(x+1<N && y-1>=0 && y+1<M) {
            answer = Math.max(answer, paper[x][y]+paper[x+1][y-1]+paper[x+1][y]+paper[x+1][y+1]);
        }
        if(x+2<N && y-1>=0) {
            answer = Math.max(answer, paper[x][y]+paper[x+1][y]+paper[x+2][y]+paper[x+1][y-1]);
        }
    }
}