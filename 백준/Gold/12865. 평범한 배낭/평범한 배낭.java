import java.io.*;
import java.util.*;

public class Main {

    static int N,K;
    static String[] info;
    static int[] W;
    static int[] V;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        K = Integer.parseInt(info[1]);
        W = new int[N+1];
        V = new int[N+1];
        dp = new int[N+1][K+1];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            W[i+1] = Integer.parseInt(info[0]);
            V[i+1] = Integer.parseInt(info[1]);
        }

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=K; j++) {
                dp[i][j] = dp[i-1][j];
                if(j-W[i] >= 0) {
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-W[i]] + V[i]);
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}