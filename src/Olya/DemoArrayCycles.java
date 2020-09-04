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

// Массив положительных чисел
    //Способ №1
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

// Способ №2 "для ленивых"
    public static void positive (int [] c) {
      int [] e = c.clone();
      Arrays.sort(e);
      for (int k=0; k<e.length; k++) {
          if (e[k]>0){
              System.out.print(e[k] + " ");
          }
      }
    }

    public static int closeTo5(int[] a) {
        int n=5;
        //помним, что у Array нумерация начинается с 0
        int close_to_a=a[0];
        for (int i = 0; i < a.length; i++) {
            if ((Math.abs(a[i]-n)<=Math.abs(close_to_a-n))&&(a[i]>close_to_a)) {
                close_to_a=a[i];
            }
        }
        return close_to_a;
    }

    public static String toBinary (int number){
      String str="";
      long g = number;
      if (number==0) {
          return "0";
      }
      while ((g/2)>=1) {
          str=(g%2)+str;
          g=Math.round(Math.floor(g/2));
      }
      if (g==1){
          str=g+str;
      }
      return str;
    }


    public static void main(String[] args) {
      int n=7;
      int [] fib = fibonachi(n);

      System.out.println("Числа Фибоначи: " + Arrays.toString(fib));
        System.out.println("Числа Фибоначи: " + fibonachi(n).toString());

        int f = 10;
        System.out.println("Факториал 10: " + factorial(f));

        // способ №2
        int [] array1 = {5, 12, -10, 4, -99, 18, 0};
        System.out.println("Имеется массив: " + Arrays.toString(array1));
        System.out.println("Выводим отсортированный массив, содержащий только положительные числа: ");
        positive(array1);
        System.out.println();

        // способ №1
        int [] array2 = { -88, -4, -99, 1, 7, 0};
        int [] array3 = toArraypositiveAsk(array2);
        System.out.println("Первый способ: " + Arrays.toString(array3));


        System.out.println("Ближайшее к 5 число из массива array2: " + closeTo5(array2));

        int z= 55;
        System.out.println("Переводим " + z + " в бинарное представление: " + toBinary(z));


    }
}
