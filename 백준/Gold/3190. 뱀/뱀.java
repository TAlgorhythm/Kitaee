// 게임 로직 세팅
// 벽이나 일반 -> 0, 사과 -> 1, 뱀 -> 2
// 동(0), 남(1), 서(2), 북(3)


import java.io.*;
import java.util.*;

public class Main {

    static int N,K,L;
    static int[][] graph;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int direction = 0;
    static int time = 0;
    static int x = 0;
    static int y = 0;
    static int tailIndex = 0;
    static HashMap<Integer, String> map = new HashMap<>();
    static List<int[]> snake = new ArrayList<>();

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for(int i=0; i<K; i++) {
            String[] info = br.readLine().split(" ");
            graph[Integer.parseInt(info[0])-1][Integer.parseInt(info[1])-1] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++) {
            String[] info = br.readLine().split(" ");
            map.put(Integer.parseInt(info[0]), info[1]);
        }

        graph[x][y] = 2;
        snake.add(new int[]{x,y});

        while(true) {
            if(map.containsKey(time)) {
                String change = map.get(time);
                if(change.equals("L")) {
                    direction -= 1;
                    if(direction == -1) {
                        direction = 3;
                    }
                } else {
                    direction += 1;
                    if(direction == 4) {
                        direction = 0;
                    }
                }
            }
            int nx = x + dx[direction];
            int ny = y + dy[direction];
            if(nx<0 || nx==N || ny<0 || ny==N || graph[nx][ny]==2) {
                break;
            } else if(graph[nx][ny]==1) {
                graph[nx][ny] = 2;
                snake.add(new int[]{nx,ny});
            } else {
                graph[nx][ny] = 2;
                snake.add(new int[]{nx,ny});
                int[] tail = snake.get(tailIndex);
                graph[tail[0]][tail[1]] = 0;
                tailIndex += 1;
            }
            x = nx;
            y = ny;
            time += 1;
        }
        System.out.println(time+1);
    }
}