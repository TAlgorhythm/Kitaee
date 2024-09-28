import java.util.*;

class Solution {
    
    static int[] giftIndex;
    static int[][] history;
    static int[] count;
    static int answer = 0;
    static Map<String, Integer> personaMap = new HashMap<>();
    
    public int solution(String[] friends, String[] gifts) {
        giftIndex = new int[friends.length];
        count = new int[friends.length];
        history = new int[friends.length][friends.length];
        
        for(int i=0; i<friends.length; i++) {
            personaMap.put(friends[i], i);
        }
        
        for(String gift : gifts) {
            String[] info = gift.split(" ");
            String sender = info[0];
            String receiver = info[1];
            
            // 선물 지수 계산
            giftIndex[personaMap.get(sender)]+=1;
            giftIndex[personaMap.get(receiver)]-=1;
            
            // 선물 히스토리 계산
            history[personaMap.get(sender)][personaMap.get(receiver)]+=1;
        }
        
        for(int i=0; i<friends.length; i++) {
            for(int j=0; j<friends.length; j++) {
                if(i==j) {
                    continue;
                } else {
                    if(history[i][j] > history[j][i]) {
                        count[i]+=1;
                    } else if(history[i][j] == history[j][i]) {
                        if(giftIndex[i] > giftIndex[j]) {
                            count[i]+=1;
                        }
                    }
                }
            }
        }
        
        for(int i=0; i<friends.length; i++) {
            answer = Math.max(answer, count[i]);
        }
        
        return answer;
    }
}