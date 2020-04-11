package main.java;

import java.util.function.*;

/*Лямбда https://www.examclouds.com/ru/java/java-core-russian/lambda-russian
Широко используется в StreamAPI - мощная функциональность (отдельное демо), поэтому необходимо понимать принцип

Общая форма записи:
(параметры) -> (тело)

Лямбда выражения являются альтернативой анонимным классам.
По сути метод без объявления, т.е. без модификаторов доступа, возвращающие значение и имя.
Они позволяют написать метод и сразу же использовать его. Особенно полезно в случае однократного
вызова метода, т.к. сокращает время на объявление и написание метода без необходимости создавать класс.
Ещё особенность в том, что можно передавать в другие методы в качестве аргумента (см. DEMOStreamAPI).

Лямбда выражения преобразуются в статические private методы класса, в котором они используют invokedynamic инструкцию.
Чтобы иметь возможность использовать лямбда выражения, необходим интерфейс.
Лямбда-выражение — это по сути реализация интерфейса с одним методом, его метода.
https://www.examclouds.com/ru/java/java-core-russian/functional-interface-russian
 */

//Cвой функциональный интерфейс
interface MyFunctionInterface<A, B, C, D> {
    //один метод
    D execute(A a, B b, C c);
}

public class DemoLyambda {
    public static void main (String [] args) {

        //Cвой функциональный интерфейс
        System.out.println("Интерфейс MyFuncInt:x*y*z:");
        //реализация метода интерфейса: (x,y,z) - выходные параметры, x*y*z - тело
        MyFunctionInterface<Integer,Integer,Integer,Integer> myFuncInt = (x,y,z) -> x*y*z;
        System.out.println(myFuncInt.execute(1,2,3));
        System.out.println();

        //Встроенные функциональные интерфейсы
        //Интерфейс Predicate https://www.examclouds.com/ru/java/java-core-russian/predicate-interface-russian
        //Принимает на вход значение, проверяет состояние и возвращает boolean значение в качестве результата.
        System.out.println("Интерфейс Predicate:x>0:");
        Predicate<Integer> isPositive = x -> {
            //м.б. произвольная логика
            //важно, чтобы в итоге возвращалось return логическое boolean значение
            //если всё обходится одним выражением, то можно писать короче, например, x -> x>0;
            return x>0;
        };
        System.out.println(isPositive.test(7));
        System.out.println(isPositive.test(-7));

        //Существуют бинарные специализации интерфейсов Predicate, Consumer, Function
        // https://www.examclouds.com/ru/java/java-core-russian/functional-interface-russian
        System.out.println("Интерфейс BiPredicate:x>y:");
        BiPredicate<Integer,Integer> xOverY = (x, y) -> (x > y);
        System.out.println(xOverY.test(2,1));
        System.out.println(xOverY.test(1,2));

        //Predicate интерфейс имеет методы and, or, negate
        System.out.println("Интерфейс Predicate:contains:");
        Predicate<String> containsA = t -> t.contains("A");
        Predicate<String> containsC = t -> t.contains("C");
        //Если перейти в реализацию and, то можно увидеть return (t) -> test(t) && other.test(t),
        //т.е. containsA.and(containsC).test("ABCD") эквивалентен
        //containsA.test("ABCD") && containsC.test("ABCD")
        System.out.println(containsA.and(containsC).test("ABCD"));
        System.out.println(containsA.and(containsC).test("ABD"));
        System.out.println();

        //Интерфейс Consumer https://www.examclouds.com/ru/java/java-core-russian/consumer-interface-russian
        //Consumer интерфейс используется в случае, если необходимо передать объект на вход
        // и произвести над ним некоторые операции, не возвращая результат.
        System.out.print("Интерфейс Consumer:sout: ");
        Consumer<String> sout = (s) -> {
            //м.б. произвольная логика, необязательно вывод
            //если всё обходится одним выражением, то можно писать короче, например, (s) -> System.out.println(s);
            System.out.println(s);
        };
        String str = "Hello world!";
        sout.accept(str);
        System.out.println("Обычный System.out.println: "+str);

        //Если лямбда выражения вызывают только один существующий метод, лучше ссылать на этот метод по его имени.
        // Ссылки на методы (Method References) – это компактные лямбда выражения для методов и конструктров у которых
        // уже есть имя.
        //https://www.examclouds.com/ru/java/java-core-russian/method-references-russian
        System.out.print("Интерфейс Consumer:Method References: ");
        //System.out::println равносильно (s) -> System.out.println(s);
        Consumer<String> soutMR = System.out::println;
        soutMR.accept(str);

        //Интерфейс Supplier https://www.examclouds.com/ru/java/java-core-russian/supplier-interface-russian
        //Интерфейс Supplier используется тогда, когда на вход не передаются значения, но необходимо вернуть результат.
        System.out.print("Интерфейс Consumer:getStringHelloWorld: ");
        String sHello = "Hello";
        String sWorld = "World";
        Supplier<String> getStringHelloWorld = () -> {
            //м.б. произвольная логика
            //важно, чтобы в итоге возвращалось return логическое выражение
            //если всё обходится одним выражением, то можно писать короче, например, () -> sHello+" "+sWorld+"!";
            return sHello+" "+sWorld+"!";
        };
        System.out.println(getStringHelloWorld.get());

        //Интерфейс Function https://www.examclouds.com/ru/java/java-core-russian/function-interface-russian
        //Принимает значение в качестве аргумента одного типа и возвращает другое значение.
        // Часто используется для преобразования одного значения в другое
        System.out.print("Интерфейс Consumer:getStringHelloWorld: ");
        Function<Integer, Integer> increaseBy10 = (t) -> {
            //м.б. произвольная логика
            //важно, чтобы в итоге возвращалось return логическое выражение
            //если всё обходится одним выражением, то можно писать короче, например, (t) -> t+10;
            return t+10;
        };
        System.out.println(increaseBy10.apply(5));
    }
}
