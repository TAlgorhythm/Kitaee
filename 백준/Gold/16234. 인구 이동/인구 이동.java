import java.io.*;
import java.util.*;

public class Main {

    static int N,L,R;
    static int[][] graph;
    static int[][] visited;
    static int answer = 0;
    static int sum = 0;
    static Boolean isContinue = false;
    static LinkedList<int[]> queue;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};
    static List<int[]> unionList;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        L = Integer.parseInt(info[1]);
        R = Integer.parseInt(info[2]);
        graph = new int[N][N];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
            }
        }

        while(true) {
            isContinue = false;
            visited = new int[N][N];
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(visited[i][j] == 0) {
                        unionList = new ArrayList<>();
                        sum = 0;
                        unionList.add(new int[]{i,j});
                        bfs(i, j);
                        if(unionList.size() > 1) {
                            isContinue = true;
                            for(int k=0; k<unionList.size(); k++) {
                                int[] union = unionList.get(k);
                                graph[union[0]][union[1]] = sum / unionList.size();
                            }
                        }
                    }
                }
            }
            if(isContinue) {
                answer += 1;
            } else {
                break;
            }
        }
        System.out.println(answer);
    }

    static void bfs(int x, int y) {
        visited[x][y] = 1;
        queue = new LinkedList<>();
        queue.offer(new int[]{x,y});
        sum += graph[x][y];

        while(!queue.isEmpty()) {
            int[] node = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = node[0] + dx[i];
                int ny = node[1] + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<N && visited[nx][ny]==0 && Math.abs(graph[nx][ny]-graph[node[0]][node[1]])>=L && Math.abs(graph[nx][ny]-graph[node[0]][node[1]])<=R) {
                    visited[nx][ny] = 1;
                    queue.offer(new int[]{nx,ny});
                    unionList.add(new int[]{nx,ny});
                    sum += graph[nx][ny];
                }
            }
        }
    }
}