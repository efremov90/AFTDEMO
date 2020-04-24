package main.java;

import java.util.*;
import java.util.stream.Collectors;

import static main.java.GenderType.FEMALE;
import static main.java.GenderType.MALE;

//Stream API https://metanit.com/java/tutorial/10.1.php
//https://habr.com/ru/company/luxoft/blog/270383/
public class DemoStreamAPI {
    public static void main(String[] args) {

        List<Person> curArrayListPerson = new ArrayList<>();
        curArrayListPerson.add(new Person("Имя1", "Фамилия1", "N1",FEMALE,29));
        curArrayListPerson.add(new Person("Имя2", "Фамилия2", "N2",MALE,30));
        curArrayListPerson.add(new Person("Имя3", "Фамилия1", "N3",FEMALE,31));
        curArrayListPerson.add(new Person("Имя4", "Фамилия4", "N4",MALE,31));
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

        System.out.println("filter: Gender=FEMALE; limit(2):");
        curArrayListPerson.stream()
                //могут быть произвольные условия поиска
                .filter(x->x.getGender().equals(FEMALE))
                //limit позволяет ограничить выборку определенным количеством первых элементов
                //не вывелся N5
                .limit(2)
                .forEach(System.out::println);
        System.out.println();

        System.out.println("sorted: Age desc:");
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
                //https://metanit.com/java/tutorial/10.12.php
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

        System.out.println("filter: Gender=FEMALE; map: Age; distinct; collect:");
        HashSet<Integer> ageSet = curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //Получение stream из Age, т.е. из списка Persons получили список Age, т.е. на входе stream может
                // один тип элементов, а на выходе можно получать другой.
                //Помним, что x->x.getAge() можно написать через Method References: Person::getAge
                .map(x->x.getAge())
                //Возвращает стрим без дубликатов на основании методов hashCode и equals, поэтому важно чтобы они были
                // переопределены для собственных классов
                .distinct()
                //Получение коллекции HashSet https://metanit.com/java/tutorial/10.6.php
                //toCollection реализует добавление элемента в коллекцию и возвращение новой коллекции с добавленным элементом.
                //Поскольку в stream перебираются все элементы, то в итоге будет возвращена коллекция, в которую
                // будут добавлены все элементы stream
                .collect(Collectors.toCollection(HashSet::new));
        TreeSet<Integer> sortedAgeSet = new TreeSet<>(Integer::compareTo);
        sortedAgeSet.addAll(ageSet);
        System.out.println(sortedAgeSet);
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: Age; max:");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //получение stream из Age
                .map(Person::getAge)
                //max принимает Comparator интерфейс
                //Помним, что (p1,p2)->p1.compareTo(p2) можно написать через Method References: Integer::compare
                .max((p1,p2)->p1.compareTo(p2))
                .get());
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: Age; reduce(max):");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //получение stream из Age
                .map(Person::getAge)
                //метод reduce принимает объект BinaryOperator<T>, который представляет функцию, которая принимает два
                //элемента и выполняет над ними некоторую операцию, возвращая результат. При этом метод reduce сохраняет
                //результат и затем опять же применяет к этому результату и следующему элементу в наборе бинарную операцию.
                .reduce((x,y)->(Integer.max(x,y)))
                .get());
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: Age; average:");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                .map(Person::getAge)
                //Через приведение сразу в map не работает
                //.map(x->{return (Integer) x.getAge();})
                //Пришлось как в примере https://habr.com/ru/company/luxoft/blog/270383/
                //приводить отдельно через mapToInt в IntStream
                .mapToInt(x->(Integer) x)
                .average()
                .getAsDouble());
        System.out.println();

        System.out.println("flatMap:");
        //List.of позволяет преобразовывать Array в ArrayList
        // массива
        HashSet<Integer> integerHashSet = List.of("1;2;0", "4;5").stream()
                //Преобразовываем строки в массив через split, см. DemoString.
                //flatMap похож на метод map, но может создавать из одного элемента несколько
                //В данном случае получается для каждой записи ArrayList происходит создание ещё на несколько
                //посредством преобразования строки в массив
                .flatMap(x->List.of(x.split(";")).stream())
                .map(x->Integer.valueOf(x))
                //получаем HashSet
                .collect(Collectors.toCollection(HashSet::new));
        TreeSet<Integer> integerTreeSet = new TreeSet<>(Integer::compareTo);
        integerTreeSet.addAll(integerHashSet);
        System.out.println(integerTreeSet);
        System.out.println();

        //Группировка https://metanit.com/java/tutorial/10.7.php
        System.out.println("groupingBy: Gender (до сортировки):");
        Map<GenderType, List<Person>> personsByGender = curArrayListPerson.stream()
                .collect(Collectors.groupingBy(Person::getGender));
        System.out.println(personsByGender);
        System.out.println("сортировка по возрасту (обратно):");
        personsByGender.entrySet().stream()
        .map(x->{
            x.getValue().sort((o1, o2) -> ((Integer) o2.getAge()).compareTo((Integer) o1.getAge()));
            return x;
        })
        .collect(Collectors.toSet());
        System.out.println(personsByGender);

        System.out.println("partitioningBy: Gender=MALE:");
        Map<Boolean, List<Person>> personsByMale = curArrayListPerson.stream()
                //partitioningBy() делит элементы на группы по принципу, соответствует ли элемент определенному условию
                .collect(Collectors.partitioningBy(x->x.getGender().equals(MALE)));
        System.out.println(personsByMale);
        System.out.println();

        System.out.println("groupingBy: Gender: counting:");
        Map<GenderType,Long> countPersonsByGender = curArrayListPerson.stream()
                .collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));
        System.out.println(countPersonsByGender);
    }
}