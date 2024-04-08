import java.util.*;

class Solution {
    
    static int[] distance;
    static int target;
    static int answer = 0;
    static Queue<Integer> queue = new LinkedList<>();
    static List<List<Integer>> graph = new ArrayList<>();
    
    public int solution(int n, int[][] edge) {
        distance = new int[n+1];
        for(int i=0; i<n+1; i++) {
            graph.add(new ArrayList<>());
            distance[i] = Integer.MAX_VALUE;
        }
        
        distance[0] = 0;
        distance[1] = 0;
        
        for(int i=0; i<edge.length; i++) {
            graph.get(edge[i][0]).add(edge[i][1]);
            graph.get(edge[i][1]).add(edge[i][0]);
        }
        
        queue.offer(1);
        while(!queue.isEmpty()) {
            Integer currentNode = queue.poll();
            for(int i=0; i<graph.get(currentNode).size(); i++) {
                int destination = graph.get(currentNode).get(i);
                if(distance[destination] > distance[currentNode]+1) {
                    distance[destination] = distance[currentNode]+1;
                    queue.offer(destination);
                }
            }
        }
        
        Arrays.sort(distance);
        target = distance[n];
        
        for(int i=n; i>=1; i--) {
            if(target == distance[i]) {
                answer+=1;
            }
            else {
                break;
            }
        }
        
        return answer;
    }
}