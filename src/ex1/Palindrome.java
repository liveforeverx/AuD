package ex1;

import java.util.*;

public class Palindrome {
    public static boolean isPalindrome(String text) {
        String newtext = text.toLowerCase();
        int elements = 0;
        Stack<Character> newStack = new Stack<Character>();
        for (int i = 0; i < newtext.length(); i++) {
            if(isIt(newtext.charAt(i))) 
            {
                newStack.push(newtext.charAt(i));
                elements++;
            }
        }
        elements /= 2;
        boolean result = true;
        int length = 0;
        for (int i = 0; i < elements; i++) {
            while(isIt(newtext.charAt(length)) != true) length++;
            if(newStack.pop() != newtext.charAt(length)) result = false;
            length++;
        }
        return result;
    }
    
    private static boolean isIt(char x){
        return (x >= 'a' && x <= 'z') ? true : false;
    }
    
    public static void main(String[] args) {
        System.out.println("Reliefpfeiler: " + isPalindrome("Reliefpfeiler"));
        System.out.println("Madam: " + isPalindrome("Madam"));
        System.out.println("Lagerregal: " + isPalindrome("Lagerregal"));
        System.out.println("Na, Fakir, Paprika-Fan?: " + isPalindrome("Na, Fakir, Paprika-Fan?"));
        System.out.println("Reliefpfeler: " + isPalindrome("Reliefpfeler"));
    }
}
