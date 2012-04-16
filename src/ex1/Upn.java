package ex1;

import java.util.Stack;

public class Upn {

    /**
     * @param args
     */
    private static void stackpull(StringBuilder out, Stack<Character> stack,
            char next) {
        if (stack.empty() == true)
            return;
        if (next == '+' || next == '-')
            while (!stack.empty() && stack.peek() != '(')
                if (stack.peek() != '(' && stack.peek() != ')')
                    out.append(stack.pop() + " ");
                else
                    stack.pop();
        if (stack.empty() == true)
            return;
        if ((stack.peek() == '*' || stack.peek() == '/') && next != '(')
            out.append(stack.pop() + " ");
        if (stack.empty() == true)
            return;
        if (next == ')') {
            if (stack.peek() != '(' && stack.peek() != ')')
                out.append(stack.pop() + " ");
        }
    }

    public static String upn(String input) {
        if (input == null)
            return "";
        char[] in = input.toCharArray();
        Stack<Character> stack = new Stack<Character>();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < in.length; i++) {
            char x = in[i];
            if (x == '+' || x == '-' || x == '*' || x == '/' || x == '('
                    || x == ')') {
                stackpull(out, stack, x);
                stack.push(x);
            } else
                out.append(in[i] + " ");
        }

        while (!stack.isEmpty()) {
            if (stack.peek() != '(' && stack.peek() != ')')
                out.append(stack.pop() + " ");
            else
                stack.pop();
        }

        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(upn("(3-4*5)*(6+5-2)"));
    }

}
