import java.util.*;

class Solution {
    
    static class Node implements Comparable<Node> {
        int node;
        int cost;
        
        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
    
    static int answer = 0;
    static int[] distance;
    static List<List<Node>> graph = new ArrayList<>();
    static Queue<Node> queue = new PriorityQueue<>();
    
    public int solution(int N, int[][] road, int K) {
        distance = new int[N+1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        
        for(int i=0; i<=N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for(int[] roadInfo : road) {
            graph.get(roadInfo[0]).add(new Node(roadInfo[1], roadInfo[2]));
            graph.get(roadInfo[1]).add(new Node(roadInfo[0], roadInfo[2]));
        }
        
        queue.offer(new Node(1,0));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.cost > distance[node.node]) {
                continue;
            }
            for(int i=0; i<graph.get(node.node).size(); i++) {
                Node destinationNode = graph.get(node.node).get(i);
                if(distance[destinationNode.node] > destinationNode.cost+node.cost) {
                    distance[destinationNode.node] = destinationNode.cost+node.cost;
                    queue.offer(new Node(destinationNode.node, distance[destinationNode.node]));
                }
            }
        }
        
        for(int i=1; i<=N; i++) {
            if(distance[i] <= K) {
                answer+=1;
            }
        }
        
        return answer;
    }
}