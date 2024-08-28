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
    static int tempRedX, tempRedY, tempBlueX, tempBlueY;
    static LinkedList<Node> queue = new LinkedList<>();
    static int[] dx = {0,0,-1,1};
    static int[] dy = {1,-1,0,0};

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        graph = new String[N][M];

        for(int i=0; i<N; i++) {
            String input = br.readLine();
            for(int j=0; j<M; j++) {
                graph[i][j] = String.valueOf(input.charAt(j));
                if(graph[i][j].equals("R")) {
                    tempRedX = i;
                    tempRedY = j;
                    graph[i][j] = ".";
                } else if(graph[i][j].equals("B")) {
                    tempBlueX = i;
                    tempBlueY = j;
                    graph[i][j] = ".";
                }
            }
        }
        queue.offer(new Node(tempRedX, tempRedY, tempBlueX, tempBlueY, 0));

        System.out.println(simulation());
    }

    static int simulation() {
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.count == 10) {
                continue;
            }
            for(int i=0; i<4; i++) {
                Boolean blueGoal = false;
                Boolean redGoal = false;
                int redX = node.redX;
                int redY = node.redY;
                int blueX = node.blueX;
                int blueY = node.blueY;
                int newRedX, newRedY, newBlueX, newBlueY;
                while(true) {
                    newRedX = redX + dx[i];
                    newRedY = redY + dy[i];
                    if(graph[newRedX][newRedY].equals("#")) {
                        break;
                    }
                    if(graph[newRedX][newRedY].equals("O")) {
                        redGoal = true;
                        break;
                    }
                    redX = newRedX;
                    redY = newRedY;
                }

                while(true) {
                    newBlueX = blueX + dx[i];
                    newBlueY = blueY + dy[i];
                    if(graph[newBlueX][newBlueY].equals("#")) {
                        break;
                    }
                    if(graph[newBlueX][newBlueY].equals("O")) {
                        blueGoal = true;
                        break;
                    }
                    blueX = newBlueX;
                    blueY = newBlueY;
                }

                if(blueGoal) {
                    continue;
                } else if(redGoal) {
                    return node.count + 1;
                }

                if(node.redX == redX && node.redY == redY && node.blueX == blueX && node.blueY == blueY) {
                    continue;
                }

                if(redX == blueX && redY == blueY) {
                    if(i == 0) {
                        if(node.redY < node.blueY) {
                            redY -= 1;
                        } else {
                            blueY -= 1;
                        }
                    } else if(i == 1) {
                        if(node.redY < node.blueY) {
                            blueY += 1;
                        } else {
                            redY += 1;
                        }
                    } else if(i == 3) {
                        if(node.redX < node.blueX) {
                            redX -= 1;
                        } else {
                            blueX -= 1;
                        }
                    } else {
                        if(node.redX < node.blueX) {
                            blueX += 1;
                        } else {
                            redX += 1;
                        }
                    }
                }
                queue.offer(new Node(redX, redY, blueX, blueY, node.count+1));
            }
        }
        return -1;
    }
}