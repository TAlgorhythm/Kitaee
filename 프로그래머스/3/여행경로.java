import java.util.*;

class Solution {
    
    static int[] visited;
    static List<String> route = new ArrayList<>();
    
    public String[] solution(String[][] tickets) {
        visited = new int[tickets.length];
        permutation(0, tickets, "ICN", "ICN");
        Collections.sort(route);
        return route.get(0).split(" ");
    }
    
    static void permutation(int depth, String[][] tickets, String start, String tempRoute) {
        if(depth == tickets.length) {
            route.add(tempRoute);
            return;
        }
        
        for(int i=0; i<tickets.length; i++) {
            if(visited[i] == 0 && tickets[i][0].equals(start)) {
                visited[i] = 1;
                permutation(depth+1, tickets, tickets[i][1], tempRoute + " " + tickets[i][1]);
                visited[i] = 0;
            }
        }
    }
}
