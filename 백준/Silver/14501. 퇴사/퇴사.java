import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] price;
    static int[] time;
    static int[] dp;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        price = new int[N];
        time = new int[N];
        dp = new int[N+1];

        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            time[i] = Integer.parseInt(info[0]);
            price[i] = Integer.parseInt(info[1]);
        }

        for(int i=0; i<N; i++) {
            if(i+time[i] <= N) {
                dp[i+time[i]] = Math.max(dp[i+time[i]], dp[i]+price[i]);
            }
            dp[i+1] = Math.max(dp[i+1], dp[i]);
        }

        System.out.println(dp[N]);
    }
}