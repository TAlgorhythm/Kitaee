import java.util.*;

class Solution {
    
    static Map<Integer, Integer> map = new HashMap<>();
    
    public int solution(int[] nums) {
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], 1);
        }
        
        if(nums.length/2 < map.size()) {
            return nums.length/2;
        } else {
            return map.size();
        }
    }
}