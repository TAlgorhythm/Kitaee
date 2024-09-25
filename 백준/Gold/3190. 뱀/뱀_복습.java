import java.io.*;
import java.util.*;

public class Main {

    // 사과는 -1, 뱀은 1

    static int answer = 1;
    static int x = 0;
    static int y = 0;
    static int N,K,L;
    static int direction = 0;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int[][] graph;
    static Map<Integer, String> directionCommand = new HashMap<>();
    static Queue<int[]> snake = new LinkedList<>();

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        graph = new int[N][N];

        for(int i=0; i<K; i++) {
            String[] info = br.readLine().split(" ");
            graph[Integer.parseInt(info[0])-1][Integer.parseInt(info[1])-1] = -1;
        }
        graph[0][0] = 1;
        snake.offer(new int[]{0,0});

        L = Integer.parseInt(br.readLine());
        for(int i=0; i<L; i++) {
            String[] info = br.readLine().split(" ");
            directionCommand.put(Integer.parseInt(info[0]), info[1]);
        }

        simulation();
        System.out.println(answer);
    }

    static void simulation() {
        while(true) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            if(0<=nx && nx<N && 0<=ny && ny<N) {
                if(graph[nx][ny] == 1) {
                    break;
                } else {
                    if(graph[nx][ny] == 0) {
                        int[] tail = snake.poll();
                        graph[tail[0]][tail[1]] = 0;
                    }
                    graph[nx][ny] = 1;
                    snake.offer(new int[]{nx,ny});
                }
            } else {
                break;
            }
            if(directionCommand.containsKey(answer)) {
                changeDirection(directionCommand.get(answer));
            }
            x = nx;
            y = ny;
            answer+=1;
        }
    }

    static void changeDirection(String command) {
        if(command.equals("L")) {
            direction-=1;
            if(direction == -1) {
                direction = 3;
            }
        } else {
            direction+=1;
            if(direction == 4) {
                direction = 0;
            }
        }
    }
}
