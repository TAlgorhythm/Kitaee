import java.io.*;
import java.util.*;

public class Main {
    
    static class Word implements Comparable<Word> {
        String letter;
        
        public Word(String letter) {
            this.letter = letter;
        }
        
        @Override
        public int compareTo(Word o) {
            if(this.letter.length() == o.letter.length()) {
                return this.letter.compareTo(o.letter);
            }
            
            return this.letter.length() - o.letter.length();
        }
    }
    
    static int N;
    static PriorityQueue<Word> queue = new PriorityQueue<>();
    static HashMap<String, Integer> map = new HashMap<>();
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        
        for(int i=0; i<N; i++) {
            queue.offer(new Word(br.readLine()));
        }
        
        while(!queue.isEmpty()) {
            String word = queue.poll().letter;
            if(!map.containsKey(word)) {
                System.out.println(word);
                map.put(word, 1);
            }
        }
    }
}