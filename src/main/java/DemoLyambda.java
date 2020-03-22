package main.java;

import java.util.function.Consumer;
import java.util.function.Predicate;

/*Лямбда https://www.examclouds.com/ru/java/java-core-russian/lambda-russian
Прочитать суть и синтаксис + функциональные интерейфесы

Запомнить главное:
Общая форма записи:
(параметры) -> (тело)

Лямбда выражения являются альтернативой анонимным классам.
https://www.examclouds.com/ru/java/java-core-russian/functional-interface-russian

У меня пока скаладывается впечатление, что непосредственно для своего кода редко применимо
НО! Широко используется в StreamAPI - мощная функциональность (отдельное демо), поэтому необходимо понимать принцип
 */
public class DemoLyambda {
    public static void main (String [] args) {

        //Интерфейс Predicate https://www.examclouds.com/ru/java/java-core-russian/predicate-interface-russian
        System.out.println("Интерфейс Predicate:x>0:");
        Predicate<Integer> isPositive = x->x>0;
        System.out.println(isPositive.test(7));
        System.out.println(isPositive.test(-7));

        System.out.println("Интерфейс Predicate:contains:");
        Predicate<String> containsA = t -> t.contains("A");
        Predicate<String> containsB = t -> t.contains("C");
        System.out.println(containsA.and(containsB).test("ABCD"));
        System.out.println(containsA.and(containsB).test("ABD"));
        System.out.println();

        //Интерфейс Consumer
        System.out.println("Интерфейс Consumer:sout:");
        Consumer<String> sout = (s)->{
            //м.б. произвольная логика
            System.out.println(s);
        };
        String str = "Hello world!";
        sout.accept(str);
        System.out.println("Обычный System.out.println: "+str);

        //Остальные интерфейсы не стал приводить, можно просто почитать
    }
}
