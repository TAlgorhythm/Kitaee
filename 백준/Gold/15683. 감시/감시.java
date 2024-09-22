import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x;
        int y;
        int num;

        public Node(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }
    }

    static int N,M;
    static int[][] graph;
    static int answer = Integer.MAX_VALUE;
    static List<Node> cctvList = new ArrayList<>();

    public static void main (String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new int[N][M];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
                if(graph[i][j]!=0 && graph[i][j]!=6) {
                    cctvList.add(new Node(i, j, graph[i][j]));
                }
            }
        }

        dfs(0, graph);
        System.out.println(answer);
    }

    static void dfs(int depth, int[][] targetGraph) {
        if(depth == cctvList.size()) {
            checkArea(targetGraph);
            return;
        }
        Node cctv = cctvList.get(depth);
        if(cctv.num == 1) {
            int[][] copyGraph = copyGraph(targetGraph);
            checkTop(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);

            copyGraph = copyGraph(targetGraph);
            checkRight(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);

            copyGraph = copyGraph(targetGraph);
            checkBottom(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);

            copyGraph = copyGraph(targetGraph);
            checkLeft(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);
        } else if(cctv.num == 2) {
            int[][] copyGraph = copyGraph(targetGraph);
            checkTop(cctv.x, cctv.y, copyGraph);
            checkBottom(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);

            copyGraph = copyGraph(targetGraph);
            checkLeft(cctv.x, cctv.y, copyGraph);
            checkRight(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);
        } else if(cctv.num == 3) {
            int[][] copyGraph = copyGraph(targetGraph);
            checkTop(cctv.x, cctv.y, copyGraph);
            checkRight(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);

            copyGraph = copyGraph(targetGraph);
            checkTop(cctv.x, cctv.y, copyGraph);
            checkLeft(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);

            copyGraph = copyGraph(targetGraph);
            checkBottom(cctv.x, cctv.y, copyGraph);
            checkLeft(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);

            copyGraph = copyGraph(targetGraph);
            checkBottom(cctv.x, cctv.y, copyGraph);
            checkRight(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);
        } else if(cctv.num == 4) {
            int[][] copyGraph = copyGraph(targetGraph);
            checkRight(cctv.x, cctv.y, copyGraph);
            checkBottom(cctv.x, cctv.y, copyGraph);
            checkLeft(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);

            copyGraph = copyGraph(targetGraph);
            checkTop(cctv.x, cctv.y, copyGraph);
            checkBottom(cctv.x, cctv.y, copyGraph);
            checkLeft(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);

            copyGraph = copyGraph(targetGraph);
            checkTop(cctv.x, cctv.y, copyGraph);
            checkRight(cctv.x, cctv.y, copyGraph);
            checkLeft(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);

            copyGraph = copyGraph(targetGraph);
            checkTop(cctv.x, cctv.y, copyGraph);
            checkRight(cctv.x, cctv.y, copyGraph);
            checkBottom(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);
        } else {
            int[][] copyGraph = copyGraph(targetGraph);
            checkTop(cctv.x, cctv.y, copyGraph);
            checkRight(cctv.x, cctv.y, copyGraph);
            checkBottom(cctv.x, cctv.y, copyGraph);
            checkLeft(cctv.x, cctv.y, copyGraph);
            dfs(depth + 1, copyGraph);
        }
    }

    static void checkArea(int[][] targetGraph) {
        int count = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(targetGraph[i][j] == 0) {
                    count+=1;
                }
            }
        }
        answer = Math.min(answer, count);
    }

    static int[][] checkTop(int x, int y, int[][] targetGraph) {
        int index = 1;
        while(x-index >= 0) {
            if(targetGraph[x-index][y] == 6) {
                break;
            } else {
                targetGraph[x-index][y] = -1;
                index+=1;
            }
        }
        return targetGraph;
    }

    static int[][] checkBottom(int x, int y, int[][] targetGraph) {
        int index = 1;
        while(x+index < N) {
            if(targetGraph[x+index][y] == 6) {
                break;
            } else {
                targetGraph[x+index][y] = -1;
                index+=1;
            }
        }
        return targetGraph;
    }

    static int[][] checkLeft(int x, int y, int[][] targetGraph) {
        int index = 1;
        while(y-index >= 0) {
            if(targetGraph[x][y-index] == 6) {
                break;
            } else {
                targetGraph[x][y-index] = -1;
                index+=1;
            }
        }
        return targetGraph;
    }

    static int[][] checkRight(int x, int y, int[][] targetGraph) {
        int index = 1;
        while(y+index < M) {
            if(targetGraph[x][y+index] == 6) {
                break;
            } else {
                targetGraph[x][y+index] = -1;
                index+=1;
            }
        }
        return targetGraph;
    }

    static int[][] copyGraph(int[][] targetGraph) {
        int[][] copyGraph = new int[N][M];
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                copyGraph[i][j] = targetGraph[i][j];
            }
        }
        return copyGraph;
    }
}