package main.java;

import java.util.*;

import static main.java.GenderType.FEMALE;
import static main.java.GenderType.MALE;

//https://www.examclouds.com/java/java-core-russian/interface-comparator
//https://metanit.com/java/tutorial/5.6.php
//http://java-online.ru/blog-comparator.xhtml

//класс реализует интерфейс Comparator для Person
class PersonComparatorGenderDescription implements Comparator<Person> {

    //переопределен метод
    @Override
    public int compare(Person o1, Person o2) {
        //в return используется compareTo - см. http://java-online.ru/blog-comparator.xhtml
        return o1.getGender().getDescription().compareTo(o2.getGender().getDescription());
        //если нужно реализовывть сложную логику сравнения, то можно реализовать самому без compareTo самостоятельно,
        //например, как для SortedByPrice  см. http://java-online.ru/blog-comparator.xhtml
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

        //Через отдельно объявленный класс PersonComparatorGenderDescription, реализующий Comparator
        Collections.sort(
                persons,
                new PersonComparatorGenderDescription()
        );
        System.out.println("Сортировка по полу:отдельно объявленный класс:");
        System.out.println(persons.toString());

        //Через создание и объявление Comparator сразу в методе sort
        Collections.sort(
                persons,
                new Comparator<Person>() {
                    @Override
                    public int compare(Person o1, Person o2) {
                        return o1.getGender().getDescription().compareTo(o2.getGender().getDescription());
                    }
                }
        );
        System.out.println("Сортировка по полу:объявление в методе sort:");
        System.out.println(persons.toString());

        //Через lyambda, т.е. анонимный метод аналогичный compare в PersonComparatorGenderDescription
        //https://riptutorial.com/java/example/387/using-lambda-expressions-to-sort-a-collection
        Collections.sort(
                persons,
                (Person o1,Person o2)->(o1.getGender().getDescription().compareTo(o2.getGender().getDescription()))
        );
        System.out.println("Сортировка по полу:lyambda:");
        System.out.println(persons.toString());

        //Через Comparator.comparing
        //Если открыть реализацию Comparator.comparing, то можно увидеть, что это метод принимает Function интерфейс,
        // т.е. lyambda (но входные параметры не Person, а getGender().getDescription()) и возвращает
        // Comparator со следующим lyambda (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
        //т.е. аналогично предыдущей реализации
        Collections.sort(
                persons,
                Comparator.comparing(x -> x.getGender().getDescription())
        );
        System.out.println("Сортировка по полу:comparing:");
        System.out.println(persons.toString());

        //Через Method References
        Collections.sort(
                persons,
                //По getDescription не получится, т.к. Method References (см. DemoLyambda) можно использовать при
                // одном вызове, а тут получается сначала вызывается getGender, а затем getDescription.
                //В данном случае ссылка на нестатический метод конкретного объекта.
                Comparator.comparing(Person::getGender)
        );
        System.out.println("Сортировка по полу:Method References:");
        System.out.println(persons.toString());

        //thenComparing  позволяет использовать цепочки компараторов для сортировки набора
        //в данном случае сначала сортировка по фамилии, затем по имени
        Collections.sort(
                persons,
                Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)
        );
        System.out.println("Сортировка по ФИ:");
        System.out.println(persons.toString());

        //Сортировка по ФИ в обратном порядке:
        Collections.sort(
                persons,
                //Для сортировки в обратном порядке нужно поменять местами с o1.compareTo(o2) на o2.compareTo(o1)
                //thenComparing - это аналог "+" через compareTo
                (Person o1,Person o2)->((o2.getLastName().compareTo(o1.getLastName()))
                        +(o2.getFirstName().compareTo(o1.getFirstName())))
        );
        System.out.println("Сортировка по ФИ (обратно):");
        System.out.println(persons.toString());
    }
}