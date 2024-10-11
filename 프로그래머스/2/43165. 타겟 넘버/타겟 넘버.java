class Solution {
    
    static int answer = 0;
    
    public int solution(int[] numbers, int target) {
        dfs(0, numbers, target, 0);
        return answer;
    }
    
    static void dfs(int depth, int[] numbers, int target, int num) {
        if(depth == numbers.length) {
            if(num == target) {
                answer+=1;
            }
            return;
        }
        dfs(depth+1, numbers, target, num+numbers[depth]);
        dfs(depth+1, numbers, target, num-numbers[depth]);
    }
}