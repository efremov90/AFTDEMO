package main.java;

public class DemoForArray {

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
        int arr[] = {-100, -10, 0, 9, 11, 100};
        System.out.println("closeTo10: "+closeTo10(arr));
        int a=444896;
        System.out.println("toBinary: "+a+" = "+toBinary(a));
    }
}
