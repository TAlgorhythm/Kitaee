// 10101111
// 01111101
// 11001110
// 00000010
// 2
// 3 -1
// 1 1

import java.io.*;
import java.util.*;

public class Main {

    static int[][] wheel = new int[4][8];
    static int K;
    static int answer = 0;

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<4; i++) {
            String input = br.readLine();
            for(int j=0; j<8; j++) {
                wheel[i][j] = Character.getNumericValue(input.charAt(j));
            }
        }
        K = Integer.parseInt(br.readLine());
        for(int i=0; i<K; i++) {
            String[] info = br.readLine().split(" ");
            int num = Integer.parseInt(info[0]) - 1;
            int direction = Integer.parseInt(info[1]);
            int[] rotateTarget = new int[4];
            rotateTarget[num] = direction;
            for(int j=1; num+j<4; j++) {
                if(wheel[num+j-1][2] == wheel[num+j][6]) {
                    break;
                }
                rotateTarget[num+j] = rotateTarget[num+j-1] * (-1);
            }
            for(int j=1; num-j>=0; j++) {
                if(wheel[num-j][2] == wheel[num-j+1][6]) {
                    break;
                }
                rotateTarget[num-j] = rotateTarget[num-j+1] * (-1);
            }
            for(int j=0; j<4; j++) {
                if(rotateTarget[j] != 0) {
                    rotate(j, rotateTarget[j]);
                }
            }
        }

        calculate();
        System.out.println(answer);
    }

    static void calculate() {
        if(wheel[0][0] == 1) {
            answer += 1;
        }
        if(wheel[1][0] == 1) {
            answer += 2;
        }
        if(wheel[2][0] == 1) {
            answer += 4;
        }
        if(wheel[3][0] == 1) {
            answer += 8;
        }
    }

    static void rotate(int num, int direction) {
        int[] temp = Arrays.copyOf(wheel[num], 8);
        if(direction == 1) {    // 시계 방향
            for(int i=1; i<8; i++) {
                wheel[num][i] = temp[i-1];
            }
            wheel[num][0] = temp[7];
        } else {    // 반시계 방향
            for(int i=0; i<7; i++) {
                wheel[num][i] = temp[i+1];
            }
            wheel[num][7] = temp[0];
        }
    }
}