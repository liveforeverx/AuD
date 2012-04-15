package ex1;
/*
 * 1) Rekursion, wo letzte Aufruf ist das funktion selbst
 */

public class TailRecursive {
    
    public static int pot2TailRec(int n) {
        return n==0 ? 1 : pot2TailRec(n, 1);
    }
    
    private static int pot2TailRec(int n, int acc)
    {
        return n==0 ? acc : pot2TailRec(n - 1, acc*2);
    }

     static public int sumFacTailRec(int n) {
         return sumFacTailRec(n, n/2, 1);       
     }
     
     static private int sumFacTailRec(int firstdigit, int n, int acc) {
         return n==1 ? acc : sumFacTailRec(firstdigit, n-1, firstdigit%n == 0 ? acc + n : acc);       
     }

     public static void main(String[] args) {
         System.out.println(pot2TailRec(4));
         System.out.println(sumFacTailRec(10));
     }
}
