package main.java;

public class DemoArrayAndCycle {

    //https://www.examclouds.com/ru/java/java-core-russian/recursion
    public static int factorialRecursion(int n) {
        if (n < 0) {
//            тут catch не нужен, иначе после него выполнится return n * factorial(n - 1);
//            т.е. снова вызовется factorial и так до бесконечности
            throw new IllegalArgumentException("Ошибка: Отрицательное число:" + n);
        } else if (n == 0) return 1;
        return n * factorialRecursion(n - 1);

    }

    public static int factorialFor(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Ошибка: Отрицательное число:" + n);
        } else if (n == 0) {
            return 1;
        }
        else {
            int f=1;
            for (int i = 1; i <= n; i++) {
                f=f*i;
            }
            return f;
        }
    }

    //    https://ru.wikipedia.org/wiki/Числа_Фибоначчи
    public static int[] fibonachi(int n) {
        //массивы https://www.examclouds.com/ru/java/java-core-russian/massivi
        int[] f = new int[n];
        f[0]=0;
        f[1]=1;
        //цикл https://www.examclouds.com/ru/java/java-core-russian/lesson5
        for (int i=2; i<n; i++) {
            f[i]=f[i-1]+f[i-2];
        }
        return f;
    }

    //Реализуйте функцию, которая для данного массива целых чисел (int)
    //возвращает значение наиболее близкое к 10. Если существует два одинаковых близких
    //значения (например, 8 и 12), считайте большее значение более близким
    public static int closeTo10(int[] a) {
        int n=10;
        int close_to_a=a[0];
        for (int i = 0; i < a.length; i++) {
            if ((Math.abs(a[i]-n)<=Math.abs(close_to_a-n))&&(a[i]>close_to_a)) {
                close_to_a=a[i];
            }
        }
        return close_to_a;
    }

    //Реаизуйте функцию, возвращающую двоичное представление числа n(n>=0).
    //Например, 101 - это двоичное представление числа 5
    public static String toBinary(int number) {
        String str="";
        long rest=number;
        if (number==0) {
            return "0";
        }
        while ((rest/2)>=1) {
            str=(rest%2)+str;
            rest=Math.round(Math.floor(rest/2));
        };
        if (rest==1) {
            str=rest+str;
        }
        return str;
    }

    public static void main (String [] args) {
        try {
            Calculator c = new Calculator();
            //задать параметр
            int n = 30;
            System.out.print("Числа Фибоначи 1-"+n+": ");
            //вызов fibonachi
            //массивы https://www.examclouds.com/ru/java/java-core-russian/massivi
            int[] fibonachi = fibonachi(n);
            //цикл foreach https://www.examclouds.com/ru/java/java-core-russian/for-each-cycle
            for (int s : fibonachi) {
                System.out.print(s+" ");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Ошибка: Обратитесь к администратору:");
            e.printStackTrace();
        }

        try {
            Calculator c = new Calculator();
            //задать параметр
            int f=5;
            //вызов factorial
            System.out.println("factorialRecursion: "+f+"! = "+factorialRecursion(f));
            System.out.println("factorialFor: "+f+"! = "+factorialFor(f));
        }
        catch (Exception e) {
            System.out.println("Ошибка: Обратитесь к администратору:");
            e.printStackTrace();
        }

        int arr[] = {-100, -10, 0, 9, 11, 100};
        System.out.println("closeTo10: "+closeTo10(arr));
        int a=444896;
        System.out.println("toBinary: "+a+" = "+toBinary(a));
    }
}
