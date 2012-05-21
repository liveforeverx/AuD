package ex4;

import java.util.Stack;

public class RecursionToStack {

    public static int whatStack2(int n) {
        Stack<Integer> stack = new Stack<Integer>();
        int sum = 0;
        while (n > 0) {
            stack.push(n % 10);
            n /= 10;
        }
        sum = 0;
        while (!stack.isEmpty())
            sum += stack.pop();
        return sum;
    }

    public static int whatStack(int n) {
        int sum = 0;
        while (n > 0) {
            sum += n % 10;
            n /= 10;
        }

        return sum;
    }
    
    public static int whatRec(int n) { 
        if (n < 10) 
            return n; 
        else 
            return whatRec(n / 10) + n % 10; 
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        System.out.println(whatStack(12345));
    }

}
