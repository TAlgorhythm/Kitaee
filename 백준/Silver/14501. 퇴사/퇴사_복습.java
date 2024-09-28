import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] time;
    static int[] cost;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N];
        cost = new int[N];
        dp = new int[N+1];

        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            time[i] = Integer.parseInt(info[0]);
            cost[i] = Integer.parseInt(info[1]);
        }

        for(int i=0; i<N; i++) {
            if(i+time[i] <= N) {
                dp[i+time[i]] = Math.max(dp[i+time[i]], dp[i]+cost[i]);
            }
            dp[i+1] = Math.max(dp[i], dp[i+1]);
        }

        System.out.println(dp[N]);
    }
}
