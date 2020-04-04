package Olya;
import  java.util.Arrays;

public class DemoComplexArray {
    public static void main (String [] args) {
        int [][] array1 = new int [4][5];

        // Заполняем массив значениями, увеличивающимися на единицу, и выводим на экран
        int a=0;
        for (int i=0; i<array1.length; i++) {
            for (int j=0; j<array1 [i].length; j++) {
                array1 [i][j] = a++;
                System.out.print(array1 [i][j] + "\t");
            }
            System.out.println();
        }

        /* Просто выводим на экран значения элементов массива
        System.out.println();
        for (int i=0; i<4; i++) {
            for (int j=0; j<5; j++) {
                System.out.print(array1 [i][j] + "\t");
            }
            System.out.println();
        }*/
        System.out.println("Элемент массива array1 [0][2] = " + array1 [0][2]);

        // Объявляем массив с уже известными значениями и выводим на экран (способ 1)
        int array2 [][] = {{100, 101, 102}, {103, 104, 105}};
        for (int i=0; i<array2.length; i++)  {
            for (int j=0; j<array2 [i].length; j++) {
                System.out.print(array2 [i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();

        // Объявляем массив с уже известными значениями и выводим на экран (способ 2)
        int array3 [][] = {
                {10, 11, 12},
                {13, 14, 15}
        };
        for (int [] i : array3)  {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }

        // Объявляем массив строк и выводим на экран (способ через import)
        String array4 [][] = {
                {"Фамилия", "Имя", "Отчество"},
                {"Ефремова", "Ольга", "Александровна"}
        };
                System.out.print(Arrays.deepToString(array4));

    }
}
