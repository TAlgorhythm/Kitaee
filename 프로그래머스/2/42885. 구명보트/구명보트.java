import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        int start = 0;
        int end = people.length-1;
        Arrays.sort(people);
        
        while(start < end) {
            if(people[start] + people[end] <= limit) {
                start+=1;
                end-=1;
            } else {
                end-=1;
            }
            answer+=1;
            if(start == end) {
                answer+=1;
                break;
            }
        }
        
        return answer;
    }
}