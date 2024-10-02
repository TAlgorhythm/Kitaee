import java.io.*;
import java.util.*;

public class Main {

    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    static int N;
    static String[] info;
    static int[] nums;
    static int[] operations = new int[4];

    public static void main(String[]args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        nums = new int[N];
        info = br.readLine().split(" ");
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(info[i]);
        }
        info = br.readLine().split(" ");
        for(int i=0; i<4; i++) {
            operations[i] = Integer.parseInt(info[i]);
        }
        dfs(nums[0], 1);
        System.out.println(MAX);
        System.out.println(MIN);
    }

    static void dfs(int num, int index) {
        if(index == N) {
            MAX = Math.max(MAX, num);
            MIN = Math.min(MIN, num);
            return;
        }
        for(int i=0; i<4; i++) {
            if(operations[i] > 0) {
                operations[i]-=1;
                switch (i) {
                    case 0: dfs(num + nums[index], index+1); break;
                    case 1: dfs(num - nums[index], index+1); break;
                    case 2: dfs(num * nums[index], index+1); break;
                    case 3: dfs(num / nums[index], index+1); break;
                }
                operations[i]+=1;
            }
        }
    }
}