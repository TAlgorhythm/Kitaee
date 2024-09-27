import java.io.*;
import java.util.*;

public class Main {

    static int N,M;
    static int[][] graph;
    static int[][] dp;
    static String[] info;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new int[N][M];
        dp = new int[N][M];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
            }
        }

        dp[0][0] = graph[0][0];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(i==0 && j==0) {
                    continue;
                } else if(i==0) {
                    dp[i][j] = graph[i][j] + dp[i][j-1];
                } else if(j==0) {
                    dp[i][j] = graph[i][j] + dp[i-1][j];
                } else {
                    dp[i][j] =Math.max(dp[i-1][j], dp[i][j-1]);
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]);
                    dp[i][j] += graph[i][j];
                }
            }
        }
        System.out.println(dp[N-1][M-1]);
    }
}