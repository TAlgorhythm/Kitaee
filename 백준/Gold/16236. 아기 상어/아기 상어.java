// 크기가 큰 물고기는 지나갈 수 없고
// 크기가 같은 물고기는 먹을 수 없고
// 자신보다 작은 물고기만 먹을 수 있음
// 먹을 수 있는 물고기가 1마리 -> 직행
// 먹을 수 있는 물고기가 1마리 이상 -> 가장 가까운 물고기
// 거리가 가까운 물고기 많으면 -> 가장 위에 -> 가장 왼쪽에

import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int x;
        int y;
        int distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node node) {
            if(this.distance == node.distance) {
                if(this.x == node.x) {
                    return this.y - node.y;
                }
                return this.x - node.x;
            }
            return this.distance - node.distance;
        }
    }

    static int N,M;
    static int[][] graph;
    static int[][] visited;
    static PriorityQueue<Node> queue;
    static LinkedList<Node> bfsQueue;
    static int size = 2;
    static int capture = 0;
    static int answer = 0;
    static int x,y;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
                if(graph[i][j] == 9) {
                    x = i;
                    y = j;
                }
            }
        }

        while(true) {
            queue = new PriorityQueue<>();
            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    if(graph[i][j]>0 && graph[i][j] < size) {
                        int distance = bfs(i,j);
                        if(distance > 0) {
                            queue.offer(new Node(i,j,distance));
                        }
                    }
                }
            }
            if(queue.isEmpty()) {
                break;
            } else {
                Node node = queue.poll();
                answer += node.distance;
                graph[node.x][node.y] = 9;
                graph[x][y] = 0;
                x = node.x;
                y = node.y;
                capture += 1;
                if(capture == size) {
                    size += 1;
                    capture = 0;
                }
            }
        }
        
        System.out.println(answer);
    }

    static int bfs(int targetX, int targetY) {
        visited = new int[N][N];
        bfsQueue = new LinkedList<>();
        bfsQueue.offer(new Node(x,y,0));
        visited[x][y] = 1;
        while(!bfsQueue.isEmpty()) {
            Node node = bfsQueue.poll();
            if(node.x == targetX && node.y == targetY) {
                return node.distance;
            }
            for(int i=0; i<4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<N && visited[nx][ny]==0 && graph[nx][ny]<=size) {
                    visited[nx][ny] = 1;
                    bfsQueue.offer(new Node(nx,ny,node.distance+1));
                }
            }
        }
        return -1;
    }
}