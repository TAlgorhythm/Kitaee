import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer;
        int compareValue = arr[0];
        List<Integer> tempAnswer = new ArrayList<>(Arrays.asList(arr[0]));
        
        for(int i=1; i<arr.length; i++) {
            if(arr[i] != compareValue) {
                tempAnswer.add(arr[i]);
                compareValue = arr[i];
            }
        }
        
        answer = new int[tempAnswer.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = tempAnswer.get(i);
        }
        
        return answer;
    }
}
