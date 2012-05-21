package ex5;

import java.util.Stack;

import aud.BinaryTree;

public class IntBinTree extends BinaryTree<Integer> {

    public static int LEFT = 1;
    public static int RIGHT = 2;

    public IntBinTree(Integer data) {
        super(data);
    }

    public IntBinTree(int data) {
        super(data);
    }

    public IntBinTree(int data, IntBinTree left, IntBinTree right) {
        super(data, left, right);
    }

    public IntBinTree setChild(int type, IntBinTree child) {
        if (type == LEFT)
            this.setLeft(child);
        else
            this.setRight(child);
        return this;
    }

    private static PathTraverser height = new PathTraverser() {
        int height;
        int actual;

        @Override
        public void pathDown(int el) {
            actual++;
        }

        @Override
        public int output() {
            return height;
        }

        @Override
        public void pathUp(int el, int state) {
            if(state == PATHDOWN) height = actual > height ? actual : height; 
            actual--;
        }
    };

    public int height() {
        return traverse(height);
    }

    private static PathTraverser maxPathTree = new PathTraverser() {
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        @Override
        public void pathDown(int el) {
        }
        @Override
        public void pathUp(int el, int state) {
            if(state == PATHDOWN) stack.push(el);
            if(state == SUBTREE1) stack.push(el + stack.pop());
            if(state == SUBTREE2) stack.push(el + stack.pop() + stack.pop());
            max = stack.peek() > max ? stack.peek() : max;
        }
        
        @Override
        public int output() {
            // TODO Auto-generated method stub
            return max;
        }
    };
    
    public int maxSum() {
        return traverse(maxPathTree);
    }

    private static PathTraverser maxPath = new PathTraverser() {
        int sum = Integer.MIN_VALUE;
        int actual;

        @Override
        public void pathDown(int el) {
            actual += el;
        }

        @Override
        public int output() {
            return sum;
        }

        @Override
        public void pathUp(int el, int state) {
            if(state == PATHDOWN) sum = actual > sum ? actual : sum;
            actual-= el;
        }

    };
    
    public int maxPath() {
        return traverse(maxPath);
    }

    public static void main(String[] args) {
        IntBinTree first = new IntBinTree(-8);
        first.setChild(
                LEFT,
                new IntBinTree(4).setChild(LEFT, new IntBinTree(6)).setChild(
                        RIGHT, new IntBinTree(-11) ));
        first.setChild(
                RIGHT,
                new IntBinTree(1).setChild(LEFT, new IntBinTree(5)).setChild(
                        RIGHT, new IntBinTree(7)));
        System.out.println(first.height());
        System.out.println(first.maxPath());
        System.out.println(first.maxSum());

    }

    public int traverse(PathTraverser Selector) {
        Stack<Integer> stack = new Stack<Integer>();
        BinaryTree<Integer> iterator = this;
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

    private BinaryTree<Integer> nextChild(Stack<Integer> stack, BinaryTree<Integer> iterator, int direction){
        stack.push(direction);
        return (direction == RIGHT) ? iterator.getRight() : iterator.getLeft();
    }
    
    public interface PathTraverser {
        public static int NOSTATE = 0;
        public static int PATHDOWN = 1;
        public static int SUBTREE1 = 2;
        public static int SUBTREE2 = 3;
        
        public void pathDown(int el);
        public void pathUp(int el, int state);
        public int output();
    }
}
