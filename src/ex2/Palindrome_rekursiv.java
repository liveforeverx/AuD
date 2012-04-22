package ex2;

import java.util.Stack;

public class Palindrome_rekursiv {

    /**
     * @param args
     */
    static int stringiterator = 0;

    public static void main(String[] args) {
        System.out.println(isTPalindrome("otto"));
        System.out.println(isTPalindrome("al(otto)la"));
        System.out.println(isTPalindrome("abc(ah(otto)v(atta)ha)cba"));
        System.out.println(isTPalindrome("abc"));
        System.out.println(isTPalindrome("abc(aha)(u)cba"));
        System.out.println(isTPalindrome("abc(ah(otto)h)cba"));
    }
    
    public static boolean isTPalindrome(String textString) {
        stringiterator = 0;
        // Nur damit Zugriff auf einen String Element wird mit text[i] anstatt text.charAt(i),
        char[] text = textString.toCharArray();
        // Wir inizieren Stack:
        Stack<Character> stack = new Stack<Character>();
        return isTPalindrome(text, stack);
    }
    
    private static boolean isTPalindrome(char[] text, Stack<Character> stack){
        // So lange den Text durchgehen, bis eine Klammer zu eitritt, oder bis die text zu Ende ist
        int elements = 0;
        while(stringiterator < text.length && text[stringiterator] != ')'){
        // Wenn es ist Anfang von eine Palindrome, dann aufruffen wir sich selbe in Rekursion
            if(text[stringiterator] == '('){
                stringiterator++;
                // Wenn es ist eine newe Palindrome, dann untersuchen wir es, in dem wir es rekursiv aufrufen(also wieder isTPalindrome)
                // Wenn es ist eine Palindrome, dann pushen wir in Stack *(Stern)
                if(isTPalindrome(text, stack))
                    stack.push('*');
                else
                    return false;
            }
            else
                // Wenn das ist nicht eine new Palindrome, dann pushen wir in stack nächste Symbole:
                stack.push(text[stringiterator++]);
            // Wir zählen, wie viel Elemente aktuell in Stack für DIESES rekursives Aufruf
        elements++;
        }
        // Weil wir gehen von while Schleife mit eine  && text[stringiterator] != ')', ohne es zu iterieren, müssen wir hier
        // das machen
        stringiterator++;
        // dann checken wir die Palindrome, die in Stack liegt mit gezählte Länge:
        return check_palindrome(stack, elements);
    }
    
    
    private static boolean check_palindrome(Stack<Character> stack, int elements){
        // Hälfte von eine Palindrome ist eine mid
        int mid = elements / 2;
        // In eine Hilfe Stack schreiben wir die Hälfte von Stack
        Stack<Character> helpstack = new Stack<Character>();
        for (int i = 0; i < mid; i++)
            helpstack.push(stack.pop());
        // Wenn die Stack ist "aba"(also n%2 == 1), dann ignorieren wir die mittlere Element
        if(elements % 2 == 1) stack.pop();
        // Vergleich des Hilfe Stacks und die andere Hälfte des Stackes.
        for (int i = 0; i < mid; i++)
            if(stack.pop() != helpstack.pop()) return false;
        return true;
        
    }
            
}
