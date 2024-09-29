import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int answer = 0;
    static int[][] graph;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
            }
        }
        simulation(0,graph);
        System.out.println(answer);
    }

    static void simulation(int depth, int[][] targetGraph) {
        if(depth == 5) {
            checkMaximumBlock(targetGraph);
            return;
        }
        for(int i=0; i<4; i++) {
            int[][] copyGraph = copyGraph(targetGraph);
            move(copyGraph, new int[N][N], i);
            simulation(depth+1, copyGraph);
        }
    }

    static void move(int[][] targetGraph, int[][] targetVisited, int direction) {
        if(direction == 0) {    // 위로 이동
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(targetGraph[j][i] == 0) {
                        continue;
                    }
                    int x = j;
                    while(true) {
                        int nx = x + dx[direction];
                        if(nx == -1) {
                            break;
                        }
                        if(targetGraph[nx][i] != 0) {
                            if(targetVisited[nx][i]==1 || targetGraph[nx][i]!=targetGraph[x][i]) {
                                break;
                            } else {
                                targetGraph[nx][i] = targetGraph[x][i]*2;
                                targetVisited[nx][i] = 1;
                                targetGraph[x][i] = 0;
                                targetVisited[x][i] = 0;
                                break;
                            }
                        }
                        targetGraph[nx][i] = targetGraph[x][i];
                        targetVisited[nx][i] = targetVisited[x][i];
                        targetGraph[x][i] = 0;
                        targetVisited[x][i] = 0;
                        x = nx;
                    }
                }
            }
        } else if(direction == 1) {
            for(int i=0; i<N; i++) {
                for(int j=N-1; j>=0; j--) {
                    if(targetGraph[j][i] == 0) {
                        continue;
                    }
                    int x = j;
                    while(true) {
                        int nx = x + dx[direction];
                        if(nx == N) {
                            break;
                        }
                        if(targetGraph[nx][i] != 0) {
                            if(targetVisited[nx][i]==1 || targetGraph[nx][i]!=targetGraph[x][i]) {
                                break;
                            } else {
                                targetGraph[nx][i] = targetGraph[x][i]*2;
                                targetVisited[nx][i] = 1;
                                targetGraph[x][i] = 0;
                                targetVisited[x][i] = 0;
                                break;
                            }
                        }
                        targetGraph[nx][i] = targetGraph[x][i];
                        targetVisited[nx][i] = targetVisited[x][i];
                        targetGraph[x][i] = 0;
                        targetVisited[x][i] = 0;
                        x = nx;
                    }
                }
            }
        } else if(direction == 2) {
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(targetGraph[i][j] == 0) {
                        continue;
                    }
                    int y = j;
                    while(true) {
                        int ny = y + dy[direction];
                        if(ny == -1) {
                            break;
                        }
                        if(targetGraph[i][ny] != 0) {
                            if(targetVisited[i][ny]==1 || targetGraph[i][ny]!=targetGraph[i][y]) {
                                break;
                            } else {
                                targetGraph[i][ny] = targetGraph[i][y]*2;
                                targetVisited[i][ny] = 1;
                                targetGraph[i][y] = 0;
                                targetVisited[i][y] = 0;
                                break;
                            }
                        }
                        targetGraph[i][ny] = targetGraph[i][y];
                        targetVisited[i][ny] = targetVisited[i][y];
                        targetGraph[i][y] = 0;
                        targetVisited[i][y] = 0;
                        y = ny;
                    }
                }
            }
        } else {
            for(int i=0; i<N; i++) {
                for(int j=N-1; j>=0; j--) {
                    if(targetGraph[i][j] == 0) {
                        continue;
                    }
                    int y = j;
                    while(true) {
                        int ny = y + dy[direction];
                        if(ny == N) {
                            break;
                        }
                        if(targetGraph[i][ny] != 0) {
                            if(targetVisited[i][ny]==1 || targetGraph[i][ny]!=targetGraph[i][y]) {
                                break;
                            } else {
                                targetGraph[i][ny] = targetGraph[i][y]*2;
                                targetVisited[i][ny] = 1;
                                targetGraph[i][y] = 0;
                                targetVisited[i][y] = 0;
                                break;
                            }
                        }
                        targetGraph[i][ny] = targetGraph[i][y];
                        targetVisited[i][ny] = targetVisited[i][y];
                        targetGraph[i][y] = 0;
                        targetVisited[i][y] = 0;
                        y = ny;
                    }
                }
            }
        }
    }

    static void checkMaximumBlock(int[][] targetGraph) {
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                answer = Math.max(answer, targetGraph[i][j]);
            }
        }
    }

    static int[][] copyGraph(int[][] targetGraph) {
        int[][] copyGraph = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                copyGraph[i][j] = targetGraph[i][j];
            }
        }
        return copyGraph;
    }
}