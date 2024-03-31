import java.util.*;

class Solution {
    
    static class Node {
        int index;
        int priority;
        
        public Node(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int maxIndex = priorities.length - 1;
        Queue<Node> queue = new LinkedList<>();
        for(int i=0; i<priorities.length; i++) {
            queue.offer(new Node(i,priorities[i]));
        }
        
        Arrays.sort(priorities);
        
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if(currentNode.priority == priorities[maxIndex]) {
                if(currentNode.index == location) {
                    return answer+1;
                } else {
                    answer+=1;
                    maxIndex-=1;
                }
            } else {
                queue.offer(currentNode);
            }
        }
        
        return answer;
    }
}