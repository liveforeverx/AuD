package ex3;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;

import aud.SList;

public class SortList<T extends Comparable<T>> {
  private SList<T> list;
  public String toString() {
        // do not change (because of backend-control)
    return list.toString();
  }
  public SortList() {
      this.list = new SList<T>(); 
  }
    
  public boolean insert(T obj) {
      Iterator<T> i = list.iterator();
      T obj2 = null;
      for (int j = 0; i.hasNext(); ++j) {
          obj2 = i.next();
          if (obj.compareTo(obj2) == 0) return false;
          if (obj.compareTo(obj2) < 0) {
              list.insert(j, obj);
              return true;
          }
      }
          list.push_back(obj);
          return true;
  }
  
  
  public static void main(String args[]) {
      SortList<Integer> test = new SortList<Integer>();
      Random rand = new Random();
      for (int i = 0; i < 20; ++i) {
          int randdigit = rand.nextInt(200);
          test.insert(randdigit);
          System.out.print(randdigit + " ");
      }
      System.out.println();

      System.out.println(test.toString());
  }
}
/*
   hier bitte Vor-, Nachbedingungen und Invariante 
   von "insert" einfuegen
*/