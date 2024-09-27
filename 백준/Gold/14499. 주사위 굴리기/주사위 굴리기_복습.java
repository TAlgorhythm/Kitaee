import java.io.*;
import java.util.*;

public class Main {

    // 이동한 칸에 쓰여 있는 수가 0이면, 주사위의 바닥면에 쓰여 있는 수가 복사된다.
    // 0이 아닌 경우에는 칸에 쓰여 있는 수가 주사위 바닥면으로 복사되며, 칸에 쓰여 있는 수는 0이 된다.

    static int N,M,x,y,K;
    static String[] info;
    static int[][] map;
    static int[] dice = new int[7];
    static int[] dx = {0,0,0,-1,1};
    static int[] dy = {0,1,-1,0,0};

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        x = Integer.parseInt(info[2]);
        y = Integer.parseInt(info[3]);
        K = Integer.parseInt(info[4]);
        map = new int[N][M];
        for(int i=0; i<N; i++) {
            info = br.readLine().split(" ");
            for(int j=0; j<M; j++) {
                map[i][j] = Integer.parseInt(info[j]);
            }
        }
        info = br.readLine().split(" ");
        for(int i=0; i<K; i++) {
            int command = Integer.parseInt(info[i]);
            int nx = x + dx[command];
            int ny = y + dy[command];
            if(0<=nx && nx<N && 0<=ny && ny<M) {
                x = nx;
                y = ny;
                roll(command);
            }
        }
    }

    static void roll(int direction) {
        int temp = dice[1];
        if(direction == 1) {    // 동쪽
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
        } else if(direction == 2) { // 서쪽
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        } else if(direction == 3) { // 북쪽
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        } else {    // 남쪽
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }
        if(map[x][y] == 0) {
            map[x][y] = dice[1];
        } else {
            dice[1] = map[x][y];
            map[x][y] = 0;
        }
        System.out.println(dice[6]);
    }
}
