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
        curArrayListPerson.add(new Person("Имя5", null, "N5",FEMALE,32));

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
                //sorted принимает Comparator интерфейс.
                //Поскольку у Age тип int, то даже если бы конструкто Person был без него, то int по умолчанию
                //проставился бы 0, т.е. null никогда не возникнет, поэтому проблемы ниже как с LastName не возникнет
                .sorted((p1,p2)->((Integer) p2.getAge()).compareTo((Integer) p1.getAge()))
                .forEach(System.out::println);
        System.out.println();

        System.out.println("sorted: LastName desc:");
        curArrayListPerson.stream()
                .sorted((o1, o2) -> {
                    /*
                    Поскольку я специально создал пользователя без фамилии. В жизни такого не бывает.
                     Но например, если бы у нас было отчество, а вот оно есть не во всех странах.
                    Поскольку строки сраниваются с использованием метода compareTo, т.е. вызов от имени строки
                     происходит o2.compareTo, то для строки null возникла бы ошибка NullPointerException.
                    https://habr.com/ru/post/221243/
                    https://docs.oracle.com/javase/7/docs/api/java/lang/NullPointerException.html
                    Чтобы ошибки не возникало, нужно явно проверять на null и обрабатывать нужным образом.
                    В данном случае null перед выполнением compareTo заменяется на ""
                    При этом "" считается меньше какой-либо строки и будет выведен вверху при сортировке по
                     возрастанию. Если хочется внизу, то можно переписать по принципу:
                     если o1==null и o2!=null, то возвращать -1,
                     если o1==null и o2==null, то 0,
                     если o1!=null и o2==null, то 1,
                     если o1!=null и o2!=null, то o1.compareTo(o2)
                     */
                    return (o1.getLastName()==null ? "" : o1.getLastName())
                            .compareTo(o2.getLastName()==null ? "" : o2.getLastName());
                })
                .forEach(System.out::println);
        System.out.println();

        System.out.println("filter: Gender=FEMALE; findFirst:");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //findFirst возвращает первый элемент из стрима (возвращает Optional)
                //есть еще findAny
                .findFirst()
                //Поскольку findFirst возвращает тип Optional, то чтобы получить Person необходимо вызвать get
                //https://metanit.com/java/tutorial/10.12.php
                //https://sboychenko.ru/java-optional/
                //https://habr.com/ru/post/225641/
                //Но нужно быть уверенным, что не возврщаемое значение не null, иначе будет ошибка
                //java.util.NoSuchElementException
                //Поскольку у Age тип int, то даже если бы конструкто Person был без него, то int по умолчанию
                // проставился бы 0, т.е. null никогда не возникнет, поэтому можно смело вызывать get
                .get());
        System.out.println();

        System.out.println("filter: Gender=FEMALE; findFirst:");
        curArrayListPerson.stream()
                .filter(x->x.getAge()>20)
                //findFirst возвращает первый элемент из стрима (возвращает Optional)
                //есть еще findAny
                .findFirst()
                //поскольку findFirst возвращает тип Optional, то чтобы получить Person необходимо вызвать get
                //https://metanit.com/java/tutorial/10.12.php
                .ifPresent(System.out::println);
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
                    //изменяются атрибуты элемента
                    x.setFirstName(x.getFirstName().toUpperCase());
                    x.setLastName(x.getLastName().toUpperCase());
                    //и вернул его
                    return x;
                })
                .forEach(System.out::println);
        System.out.println("curArrayListPerson: "+curArrayListPerson);
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: Age; distinct; collect:");
        //TreeSet поскольку было желание вернуть отсортированную коллекцию
        TreeSet<Integer> ageSet = curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //Получение stream из Age, т.е. из списка Persons получили список Age, т.е. на входе stream может
                // один тип элементов, а на выходе можно получать другой.
                //Помним, что x->x.getAge() можно написать через Method References: Person::getAge
                .map(x->x.getAge())
                //Возвращает стрим без дубликатов на основании методов hashCode и equals, поэтому важно чтобы они были
                // переопределены для собственных классов
                .distinct()
                //Перед формированием коллекции TreeSet производится сортировка элементов и соответственно при
                // создании TreeSet элемент будут добавлены попорядку
                //Помним, что (p1,p2)->p1.compareTo(p2) можно написать через Method References: Integer::compare
                .sorted((p1,p2)->p1.compareTo(p2))
                //Получение коллекции TreeSet https://metanit.com/java/tutorial/10.6.php
                //toCollection реализует добавление элемента в коллекцию и возвращение новой коллекции с добавленным элементом.
                //Поскольку в stream перебираются все элементы, то в итоге будет возвращена коллекция, в которую
                // будут добавлены все элементы stream
                //Помни, что ()->new TreeSet<>() можно написать через TreeSet::new
                .collect(Collectors.toCollection(()->new TreeSet<>()));
        System.out.println(ageSet);
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: Age; max:");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //получение stream из Age
                .map(Person::getAge)
                //max принимает Comparator интерфейс
                .max(Integer::compareTo)
                //Допустим, если вораст м.б. не известен, то чтобы не возникало исключение при вызове get,
                // используется orElse с возвратом в этом случае по умолчанию 0
                //https://metanit.com/java/tutorial/10.12.php
                //Можно также генерировать исключение orElseThrow
//                .orElse(0)
                .get());
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: LastName; max:");
        System.out.println(curArrayListPerson.stream()
                //Если не исключить LastName=null, то на этапе map нужно подготавливать данные для max, иначе
                // возникнет ошибка java.lang.NullPointerException, т.е. заменять null на "", см. ниже
                .filter(x->x.getLastName()!=null)
                //Получение stream из Age
                .map(Person::getLastName)
//                .map(x->{
//                    if (x.getLastName()!=null) {
//                        return x.getLastName();
//                    } else {
//                        return "";
//                    }})
                //max принимает Comparator интерфейс
                .max(String::compareTo)
                //Допустим, если вораст м.б. не известен, то чтобы не возникало исключение
                // java.util.NoSuchElementException при вызове get используется orElse с возвратом значения по умолчанию
                //https://metanit.com/java/tutorial/10.12.php
                //Можно также генерировать исключение orElseThrow
//                .orElse("-")
                .get()
        );
        System.out.println();

        //Поиск максимального через reduce
        System.out.println("filter: Gender=FEMALE; map: Age; reduce(max):");
        System.out.println(curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //получение stream из Age
                .map(Person::getAge)
                //метод reduce принимает объект BinaryOperator<T>, который представляет функцию, которая принимает два
                //элемента и выполняет над ними некоторую операцию, возвращая результат. При этом метод reduce сохраняет
                //результат и затем опять же применяет к этому результату и следующему элементу в наборе бинарную операцию.
                .reduce((x,y)->(Integer.max(x,y)))
                .orElse(0));
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
                .orElse(0));
        System.out.println();

        System.out.println("flatMap:");
        //List.of позволяет преобразовывать Array в ArrayList
        // массива
        TreeSet<Integer> integerTreeSet = List.of("1;2;0", "4;5").stream()
                //Преобразовываем строки в массив через split, см. DemoString.
                //flatMap похож на метод map, но может создавать из одного элемента несколько
                //В данном случае получается для каждой записи ArrayList происходит создание ещё на несколько
                //посредством преобразования строки в массив
                .flatMap(x->List.of(x.split(";")).stream())
                .map(x->Integer.valueOf(x))
                .sorted(Integer::compareTo)
                //Получаем TreeSet
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(integerTreeSet);
        System.out.println();

        //Группировка https://metanit.com/java/tutorial/10.7.php
        System.out.println("groupingBy: Gender (до сортировки):");
        //При группировке будет создан объект Map, в котором ключами являются Gender, а значениями - List<Person>
        Map<GenderType, List<Person>> personsByGender = curArrayListPerson.stream()
                .collect(Collectors.groupingBy(Person::getGender));
        System.out.println(personsByGender);
        System.out.println("сортировка по возрасту (обратно):");
        personsByGender.entrySet().stream()
        .map(x->{
            x.getValue().sort((o1, o2) -> ((Integer) o2.getAge()).compareTo((Integer) o1.getAge()));
            return x;
        })
        .collect(Collectors.toList());
        System.out.println(personsByGender);

        System.out.println("partitioningBy: Gender=MALE:");
        //При группировке будет создан объект Map, в котором ключами являются Boolean, а значениями - List<Person>
        Map<Boolean, List<Person>> personsByMale = curArrayListPerson.stream()
                //partitioningBy() делит элементы на группы по принципу, соответствует ли элемент определенному условию
                .collect(Collectors.partitioningBy(x->x.getGender().equals(MALE)));
        System.out.println(personsByMale);
        System.out.println();

        System.out.println("groupingBy: Gender: counting:");
        Map<GenderType,Long> countPersonsByGender = curArrayListPerson.stream()
                .collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));
        System.out.println(countPersonsByGender);
        System.out.println();

        System.out.println("groupingBy: Gender: summingInt:");
        Map<GenderType,Integer> sumAgePersonsByGender = curArrayListPerson.stream()
                .collect(Collectors.groupingBy(Person::getGender, Collectors.summingInt(Person::getAge)));
        System.out.println(sumAgePersonsByGender);
        System.out.println();

        System.out.println("groupingBy: Gender: maxBy:");
        //При группировке будет создан объект Map, в котором ключами являются Gender, а значениями - Optional<Person>
        Map<GenderType,Optional<Person>> maxAgeOptionalPersonsByGender = curArrayListPerson.stream()
                .collect(Collectors.groupingBy(Person::getGender,
                        Collectors.maxBy(Comparator.comparing(Person::getAge)))
                );
        //Преобрзовать в Map<GenderType,Person> без Optional сразу в одном stream не получилось, пришлось отдельно
//        Map<GenderType,Person> maxAgePersonsByGender = new HashMap<>();
//        maxAgeOptionalPersonsByGender.forEach((x,y)->{
//            maxAgePersonsByGender.put(x,y.get());
//        });
//        Map<GenderType,Person> maxAgePersonsByGender = maxAgeOptionalPersonsByGender.entrySet().stream()
//                .collect()
//        System.out.println(maxAgePersonsByGender);
//        System.out.println();

        System.out.println("groupingBy: Gender: maxBy:");
        //При группировке будет создан объект Map, в котором ключами являются Gender, а значениями - Optional<Person>
//        Map<GenderType,Person> maxAgeOptionalPersonsByGender1 = curArrayListPerson.stream()
//                .collect(Collectors.groupingBy(Person::getGender,
//                        Collectors.maxBy(Comparator.comparing(Person::getAge)))
//                )
//                .entrySet()
//                .stream()
//                .collect(Collectors.toCollection());
        //Преобрзовать в Map<GenderType,Person> без Optional сразу в одном stream не получилось, пришлось отдельно
//        Map<GenderType,Person> maxAgePersonsByGender1 = new HashMap<>();
//        maxAgeOptionalPersonsByGender.forEach((x,y)->{
//            maxAgePersonsByGender1.put(x,y.get());
//        });
//        System.out.println(maxAgePersonsByGender1);
//        System.out.println();

        System.out.println("groupingBy: Gender: summarizingInt: Age:");
        Map<GenderType,IntSummaryStatistics> intSummaryStatisticsGenderMap = curArrayListPerson.stream()
                .collect(Collectors.groupingBy(Person::getGender, Collectors.summarizingInt(Person::getAge)));
        System.out.println(intSummaryStatisticsGenderMap);

    }
}