package main.java;

import java.util.Arrays;
import java.util.SortedMap;

import static main.java.GenderType.*;

//о методах можно почитать https://javarush.ru/groups/posts/1419-perechislenija-v-java-java-enum
//https://www.examclouds.com/ru/java/java-core-russian/perechisleniya
public class DemoEnum {

    public static void main (String [] args) {
        System.out.println("Получение инф. по значению enum:");
        System.out.println(MALE+" "+ MALE.getCode()+" "+ MALE.getDescription());
        System.out.println();

        System.out.println("Получение списка Значений enum:");
        GenderType[] genderTypes = GenderType.values();
        for (GenderType s : genderTypes) { System.out.println(s.getDescription()); };
        System.out.println();

        System.out.println("Использование в объекте:");
        Person p = new Person("Александр", "Ефремов", MALE);
        System.out.println("Переопределил toString для вывода русских наименований полей:");
        System.out.println(p.toString());
        System.out.println();
        System.out.println("Получение информации об объекте:");
        System.out.println(p.getLastName()+" "+p.getFirstName()+" - "+p.getGender().getDescription());
        System.out.println();

        System.out.println("Сравнение ==:");
        if (p.getGender()==MALE) {
            System.out.println("Мужчина");
        }
        System.out.println();
        System.out.println("Сравнение equals:");
        if (p.getGender().equals(MALE)) {
            System.out.println("Мужчина");
        }
    }
}
