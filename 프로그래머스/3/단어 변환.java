import java.util.*;

class Solution {
    
    static class Node {
        String word;
        int cost;
        
        public Node(String word, int cost) {
            this.word = word;
            this.cost = cost;
        }
    }
    
    static int[] visited;
    static LinkedList<Node> queue = new LinkedList<>();
    
    public int solution(String begin, String target, String[] words) {
        visited = new int[words.length];
        queue.offer(new Node(begin,0));
        
        while(!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if(currentNode.word.equals(target)) {
                return currentNode.cost;
            }
            for(int i=0; i<words.length; i++) {
                if(visited[i] == 0 && isExchangable(currentNode.word, words[i])) {
                    queue.offer(new Node(words[i], currentNode.cost+1));
                    visited[i] = 1;
                }
            }
        }
        return 0;
    }
    
    Boolean isExchangable(String word1, String word2) {
        int count = 0;
        for(int i=0; i<word1.length(); i++) {
            if(word1.charAt(i) != word2.charAt(i)) {
                count+=1;
            }
        }
        if(count == 1) {
            return true;
        } return false;
    }
}
