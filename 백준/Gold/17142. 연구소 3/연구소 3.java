import java.io.*;
import java.util.*;

public class Main {

    static class Virus {
        int x;
        int y;
        int count;

        public Virus(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    static int N,M;
    static int space = 0;
    static int answer = Integer.MAX_VALUE;
    static int[][] graph;
    static int[][] visited;
    static List<int[]> virus = new ArrayList<>();
    static int[] result;
    static int[] arr;
    static int[] arrVisited;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static LinkedList<Virus> virusList;

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new int[N][N];
        result = new int[M];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
                if(graph[i][j] == 2) {
                    virus.add(new int[]{i,j});
                } else if(graph[i][j] == 0) {
                    space+=1;
                }
            }
        }

        if(space == 0) {
            System.out.println(0);
            return;
        }

        arr = new int[virus.size()];
        arrVisited = new int[virus.size()];
        for(int i=0; i<virus.size(); i++) {
            arr[i] = i;
        }

        combination(0,0);
        if(answer == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }

    static void simulation(int[] result, int empty) {
        int[][] copyGraph = copyGraph();
        visited = new int[N][N];
        virusList = new LinkedList<>();
        for(int i=0; i<result.length; i++) {
            int[] activeVirus = virus.get(result[i]);
            copyGraph[activeVirus[0]][activeVirus[1]] = -1;
            virusList.offer(new Virus(activeVirus[0], activeVirus[1], 0));
        }

        while(!virusList.isEmpty()) {
            Virus activeVirus = virusList.poll();
            for(int i=0; i<4; i++) {
                int nx = activeVirus.x + dx[i];
                int ny = activeVirus.y + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<N && visited[nx][ny]==0 && copyGraph[nx][ny]!=1) {
                    visited[nx][ny] = 1;
                    if(copyGraph[nx][ny]==0) {
                        empty-=1;
                    }
                    if(empty == 0) {
                        answer = Math.min(answer, activeVirus.count+1);
                        return;
                    }
                    virusList.offer(new Virus(nx,ny,activeVirus.count+1));
                }
            }
        }
    }

    static void combination(int depth, int start) {
        if(depth == M) {
            simulation(result, space);
            return;
        }
        for(int i=start; i<arr.length; i++) {
            if(arrVisited[i] == 0) {
                arrVisited[i] = 1;
                result[depth] = arr[i];
                combination(depth+1, i+1);
                arrVisited[i] = 0;
            }
        }
    }

    static int[][] copyGraph() {
        int[][] copyGraph = new int[N][N];
        for(int i=0; i<N; i++) {
            for(int j=0; j<N; j++) {
                copyGraph[i][j] = graph[i][j];
            }
        }
        return copyGraph;
    }
}