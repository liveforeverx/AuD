package ex3;

import java.util.Iterator;

import aud.SList;

public class IntSList {
    SList<Integer> li;

    private static Predicate filter = new Predicate() {
        @Override
        public boolean test(int el) {
            return el % 2 == 0;
        }
    };
    
    public IntSList() {
        li = new SList<Integer>();
    }

    public String toString() {
        return li.toString();
    }

    public void push_back(int obj) {
        li.push_back(obj);
    }

    public IntSList filter(Predicate p) {
        IntSList resultlist = new IntSList();

        Iterator<Integer> i = this.li.iterator();
        Integer data;
        while (i.hasNext()) {
            data = i.next();
            if (p.test(data))
                resultlist.push_back(data);
        }
        return resultlist;
    }

    public static void main(String args[]) {
        IntSList test = new IntSList();
        Object x = new Object(){
            public String toString(){
                return "xyz";
            }
        };
        System.out.println(x.toString());
        test.push_back(85);
        test.push_back(-72);
        test.push_back(93);
        test.push_back(81);
        test.push_back(-74);
        test.push_back(42);
        System.out.println(test.toString());

        IntSList filtered = test.filter(filter);

        System.out.println(filtered);
    }

}
