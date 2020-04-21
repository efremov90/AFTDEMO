package main.java;

import java.util.*;

import static main.java.GenderType.FEMALE;
import static main.java.GenderType.MALE;

public class DemoStreamAPI {
    public static void main(String[] args) {
        List<Person> curArrayListPerson = new ArrayList<>();
        curArrayListPerson.add(new Person("Имя1", "Фамилия1", "N1",MALE,29));
        curArrayListPerson.add(new Person("Имя2", "Фамилия2", "N2",MALE,30));
        curArrayListPerson.add(new Person("Имя3", "Фамилия1", "N3",FEMALE,31));
        curArrayListPerson.add(new Person("Имя4", "Фамилия4", "N4",FEMALE,31));
        curArrayListPerson.add(new Person("Имя5", "Фамилия5", "N5",FEMALE,32));

        System.out.println("curArrayListPerson:");
        System.out.println(curArrayListPerson);
        System.out.println();

        System.out.println("filter: Фамилия1:");
        curArrayListPerson.stream()
                .filter(x->x.getLastName()=="Фамилия1")
                .forEach(System.out::println);
        System.out.println();

        System.out.println("filter: Фамилия1, Имя3:");
        curArrayListPerson.stream()
                .filter(x->(x.getLastName()=="Фамилия1"&&x.getFirstName()=="Имя3"))
                .forEach(System.out::println);
        System.out.println();

        System.out.println("filter: Age=29; map: FirstName,LastName.toUpperCase:");
        curArrayListPerson.stream()
                .filter(x->x.getAge()==29)
                .map(x->{
                    x.setFirstName(x.getFirstName().toUpperCase());
                    x.setLastName(x.getLastName().toUpperCase());
                    return x;
                })
                .forEach(System.out::println);
        System.out.println("curArrayListPerson: "+curArrayListPerson);
        System.out.println();

        System.out.println("filter: FEMALE; count:");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                .count());
        System.out.println();

        System.out.println("filter: FEMALE; map:getAge; distinct:");
        curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //получение stream из Age
                .map(Person::getAge)
                .distinct()
                .forEach(System.out::println);
        System.out.println();

        System.out.println("filter: FEMALE; map:getAge; max:");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //получение stream из Age
                .map(Person::getAge)
                .max((p1,p2)->p1.compareTo(p2))
                .get());
        System.out.println();
    }
}
