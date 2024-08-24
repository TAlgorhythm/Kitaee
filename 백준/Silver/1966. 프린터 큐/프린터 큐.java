// Node 클래스를 만들어서 index랑 중요도를 저장하자.

import java.io.*;
import java.util.*;

public class Main {

    static class Node {
        int index;
        int priority;

        public Node(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }

    static int T;
    static int N,M;
    static int answer;
    static int currentIndex;
    static int[] arr;
    static LinkedList<Node> queue;

    public static void main(String args[]) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      T = Integer.parseInt(br.readLine());
      for(int i=0; i<T; i++) {
        String[] info = br.readLine().split(" ");
        N = Integer.parseInt(info[0]);
        M = Integer.parseInt(info[1]);
        arr = new int[N];
        queue = new LinkedList<>();
        currentIndex = N-1;
        answer = 1;

        info = br.readLine().split(" ");
        for(int j=0; j<N; j++) {
            arr[j] = Integer.parseInt(info[j]);
            queue.offer(new Node(j, arr[j]));
        }
        Arrays.sort(arr);

        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(node.priority == arr[currentIndex]) {
                if(node.index == M) {
                    System.out.println(answer);
                    break;
                } else {
                    answer+=1;
                    currentIndex-=1;
                }
            } else {
                queue.offer(node);
            }
        }
      }
    }
}