class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int index = section[0];
        for(int i=0; i<section.length; i++) {
            if(section[i] >= index) {
                answer+=1;
                index = section[i]+m;
                if(index > n) {
                    break;
                }
            }
        }
        return answer;
    }
}