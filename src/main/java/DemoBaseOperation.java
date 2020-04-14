package main.java;

//базовые операции https://www.examclouds.com/ru/java/java-core-russian/lesson4
public class DemoBaseOperation {

    //Реализуйте функцию, которая для заданного числа int
    // печатает "foo", если это число делится на 3 без остатка,
    // печатает "bar", если это число делится на 5 без остатка,
    // печатает "foobar", если это число делится на 15 без остатка,
    // печатает само себя в остальных случаях

    public static void printFooBar(int a) {
        System.out.print(a + ": ");
        if ((a % 3) == 0) {
            System.out.print("bar ");
        }
        if ((a % 5) == 0) {
            System.out.print("foo ");
        }
        if ((a % 15) == 0) {
            System.out.print("foobar ");
        }
        if (((a % 3) != 0) && ((a % 5) != 0)) {
            System.out.print(a);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printFooBar(3);
        printFooBar(5);
        printFooBar(15);
        printFooBar(13);
    }
}
