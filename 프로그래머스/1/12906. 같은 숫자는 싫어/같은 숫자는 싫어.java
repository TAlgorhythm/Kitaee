import java.util.*;

public class Solution {
    
    static int[] answer;
    static Stack<Integer> stack = new Stack<>();
    
    public int[] solution(int[] arr) {
        for(int i=0; i<arr.length; i++) {
            if(stack.isEmpty()) {
                stack.push(arr[i]);
            } else {
                if(stack.peek() != arr[i]) {
                    stack.push(arr[i]);
                }
            }
        }
        
        answer = new int[stack.size()];
        for(int i=0; i<stack.size(); i++) {
            answer[i] = stack.get(i);
        }
        
        return answer;
    }
}