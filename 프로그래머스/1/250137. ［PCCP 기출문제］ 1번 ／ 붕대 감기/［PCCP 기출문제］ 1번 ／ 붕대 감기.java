import java.util.*;

class Solution {
    
    static int time = 1;
    static int count = 0;
    static int targetTime;
    static int HEALTH_LIMIT;
    static Map<Integer, Integer> attackInfo = new HashMap<>();
    
    public int solution(int[] bandage, int health, int[][] attacks) {
        HEALTH_LIMIT = health;
        
        for(int i=0; i<attacks.length; i++) {
            attackInfo.put(attacks[i][0], attacks[i][1]);
            if(i==attacks.length-1) {
                targetTime = attacks[i][0];
            }
        }
        
        while(time <= targetTime) {
            if(attackInfo.containsKey(time)) {
                count = 0;
                health-=attackInfo.get(time);
                if(health <= 0) {
                    return -1;
                }
            } else {
                count+=1;
                health+=bandage[1];
                if(count == bandage[0]) {
                    count = 0;
                    health+=bandage[2];
                }
                if(health > HEALTH_LIMIT) {
                    health = HEALTH_LIMIT;
                }
            }
            time+=1;
        }
        return health;
    }
}