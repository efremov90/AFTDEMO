package main.java;

public class Calculator {
    public double divide(double a, double b) {
//      пример обработки исключений
//      https://metanit.com/java/tutorial/2.10.php
//      https://metanit.com/java/tutorial/4.3.php в комментариях написано зачем писать свои классы Exception
//        также полезно https://www.examclouds.com/ru/java/java-core-russian/lesson17
        try {
            if (b == 0) {
                //принудительно генерим исключение
                throw new IllegalArgumentException("Ошибка: Деление на 0.");
            } else if (true) {
                return a / b;
            }
        }
        //обработка исключения - конкретного, т.к. базовый это Exception, а тут IllegalArgumentException
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        return 0;
    }
}