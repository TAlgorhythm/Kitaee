import java.util.*;

class Solution {
    
    static class MaxHeap implements Comparable<MaxHeap> {
        private int value;
        private int index;
        
        public MaxHeap(int value, int index) {
            this.value = value;
            this.index = index;
        }
        
        @Override
        public int compareTo(MaxHeap o) {
            return o.value - this.value;
        }
    }
    
    static class MinHeap implements Comparable<MinHeap> {
        private int value;
        private int index;
        
        public MinHeap(int value, int index) {
            this.value = value;
            this.index = index;
        }
        
        @Override
        public int compareTo(MinHeap o) {
            return this.value - o.value;
        }
    }
    
    static PriorityQueue<MaxHeap> maxQueue = new PriorityQueue<>();
    static PriorityQueue<MinHeap> minQueue = new PriorityQueue<>();
    static List<Boolean> isDeleted = new ArrayList<>();
    static int heapIndex = 0;
    static int[] answer = new int[2];
    
    public int[] solution(String[] operations) {
        
        for(String operation : operations) {
            String[] command = operation.split(" ");
            if(command[0].equals("I")) {    // 삽입 연산
                maxQueue.offer(new MaxHeap(Integer.parseInt(command[1]), heapIndex));
                minQueue.offer(new MinHeap(Integer.parseInt(command[1]), heapIndex));
                isDeleted.add(false);
                heapIndex+=1;
            } else {    // 삭제 연산
                if(command[1].equals("1")) {    // 최댓값 삭제
                    while(!maxQueue.isEmpty()) {
                        MaxHeap target = maxQueue.poll();
                        if(!isDeleted.get(target.index)) {
                            isDeleted.set(target.index, true);
                            break;
                        }
                    }
                } else {    // 최솟값 삭제
                    while(!minQueue.isEmpty()) {
                        MinHeap target = minQueue.poll();
                        if(!isDeleted.get(target.index)) {
                            isDeleted.set(target.index, true);
                            break;
                        }
                    }
                }
            }
        }
        
        while(!maxQueue.isEmpty()) {
            MaxHeap target = maxQueue.poll();
            if(!isDeleted.get(target.index)) {
                answer[0] = target.value;
                break;
            }
        }
        
        while(!minQueue.isEmpty()) {
            MinHeap target = minQueue.poll();
            if(!isDeleted.get(target.index)) {
                answer[1] = target.value;
                break;
            }
        }
        
        return answer;
    }
}
