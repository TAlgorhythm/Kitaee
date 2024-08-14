import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        HashSet<String> phoneBook = new HashSet<>();
        for(String phone : phone_book) {
            phoneBook.add(phone);
        }
        
        for(int i=0; i<phone_book.length; i++) {
            String targetPhone = phone_book[i];
            for(int j=1; j<targetPhone.length(); j++) {
                if(phoneBook.contains(targetPhone.substring(0,j))) {
                    return false;
                }
            }
        }
        
        return true;
    }
}