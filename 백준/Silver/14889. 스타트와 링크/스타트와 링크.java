import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] graph;
    static int answer = Integer.MAX_VALUE;
    static int[] visited;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        visited = new int[N];

        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
            }
        }

        combination(0,0);

        System.out.println(answer);
    }

    static void combination(int depth, int start) {
        if(depth == N/2) {
            calculate();
            return;
        }
        for(int i=start; i<N; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                combination(depth+1, i+1);
                visited[i] = 0;
            }
        }
    }

    static void calculate() {
        int team1 = 0;
        int team2 = 0;

        for(int i=0; i<N-1; i++) {
            for(int j=i+1; j<N; j++) {
                if(visited[i] == 1 && visited[j] == 1) {
                    team1 += graph[i][j];
                    team1 += graph[j][i];
                }
                else if(visited[i] == 0 && visited[j] == 0) {
                    team2 += graph[i][j];
                    team2 += graph[j][i];
                }
            }
        }

        answer = Math.min(Math.abs(team1-team2), answer);
    }
}