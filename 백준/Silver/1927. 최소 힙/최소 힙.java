import java.io.*;
import java.util.*;

public class Main {
    
    static class Node implements Comparable<Node> {
        int num;
        
        public Node(int num) {
            this.num = num;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.num - o.num;
        }
    }
    
    static int N;
    static PriorityQueue<Node> queue = new PriorityQueue<>();
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        for(int i=0; i<N; i++) {
            int command = Integer.parseInt(br.readLine());
            if(command == 0) {
                if(queue.isEmpty()) {
                    System.out.println(0);
                } else {
                    System.out.println(queue.poll().num);
                }
            } else {
                queue.offer(new Node(command));
            }
        }
    }
}