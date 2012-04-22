package ex2;

public class MyDList<T> extends DList<T> {
    public MyDList() {
        super();
    }

    public void append(MyDList<T> li) {
        int n = li.size();
        for (int i = 0; i < n; i++) {
            this.push_back(li.at(i));
        }
    }

    public void insert(int n, MyDList<T> li) {
        int size = li.size();
        for (int i = 0; i < size; i++) {
            this.insert(n, li.at(i));
            n++;
        }
    }

    public static void main(String[] args) {
        MyDList<Integer> list = new MyDList<Integer>();
        MyDList<Integer> list2 = new MyDList<Integer>();
        list.push_back(100);
        list.push_back(200);
        System.out.println(list.toString());
        list2.push_back(100);
        list2.push_back(200);
        System.out.println(list2.toString());
        list.insert(1, list2);
        System.out.println(list.toString());
    }
}
