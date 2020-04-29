package Olya;

import java.lang.reflect.Array;
import java.util.Arrays;

import static Olya.SeasonType.*;

public class DemoEnumSeason {
    public static void main(String[] args) {
        System.out.println(WINTER + " " + WINTER.getCode() + " " + WINTER.getName());
        SeasonType s1 = SeasonType.valueOf("SUMMER");
        System.out.println(s1);

        // Преобразуем в String
        String word = SPRING.toString();
        System.out.println(word);

        // Вывести значения enum:
        // 1 способ
        SeasonType [] s2 = SeasonType.values();
        for (SeasonType x : s2) {
            System.out.println (x);
        }
        // 2 способ
        for (SeasonType k : SeasonType.values()) {
            System.out.print (k + " ");
        }
        System.out.println();

        // 3 способ
        System.out.println(Arrays.toString(SeasonType.values()));

        System.out.println("Узнаем имя у перечисления SUMMER: " + SUMMER.name() + " и его порядковый номер: " + SUMMER.ordinal());

        System.out.println("Получить название сезона по коду: " + SeasonType.getNamebyCode(4));

        System.out.print("Получить описание месяца: ");
        SeasonType.description(AUTUMN);

        // Создание объектов
        Holiday madrid = new Holiday("Испания", SUMMER);
        Holiday tokyo = new Holiday("Япония", SPRING);

        System.out.println(madrid.toString());
        System.out.println("Получение информации об объекте: ");
        System.out.println(tokyo.country + ", " + tokyo.nameSeason.getName());

        // Сравнение
        System.out.println(WINTER.equals(SUMMER));
        System.out.println(WINTER.equals(WINTER));
        System.out.println(WINTER.compareTo(AUTUMN));
        System.out.println(AUTUMN.compareTo(AUTUMN));

        if (madrid.nameSeason == SUMMER) System.out.println("Это лето");
        if (madrid.nameSeason.equals(SUMMER)) System.out.println("Это лето");
    }
}
