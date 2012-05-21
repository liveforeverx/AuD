package ex2;

public class JosephusProblem {

    /**
     * @param args
     */
    int num;
    
    public JosephusProblem(int num) {
        this.num = num;
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return num + " ";
    }
    
    public static <T> Queue<T> josephus2(T[] children, int numbSyl) {

        Queue<T> live = new Queue<T>();
        Queue<T> dead = new Queue<T>();

        for (int i = 0; i < children.length; ++i) {
            live.enqueue(children[i]);
        }

        while (!live.is_empty()) {
            for (int i = 0; i < numbSyl - 1; ++i)
                live.enqueue(live.dequeue());
            dead.enqueue(live.dequeue());
        }

        return dead;

    }
    
    
    public static <T> Queue<T> josephus(T[] children, int numbSyl) {
        Queue<T> queue = new Queue<T>();
        int pos = -1;
        int dead = 0;
        int p[] = new int[children.length];
        while(dead != children.length){
            int iterator = 0;
            while(iterator < numbSyl){
                pos++;
                if(pos >= p.length) pos = 0;
                iterator++;
                while(p[pos] != 0) {
                    pos++;
                    if(pos >= p.length) pos = 0;
                }
            }
            System.out.println(pos);
            queue.enqueue(children[pos]);
            p[pos] = -1;
            dead++;
        }
        return queue;
    }
    
    public static void main(String[] args) {
        JosephusProblem[] array = new JosephusProblem[10];
        for (int i = 0; i < array.length; i++) {
            array[i] = new JosephusProblem(i);
        }
        System.out.println(josephus2(array, 2).toString());
    }

}
