class Solution {
    public String solution(int[] food) {
        String answer;
        StringBuilder sb = new StringBuilder();
        
        for(int i=1; i<food.length; i++) {
            sb.append(String.valueOf(i).repeat(food[i]/2));
        }
        
        answer = sb + "0";
        answer += sb.reverse();
        
        return answer;
    }
}
