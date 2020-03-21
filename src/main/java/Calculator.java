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
            } else if (false) {
                return a / b;
            }
        }
        //обработка исключения
        catch (IllegalArgumentException e)
        {
            e.printStackTrace();
        }
        return 0;
    }

    //https://www.examclouds.com/ru/java/java-core-russian/recursion
    public int factorialRecursion(int n) {
        if (n < 0) {
//            тут catch не нужен, иначе после него выполнится return n * factorial(n - 1);
//            т.е. снова вызовется factorial и так до бесконечности
            throw new IllegalArgumentException("Ошибка: Отрицательное число:" + n);
        } else if (n == 0) return 1;
        return n * factorialRecursion(n - 1);

    }

    public int factorialFor(int n) {
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
    public int[] fibonachi(int n) {
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
}