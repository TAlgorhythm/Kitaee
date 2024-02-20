import java.util.*;

class Solution {
    
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(numbers, target, 0, 0);
        return answer;
    }
    
    static void dfs(int[] numbers, int target, int sum, int depth) {
        if(depth == numbers.length) {
            if(sum == target) {
                answer+=1;   
            }
            return;
        } else {
            dfs(numbers, target, sum+numbers[depth], depth+1);
            dfs(numbers, target, sum-numbers[depth], depth+1);
        }
    }
}