package ex6;

import aud.example.expr.Divide;
import aud.example.expr.ExpressionParser;
import aud.example.expr.ExpressionTree;
import aud.example.expr.Power;
import aud.example.expr.Times;
import aud.example.expr.Tokenizer;
import aud.util.DotViewer;

public class ExprWithPow extends ExpressionParser {

    protected ExpressionTree product(int level) {
        verbose(level, "<product>");

        ExpressionTree left = power(level + 1);

        if (lookahead() == Tokenizer.TIMES) {
            nextToken();
            ExpressionTree right = product(level + 1);
            return new ExpressionTree(new Times(), left, right);
        } else if (lookahead() == Tokenizer.DIVIDE) {
            nextToken();
            ExpressionTree right = product(level + 1);
            return new ExpressionTree(new Divide(), left, right);
        }
        return left;
    }

    public ExpressionTree power(int level) {
        ExpressionTree left = factor(level + 1);
        if (lookahead() == Tokenizer.POWER) {
            System.out.println("power");
            nextToken();
            ExpressionTree right = power(level + 1);
            return new ExpressionTree(new Power(), left, right);
        }
        return left;
    }

    public static void main(String[] args) {
        String expression = "(4+1)*12-(17*8)^3";
        ExprWithPow parser = new ExprWithPow();
        ExpressionTree expTree = parser.parse(expression);

//        System.out.println(expTree.getValue());
        DotViewer.displayWindow(expTree, "");
    }
}
