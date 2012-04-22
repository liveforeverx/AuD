package ex2;

import ex2.DList.Node;

public class MyCircle<T> {
    class Node {
        T data_ = null;
        Node next_ = null;
        Node prev_ = null;

        Node(T obj, Node prv, Node nxt) {
            data_ = obj;
            prev_ = prv;
            next_ = nxt;
        }
    }

    protected Node head_ = null;

    public MyCircle() {
        // do not change
        head_ = null;
    }

    public T front() {
        return head_.data_;
    }

    public String toString() {
        // do not change (because of backend-control)
        if (empty())
            return "[]";
        String rv = "[";
        Node node = head_;
        do {
            rv += node.data_.toString();
            if (node.next_ != head_)
                rv += ",";
            node = node.next_;
        } while (node != head_);
        rv += "]";
        return rv;
    }

    public int size() {
        if (head_ == null)
            return 0;
        int n = 1;
        Node node = head_;
        while (node.next_ != head_) {
            node = node.next_;
            ++n;
        }
        return n;
        // TODO: Implementation
    }

    public boolean empty() {
        if (head_ == null)
            return true;
        return false;
    }

    public void push_back(T obj) {
        Node newNode = new Node(obj, null, head_);
        if (head_ != null) {
            head_.prev_.next_ = newNode;
            newNode.prev_ = head_.prev_;
            head_.prev_ = newNode;
        } else {
            head_ = newNode;
            head_.prev_ = head_;
            head_.next_ = head_;
        }
    }

    public void pop_front() {
        if (head_ == head_.prev_){
            System.out.println("next = prev");
            head_ = null;
        }
        else
        {
            head_ = head_.next_;
            head_.prev_.prev_.next_ = head_;
            head_.prev_ = head_.prev_.prev_;
        }
    }

    public static void main(String[] args) {
        MyCircle<Integer> circle = new MyCircle<Integer>();
        circle.push_back(1);
        circle.push_back(2);
        circle.push_back(3);
        circle.push_back(4);
        System.out.println(circle.toString());
        System.out.println(circle.size());
        circle.pop_front();
        circle.pop_front();
        circle.pop_front();
        System.out.println(circle.toString());
        System.out.println(circle.size());
    }
}