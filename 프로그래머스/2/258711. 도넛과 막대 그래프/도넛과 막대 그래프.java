import java.util.*;

class Solution {
    
    static int[] answer = new int[4];
    static Map<Integer, Integer> in = new HashMap<>();
    static Map<Integer, Integer> out = new HashMap<>();
    
    public int[] solution(int[][] edges) {
        for(int[] edge : edges) {
            in.put(edge[1], in.getOrDefault(edge[1],0)+1);
            out.put(edge[0], out.getOrDefault(edge[0],0)+1);
        }
        
        for(int node : out.keySet()) {
            if(out.get(node) > 1) {
               if(!in.containsKey(node)) {
                   answer[0] = node;
               } else {
                   answer[3]+=1;
               }
            }
        }
        
        for(int node : in.keySet()) {
            if(!out.containsKey(node)) {
                answer[2]+=1;
            }
        }
        
        answer[1] = out.get(answer[0]) - answer[2] - answer[3];
        return answer;
    }
}