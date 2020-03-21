package main.java;

public class DemoMainException {

    public static void main(String[] args) {

        try {
            Calculator c = new Calculator();
//            Обработку исключения деления на 0 в методе divide см. метод, там же ссылки на статьи
            System.out.println(c.divide(3, 0));
//          double zero = 3/0;

//          закомментировать/расскоменировать можно ctrl+/ (строчки) или ctrl+shift+/ (часть текста)
//          Если закомментировать divide и расскомменировать double zero = 3/0, то обработается исключение и программа не упадет.
//          Process finished with exit code 0
//          Если try catch не было бы, то программа упала бы.
//          Process finished with exit code 1
        }
        catch (Exception e) {
            System.out.println("Ошибка: Обратитесь к администратору:");
            e.printStackTrace();
        }
    }
}
