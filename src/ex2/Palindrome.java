package ex2;

import java.util.Stack;

public class Palindrome {

    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(isTPalindrome("otto"));
        System.out.println(isTPalindrome("al(otto)la"));
        System.out.println(isTPalindrome("abc(ah(otto)v(atta)ha)cba"));
        System.out.println(isTPalindrome("abc"));
        System.out.println(isTPalindrome("abc(aha)(u)cba"));
        System.out.println(isTPalindrome("abc(ah(otto)h)cba"));
    }
    

    public static boolean isTPalindrome(String textString) {
        // Char Array
        char[] text = textString.toCharArray();
        // Stack
        Stack<Character> stack = new Stack<Character>();
        
        Stack<Integer> rec_stack = new Stack<Integer>();
        
        // Iterator, start and end 
        int iterator = 0;
        int stackiterator = -1;
        
        while ( iterator < text.length) {
            //System.out.println("Stack: " + stack.toString() + " i: " + iterator + " is: " + stackiterator + " length: " + text.length);
            if(text[iterator] == '(') rec_stack.push(stackiterator + 1);
            else if(text[iterator] == ')') 
            {
                int end = stackiterator;
                stackiterator = rec_stack.pop();
                if(flushstack(stackiterator, end, stack))
                    stack.push('*');
                else
                    return false;
            } else {
                stack.push(text[iterator]);
                stackiterator++;
            }
            iterator++;
        }
        //System.out.println("Stack: " + stack.toString() + " i: " + iterator + " is: " + stackiterator + " length: " + text.length);
        return flushstack(0, stackiterator, stack);
    }
    
    private static boolean flushstack(int start, int end, Stack<Character> stack){
        //System.out.println(" start " + start + " end " + end);
        Stack<Character> helpstack = new Stack<Character>();
        int elements = (end - start + 1)/2;
        
        for (int i = 0; i < elements; i++)
            helpstack.push(stack.pop());
        
        if((end - start) % 2 == 0) stack.pop();
        //System.out.println("elements: " + elements + " helpstack: " + helpstack.toString());
        for (int i = 0; i < elements; i++){
            //System.out.println(stack.top() + " == " + helpstack.top());            
            if(stack.pop() != helpstack.pop()) return false;
        }
        return true;
    }

}
