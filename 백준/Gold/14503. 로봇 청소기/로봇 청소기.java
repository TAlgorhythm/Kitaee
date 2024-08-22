// 각 칸은 벽 또는 빈 칸이다
// 청소기는 바라보는 방향이 있다. (동서남북)
// 처음에 빈 칸은 전부 청소되지 않은 상태
// 3 <= N, M <= 50

// 청소를 시켜

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
    static int answer = 0;
    static int xIndex,yIndex;
    static int direction;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static LinkedList<Node> queue = new LinkedList<>();
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new int[N][M];
        
        info = br.readLine().split(" ");
        xIndex = Integer.parseInt(info[0]);
        yIndex = Integer.parseInt(info[1]);
        direction = Integer.parseInt(info[2]);
        
        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
            }
        }

        bfs(xIndex, yIndex);

        System.out.println(answer);
    }
    
    static void bfs(int currentX, int currentY) {
        queue.offer(new Node(currentX, currentY));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            Boolean isLeft = false;

            if(graph[node.x][node.y] == 0) {    // 청소해야함
                graph[node.x][node.y] = 2;
                answer+=1;
            }

            for(int i=0; i<4; i++) {
                int nx = dx[i] + node.x;
                int ny = dy[i] + node.y;
                if(0<=nx && nx<N && 0<=ny && ny<M && graph[nx][ny]==0) {
                    isLeft = true;
                    break;
                }
            }

            if(isLeft) {    // 청소할 구역이 남았다.
                while(true) {
                    int nx,ny;
                    direction-=1;
                    if(direction < 0) {
                        direction+=4;
                    }
                    nx = node.x + dx[direction];
                    ny = node.y + dy[direction];

                    if(0<=nx && nx<N && 0<=ny && ny<M && graph[nx][ny]==0) {
                        queue.offer(new Node(nx,ny));
                        break;
                    }
                }
            } else {    // 청소할 구역이 없다.
                int nx,ny;
                if(direction == 0) {
                    nx = node.x+1;
                    ny = node.y;
                } else if(direction == 1) {
                    nx = node.x;
                    ny = node.y-1;
                } else if(direction == 2) {
                    nx = node.x-1;
                    ny = node.y;
                } else {
                    nx = node.x;
                    ny = node.y+1;
                }

                if(0<=nx && nx<N && 0<=ny && ny<M && graph[nx][ny]!=1) {
                    queue.offer(new Node(nx,ny));
                }
            }
        }
    }
}