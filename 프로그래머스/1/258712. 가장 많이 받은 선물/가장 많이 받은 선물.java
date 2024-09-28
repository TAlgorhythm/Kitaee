import java.util.*;

class Solution {
    
    static int answer = 0;
    static int[] giftIndex;
    static int[] count;
    static Map<String, Integer> personaMap = new HashMap<>();
    static Map<String, int[]> history = new HashMap<>();
    
    public int solution(String[] friends, String[] gifts) {
        giftIndex = new int[friends.length];
        count = new int[friends.length];
        
        for(int i=0; i<friends.length; i++) {
            personaMap.put(friends[i], i);
        }
        
        for(String gift : gifts) {
            // 선물 지수 계산
            String[] info = gift.split(" ");
            String sender = info[0];
            String receiver = info[1];
            
            giftIndex[personaMap.get(sender)]+=1; // 선물을 준 사람은 선물 지수 +1
            giftIndex[personaMap.get(receiver)]-=1; // 선물을 받은 사람은 선물 지수 -1;
            
            // 선물 히스토리 계산
            if(history.containsKey(sender)) {
                int[] giftHistory = history.get(sender);
                giftHistory[personaMap.get(receiver)]+=1;
                history.put(sender, giftHistory);
            } else {
                int[] giftHistory = new int[friends.length];
                giftHistory[personaMap.get(sender)] = -1; // 자기 자신은 -1로 세팅
                giftHistory[personaMap.get(receiver)] = 1;
                history.put(sender, giftHistory);
            }
        }
        
        for(String friend : friends) {
            if(history.containsKey(friend)) {
                int[] giftHistory = history.get(friend);
                for(int i=0; i<giftHistory.length; i++) {
                    if(giftHistory[i] == -1) {
                        continue;
                    } else {
                        int sendCount = giftHistory[i];
                        int receiveCount = 0;
                        if(history.containsKey(friends[i])) {
                            int[] receiveHistory = history.get(friends[i]);
                            receiveCount = receiveHistory[personaMap.get(friend)];
                            receiveHistory[personaMap.get(friend)] = -1;
                            history.put(friends[i], receiveHistory);
                        }
                        if(sendCount > receiveCount) {
                            count[personaMap.get(friend)]+=1;
                        } else if(sendCount == receiveCount) {
                            if(giftIndex[personaMap.get(friend)] > giftIndex[i]) {
                                count[personaMap.get(friend)]+=1;
                            } else if(giftIndex[personaMap.get(friend)] == giftIndex[i]) {
                                continue;
                            } else {
                                count[i]+=1;    
                            }
                        } else {
                            count[i]+=1;
                        }
                    }
                }
            }
        }
        getAnswer();
        return answer;
    }
    
    static void getAnswer() {
        for(int i=0; i<count.length; i++) {
            answer = Math.max(answer, count[i]);
        }
    }
}