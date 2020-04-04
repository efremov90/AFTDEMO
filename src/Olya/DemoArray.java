package Olya;
import  java.util.Arrays;

public class DemoArray {
    public static void main (String [] args) {
        int [] array1 = new int[10];
        int [] array2 = {4, 5, 6, 10, 12};

        // Определяем третий элемент массива array2
        System.out.println("Второй элемент массива array2 = " + array2 [2]);

        // Выводим на экран все элементы массива array2 (способ 1)
        for (int i=0; i<array2.length; i++) {
            System.out.print(array2 [i] + " ");
        }
        System.out.println();

        // Определяем длину массива array2
        System.out.println("Длина массива array2 = " + array2.length);
        // Выводим на экран все элементы массива array2 (способ 2)
        for (int element: array2) {
            System.out.print(element + " " );
        }
        System.out.println("Данный масссив был изменен на: " );

        // Изменяем количественный и качественный состав массива array2
        array2 = new int[] {5, 6, 7, 11};
        for (int newelement : array2) {
            System.out.print(newelement + " ");
        }
        System.out.println();

        // Посчитаем сумму элементов массива array2 (способ 1)
        int sum = 0;
        for (int i =0; i<array2.length; i++) {
            sum += array2 [i];
        }
        System.out.println("Сумма элементов массива array2 = " + sum);
        System.out.println();
        // Посчитаем сумму элементов массива array2 (способ 2)
        int sum2 = 0;
        for (int element : array2) {
            sum2 += element;
        }
        System.out.println("Сумма элементов массива array2 = " + sum2);
        System.out.println("Среднее арифметическое элементов массива array2 = " + sum2/array2.length);
        System.out.println();
        // Заполняем элементы массива array1 значениями, увеличивающимися на 1, и выводим их на экран
        int a=0;
        for (int j=0; j<10; j++) {
            array1 [j] = a++;
            System.out.print(array1 [j] + " ");
        }
        System.out.println();
        // Создаем массив и сортируем значения по возрастанию  и выводим на экран с помощью import
        int[] array5 = {12, 5, 7, 18, 2, 1};
        Arrays.sort(array5);
        System.out.println(Arrays.toString(array5));
        System.out.println();

        /* Создаем массив из 3 элементов, в который (заполняя с 0 позиции) копируем 3 значения из отсортированного
        ранее массива array5, начиная с позиции 2 (т.е. цифры 5, 7, 12)
        Выводим новый массив на экран
         */
        int[] array6 = new int [3];
        System.arraycopy(array5, 2, array6, 0, 3);
        System.out.println(Arrays.toString(array6));
    }
}
