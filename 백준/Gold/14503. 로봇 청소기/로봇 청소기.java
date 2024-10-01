import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static int x,y,direction;
    static int N,M;
    static int[][] graph;
    static String[] info;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new int[N][M];
        info = br.readLine().split(" ");
        x = Integer.parseInt(info[0]);
        y = Integer.parseInt(info[1]);
        direction = Integer.parseInt(info[2]);

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                graph[i][j] = Integer.parseInt(info[j]);
            }
        }

        while(true) {
            boolean clearTarget = false;
            if(graph[x][y] == 0) {
                answer+=1;
                graph[x][y] = -1;
            }

            for(int i=0; i<4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(0<=nx && nx<N && 0<=ny && ny<M) {
                    if(graph[nx][ny] == 0) {    // 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우
                        clearTarget = true;
                        break;
                    }
                }
            }

            if(clearTarget) {
                direction -= 1;
                if(direction == -1) {
                    direction = 3;
                }
                int nx = x + dx[direction];
                int ny = y + dy[direction];

                if(0<=nx && nx<N && 0<=ny && ny<M && graph[nx][ny]==0) {
                    x = nx;
                    y = ny;
                }
            } else {
                int reverseDirection = direction+2;
                if(reverseDirection >= 4) {
                    reverseDirection -= 4;
                }
                int nx = x + dx[reverseDirection];
                int ny = y + dy[reverseDirection];
                if(0<=nx && nx<N && 0<=ny && ny<M && graph[nx][ny]!=1) {
                    x = nx;
                    y = ny;
                } else {
                    break;
                }
            }
        }
        System.out.println(answer);
    }
}