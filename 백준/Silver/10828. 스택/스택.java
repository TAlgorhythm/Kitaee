import java.io.*;
import java.util.*;

public class Main {

    static int N;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            if(info.length == 2) {
                stack.push(Integer.parseInt(info[1]));
            } else {
                if(info[0].equals("top")) {
                    if(stack.isEmpty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(stack.peek());   
                    }
                } else if(info[0].equals("size")) {
                    System.out.println(stack.size());
                } else if(info[0].equals("empty")) {
                    if(stack.isEmpty()) {
                        System.out.println(1);
                    } else {
                        System.out.println(0);
                    }
                } else {
                    if(stack.isEmpty()) {
                        System.out.println(-1);
                    } else {
                        System.out.println(stack.pop());
                    }
                }
            }
        }
    }
}