import java.io.*;
import java.util.*;

public class Main {
    
    static class Shark {
        int x;
        int y;
        int direction;
        int count;

        public Shark(int x, int y, int direction, int count) {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.count = count;
        }
    }

    static class Fish {
        int x;
        int y;
        int id;
        int direction;
        boolean isAlive;
        
        public Fish(int x, int y, int id, int direction, boolean isAlive) {
            this.x = x;
            this.y = y;
            this.id = id;
            this.direction = direction;
            this.isAlive = isAlive;
        }
    }
    
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, -1, -1, -1, 0, 1, 1, 1};
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] arr = new int[4][4];
        List<Fish> fishes = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 4; j++) {
                Fish fish = new Fish(i,j,Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())-1,true);
                fishes.add(fish);
                arr[i][j] = fish.id;
            }
        }

        Collections.sort(fishes, new Comparator<Fish>() {
            @Override
            public int compare(Fish o1, Fish o2) {
                return o1.id - o2.id;
            }
        });

        Fish fish = fishes.get(arr[0][0] - 1);
        Shark shark = new Shark(0, 0, fish.direction, fish.id);
        fish.isAlive = false;
        arr[0][0] = -1;
        
        dfs(arr, shark, fishes);
        System.out.println(answer);
    }

    static void dfs(int[][] arr, Shark shark, List<Fish> fishes) {
        answer = Math.max(answer, shark.count);

        for(Fish fish : fishes) {
            moveFish(fish, arr, fishes);
        }
        
        for (int dist = 1; dist < 4; dist++) {
            int nx = shark.x + dx[shark.direction] * dist;
            int ny = shark.y + dy[shark.direction] * dist;
    
            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > 0) {
                int[][] arrCopies = copyArr(arr);
                List<Fish> fishCopies = copyFishes(fishes);
                
                arrCopies[shark.x][shark.y] = 0;
                Fish fish = fishCopies.get(arr[nx][ny] - 1);
                Shark newShark = new Shark(fish.x, fish.y, fish.direction, shark.count + fish.id);
                fish.isAlive = false;
                arrCopies[fish.x][fish.y] = -1;
                
                dfs(arrCopies, newShark, fishCopies);
            }
        }
    }
    
    static void moveFish(Fish fish, int[][] arr, List<Fish> fishes) {
        if (fish.isAlive == false) return;

        for (int i = 0; i < 8; i++) {
            int nextDir = (fish.direction + i) % 8;
            int nx = fish.x + dx[nextDir];
            int ny = fish.y + dy[nextDir];

            if (0 <= nx && nx < 4 && 0 <= ny && ny < 4 && arr[nx][ny] > -1) {
                arr[fish.x][fish.y] = 0;
                
                if (arr[nx][ny] == 0) {
                    fish.x = nx;
                    fish.y = ny;
                } else {
                    Fish temp = fishes.get(arr[nx][ny] - 1);
                    temp.x = fish.x;
                    temp.y = fish.y;
                    arr[fish.x][fish.y] = temp.id;

                    fish.x = nx;
                    fish.y = ny;
                }

                arr[nx][ny] = fish.id;
                fish.direction = nextDir;
                return;
            }
        }
    }

    static int[][] copyArr(int[][] arr) {
        int[][] temp = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                temp[i][j] = arr[i][j];
            }
        }

        return temp;
    }

    static List<Fish> copyFishes(List<Fish> fishes) {
        List<Fish> temp = new ArrayList<>();
        for(Fish fish : fishes) {
            temp.add(new Fish(fish.x, fish.y, fish.id, fish.direction, fish.isAlive));
        }
        return temp;
    }
}