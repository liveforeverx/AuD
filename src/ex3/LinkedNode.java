package ex3;

public class LinkedNode<T> {

    T data_ = null;
    LinkedNode<T> next_ = null;

    // TODO: constructors
    // TODO: getters and setters
    T get(int i)
    {
        LinkedNode<T> getted = this;
        for (int j = 0; j < i; j++)
            getted = getted.next_;
        return getted.data_;
    }
    
    T get()
    {
        return data_;
    }
    
    void set(int i, T data)
    {
        LinkedNode<T> setted = this;
        for (int j = 0; j < i; j++)
            setted = setted.next_;
        LinkedNode<T> newNode = new LinkedNode<T>();
        newNode.next_ = setted.next_;
        setted.next_ = newNode;
        newNode.data_ = data;
    }
    
    
    String toText() {
        return (data_ != null ? data_.toString() : "") + " " + (next_ != null ? next_.toText() : "");       
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        LinkedNode<String> times = new LinkedNode<String>();
        times.set(0, "Weihnachten");
        times.set(1, "Neujahr");
        System.out.println(times.toText());
        times.set(1, "Silvester");
        System.out.println(times.toText());        
    }

}
