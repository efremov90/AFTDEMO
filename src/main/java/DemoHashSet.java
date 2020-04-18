package main.java;

import java.util.HashSet;

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
        System.out.println("Hello world!");

        //Инициирование HashSet
        HashSet<Person> curArrayListPerson = new HashSet<>();
        //Добавление элементов с созданием при вызове
        //add без указания индекса добавляет в конец
        curArrayListPerson.add(new Person("Имя1", "Фамилия1", MALE));
        //заранее созданных, полученных откуда-либо
        Person P2 = new Person("Имя2", "Фамилия2", MALE);
        curArrayListPerson.add(P2);
        //Можно добавлять дубли. При этом при первом удалении remove один удалится, при втором следующий
        //Добавил на позицию 0
        curArrayListPerson.add(new Person("Имя2", "Фамилия2", MALE));
        //Вывод
        System.out.println("curArrayListPerson add1, add2, add2: "+curArrayListPerson.toString());
        //Получение элемента по индексу (как и в массиве нумерация с 0)
        System.out.println("get(2): "+curArrayListPerson.toString());
        //set(int index, E obj) естественно нужно указывать в какой позиции index нужно задать obj
        //remove(int index), remove(E obj) работает либо по индексту, либо по объекту
    }
}
