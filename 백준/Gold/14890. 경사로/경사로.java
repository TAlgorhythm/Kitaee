import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static int N,L;
    static String[] info;
    static int[][] graph;
    static int[] visited;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        L = Integer.parseInt(info[1]);
        graph = new int[N][N];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
            }
        }

        for(int i=0; i<N; i++) {
            if(checkHorizontal(i)) answer+=1;
            if(checkVertical(i)) answer+=1;
        }

        System.out.println(answer);
    }

    static boolean checkHorizontal(int x) {
        visited = new int[N];
        for(int i=0; i<N-1; i++) {
            int height = Math.abs(graph[x][i]-graph[x][i+1]);
            if(height > 1) {
                return false;
            } else if(height == 0) {
                continue;
            } else {
                if(graph[x][i] > graph[x][i+1]) {   // -> 방향으로 경사로 설치
                    for(int j=i+1; j<i+1+L; j++) {
                        if(0<=j && j<N && visited[j]==0 && graph[x][i+1]==graph[x][j]) {
                            visited[j] = 1;
                        } else {
                            return false;
                        }
                    }
                } else {    // <- 방향으로 경사로 설치
                    for(int j=i; j>i-L; j--) {
                        if(0<=j && j<N && visited[j]==0 && graph[x][i]==graph[x][j]) {
                            visited[j] = 1;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    static boolean checkVertical(int y) {
        visited = new int[N];
        for(int i=0; i<N-1; i++) {
            int height = Math.abs(graph[i][y]-graph[i+1][y]);
            if(height > 1) {
                return false;
            } else if(height == 0) {
                continue;
            } else {
                if(graph[i][y] > graph[i+1][y]) {   // -> 방향으로 경사로 설치
                    for(int j=i+1; j<i+1+L; j++) {
                        if(0<=j && j<N && visited[j]==0 && graph[i+1][y]==graph[j][y]) {
                            visited[j] = 1;
                        } else {
                            return false;
                        }
                    }
                } else {    // <- 방향으로 경사로 설치
                    for(int j=i; j>i-L; j--) {
                        if(0<=j && j<N && visited[j]==0 && graph[i][y]==graph[j][y]) {
                            visited[j] = 1;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}