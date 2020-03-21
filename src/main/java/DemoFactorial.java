package main.java;

public class DemoFactorial {
    public static void main(String[] args) {

        try {
            Calculator c = new Calculator();
            //задать параметр
            int f=5;
            //вызов factorial
            System.out.println("factorialRecursion: "+f+"! = "+c.factorialRecursion(f));
            System.out.println("factorialFor: "+f+"! = "+c.factorialFor(f));
        }
        catch (Exception e) {
            System.out.println("Ошибка: Обратитесь к администратору:");
            e.printStackTrace();
        }
    }
}
