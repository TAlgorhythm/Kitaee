import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int length = A.length;
        int targetIndex = B.length-1;
        
        Arrays.sort(A);
        Arrays.sort(B);
        
        for(int i=length-1; i>=0; i--) {
            if(A[i] < B[targetIndex]) {
                answer+=1;
                targetIndex-=1;
                if(targetIndex < 0) {
                    break;
                }
            }
        }
        return answer;
    }
}