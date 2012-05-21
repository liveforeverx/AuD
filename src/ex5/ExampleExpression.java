package ex5;

import aud.example.expr.ExpressionParser;
import aud.example.expr.ExpressionTree;
import aud.util.DotViewer;

public class ExampleExpression {

    
    public static void main(String[] args) {
        String expression = "(4+1)*12-(17*8)";
        ExpressionParser parser = new ExpressionParser();
        ExpressionTree expTree = parser.parse(expression);

        System.out.println(expTree.getValue());

        System.out.println("PreOrder:\t" + expTree.preorder());
        System.out.println("InOrder:\t" + expTree.inorder());
        System.out.println("PostOrder:\t" + expTree.postorder());
        System.out.println("LevelOrder:\t" + expTree.levelorder());
        DotViewer.displayWindow(expTree, "");
    }
}
