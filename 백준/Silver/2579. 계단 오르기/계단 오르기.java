import java.io.*;
import java.util.*;

public class Main {

    // 한번에 한 계단씩 또는 두 계단씩
    // 연속된 3개의 계단을 밟으면 안된다.
    // 마지막 도착 계단은 반드시 밟아야 한다.

    static int N;
    static int[] stairs;
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        stairs = new int[N];
        dp = new int[N];

        for(int i=0; i<N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        if(N == 1) {
            System.out.println(stairs[0]);
            return;
        } else if(N == 2) {
            System.out.println(stairs[0] + stairs[1]);
            return;
        } else if(N == 3) {
            System.out.println(Math.max(stairs[0], stairs[1]) + stairs[2]);
            return;
        } else {
            dp[0] = stairs[0];
            dp[1] = dp[0] + stairs[1];
            dp[2] = Math.max(stairs[0], stairs[1]) + stairs[2];
            for(int i=3; i<N; i++) {
                dp[i] = Math.max(stairs[i-1]+dp[i-3], dp[i-2]) + stairs[i];
            }
        }
        System.out.println(dp[N-1]);
    }
}