import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] cost;
    static int[][] dp;
    static int answer = 0;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        dp = new int[N][N];

        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            for(int j=0; j<3; j++) {
                cost[i][j] = Integer.parseInt(info[j]);
            }
        }

        dp[0][0] = cost[0][0];
        dp[0][1] = cost[0][1];
        dp[0][2] = cost[0][2];

        for(int i=1; i<N; i++) {
            dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + cost[i][0];
            dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + cost[i][1];
            dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + cost[i][2];
        }

        answer = Math.min(dp[N-1][0], dp[N-1][1]);
        answer = Math.min(answer, dp[N-1][2]);
        System.out.println(answer);
    }
}