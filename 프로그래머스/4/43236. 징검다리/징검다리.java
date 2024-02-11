import java.util.*;

class Solution {
    
    static int answer = 0;
    
    public int solution(int distance, int[] rocks, int n) {
        int start = 1;
        int end = distance;
        Arrays.sort(rocks);
        
        
        while(start<=end) {
            int mid = (start+end)/2;
            if(removeRockCount(mid, rocks, distance) > n) {
                end = mid-1;
            } else {
                answer = mid;
                start = mid+1;
            }
        }
        
        return answer;
    }
    
    static int removeRockCount(int mid, int[] rocks, int distance) {
        int removeCount = 0;
        int before = 0;
        for(int i=0; i<rocks.length; i++) {
            if(rocks[i]-before < mid) {
                removeCount+=1;
            } else {
                before = rocks[i];
            }
        }
        if(distance-before < mid) {
            removeCount+=1;
        }
        return removeCount;
    }
}