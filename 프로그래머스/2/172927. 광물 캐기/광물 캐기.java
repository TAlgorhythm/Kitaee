import java.util.*;

class Solution {
    
    static class Node implements Comparable<Node> {
        List<String> pickList;
        int damage;
        
        public Node(List<String> pickList, int damage) {
            this.pickList = pickList;
            this.damage = damage;
        }
        
        @Override
        public int compareTo(Node node) {
            return node.damage - this.damage;
        }
    }
    
    public int solution(int[] picks, String[] minerals) {
        int count = 5;
        int damage = 0;
        int answer = 0;
        List<String> mineralList = new ArrayList<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();
        
        if((picks[0]+picks[1]+picks[2])*5 < minerals.length) {
            minerals = Arrays.copyOf(minerals, (picks[0]+picks[1]+picks[2])*5);
        }
        
        for(int i=0; i<minerals.length; i++) {
            mineralList.add(minerals[i]);
            damage += pickMinerals(2, minerals[i]);
            count -= 1;
            if(count == 0) {
                queue.offer(new Node(mineralList, damage));
                mineralList = new ArrayList<>();
                damage = 0;
                count = 5;
            }
        }
        
        if(!mineralList.isEmpty()) {
            queue.offer(new Node(mineralList, damage));
        }
        
        for(int i=0; i<picks.length; i++) {
            for(int j=0; j<picks[i]; j++) {
                if(!queue.isEmpty()) {
                    List<String> targetMinerals = queue.poll().pickList;
                    for(String mineral : targetMinerals) {
                        answer += pickMinerals(i, mineral);
                    }
                } else {
                    return answer;
                }
            }
        }
        
        return answer;
    }
    
    private int pickMinerals(int pick, String mineral) {
        if(pick == 0) {
            return 1;
        } else if(pick == 1) {
            if(mineral.equals("diamond")) {
                return 5;
            } else {
                return 1;
            }
        } else {
            if(mineral.equals("diamond")) {
                return 25;
            } else if(mineral.equals("iron")) {
                return 5;
            } else {
                return 1;
            }
        }
    }
}