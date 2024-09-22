import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int[][] graph;
    static int answer = 0;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int direction = 0;
    static int x,y;

    static int count = 0;
    static int length = 1;

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

        x = N/2;
        y = N/2;

        while(length < N) {
            for(int i=0; i<length; i++) {
                x += dx[direction];
                y += dy[direction];
                move(x, y);
            }
            count+=1;
            if(count == 2) {
                length+=1;
                count = 0;
            }
            direction+=1;
            if(direction == 4) {
                direction = 0;
            }
        }

        for(int i=0; i<N-1; i++) {
            x += dx[direction];
            y += dy[direction];
            move(x, y);
        }
        System.out.println(answer);
    }

    static void move(int x, int y) {
        int count = graph[x][y];
        if(direction == 0) {    // 왼쪽
            count -= checkRangeAndPlus(x-1, y, graph[x][y], 7);
            count -= checkRangeAndPlus(x-1, y+1, graph[x][y], 1);
            count -= checkRangeAndPlus(x-1, y-1, graph[x][y], 10);
            count -= checkRangeAndPlus(x-2, y, graph[x][y], 2);
            count -= checkRangeAndPlus(x+1, y, graph[x][y], 7);
            count -= checkRangeAndPlus(x+1, y+1, graph[x][y], 1);
            count -= checkRangeAndPlus(x+1, y-1, graph[x][y], 10);
            count -= checkRangeAndPlus(x+2, y, graph[x][y], 2);
            count -= checkRangeAndPlus(x, y-2, graph[x][y], 5);
            checkRangeAndPlus(x, y-1, count, 100);
        } else if(direction == 1) { // 아래쪽
            count -= checkRangeAndPlus(x-1, y-1, graph[x][y], 1);
            count -= checkRangeAndPlus(x, y-1, graph[x][y], 7);
            count -= checkRangeAndPlus(x+1, y-1, graph[x][y], 10);
            count -= checkRangeAndPlus(x, y-2, graph[x][y], 2);
            count -= checkRangeAndPlus(x-1, y+1, graph[x][y], 1);
            count -= checkRangeAndPlus(x, y+1, graph[x][y], 7);
            count -= checkRangeAndPlus(x+1, y+1, graph[x][y], 10);
            count -= checkRangeAndPlus(x, y+2, graph[x][y], 2);
            count -= checkRangeAndPlus(x+2, y, graph[x][y], 5);
            checkRangeAndPlus(x+1, y, count, 100);
        } else if(direction == 2) { // 오른쪽
            count -= checkRangeAndPlus(x-1, y-1, graph[x][y], 1);
            count -= checkRangeAndPlus(x-1, y, graph[x][y], 7);
            count -= checkRangeAndPlus(x-1, y+1, graph[x][y], 10);
            count -= checkRangeAndPlus(x-2, y, graph[x][y], 2);
            count -= checkRangeAndPlus(x+1, y-1, graph[x][y], 1);
            count -= checkRangeAndPlus(x+1, y, graph[x][y], 7);
            count -= checkRangeAndPlus(x+1, y+1, graph[x][y], 10);
            count -= checkRangeAndPlus(x+2, y, graph[x][y], 2);
            count -= checkRangeAndPlus(x, y+2, graph[x][y], 5);
            checkRangeAndPlus(x, y+1, count, 100);
        } else {    // 위쪽
            count -= checkRangeAndPlus(x-1, y-1, graph[x][y], 10);
            count -= checkRangeAndPlus(x, y-1, graph[x][y], 7);
            count -= checkRangeAndPlus(x+1, y-1, graph[x][y], 1);
            count -= checkRangeAndPlus(x, y-2, graph[x][y], 2);
            count -= checkRangeAndPlus(x-1, y+1, graph[x][y], 10);
            count -= checkRangeAndPlus(x, y+1, graph[x][y], 7);
            count -= checkRangeAndPlus(x+1, y+1, graph[x][y], 1);
            count -= checkRangeAndPlus(x, y+2, graph[x][y], 2);
            count -= checkRangeAndPlus(x-2, y, graph[x][y], 5);
            checkRangeAndPlus(x-1, y, count, 100);
        }
    }

    static int checkRangeAndPlus(int x, int y, int value, int ratio) {
        int count = value*ratio/100;
        if(0<=x && x<N && 0<=y && y<N) {
            graph[x][y] += count;
        } else {
            answer += count;
        }
        return count;
    }
}