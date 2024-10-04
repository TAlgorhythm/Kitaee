import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] graph;
    static String[] info;
    static long[][] dp;
    static int[] dx = {0,1};
    static int[] dy = {1,0};

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        dp = new long[N][N];
        dp[0][0] = 1;

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
            }
        }

        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                if(i==N-1 && j==N-1) continue;
                for(int direction=0; direction<2; direction++) {
                    int nx = i + dx[direction]*graph[i][j];
                    int ny = j + dy[direction]*graph[i][j];
                    if(0<=nx && nx<N && 0<=ny && ny<N) {
                        dp[nx][ny] += dp[i][j];
                    }
                }
            }
        }
        System.out.println(dp[N-1][N-1]);
    }
}