import java.io.*;
import java.util.*;

public class Main {

    static class Node implements Comparable<Node> {
        int vertex;
        int cost;

        public Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }

    static int T;
    static int n,d,c;
    static int a,b,s;
    static String[] info;
    static int[] distance;
    static List<List<Node>> graph;
    static Queue<Node> queue;
    static int[][] answer;
    static int count;
    static int time;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        answer = new int[T][2];

        for(int test = 0; test<T; test++) {
            info = br.readLine().split(" ");
            n = Integer.parseInt(info[0]);
            d = Integer.parseInt(info[1]);
            c = Integer.parseInt(info[2]);

            distance = new int[n+1];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[c] = 0;

            graph = new ArrayList<>();
            for(int i=0; i<=n; i++) {
                graph.add(new ArrayList<>());
            }

            for(int i=0; i<d; i++) {
                info = br.readLine().split(" ");
                a = Integer.parseInt(info[0]);
                b = Integer.parseInt(info[1]);
                s = Integer.parseInt(info[2]);
                graph.get(b).add(new Node(a,s));
            }

            queue = new PriorityQueue<>();
            queue.offer(new Node(c, 0));
            while(!queue.isEmpty()) {
                Node node = queue.poll();
                if(node.cost > distance[node.vertex]) {
                    continue;
                }
                for(int i=0; i<graph.get(node.vertex).size(); i++) {
                    Node targetNode = graph.get(node.vertex).get(i);
                    if(distance[targetNode.vertex] > node.cost+targetNode.cost) {
                        distance[targetNode.vertex] = node.cost+targetNode.cost;
                        queue.offer(new Node(targetNode.vertex, distance[targetNode.vertex]));
                    }
                }
            }

            count = 0;
            time = 0;
            for(int i=1; i<=n; i++) {
                if(distance[i] != Integer.MAX_VALUE) {
                    count+=1;
                    time = Math.max(time, distance[i]);
                }
            }
            answer[test][0] = count;
            answer[test][1] = time;
        }

        for(int i=0; i<answer.length; i++) {
            System.out.println(answer[i][0] + " " + answer[i][1]);
        }
    }
}