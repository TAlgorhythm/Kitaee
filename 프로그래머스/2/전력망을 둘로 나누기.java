class Solution {
    
    static int index;
    static int answer = 100;
    static int[] parent;
    
    public int solution(int n, int[][] wires) {
        while(index < wires.length) {
            parent = new int[n+1];
            for(int i=1; i<n+1; i++) {
                parent[i] = i;
            }
            for(int i=0; i<wires.length; i++) {
                if(i == index) {
                    continue;
                }
                unionParent(wires[i][0], wires[i][1]);
            }
            answer = Math.min(answer, getTwoParents());
            index+=1;
        }
        return answer;
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
    
    static int getTwoParents() {
        int tempParent = findParent(parent[1]);
        int count1 = 0;
        int count2 = 0;
        for(int i=1; i<parent.length; i++) {
            if(findParent(parent[i]) == tempParent) {
                count1+=1;
            } else {
                count2+=1;
            }
        }
        
        return Math.abs(count1-count2);
    }
}
