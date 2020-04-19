package main.java;

import java.util.*;

import static main.java.GenderType.FEMALE;
import static main.java.GenderType.MALE;

/*
HashSet https://vertex-academy.com/tutorials/ru/set-v-java-hashset/
http://java-online.ru/java-set.xhtml
https://devcolibri.com/hashmap-и-hashset-что-это-на-самом-деле/
https://javahelp.online/collections/interfeys-java-set
https://www.examclouds.com/ru/java/java-core-russian/klass-hashset

https://ru.stackoverflow.com/questions/619860/Зависимость-работы-hashset-от-переопределения-equals-и-hashcode-у-элементов

Реализация https://javarush.ru/groups/posts/2147-hashset-v-java
Основан на HashMap. Отличие в том, что ключом является сам объект, а в качестве значения используется константа PRESENT.
Метод add() у HashSet вызывает метод put() у внутреннего объекта HashMap, передавая ему в
качестве ключа добавляемый элемент, а в качестве значения – константу PRESENT.

В остальном всё справедливо как для HashMap, см. DemoHashMap.

HashMap позволяет дублировать значения, но не ключи. В HashSet значение является ключом, соответственно вообще
дублирование не возможно.
HashSet нужно использовать вместо HashMap, если необходимо просто проверять наличе элемента по значению contains(),
которое будет являться в том числе ключом, что будет обеспечивать быстрый поиск.

HashSet хранит элементы в произвольном порядке, но зато быстро ищет.
LinkedHashSet будет хранить элементы в порядке добавления, но работает медленнее и занимает больше места.
TreeSet хранит элементы отсортированными.
Сложность HashSet, LinkedHashSet, TreeSet аналогична HashMap, LinkedHashMap, TreeMap
*/
public class DemoHashSet {
    public static void main(String[] args) {

        HashSet<Person> curHashSet = new HashSet<>();
        //Добавление элементов с созданием при вызове
        curHashSet.add(new Person("Имя1", "Фамилия1", "N1",MALE,30));
        //заранее созданных, полученных откуда-либо
        Person P2 = new Person("Имя2", "Фамилия2", "N2",FEMALE,31);
        curHashSet.add(P2);
        //При добавлении дубля элемент не добавляется
        curHashSet.add(new Person("Имя1", "Фамилия1", "N1",MALE,30));
        //Вывод
        System.out.println("curHashMap put, put, put: "+curHashSet.toString());

        //contains(Object o) возвращает true, если o является элементом вызывающей коллекции
        //также как и во всех коллекциях
        System.out.println("curHashMap.containsKey('N2'): "+curHashSet.contains(new Person("Имя1", "Фамилия1", "N1",MALE,30)));

        //Перебор https://javahelp.online/collections/map-v-java-s-primerami
        //Варианты перебора аналогичны DemoIterator
        //Помним, что HashMap хранит значения в произвольном порядке, поэтому вывод будет каждый раз в произвольном
        // порядке.
        //В данном случае использовался перебор entrySet().
        // Также можно работать с keySet(), values().
        for (Person person : curHashSet) {
            System.out.println("person = "+curHashSet.toString());
        }
        System.out.println();

        //Сортировка через LinkedList
        List<Person> persons = new LinkedList<>(curHashSet);
        Collections.sort(
                persons,
                Comparator.comparing(Person::getLastName).thenComparing(Person::getFirstName)
        );
        System.out.println("Сортировка по ФИ LinkedList:");
        System.out.println(persons.toString());
        System.out.println();

        //Использование SortedSet см. в DemoHashMap
    }
}
