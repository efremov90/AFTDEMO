package main.java;

import java.util.*;
import java.util.stream.Collectors;

import static main.java.GenderType.FEMALE;
import static main.java.GenderType.MALE;

//Stream API https://habr.com/ru/company/luxoft/blog/270383/
//https://metanit.com/java/tutorial/10.1.php
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

        System.out.println("filter: PersonalNumber=N1:");
        curArrayListPerson.stream()
                //filter принимает Predicate интерфейс
                //могут быть произвольные условия поиска
                .filter(x->x.getPersonalNumber()=="N1")
                //forEach принимает Consumer интерфейс
                .forEach(System.out::println);
        System.out.println();

        System.out.println("filter: Gender=MALE; limit(2):");
        curArrayListPerson.stream()
                //могут быть произвольные условия поиска
                .filter(x->x.getGender().equals(FEMALE))
                //limit позволяет ограничить выборку определенным количеством первых элементов
                //не вывелся N5
                .limit(2)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("filter: Gender=MALE; sorted: Age desc:");
        curArrayListPerson.stream()
                //sorted принимает Comparator интерфейс
                .sorted((p1,p2)->((Integer) p2.getAge()).compareTo((Integer) p1.getAge()))
                .forEach(System.out::println);
        System.out.println();

        System.out.println("filter: Gender=FEMALE; findFirst:");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //findFirst возвращает первый элемент из стрима (возвращает Optional)
                //есть еще findAny
                .findFirst()
                //поскольку findFirst возвращает тип Optional, то чтобы получить Person необходимо вызвать get
                .get());
        System.out.println();

        System.out.println("anyMatch: Gender=FEMALE, Age=30:");
        System.out.println(curArrayListPerson.stream()
                //anyMatch возвращает true, если условие выполняется хотя бы для одного элемента
                //есть еще noneMatch, allMatch
                .anyMatch(x->(x.getGender().equals(FEMALE) && x.getAge()==30)));
        System.out.println();

        System.out.println("filter: Gender=FEMALE; count:");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                .count());
        System.out.println();

        System.out.println("filter: Gender=MALE; map: FirstName,LastName.toUpperCase:");
        curArrayListPerson.stream()
                .filter(x->x.getGender().equals(MALE))
                //map принимает Function интерфейс
                .map(x->{
                    //изменил атрибуты элемента
                    x.setFirstName(x.getFirstName().toUpperCase());
                    x.setLastName(x.getLastName().toUpperCase());
                    //и вернул его
                    return x;
                })
                .forEach(System.out::println);
        System.out.println("curArrayListPerson: "+curArrayListPerson);
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: Age; distinct:");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //Получение stream из Age, т.е. из списка Persons получили список Age, т.е. на входе stream может
                // один тип элементов, а на выходе можно получать другой.
                //Помним, что x->x.getAge() можно написать через Method References: Person::getAge
                .map(x->x.getAge())
                //Возвращает стрим без дубликатов на основании методов hashCode и equals, поэтому важно чтобы они были
                // переопределены для собственных классов
                .distinct()
                //Получение коллекции Set
                //Также есть метод toList
                .collect(Collectors.toSet()));
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: Age; max:");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //получение stream из Age
                .map(Person::getAge)
                //max принимает Comparator интерфейс
                //Помним, что (p1,p2)->p1.compareTo(p2) можно написать через Method References: Integer::compareTo
                .max((p1,p2)->p1.compareTo(p2))
                .get());
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: Age; average:");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                .map(x->x.getAge())
                //Через приведение сразу в map не работает
                //.map(x->{return (Integer) x.getAge();})
                //Пришлось как в примере https://habr.com/ru/company/luxoft/blog/270383/
                //приводить отдельно через mapToInt
                .mapToInt(x->(Integer) x)
                .average()
                .getAsDouble());
        System.out.println();

        System.out.println("flatMap:");
        System.out.println(
                //Arrays.toString возвращает строковое представление массива, см. DemoArrayAndCycle
                Arrays.toString(
                        //List.of позволяет преобразовывать Array в ArrayList
                        // массива
                        List.of("1;2;0", "4;5").stream()
                        //Преобразовываем строки в массив через split, см. DemoString.
                        //flatMap похож на метод map, но может создавать из одного элемента несколько
                        //В данном случае получается для каждой записи ArrayList происходит создание ещё на несколько
                        //посредством преобразования строки в массив
                        .flatMap(x->List.of(x.split(";")).stream())
                        //получаем массив
                        .toArray(String[]::new)
                )
        );
        System.out.println();
    }
}