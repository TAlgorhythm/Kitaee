import java.io.*;
import java.util.*;

public class Main {
    
    static int N,M;
    static int[] parent;
    static int basicNode;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parent = new int[N+1];
        for(int i=1; i<=N; i++) {
            parent[i] = i;
        }
        
        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            for(int j=0; j<N; j++) {
                if(info[j].equals("1")) {   // i+1과 j+1 도시는 이어져있음
                    unionParent(i+1, j+1);
                }
            }
        }
        
        String[] info = br.readLine().split(" ");
        basicNode = findParent(Integer.parseInt(info[0]));
        
        for(int i=1; i<M; i++) {
            int node = Integer.parseInt(info[i]);
            if(findParent(node) != basicNode) {
                System.out.println("NO");
                return;
            }
        }
        System.out.println("YES");
    }
    
    static int findParent(int node) {
        if(parent[node] == node) {
            return node;
        } else {
            node = parent[node];
            return findParent(node);
        }
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