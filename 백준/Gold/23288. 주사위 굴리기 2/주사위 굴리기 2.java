import java.io.*;
import java.util.*;

public class Main {

    static int answer = 0;
    static int x = 0;
    static int y= 0;
    static int N,M,K;
    static String[] info;
    static int[][] map;
    static int direction = 0;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int[] dice = new int[7];
    static LinkedList<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        K = Integer.parseInt(info[2]);
        map = new int[N][M];

        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(info[j]);
            }
        }

        for(int i=1; i<=6; i++) {
            dice[i] = i;
        }

        while(K > 0) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if(0<=nx && nx<N && 0<=ny && ny<M) {
                answer += (bfs(nx, ny, new int[N][M])*map[nx][ny]);
                roll();
            } else {
                direction += 2;
                if(direction >= 4) {
                    direction -= 4;
                }
                nx = x + dx[direction];
                ny = y + dy[direction];
                roll();
                answer += (bfs(nx, ny, new int[N][M])*map[nx][ny]);
            }
            if(dice[6] > map[nx][ny]) {
                direction += 1;
                if(direction == 4) {
                    direction = 0;
                }
            } else if(dice[6] < map[nx][ny]) {
                direction -= 1;
                if(direction == -1) {
                    direction = 3;
                }
            }
            x = nx;
            y = ny;
            K -= 1;
        }
        System.out.println(answer);
    }

    static int bfs(int currentX, int currentY, int[][] visited) {
        int count = 1;
        queue = new LinkedList<>();
        visited[currentX][currentY] = 1;
        queue.offer(new int[]{currentX, currentY});
        while(!queue.isEmpty()) {
            int[] now = queue.poll();
            for(int i=0; i<4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];
                if(0<=nx && nx<N && 0<=ny && ny<M && visited[nx][ny]==0 && map[nx][ny]==map[currentX][currentY]) {
                    count += 1;
                    visited[nx][ny] = 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        return count;
    }

    static void roll() {
        int temp = dice[1];
        if(direction == 0) {    // 동쪽
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
        } else if(direction == 1) { // 남쪽
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        } else if(direction == 2) { // 서쪽
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        } else {    // 북쪽
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }
    }
}