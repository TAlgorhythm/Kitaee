import java.util.*;

class Solution {
    
    static HashMap<String, Integer> map = new HashMap<>();
    static int totalNumber = 10;
    static int answer = 0;
    
    public int solution(String[] want, int[] number, String[] discount) {
        for(int i=0; i<want.length; i++) {
            map.put(want[i], number[i]);
        }
        
        int start = 0;
        int index = 0;
        
        while(start+index < discount.length && index < 10) {
            if(!map.containsKey(discount[start+index]) || map.get(discount[start+index]) == 0) {
                start+=1;
                index=0;
                totalNumber=10;
                for(int i=0; i<want.length; i++) {
                    map.put(want[i], number[i]);
                }
                continue;
            }
            
            map.put(discount[start+index], map.get(discount[start+index])-1);
            totalNumber-=1;
            index+=1;
            if(totalNumber == 0) {
                answer+=1;
                start+=1;
                index=0;
                totalNumber=10;
                for(int i=0; i<want.length; i++) {
                    map.put(want[i], number[i]);
                }
                continue;
            }
        }
        return answer;
    }
}
