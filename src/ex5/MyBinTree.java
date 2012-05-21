package ex5;

import java.util.Stack;
import java.util.Vector;

import java.util.LinkedList;

import aud.BinaryTree;

public class MyBinTree<T> extends BinaryTree<T>{
    public static int LEFT = 1;
    public static int RIGHT = 2;
    
    public MyBinTree(T data) {
        super(data);
    }
    
    public MyBinTree(T data,MyBinTree<T> left,MyBinTree<T> right){
        super(data, left, right);
    }

    private static PathTraverser maxWidth = new PathTraverser() {
        int height;
        Vector<Integer> heights = new Vector<Integer>(10);

        @Override
        public void pathDown(Object el) {
            height++;
            if(heights.size() < height)
                heights.addElement(new Integer(1));
            else 
                heights.set((height - 1), new Integer(1 + heights.get(height - 1)));
        }

        @Override
        public int output() {
            int max = Integer.MIN_VALUE;
            System.out.println(heights.toString());
            for (Integer i : heights) {
                if(i.intValue() > max) max = i.intValue();
            }
            return max;
        }

        @Override
        public void pathUp(Object el, int state) {
            height--;
        }
    };

    public int maxWidth(){
        return traverse(maxWidth);
    }


    public int maxWidth1(){
        int maxwidth = 0;
        
        int actual = 0;
        int niveau = 1;
        int niveaunext = 0;

        LinkedList<BinaryTree<T> > queue = new LinkedList<BinaryTree<T>>();    
        queue.addLast(this);
        while (!queue.isEmpty()) {
            actual++;
            BinaryTree<T> node=queue.poll();
            if (node.getLeft() !=null) {niveaunext++; queue.addLast(node.getLeft());}
            if (node.getRight()!=null) {niveaunext++; queue.addLast(node.getRight());}
            if(niveau == actual){
                if(niveau > maxwidth) maxwidth = niveau;
                niveau = niveaunext;
                niveaunext = 0;
                actual = 0;
            }
        }
        return maxwidth;
    }
    
    public static void main(String[] args) {
        
        MyBinTree<Integer> t1 = new MyBinTree<Integer>(6);
        MyBinTree<Integer> t2 = new MyBinTree<Integer>(-11, t1, null);
        MyBinTree<Integer> t3 = new MyBinTree<Integer>(5);
        MyBinTree<Integer> t4 = new MyBinTree<Integer>(7);
        MyBinTree<Integer> textra = new MyBinTree<Integer>(7);
        MyBinTree<Integer> t5 = new MyBinTree<Integer>(4, t2, textra);
        MyBinTree<Integer> t6 = new MyBinTree<Integer>(1, t3, t4);
        MyBinTree<Integer> t7 = new MyBinTree<Integer>(-8, t5, t6);
        System.out.println(t7.maxWidth());
        System.out.println(t7.maxWidth1());
    }
    
    public int traverse(PathTraverser Selector) {
        Stack<Integer> stack = new Stack<Integer>();
        BinaryTree<T> iterator = this;
        int state = PathTraverser.NOSTATE;
        do {
            Selector.pathDown(iterator.getData());
            if (iterator.getLeft() != null) {
                iterator = nextChild(stack, iterator, LEFT);
            } else if (iterator.getRight() != null) {
                iterator = nextChild(stack, iterator, RIGHT);
            } else {
                boolean whiletrue = true;
                state = PathTraverser.PATHDOWN;
                while (whiletrue && iterator != this) {
                    Selector.pathUp(iterator.getData(), state);
                    iterator = iterator.getParent();
                    if (iterator.getRight() != null && stack.peek() == LEFT) {
                        whiletrue = false;
                        stack.pop();
                        iterator = nextChild(stack, iterator, RIGHT);
                    } else {
                        state = (iterator.getLeft() == null || iterator.getRight() == null) ? 
                                PathTraverser.SUBTREE1 : PathTraverser.SUBTREE2;
                        stack.pop();
                    }
                }
            }
        } while (iterator != this);
        Selector.pathUp(iterator.getData(), state);
        
        return Selector.output();
    }

    private BinaryTree<T> nextChild(Stack<Integer> stack, BinaryTree<T> iterator, int direction){
        stack.push(direction);
        return (direction == RIGHT) ? iterator.getRight() : iterator.getLeft();
    }
    
    public interface PathTraverser {
        public static int NOSTATE = 0;
        public static int PATHDOWN = 1;
        public static int SUBTREE1 = 2;
        public static int SUBTREE2 = 3;
        
        public void pathDown(Object el);
        public void pathUp(Object el, int state);
        public int output();
    }
}