import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] card;
    static int[] dp;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N+1];
        card = new int[N+1];
        String[] info = br.readLine().split(" ");
        for(int i=1; i<=N; i++) {
            card[i] = Integer.parseInt(info[i-1]);
        }

        for(int i=1; i<=N; i++) {
            for(int j=1; j<=i; j++) {
                dp[i] = Math.max(dp[i-j] + card[j], dp[i]);
            }
        }
        System.out.println(dp[N]);
    }
}