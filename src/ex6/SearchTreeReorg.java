package ex6;

import aud.BinarySearchTree;
import aud.BinaryTree;
import aud.Vector;
import aud.util.DotViewer;

import java.util.Stack;

public class SearchTreeReorg<Key extends Comparable<Key>, Value> extends
        BinarySearchTree<Key, Value> {

    public SearchTreeReorg() {
        super();
    }

    public String levelOrder() {
        return head_.getRight().levelorder().toString();
    }

    public SearchTreeReorg<Key, Value> reorganize() {
        Vector<BinarySearchTree<Key, Value>.Entry> vector = new Vector<BinarySearchTree<Key, Value>.Entry>();
        boolean head = true;
        for (BinaryTree<BinarySearchTree<Key, Value>.Entry> entry : head_
                .inorder()) {
            if (head == true)
                head = false;
            else
                vector.push_back(entry.getData());
        }
        System.out.println(vector.toString());
        SearchTreeReorg<Key, Value> newTree = reorganize2(vector);
        return newTree;
    }

    public SearchTreeReorg<Key, Value> reorganize(Vector<Entry> vector) {
        SearchTreeReorg<Key, Value> newTree = new SearchTreeReorg<Key, Value>();
        reorganize(newTree, vector, 0, vector.size() - 1);
        return newTree;
    }

    public SearchTreeReorg<Key, Value> reorganize2(Vector<Entry> vector) {
        SearchTreeReorg<Key, Value> tree = new SearchTreeReorg<Key, Value>();
        Stack<Integer> stack = new Stack<Integer>();
        stack.add(0);
        stack.add(vector.size() - 1);
        do {
            int end = stack.pop();
            int start = stack.pop();            
            if(start <= end){
                int mid = start + (end - start) / 2;
                tree.insert(vector.at(mid).getKey(), vector.at(mid).getValue());
                int[] array = {mid + 1, end, start, mid - 1};
                stackPusher(stack, array);
            } 
        } while(  ! stack.isEmpty() );
        return tree;
    }

    public void stackPusher(Stack<Integer> stack, int[] array) {
        for (Integer e : array) {
            stack.push(e);
        }
    }

    public void reorganize(SearchTreeReorg<Key, Value> tree,
            Vector<Entry> vector, int start, int end) {
        if (start > end)
            return;
        int mid = start + (end - start) / 2;
        tree.insert(vector.at(mid).getKey(), vector.at(mid).getValue());
        reorganize(tree, vector, start, mid - 1);
        reorganize(tree, vector, mid + 1, end);
    }

    public static void main(String[] args) {
        SearchTreeReorg<Integer, Integer> tree = new SearchTreeReorg<Integer, Integer>();
        tree.insert(20, 1);
        tree.insert(5, 1);
        tree.insert(25, 1);
        tree.insert(11, 1);
        tree.insert(22, 1);
        tree.insert(27, 1);
        tree.insert(10, 1);
        tree.insert(14, 1);
        tree.insert(30, 1);
        tree.insert(17, 1);
        tree.insert(33, 1);
        System.out.println(tree.levelOrder());
        SearchTreeReorg<Integer, Integer> newTree = tree.reorganize();
    }
}