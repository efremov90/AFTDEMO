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
                //В данном случае осуществляется просто вывод, примеры с формирование новой коллекции будут ниже
                //c использованием метода .collect
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
        curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //findFirst возвращает первый элемент из стрима (возвращает Optional)
                //есть еще findAny
                .findFirst()
                //Поскольку findFirst возвращает тип Optional, то чтобы получить Person необходимо вызвать get
                //https://metanit.com/java/tutorial/10.12.php
                //https://sboychenko.ru/java-optional/
                //https://habr.com/ru/post/225641/
                //Но нужно быть уверенным, что возвращаемое значение не null, иначе будет ошибка
                //java.util.NoSuchElementException
                //Поэтому чтобы ошибка не возникала можно использовать ifPresent, который также как forEach принимает
                // интерфейс Consumer, т.е. ничего не возвращает - void
                //Так же есть ifPresentOrElse, OrElse, orElseThrow, см. пример использования ниже
                .ifPresent(System.out::println);
        System.out.println();

        System.out.println("filter: Age>100; findFirst:");
        curArrayListPerson.stream()
                .filter(x->x.getAge()>100)
                .findFirst()
                //ifPresentOrElse как и ifPresent принимает Consumer
                .ifPresentOrElse(
                        System.out::println,
                        ()-> System.out.println("Not found")
                );
        System.out.println();

        System.out.println("filter: Age>100; findFirst:");
        Person firstPerson = curArrayListPerson.stream()
                .filter(x->x.getAge()>100)
                .findFirst()
                /*Если цель обработки не вывод c использованием ifPresent, ifPresentOrElse, а присвоить найденное
                 значение переменной, то обрабатывать нужно через orElse, который НЕ void
                Написать тоже самое через ifPresentOrElse, например:
                .ifPresentOrElse(
                    x->{firstPerson=x;},
                    ()->{firstPerson=new Person();}
                );
                не получится, т.к. в lyambda-выражениях локальные переменные должны быть final или effective final
                https://www.examclouds.com/ru/java/java-core-russian/functional-interface-russian*/
                .orElse(new Person());
        System.out.println(firstPerson);
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
                    //и возвращает его
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
                // создании TreeSet элементы будут добавлены попорядку
                //Помним, что (p1,p2)->p1.compareTo(p2) можно написать через Method References: Integer::compare
                .sorted((p1,p2)->p1.compareTo(p2))
                /*Получение коллекции TreeSet https://metanit.com/java/tutorial/10.6.php
                toCollection реализует добавление элемента в коллекцию и возвращение новой коллекции с добавленным элементом:
                CollectorImpl<>(collectionFactory, Collection<T>::add,
                                (r1, r2) -> { r1.addAll(r2); return r1; },
                                CH_ID)
                Поскольку в stream перебираются все элементы, то в итоге будет возвращена коллекция, в которую
                 будут добавлены все элементы stream.
                Помним, что ()->new TreeSet<>() можно написать через TreeSet::new*/
                .collect(Collectors.toCollection(()->new TreeSet<>()));
        System.out.println(ageSet);
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: Age; max:");
        curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //получение stream из Age
                .map(Person::getAge)
                //max принимает Comparator интерфейс и возвращает Optional
                .max(Integer::compareTo)
                //Поскольку у Age тип int, то даже если бы конструкто Person был без него, то int по умолчанию
                //проставился бы 0, т.е. null никогда не возникнет, поэтому можно смело вызывать get
                //Но будем использовать специальные методы по аналогию с описанием выше
                .ifPresent(System.out::println);
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: LastName; max:");
        curArrayListPerson.stream()
                //Если не исключить LastName=null, то на этапе map нужно подготавливать данные для max, иначе
                // возникнет ошибка java.lang.NullPointerException, т.е. заменять null на "", см. ниже
                .filter(x->x.getLastName()!=null)
                //Получение stream из LastName
                //Поскольку дальше в max используется сравнение String, то чтобы исключить проблему аналогичную
                //примеру "sorted: LastName desc:", нужно заменить null на ""
                //Второй вариант исключить LastName=null заранее с помощью filter
                .map(x->{return x.getLastName()==null ? "" : x.getLastName();})
                .max(String::compareTo)
                .ifPresent(System.out::println);
        System.out.println();

        //Поиск максимального через reduce
        System.out.println("filter: Gender=FEMALE; map: Age; reduce(max):");
        curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                //получение stream из Age
                .map(Person::getAge)
                //метод reduce принимает объект BinaryOperator<T>, который представляет функцию, которая принимает два
                //элемента и выполняет над ними операцию, возвращая результат. При этом метод reduce сохраняет
                //результат и затем опять же применяет к этому результату и следующему элементу в наборе бинарную операцию.
                .reduce((x,y)->(Integer.max(x,y)))
                //reduce тоже возвращает Optional
                .ifPresent(System.out::println);
        System.out.println();

        System.out.println("filter: Gender=FEMALE; map: Age; average:");
        curArrayListPerson.stream()
                .filter(x->x.getGender().equals(FEMALE))
                .map(Person::getAge)
                //Через приведение сразу в map не работает
                //.map(x->{return (Integer) x.getAge();})
                //Пришлось как в примере https://habr.com/ru/company/luxoft/blog/270383/
                //приводить отдельно через mapToInt в IntStream
                .mapToInt(x->(Integer) x)
                .average()
                .ifPresent(System.out::println);
        System.out.println();

        System.out.println("flatMap:");
        //List.of позволяет преобразовывать Array в ArrayList
        TreeSet<Integer> integerTreeSet = List.of("1;2;0", "4;5").stream()
                //Преобразовываем строки в массив через split, см. DemoString.
                //flatMap похож на метод map, но может создавать из одного элемента несколько
                //В данном случае получается для каждой записи ArrayList происходит создание несколько
                //посредством преобразования строки в массив
                .flatMap(x->List.of(x.split(";")).stream())
                //valueOf преобразовываем String в Integer
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
        System.out.println();

        System.out.println("сортировка List<Person> по возрасту (убыванию):");
        personsByGender.entrySet().stream()
                .map(x->{
                    //Сортируем каждый List<Person> и возвращаем его
                    x.getValue().sort((o1, o2) -> ((Integer) o2.getAge()).compareTo((Integer) o1.getAge()));
                    return x;
                })
                //Получаем обновленную отсортированную коллекцию
                .collect(Collectors.toList());
        System.out.println(personsByGender);

        System.out.println("partitioningBy: Gender=MALE:");
        //При группировке будет создан объект Map, в котором ключами являются Boolean, а значениями - List<Person>
        Map<Boolean, List<Person>> personsByMale = curArrayListPerson.stream()
                //partitioningBy() делит элементы на группы по принципу, соответствует ли элемент определенному условию
                .collect(Collectors.partitioningBy(x->x.getGender().equals(MALE)));
        System.out.println(personsByMale);
        System.out.println();

        System.out.println("groupingBy: Gender: counting (до сортировки):");
        //Long поскольку counting возвращает Long
        Map<GenderType,Long> countPersonsByGender = curArrayListPerson.stream()
                .collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));
        System.out.println(countPersonsByGender);
        System.out.println();

        //Тоже самое, но сразу с сортировкой
        System.out.println("groupingBy: Gender: counting: сортировка по количеству (возрастанию):");
        List<Map.Entry<GenderType,Long>> listCountGenderType = curArrayListPerson.stream()
                //Аналогично реализации выше
                .collect(Collectors.groupingBy(Person::getGender, Collectors.counting()))
                //Получаем Set<Map.Entry<GenderType,Long>> и преобразуем в stream
                .entrySet().stream()
                        //Сортируем
                        .sorted((o1, o2) -> {return ((Long) o1.getValue().longValue()).compareTo((Long) o2.getValue().longValue());})
                        //Получаем результирующую коллекцию List
                        .collect(Collectors.toList());
        System.out.println(listCountGenderType);
        System.out.println();

        System.out.println("groupingBy: Gender: summingInt:");
        Map<GenderType,Integer> sumAgePersonsByGender = curArrayListPerson.stream()
                .collect(Collectors.groupingBy(Person::getGender, Collectors.summingInt(Person::getAge)));
        System.out.println(sumAgePersonsByGender);
        System.out.println();

        System.out.println("groupingBy: Gender: maxBy:");
        //Получение человека с максимальным возрастом в разрезе пола
        Map<GenderType,Person> personsMaxByAgeGroupByGender = curArrayListPerson.stream()
                //При группировке будет создан объект Map, в котором ключами являются Gender, а значениями - Optional<Person>
                .collect(Collectors.groupingBy(Person::getGender,
                        Collectors.maxBy(Comparator.comparing(Person::getAge)))
                )
                //Получаем EntrySet для преобразования Map с optional в Map без optional (например нужно)
                .entrySet().stream()
                //Получаем результирующую коллекцию Map
                .collect(Collectors.toMap(x->x.getKey(),x->x.getValue().orElse(new Person())));
        System.out.println(personsMaxByAgeGroupByGender);
        System.out.println();

        System.out.println("groupingBy: Gender: summarizingInt: Age:");
        Map<GenderType,IntSummaryStatistics> intSummaryStatisticsGenderMap = curArrayListPerson.stream()
                .collect(Collectors.groupingBy(Person::getGender, Collectors.summarizingInt(Person::getAge)));
        System.out.println(intSummaryStatisticsGenderMap);
        System.out.println();

        //https://reversecoding.net/java-8-list-to-map/
        System.out.println("Преобразование ArrayList<Persons> в Map<PersonalNumber,Person>:");
        Map<String,Person> personMap = curArrayListPerson.stream()
                .collect(Collectors.toMap(x->x.getPersonalNumber(), x->x));
        System.out.println(personMap);
        System.out.println();

        System.out.println("Преобразование ArrayList<Persons> в Map<Gender,PersonalNumber> с сортировкой по " +
                "Gender (убыванию):");
        TreeMap<GenderType,String> agesByGenderTreeMap = curArrayListPerson.stream()
                .collect(Collectors.toMap(x->x.getGender(),x->x.getPersonalNumber(),(x,y)->x+", "+y,
                        ()->new TreeMap<>((o1, o2) -> o2.compareTo(o1))));
        System.out.println(agesByGenderTreeMap);
        System.out.println();

        System.out.println("Получение для Gender сконкантенированной строки из PersonalNumber:");
        Map<GenderType,String> agesByGenderMap = curArrayListPerson.stream()
                .collect(Collectors.toMap(x->x.getGender(),x->x.getPersonalNumber(),(x,y)->x+", "+y));
        System.out.println(agesByGenderMap);
        System.out.println();

        System.out.println("Преобразование Map<Gender,List<Person>> в TreeMap<Gender,LinkedList<PersonalNumber>> с " +
                "сортировкой по Gender (убыванию) и сортировкой PersonalNumber (убыванию):");
        TreeMap<GenderType,LinkedList<String>> agesListGroupByGender = curArrayListPerson.stream()
                //При группировке получим Map<Gender,List<Person>>
                .collect(Collectors.groupingBy(Person::getGender))
                .entrySet().stream()
                        .collect(Collectors.toMap(x->x.getKey(),
                                x->{
                            LinkedList<String> y = new LinkedList<>();
                            x.getValue().forEach(z->y.add(z.getPersonalNumber()));
                            Collections.sort(y,(o1, o2) -> o2.compareTo(o1));
                            return y;
                            }
                                ,(x,y)->y
                                ,()->new TreeMap<>((o1, o2) -> o2.compareTo(o1))
                        )
                        );
        System.out.println(agesListGroupByGender);
    }
}