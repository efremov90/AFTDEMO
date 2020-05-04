package Olya;
import java.util.Arrays;

public class DemoArrayCycles {
  public static int factorialRecursion (int n) {
      if (n<0) {
          throw new IllegalArgumentException("Error: negative number " + n);
      } else if (n==0) return 1;
      return n * factorialRecursion(n - 1);
  }

  public static int factorial (int n) {
      if (n<0) {
          throw new IllegalArgumentException("Error: negative number " + n);
      }
      else if (n==0) return 1;
      else {
          int f = 1;
          for (int i = 1; i <= n; i++){
              f=f*i;
          }
          return f;
      }
  }

public static int [] fibonachi (int n) {
      int[] f = new int[n];
      f[0] = 0;
      f[1]=1;
      for (int i =2; i < n; i++){
          f[i]=f[i-1]+f[i-2];
      }
      return f;
}

public static int []  toArraypositiveAsk (int [] a){
      int countPosotive = 0;
      int[] b= a.clone();

      for (int i=0; i<b.length-1; i++){
          for (int j=b.length-1; j>i; j--){
              if (b[j-1]>b[j]) {
                  int tmp=b[j-1];
                  b[j-1]=b[j];
                  b[j]=tmp;
              }
          }
      }
      for (int i:a) {
          if (i>0) {
              countPosotive=countPosotive+1;
          }
      }
      int [] arrayPosit = new int[countPosotive];
      for (int i = 0; i< arrayPosit.length; i++){
          arrayPosit[i]=b[b.length-arrayPosit.length+i];
      }
      return arrayPosit;
}


    public static void main(String[] args) {
      int n=7;
      int [] fib = fibonachi(n);

      System.out.println("Числа Фибоначи: " + Arrays.toString(fib));
        System.out.println("Числа Фибоначи: " + fibonachi(n).toString());

        int f = 10;
        System.out.println("Факториал 10: " + factorial(f));

    }
}
