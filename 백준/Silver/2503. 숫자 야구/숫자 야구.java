import java.io.*;

public class Main {
    
    static int N;
    static int[] num;
    static int strike, ball;
    static int answer = 0;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        num = new int[1000];
        
        for(int i=123; i<1000; i++) {
            String strNum = String.valueOf(i);
            if(strNum.charAt(0) == '0' || strNum.charAt(1) == '0' || strNum.charAt(2) == '0') {
                num[i] = 1;
            }
            if(strNum.charAt(0) == strNum.charAt(1) || strNum.charAt(1) == strNum.charAt(2) || strNum.charAt(0) == strNum.charAt(2)) {
                num[i] = 1;
            }
        }
        
        for(int i=0; i<N; i++) {
            String[] info = br.readLine().split(" ");
            int targetNum = Integer.parseInt(info[0]);
            strike = Integer.parseInt(info[1]);
            ball = Integer.parseInt(info[2]);
            for(int j=123; j<1000; j++) {
                if(num[j] == 0) {
                    if(!isPossible(String.valueOf(targetNum), String.valueOf(j))) {
                        num[j] = 1;
                    }
                }
            }
        }
        
        for(int i=123; i<1000; i++) {
            if(num[i] == 0) {
                answer+=1;
            }
        }
        
        System.out.println(answer);
    }
    
    static Boolean isPossible(String targetNum, String compareNum) {
        int tempStrike = 0;
        int tempBall = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(targetNum.charAt(i) == compareNum.charAt(j)) {
                    if(i == j) {
                        tempStrike += 1;
                    } else {
                        tempBall += 1;
                    }
                }
            }
        }
        
        if(tempStrike == strike && tempBall == ball) {
            return true;
        }
        return false;
    }
}