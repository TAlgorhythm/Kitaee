class Solution {
    
    static int[] visited;
    static int answer = 0;
    
    public int solution(int n, int[][] computers) {
        visited = new int[n];
        for(int i=0; i<n; i++) {
            if(visited[i] == 0) {
                answer+=1;
                dfs(i, computers);
            }
        }
        
        return answer;
    }
    
    static void dfs(int computer, int[][] computers) {
        visited[computer] = 1;
        for(int i=0; i<computers[computer].length; i++) {
            if(visited[i] == 0 && computers[computer][i] == 1) {
                dfs(i, computers);
            }
        }
    }
}
