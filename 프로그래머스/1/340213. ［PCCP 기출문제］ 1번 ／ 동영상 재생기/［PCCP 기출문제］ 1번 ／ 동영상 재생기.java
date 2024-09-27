import java.io.*;
import java.util.*;

class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLen = changeTimeFormat(video_len);
        int intPos = changeTimeFormat(pos);
        int opStart = changeTimeFormat(op_start);
        int opEnd = changeTimeFormat(op_end);
        
        if(intPos>=opStart && intPos<=opEnd) {
            intPos = opEnd;
        }
        
        for(String command : commands) {
            if(command.equals("next")) {
                intPos+=10;
                if(intPos > videoLen) {
                    intPos = videoLen;
                }
            } else {
                intPos-=10;
                if(intPos < 0) {
                    intPos = 0;
                }
            }
            if(intPos>=opStart && intPos<=opEnd) {
                intPos = opEnd;
            }
        }
        
        String hour = String.valueOf(intPos/60);
        String minute = String.valueOf(intPos%60);
        
        if(hour.length() == 1) {
            hour = "0" + hour;
        }
        if(minute.length() == 1) {
            minute = "0" + minute;
        }
        
        return hour+":"+minute;
    }
    
    static int changeTimeFormat(String time) {
        String[] stringTime = time.split(":");
        int hour = Integer.parseInt(stringTime[0]) * 60;
        int minute = Integer.parseInt(stringTime[1]);
        return hour+minute;
    }
}