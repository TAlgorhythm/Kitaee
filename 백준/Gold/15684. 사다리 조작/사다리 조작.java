import java.io.*;
import java.util.*;

public class Main {

    static int answer = 1;
    static boolean flag = false;
    static int N,M,H;
    static int[][] graph;
    static List<int[]> targetList = new ArrayList<>();
    static int[] arr;
    static int[] result;
    static int[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        H = Integer.parseInt(info[2]);
        graph = new int[H][N];

        for(int i=0; i<M; i++) {
            info = br.readLine().split(" ");
            int a = Integer.parseInt(info[0]);
            int b = Integer.parseInt(info[1]);
            graph[a-1][b-1] = 1;
        }

        for(int i=0; i<H; i++) {
            for(int j=0; j<N-1; j++) {
                if(j==0) {
                    if(graph[i][j]==0 && graph[i][j+1]==0) {
                        targetList.add(new int[]{i,j});
                    }
                } else {
                    if(graph[i][j-1]==0 && graph[i][j]==0) {
                        targetList.add(new int[]{i,j});
                    }
                }
            }
        }

        if(isSolved(graph)) {
            System.out.println(0);
            return;
        }

        arr = new int[targetList.size()];
        visited = new int[targetList.size()];
        for(int i=0; i<arr.length; i++) {
            arr[i] = i;
        }

        while(answer <= 3) {
            result = new int[answer];
            combination(0,0);
            if(flag) {
                System.out.println(answer);
                return;
            }
            answer += 1;
        }
        System.out.println(-1);
    }

    static void combination(int depth, int start) {
        if(depth == answer) {
            int[][] ladder = copyGraph(graph);
            for(int i=0; i<result.length; i++) {
                int[] line = targetList.get(result[i]);
                ladder[line[0]][line[1]] = 1;
            }
            if(isSolved(ladder)) {
                flag = true;
            }
            return;
        }
        for(int i=start; i<arr.length; i++) {
            if(visited[i] == 0) {
                visited[i] = 1;
                result[depth] = arr[i];
                combination(depth+1, i+1);
                visited[i] = 0;
            }
        }
    }

    static int[][] copyGraph(int[][] graph) {
        int[][] copyGraph = new int[H][N];
        for(int i=0; i<H; i++) {
            for(int j=0; j<N; j++) {
                copyGraph[i][j] = graph[i][j];
            }
        }
        return copyGraph;
    }

    static boolean isSolved(int[][] ladder) {
        for(int i=0; i<N; i++) {
            if(simulation(ladder, i) != i) {
                return false;
            }
        }
        return true;
    }

    static int simulation(int[][] ladder, int num) {
        for(int line=0; line<H; line++) {
            if(ladder[line][num] == 1) {
                num+=1;
            } else {
                if(num>0 && ladder[line][num-1]==1) {
                    num-=1;
                }
            }
        }
        return num;
    }
}