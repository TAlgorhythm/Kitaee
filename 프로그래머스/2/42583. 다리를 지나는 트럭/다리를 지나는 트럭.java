import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        int sum = 0;
        int index = 0;
        LinkedList<Integer> bridge = new LinkedList<>();
        
        for(int i=0; i<bridge_length; i++) {
            bridge.offer(0);
        }
        
        while(true) {
            time+=1;
            sum-=bridge.poll();
            if(truck_weights[index] + sum <= weight) {
                if(index == truck_weights.length-1) {
                    time+=bridge_length;
                    break;
                }
                bridge.offer(truck_weights[index]);
                sum+=truck_weights[index];
                index+=1;
            } else {
                bridge.offer(0);
            }
        }
        
        return time;
    }
}