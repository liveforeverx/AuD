package ex4;


import aud.BinaryTree;
import aud.util.Graphviz;

public class BuildBinTree {
    public static void main(String[] args) {
        BinaryTree<Integer> mEight = new BinaryTree<Integer>(-8);

        BinaryTree<Integer> four = new BinaryTree<Integer>(4);
        BinaryTree<Integer> one = new BinaryTree<Integer>(1);

        BinaryTree<Integer> six = new BinaryTree<Integer>(6);
        BinaryTree<Integer> mEleven = new BinaryTree<Integer>(-11);
        BinaryTree<Integer> five = new BinaryTree<Integer>(5);
        BinaryTree<Integer> seven = new BinaryTree<Integer>(7);

        mEight.setLeft(four);
        mEight.setRight(one);

        four.setLeft(six);
        four.setRight(mEleven);

        one.setLeft(five);
        one.setRight(seven);
        
        System.out.println("Preorder\t: "+mEight.preorder().toString());
        System.out.println("Inorder \t: "+mEight.inorder().toString());
        System.out.println("Postorder\t: "+mEight.postorder().toString());       
        System.out.println("Level-order\t: "+mEight.levelorder().toString()); 
        Graphviz view = new Graphviz();
        view.display(mEight);
        
     //   SingleStepper
    }
}
