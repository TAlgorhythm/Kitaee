import java.io.*;
import java.util.*;

public class Main {
    
    static int N;
    static int[][] arr;
    static int[] dp;
    static int answer = -1;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N+1][2];
        dp = new int[N+1];
        
        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(info[0]);
            arr[i][1] = Integer.parseInt(info[1]);
        }
        
        for(int i=0; i<N+1; i++) {
            answer = Math.max(answer, dp[i]);
            dp[i] = answer;
            if(arr[i][0]+i < N+1) {
                dp[arr[i][0]+i] = Math.max(dp[arr[i][0]+i], arr[i][1]+dp[i]);   
            }
        }
        
        System.out.println(answer);
    }
}