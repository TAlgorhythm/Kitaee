import java.io.*;
import java.util.*;

public class Main {

    // 4 = 3+1
    // 4 = 2+2

    static int T;
    static int[] dp = new int[11];
    static int[] answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        answer = new int[T];
        setDp();
        for(int i=0; i<T; i++) {
            answer[i] = dp[Integer.parseInt(br.readLine())];
        }
        for(int i=0; i<T; i++) {
            System.out.println(answer[i]);
        }
    }

    static void setDp() {
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i=4; i<=10; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
    }
}