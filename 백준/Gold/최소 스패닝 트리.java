import java.io.*;
import java.util.*;

public class Main {
    
    static class Node implements Comparable<Node>{
        int start;
        int end;
        int cost;
        
        public Node(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
    
    
    static int V,E;
    static int[] parent;
    static int answer = 0;
    static PriorityQueue<Node> queue = new PriorityQueue<>();
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] info = br.readLine().split(" ");
        V = Integer.parseInt(info[0]);
        E = Integer.parseInt(info[1]);
        
        parent = new int[V+1];
        for(int i=1; i<V+1; i++) {
            parent[i] = i;
        }
        
        for(int i=0; i<E; i++) {
            info = br.readLine().split(" ");
            queue.offer(new Node(Integer.parseInt(info[0]), Integer.parseInt(info[1]), Integer.parseInt(info[2])));
        }
        
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            int node1 = findParent(currentNode.start);
            int node2 = findParent(currentNode.end);
            if(parent[node1] != parent[node2]) {
                unionParent(node1, node2);
                answer+=currentNode.cost;
            }
        }
        
        System.out.println(answer);
    }
    
    static int findParent(int node) {
        if(parent[node] == node) {
            return node;
        }
        return findParent(parent[node]);
    }
    
    static void unionParent(int node1, int node2) {
        node1 = findParent(node1);
        node2 = findParent(node2);
        
        if(node1 < node2) {
            parent[node2] = node1;
        } else {
            parent[node1] = node2;
        }
    }
}

// 3 3
// 1 2 1
// 2 3 2
// 1 3 3
