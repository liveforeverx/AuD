package ex6;

public class SimpleTree<T extends Comparable<T>> {
    
    public class Node {

        protected Node parent;

        protected Node left;
        protected Node right;

        protected T data;

        public Node(T data) {
            this.data = data;
        }

        public Node(Node parent, T data) {
            this.parent = parent;
            this.data = data;
        }
        
        public String toString(){
            return 
            (left != null ? left.toString() : "") +
            data.toString() + " " +
            (right != null ? right.toString() : "");
            
        }
    }
    
    public class Search {
        boolean left = true;
        
        public Search(Node root) {
            current = root;
            parent = root;
        }
        
        public void save_parent(){
            parent = current;
        }
        
        public void toRight(){
            current = current.right;
            left = false;
        }

        public void toLeft(){
            current = current.left;
            left = true;
        }
        
        public T data(){
            return current.data;
        }
        
        public boolean currentNull(){
            return current == null ? true : false;
        }
        
        public boolean found(){
            return !currentNull();
        }
        
        public boolean root(){
            return ((current == parent) && (current == null)) ? true : false; 
        }
        
        public void createNewNode(T obj){
            if(currentNull()){
                if(left){
                    parent.left = new Node(parent, obj);
                } else {
                    parent.right = new Node(parent, obj);
                }
            }
        }
        
        Node parent;
        Node current;
    }
    
    private Node root = null;

    public SimpleTree() {
        // EMPTY
    }

    protected SimpleTree(Node parent, T data) {
        root = new Node(parent, data);
    }

    public void insert(T obj) {
        Search newSearch = node_search(obj);
        if(newSearch.root()) {
            root = new Node(obj);
        } else newSearch.createNewNode(obj);
    }

    public boolean search(T obj) {
        Search newSearch = node_search(obj);
        return newSearch.found() ? true : false;
    }

    private Search node_search(T obj){
        Search search = new Search(root);
        if (root == null)
            return search;
        else {
            while (!search.data().equals(obj)) {
                search.save_parent();
                if (search.data().compareTo(obj) < 0) {
                    search.toRight();                    
                }
                else if (search.data().compareTo(obj) > 0) {
                    search.toLeft();
                }
                if (search.currentNull()) {
                    break;
                }
            }
            return search;
        }
    }

    public String toString() {
        if (root == null) return "";
        return root.toString();
    }
    
    public static void main(String[] args) {
        SimpleTree<Integer> myTree = new SimpleTree<Integer>();

        myTree.insert(5);
        myTree.insert(2);
        myTree.insert(1);
        myTree.insert(3);

        myTree.insert(8);
        myTree.insert(6);
        myTree.insert(9);
        myTree.insert(7);

        myTree.insert(9);

        System.out.println(myTree);

        System.out.println(myTree.search(2));
        System.out.println(myTree.search(-2));  
    }
 }
