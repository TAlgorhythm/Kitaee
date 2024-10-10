import java.util.*;

class Solution {
    
    static class Result implements Comparable<Result> {
        int user;
        int price;
        
        public Result(int user, int price) {
            this.user = user;
            this.price = price;
        }
        
        @Override
        public int compareTo(Result result) {
            if(this.user == result.user) {
                return result.price - this.price;
            }
            return result.user - this.user;
        }
    }
    
    static PriorityQueue<Result> queue = new PriorityQueue<>();
    static int[] sales = new int[]{10,20,30,40};
    static int[] result;
    
    public int[] solution(int[][] users, int[] emoticons) {
        result = new int[emoticons.length];
        dfs(0, users, emoticons);
        Result result = queue.poll();
        return new int[]{result.user, result.price};
    }
    
    static void dfs(int depth, int[][] users, int[] emoticons) {
        if(depth == result.length) {
            int subscriber = 0;
            int payTotal = 0;
            for(int[] user : users) {
                int total = 0;
                for(int i=0; i<result.length; i++) {
                    if(user[0] <= result[i]) {
                        total += ((emoticons[i]*(100-result[i]))/100);
                    }
                }
                if(total >= user[1]) {
                    subscriber += 1;
                } else {
                    payTotal += total;
                }
            }
            queue.offer(new Result(subscriber, payTotal));
            return;
        }
        for(int i=0; i<4; i++) {
            result[depth] = sales[i];
            dfs(depth+1, users, emoticons);
        }
    }
}