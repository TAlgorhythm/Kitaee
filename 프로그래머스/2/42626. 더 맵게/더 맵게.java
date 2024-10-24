import java.util.*;

class Solution {
    
    static class Scoville implements Comparable<Scoville> {
        int spicy;
        
        public Scoville(int spicy) {
            this.spicy = spicy;
        }
        
        @Override
        public int compareTo(Scoville scoville) {
            return this.spicy - scoville.spicy;
        }
    }
    
    Queue<Scoville> queue = new PriorityQueue<>();
    
    public int solution(int[] scoville, int K) {
        int answer = 0;
        for(int i=0; i<scoville.length; i++) {
            queue.offer(new Scoville(scoville[i]));
        }
        
        while(!queue.isEmpty()) {
            Scoville sc = queue.poll();
            if(sc.spicy >= K) {
                break;
            }
            if(queue.isEmpty()) {
                return -1;
            }
            Scoville sc1 = queue.poll();
            queue.offer(new Scoville(sc.spicy + sc1.spicy*2));
            answer+=1;
        }
        
        return answer;
    }
}