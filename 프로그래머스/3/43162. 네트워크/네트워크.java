import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int[] parent = new int[n];
        HashMap<Integer, Integer> parentMap = new HashMap<>();
        int answer = 0;
        
        for(int i=0; i<n; i++) {
            parent[i] = i;
        }
        
        for(int i=0; i<computers.length; i++) {
            for(int j=0; j<computers[i].length; j++) {
                if(computers[i][j] == 1) {
                    unionParent(parent, i, j);
                }
            }
        }
        
        for(int i=0; i<n; i++) {
            int targetNode = findParent(parent, i);
            if(!parentMap.containsKey(targetNode)) {
                answer+=1;
                parentMap.put(targetNode, 1);
            }
        }
        
        return answer;
    }
    
    static int findParent(int[] parent, int node) {
        if(parent[node] == node) {
            return node;
        }
        node = parent[node];
        return findParent(parent, node);
    }
    
    static void unionParent(int[] parent, int node1, int node2) {
        node1 = findParent(parent, node1);
        node2 = findParent(parent, node2);
        
        if(node1 < node2) {
            parent[node2] = node1;
        } else {
            parent[node1] = node2;
        }
    }
}