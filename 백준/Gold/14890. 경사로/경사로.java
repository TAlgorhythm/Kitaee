import java.io.*;
import java.util.*;

public class Main {

    static int N,L;
    static int[][] graph;
    static int[][] visited;
    static int answer = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        L = Integer.parseInt(info[1]);
        graph = new int[N][N];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
            }
        }

        visited = new int[N][N];
        // 가로 탐색
        for(int i=0; i<N; i++) {
            int index = 1;
            boolean isPossible = true;
            while(true) {
                if(graph[i][index-1] == graph[i][index]) {
                    index+=1;
                } else if(Math.abs(graph[i][index-1]-graph[i][index])>1) {
                    isPossible = false;
                    break;
                } else if(graph[i][index-1] - graph[i][index] == 1) {   // 3 2 같은 상황 (-> 방향으로 경사로)
                    for(int j=index; j<index+L; j++) {
                        if(j>=N || visited[i][j]==1 || graph[i][index]!=graph[i][j]) {
                            isPossible = false;
                            break;
                        }
                        visited[i][j] = 1;
                    }
                    if(isPossible) {
                        index+=1;
                    } else {
                        break;
                    }
                } else { // 2 3 같은 상황 (<- 방향으로 경사로)
                    for(int j=index-1; j>index-1-L; j--) {
                        if(j<0 || visited[i][j]==1 || graph[i][index-1]!=graph[i][j]) {
                            isPossible = false;
                            break;
                        }
                        visited[i][j] = 1;
                    }
                    if(isPossible) {
                        index+=1;
                    } else {
                        break;
                    }
                }
                if(index == N) {
                    break;
                }
            }
            if(isPossible) {
                answer+=1;
            }
        }

        visited = new int[N][N];
        // 세로 탐색
        for(int i=0; i<N; i++) {
            int index = 1;
            boolean isPossible = true;
            while(true) {
                if(graph[index-1][i] == graph[index][i]) {
                    index+=1;
                } else if(Math.abs(graph[index-1][i]-graph[index][i])>1) {
                    isPossible = false;
                    break;
                } else if(graph[index-1][i] - graph[index][i] == 1) {   // 3 2 같은 상황 (-> 방향으로 경사로)
                    for(int j=index; j<index+L; j++) {
                        if(j>=N || visited[j][i]==1 || graph[index][i]!=graph[j][i]) {
                            isPossible = false;
                            break;
                        }
                        visited[j][i] = 1;
                    }
                    if(isPossible) {
                        index+=1;
                    } else {
                        break;
                    }
                } else { // 2 3 같은 상황 (<- 방향으로 경사로)
                    for(int j=index-1; j>index-1-L; j--) {
                        if(j<0 || visited[j][i]==1 || graph[index-1][i]!=graph[j][i]) {
                            isPossible = false;
                            break;
                        }
                        visited[j][i] = 1;
                    }
                    if(isPossible) {
                        index+=1;
                    } else {
                        break;
                    }
                }
                if(index == N) {
                    break;
                }
            }
            if(isPossible) {
                answer+=1;
            }
        }
        System.out.println(answer);
    }
}