package ex3;

public class MySList implements Iterable<Integer> {
    class Node {
        private Integer data;
        private Node next = null;

        public Node(int digit) {
            this.data = digit;
        }

        public Integer getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNew(int data) {
            this.next = new Node(data);
        }
    }

    Node head_ = null;

    public MySList() {

    }

    public void push_back(int digit) {
        if (head_ == null)
            head_ = new Node(digit);
        else {
            Node nextNode = head_;
            while (nextNode.next != null)
                nextNode = nextNode.next;
            nextNode.setNew(digit);
        }
    }

    public Iterator iterator() {
        return new IteratorPrime();
    }

    public class Iterator implements java.util.Iterator<Integer> {

        private Node currunt;

        public Iterator() {
            if (Math.abs(head_.getData() % 2) != 1) {
                currunt = getNextNode(head_);
            } else {
                this.currunt = head_;
            }
        }

        @Override
        public boolean hasNext() {
            return currunt != null;
        }

        @Override
        public Integer next() {
            int data = currunt.getData();
            currunt = getNextNode(currunt);
            return data;
        }

        private Node getNextNode(Node start) {
            while (start.getNext() != null) {
                start = start.getNext();
                if (Math.abs(start.getData() % 2) == 1)
                    return start;
            }
            return start.getNext();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public class IteratorPrime implements java.util.Iterator<Integer> {

        private Node currunt;
        private int max;

        public Iterator() {
            if (Math.abs(head_.getData() % 2) != 1) {
                currunt = getNextNode(head_);
            } else {
                this.currunt = head_;
            }
        }
        
        public void setMax(int max) {
            this.max = max;
        }

        @Override
        public boolean hasNext() {
            return currunt != null;
        }

        @Override
        public Integer next() {
            int data = currunt.getData();
            currunt = getNextNode(currunt);
            return data;
        }

        private Node getNextNode(Node start) {
            while (start.getNext() != null) {
                start = start.getNext();
                if ( isPrime(start.getData()) && start.getData() < max)
                    return start;
            }
            return start.getNext();
        }

        public boolean isPrime(int n) {
            boolean prime = true;
            for (long i = 3; i <= Math.sqrt(n); i += 2)
                if (n % i == 0) {
                    prime = false;
                    break;
                }
            if ((n % 2 != 0 && prime && n > 2) || n == 2) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        MySList test = new MySList();

        test.push_back(85);
        test.push_back(72);
        test.push_back(93);
        test.push_back(81);
        test.push_back(74);
        test.push_back(42);
        test.push_back(3);
        for (Integer i : test)
            System.out.println(i);
    }
}
