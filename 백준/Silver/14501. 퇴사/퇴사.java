import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[] time;
    static int[] cost;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        time = new int[N];
        cost = new int[N];

        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            time[i] = Integer.parseInt(info[0]);
            cost[i] = Integer.parseInt(info[1]);
        }

        dfs(0,0);
        System.out.println(answer);
    }

    static void dfs(int depth, int totalCost) {
        if(depth > N) {
            return;
        } else if(depth == N) {
            answer = Math.max(answer, totalCost);
            return;
        } else {
            dfs(depth+time[depth], totalCost+cost[depth]);
            dfs(depth + 1, totalCost);
        }
    }
}