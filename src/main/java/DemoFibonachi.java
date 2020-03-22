package main.java;

public class DemoFibonachi {
    public static void main(String[] args) {
        try {
            Calculator c = new Calculator();
            //задать параметр
            int n = 30;
            System.out.println("Числа Фибоначи 1-"+n+": ");
            //вызов fibonachi
            //массивы https://www.examclouds.com/ru/java/java-core-russian/massivi
            int[] fibonachi = c.fibonachi(n);
            //цикл foreach https://www.examclouds.com/ru/java/java-core-russian/for-each-cycle
            for (int s : fibonachi) {
                System.out.print(s+" ");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println("Ошибка: Обратитесь к администратору:");
            e.printStackTrace();
        }
    }
}
