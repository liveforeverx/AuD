package ex6;


import aud.BinaryTree;
import aud.example.expr.AtomicExpression;
import aud.example.expr.ExpressionParser;
import aud.example.expr.ExpressionTree;
import aud.example.expr.Number;
import aud.example.expr.Plus;
import aud.example.expr.Times;

public class Derivative {

    private final static AtomicExpression plus = new Plus();
    private final static AtomicExpression times = new Times();

    public static ExpressionTree derivate(BinaryTree<AtomicExpression> t,
            String var) {
        return derivate((ExpressionTree) t, var);
    }

    public static ExpressionTree derivate(ExpressionTree t, String var) {
        BinaryTree<AtomicExpression> binaryTree = t;
        if (binaryTree.getData().toString().equals(plus.toString())) {
            return new ExpressionTree(plus, derivate(
                    binaryTree.getLeft(), var), derivate(binaryTree.getRight(),
                    var));
        } else if (binaryTree.getData().toString().equals(times.toString())) {
            return new ExpressionTree(plus, new ExpressionTree(times, derivate(
                    binaryTree.getLeft(), var),
                    (ExpressionTree) binaryTree.getRight()),
                    new ExpressionTree(times, (ExpressionTree) binaryTree
                            .getLeft(), derivate(binaryTree.getRight(), var)));
        } else if(binaryTree.getData().toString().equals(var)) {
            return new ExpressionTree(new Number(1.0));
        } else { 
            return new ExpressionTree(new Number(0.0));
        }
    }

    public static void main(String[] args) {
        String expression = "x+x*y";
        ExpressionParser parser = new ExpressionParser();
        ExpressionTree expTree = parser.parse(expression);
        ExpressionTree der = derivate(expTree, "y");
    }
}
