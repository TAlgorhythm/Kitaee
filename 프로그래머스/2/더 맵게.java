import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int count = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for(int i=0; i<scoville.length; i++) {
            queue.offer(scoville[i]);
        }
        
        while(queue.size() > 1) {
            int first = queue.poll();
            if(first >= K) {
                return count;
            }
            int second = queue.poll();
            
            queue.offer(first + (second*2));
            count+=1;
        }
        
        if(!queue.isEmpty() && queue.peek() >= K) {
            return count;
        }
        
        return -1;
    }
}
