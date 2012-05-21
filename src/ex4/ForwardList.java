package ex4;

import java.util.Stack;
    
    public class ForwardList<T> implements Iterable<T> {

        private class Node {

            T data_;
            Node next_ = null;

            public Node(T data, Node next) {
                data_ = data;
                next_ = next;
            }
  
            public Node(T data) {
                data_ = data;
            }

            public T getData() {
                return data_;
            }

            public Node getNext() {
                return next_;
            }

        }

        private Node head;

        public ForwardList() {
            head = null;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public void push_front(T e) {
            if (isEmpty())
                head = new Node(e);
            else {
                head = new Node(e, head);
            }
        }

        public void backTraverse() {
            if (isEmpty())
                System.out.println("[]");
            else
                System.out.print("[");
                backTraverse(head);
                System.out.println("]");
        }

        private void backTraverse(Node node) {
            if (node.next_ != null) {
                backTraverse(node.getNext());
                System.out.println(node.getData().toString() + " ");
            } else
                System.out.println(node.getData().toString());
        }

        public class BackIterator<E> implements java.util.Iterator<T> {

            private Stack<T> stack = new Stack<T>();

            public BackIterator() {
                if (isEmpty())
                    return;
                
                Node iterator = head;
                while (iterator.next_ != null) {
                    stack.push(iterator.getData());
                    iterator = iterator.getNext();
                }
                stack.push(iterator.getData());
            }

            @Override
            public boolean hasNext() {
                return !stack.isEmpty();
            }
            @Override
            public T next() {
                return stack.pop();
            }
            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        }

        @Override
        public BackIterator<T> iterator() {
            return new BackIterator<T>();
        }
     
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
