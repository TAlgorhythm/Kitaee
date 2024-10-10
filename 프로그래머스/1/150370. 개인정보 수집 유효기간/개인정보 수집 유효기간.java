import java.util.*;

class Solution {
    
    static HashMap<String, Integer> map = new HashMap<>();
    static List<Integer> answerList = new ArrayList<>();
    static int[] answer;
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        int convertToday = convertTerm(today);
        System.out.println(convertToday);
        
        for(String term : terms) {
            String[] splitTerm = term.split(" ");
            map.put(splitTerm[0], Integer.parseInt(splitTerm[1]));
        }
        
        for(int i=0; i<privacies.length; i++) {
            String[] splitPrivacy = privacies[i].split(" ");
            int startDate = convertTerm(splitPrivacy[0]);
            String type = splitPrivacy[1];
            int endDate = startDate + 28*map.get(type)-1;
            if(convertToday > endDate) {
                answerList.add(i+1);
            }
        }
        getAnswer();
        return answer;
    }
    
    static int convertTerm(String term) {
        String[] splitTerm = term.split("\\.");
        int year = Integer.parseInt(splitTerm[0]);
        int month = Integer.parseInt(splitTerm[1]);
        int day = Integer.parseInt(splitTerm[2]);
        return year*12*28 + month*28 + day;
    }
    
    static void getAnswer() {
        answer = new int[answerList.size()];
        for(int i=0; i<answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
    }
}