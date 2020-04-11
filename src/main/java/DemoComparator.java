package main.java;

import java.util.*;

import static main.java.GenderType.FEMALE;
import static main.java.GenderType.MALE;

//https://www.examclouds.com/java/java-core-russian/interface-comparator
//https://riptutorial.com/java/example/387/using-lambda-expressions-to-sort-a-collection
//класс реализует интерфейс Comparator для Person
class PersonComparatorGenderDescription implements Comparator<Person> {

    //переопределен метод
    @Override
    public int compare(Person o1, Person o2) {
        return o1.getGender().getDescription().compareTo(o2.getGender().getDescription());
    }
}

public class DemoComparator {
    public static void main(String[] args) {

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Имя2", "Фамилия2", MALE));
        persons.add(new Person("Имя1", "Фамилия1", FEMALE));
        persons.add(new Person("Имя4", "Фамилия4", MALE));
        persons.add(new Person("Имя3", "Фамилия3", FEMALE));

        System.out.println("До сортировки:");
        System.out.println(persons.toString());

        //Через отдельно объявленный PersonComparatorGenderDescription
        Collections.sort(
                persons,
                new PersonComparatorGenderDescription()
        );
        System.out.println("Сортировка по полу:");
        System.out.println(persons.toString());

        //Через lyambda, т.е. анонимный метод аналогичный compare в PersonComparatorGenderDescription
        Collections.sort(
                persons,
                (Person o1,Person o2)->(o1.getGender().getDescription().compareTo(o2.getGender().getDescription()))
        );
        System.out.println("Сортировка по полу:lyambda:");
        System.out.println(persons.toString());

        //Через Comparator.comparing
        //Если открыть реализацию Comparator.comparing, то можно увидеть, что это метод принимает Function интерфейс,
        // т.е. lyambda (но выходные параметры не Person, а getGender().getDescription()) и возвращает
        // Comparator со следующим lyambda (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
        //т.е. аналогично предыдущей реализации
        Collections.sort(
                persons,
                Comparator.comparing(x -> x.getGender().getDescription())
        );
        System.out.println("Сортировка по полу:comparing:");
        System.out.println(persons.toString());

        //Через Method References
        //Если открыть реализацию Comparator.comparing, то можно увидеть, что это метод возвращающий Comparator
        //со следующим лямбда выражением (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
        //т.е. аналогично предыдущей реализации
        Collections.sort(
                persons,
                //но по getDescription не получится, т.к. Method References (см. DemoLyambda) можно использовать при
                // одном вызове, а тут получается сначала вызывается getGender, а затем getDescription
                // вданном случае ссылка на нестатический метод конкретного объекта
                Comparator.comparing(Person::getGender)
        );
        System.out.println("Сортировка по полу:Method References:");
        System.out.println(persons.toString());

        Collections.sort(
                persons,
                Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)
        );
        System.out.println("Сортировка по ФИ:");
        System.out.println(persons.toString());
    }
}
