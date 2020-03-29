package main.java;

import java.util.Arrays;

//Задачи с собеседования
public class DemoArrayAndCycle {

    //Напишите функцию вычисления факториала
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

    //Напишите функцию вычисления первых числе Фибоначчи
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

    //Реализуйте функцию, которая для массива целых чисел возвращает массив положительных чисел,
    //отсортированных по возрастанию
    public static int[] toArrayAscPositive(int[] a) {
        int count_positive=0;
        //Если не клонировать, а просто присвоить, то поскольку массив это объект,
        //то B присвоится ссылка на объект A и при изменении B изменится A
        //при клонировании B будет создан самостоятельно
        int[] b = a.clone();
        //сортировка пузырьком
        for (int i = 0; i < b.length - 1; i++) {
            for (int j = b.length - 1; j > i; j--) {
                if (b[j - 1] > b[j]) {
                    int tmp = b[j - 1];
                    b[j - 1] = b[j];
                    b[j] = tmp;
                }
            }
        }
        //подсчитываем количество положительных чисел
        for (int i : a) {
            if (i>0) {
                count_positive=count_positive+1;
            }
        }
        //cоздаем массив для положительных чисел
        int arr [] = new int[count_positive];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=b[b.length-arr.length+i];
        }
        return arr;
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
    //Метод должен иметь сигнатуру String toBinary(int number)
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
            System.out.println("Числа Фибоначи 1-"+n+": ");
            System.out.print("вывод foreach: ");
            //вызов fibonachi
            //массивы https://www.examclouds.com/ru/java/java-core-russian/massivi
            int[] fibonachi = fibonachi(n);
            //цикл foreach https://www.examclouds.com/ru/java/java-core-russian/for-each-cycle
            for (int s : fibonachi) {
                System.out.print(s+" ");
            }
            System.out.println();
            //Статичный метод Arrays.toString() возвращает строковое представление одномерного массива,
            // разделяя элементы запятой. Вместо того, чтобы перебирать массивы циклом for,
            // можно воспользоваться этим методом для вывода элементов на консоль.
            // fibonachi.toString() выведет лишь ссылку на объект
            System.out.println("вывод Arrays.toString: "+Arrays.toString(fibonachi));
            System.out.println("вывод fibonachi.toString: "+fibonachi.toString());
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

        System.out.print("toArrayAscPositive: ");
        int arr1[] = {11, -10, 9, 100, -100, 0};
        int arr2[] = toArrayAscPositive(arr1);
        for (int i : arr2) {
            System.out.print(i+" ");
        }
        System.out.println();

        int arr3[] = {-100, -10, 0, 9, 11, 100};
        System.out.println("closeTo10: "+closeTo10(arr3));
        int a=444896;
        System.out.println("toBinary: "+a+" = "+toBinary(a));
    }
}
