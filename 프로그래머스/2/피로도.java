class Solution {
    
    static int answer = -1;
    static int[] visited;
    
    public int solution(int k, int[][] dungeons) {
        visited = new int[dungeons.length];
        permutation(0, k, 0, dungeons);
        return answer;
    }
    
    static void permutation(int depth, int hp, int count, int[][] dungeons) {
        if(depth == dungeons.length) {
            answer = Math.max(answer, count);
            return;
        }
        
        for(int i=0; i<dungeons.length; i++) {
            if(visited[i] == 0 && dungeons[i][0] <= hp) {
                visited[i] = 1;
                permutation(depth+1, hp-dungeons[i][1], count+1, dungeons);
                visited[i] = 0;
            }
        }
        
        answer = Math.max(answer, count);
    }
}
