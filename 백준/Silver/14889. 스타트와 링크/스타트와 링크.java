import java.io.*;
import java.util.*;

public class Main {

    static int answer = Integer.MAX_VALUE;
    static int N;
    static int[][] arr;
    static String[] info;
    static int[] team;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                arr[i][j] = Integer.parseInt(info[j]);
            }
        }

        team = new int[N];
        combination(0, 0);
        System.out.println(answer);
    }

    static void combination(int depth, int start) {
        if(depth == N/2) {
            answer = Math.min(Math.abs(calculate(1) - calculate(0)), answer);
            return;
        }
        for(int i=start; i<team.length; i++) {
            if(team[i] == 0) {
                team[i] = 1;
                combination(depth+1, i+1);
                team[i] = 0;
            }
        }
    }

    static int calculate(int target) {
        int count = 0;
        for(int i=0; i<N-1; i++) {
            for(int j=i+1; j<N; j++) {
                if(team[i]==target && team[j]==target) {
                    count+=arr[i][j];
                    count+=arr[j][i];
                }
            }
        }
        return count;
    }
}