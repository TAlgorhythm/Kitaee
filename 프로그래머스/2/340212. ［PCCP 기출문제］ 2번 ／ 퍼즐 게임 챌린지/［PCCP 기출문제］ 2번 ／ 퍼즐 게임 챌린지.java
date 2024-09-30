class Solution {
    
    static int MIN = 1;
    static int MAX = 0;
    static int answer;
    
    public int solution(int[] diffs, int[] times, long limit) {
        for(int i=0; i<diffs.length; i++) {
            MAX = Math.max(MAX, diffs[i]);
        }
        
        while(MIN <= MAX) {
            int level = (MIN+MAX) / 2;
            long time = 0;
            for(int i=0; i<diffs.length; i++) {
                if(level >= diffs[i]) {
                    time += times[i];
                } else {
                    int count = diffs[i]-level;
                    time += ((times[i]+times[i-1])*count);
                    time += times[i];
                }
            }
            if(limit < time) {
                MIN = level+1;
            } else {
                answer = level;
                MAX = level-1;
            }
        }
        return answer;
    }
}