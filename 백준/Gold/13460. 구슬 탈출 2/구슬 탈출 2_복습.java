import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int redX;
        int redY;
        int blueX;
        int blueY;
        int count;

        public Node(int redX, int redY, int blueX, int blueY, int count) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.count = count;
        }
    }

    static int N,M;
    static String[][] graph;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static LinkedList<Node> queue = new LinkedList<>();

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new String[N][M];
        Node node = new Node(0,0,0,0,0);

        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<M; j++) {
                graph[i][j] = String.valueOf(line.charAt(j));
                if(graph[i][j].equals("R")) {
                    node.redX = i;
                    node.redY = j;
                    graph[i][j] = ".";
                } else if(graph[i][j].equals("B")) {
                    node.blueX = i;
                    node.blueY = j;
                    graph[i][j] = ".";
                }
            }
        }

        queue.offer(node);
        System.out.println(simulation());
    }

    static int simulation() {
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.count >= 10) {
                return -1;
            } else {
                for(int i=0; i<4; i++) {
                    int redX = node.redX;
                    int redY = node.redY;
                    int blueX = node.blueX;
                    int blueY = node.blueY;
                    boolean redHoleIn = false;
                    boolean blueHoleIn = false;

                    // 빨간 구슬 이동
                    while(true) {
                        int moveRedX = redX + dx[i];
                        int moveRedY = redY + dy[i];
                        if(graph[moveRedX][moveRedY].equals("#")) {
                            break;
                        }
                        if(graph[moveRedX][moveRedY].equals("O")) {
                            redHoleIn = true;
                            break;
                        }
                        redX = moveRedX;
                        redY = moveRedY;
                    }

                    // 파란 구슬 이동
                    while(true) {
                        int moveBlueX = blueX + dx[i];
                        int moveBlueY = blueY + dy[i];
                        if(graph[moveBlueX][moveBlueY].equals("#")) {
                            break;
                        }
                        if(graph[moveBlueX][moveBlueY].equals("O")) {
                            blueHoleIn = true;
                            break;
                        }
                        blueX = moveBlueX;
                        blueY = moveBlueY;
                    }

                    if(blueHoleIn) {
                        continue;
                    } else if(redHoleIn) {
                        return node.count+1;
                    } else {
                        if(redX==blueX && redY==blueY) {
                            if(i==0) {  // 왼쪽
                                if(node.redY < node.blueY) {
                                    blueY+=1;
                                } else {
                                    redY+=1;
                                }
                            } else if(i==1) {   // 오른쪽
                                if(node.redY < node.blueY) {
                                    redY-=1;
                                } else {
                                    blueY-=1;
                                }
                            } else if(i==2) {   // 위쪽
                                if(node.redX < node.blueX) {
                                    blueX+=1;
                                } else {
                                    redX+=1;
                                }
                            } else {    // 아래쪽
                                if(node.redX < node.blueX) {
                                    redX-=1;
                                } else {
                                    blueX-=1;
                                }
                            }
                        }
                    }
                    queue.offer(new Node(redX, redY, blueX, blueY, node.count+1));
                }
            }
        }
        return -1;
    }
}
