// 공기 중에서 치즈가 모두 녹아 없어지는 데 걸리는 시간
// 모두 녹기 한 시간 전에 남아 있는 치조 조각이 놓여 있는 칸의 개수

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N,M;
    static int[][] graph;
    static List<Integer> answer = new ArrayList<>();
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[][] meltTarget;
    static int[][] visited;
    static LinkedList<Node> queue = new LinkedList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new int[N][M];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
            }
        }

        while(true) {
            int count = 0;
            meltTarget = new int[N][M];
            visited = new int[N][M];

            for(int i=0; i<M; i++) {
                queue.offer(new Node(0,i));
                visited[0][i] = 1;
                queue.offer(new Node(N-1,i));
                visited[N-1][i] = 1;
            }

            for(int i=1; i<N-1; i++) {
                queue.offer(new Node(i,0));
                visited[i][0] = 1;
                queue.offer(new Node(i,M-1));
                visited[i][M-1] = 1;
            }

            bfs();

            for(int i=0; i<N; i++) {
                for(int j=0; j<M; j++) {
                    if(meltTarget[i][j] == 1) {
                        count += 1;
                        graph[i][j] = 0;
                    }
                }
            }

            if(count == 0) {
                break;
            } else {
                answer.add(count);
            }
        }

        System.out.println(answer.size());
        System.out.println(answer.get(answer.size()-1));
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny]==0) {
                    visited[nx][ny] = 1;
                    if(graph[nx][ny] == 1) {
                        meltTarget[nx][ny] = 1;
                    } else {
                        queue.offer(new Node(nx,ny));
                    }
                }
            }
        }
    }
}