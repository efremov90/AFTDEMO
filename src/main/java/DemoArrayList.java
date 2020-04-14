package main.java;

import java.util.*;

import static main.java.GenderType.FEMALE;
import static main.java.GenderType.MALE;

/*
Collection знакомство
https://www.examclouds.com/ru/java/java-core-russian/interface-collection
https://www.examclouds.com/ru/java/java-core-russian/collections-russian

List знакомство https://www.examclouds.com/ru/java/java-core-russian/interface-list

ArrayList https://www.examclouds.com/ru/java/java-core-russian/klass-arraylist

Обязательно:
https://habr.com/ru/post/128269/
https://javarush.ru/groups/posts/1936-rabota-arraylist-v-kartinkakh--

Сложность ArrayList и LinkedList (по индексу элемента, а не значению (объекту)!):
                            |  ArrayList  |  LinkedList
add (в начало)              |     O(n)    |   O(1)
add (в середину)            |     O(n)    |   O(n)
add (в конец списка)        |     O(n)    |   O(1)
get (первый элемент)        |     O(1)    |   O(1)
get (из середины)           |     O(1)    |   O(n)
get (последний элемент)     |     O(1)    |   O(1)
remove (первый элемент)     |     O(n)    |   O(1)
remove (из середины)        |     O(n)    |   O(n)
remove (последний элемент)  |     O(1)    |   O(1)

Таким образом ArrayList имеет смысл использовать, если приходится часто получать элементы по индексу
*/
public class DemoArrayList {
    public static void main(String[] args) {

        //Инициирование ArrayList
        //ArrayList<>() без указания в скобках количества по умолчанию создает пустой массив на 10 элементов
        //Если задать 1, то при добавлении первых элементов массив будет часто перераспределяться память с учетом
        // формулы (oldCapacity * 3) / 2 + 1 (больше в 1.5 раза + 1) https://habr.com/ru/post/128269/,
        // а частые перераспределения уменьшаю производительность
        //Создавать заранее специально очень большим, тоже не имеет смысла, будет выделена лишняя память,
        // которая реально не будет использована
        List<Person> curArrayListPerson = new ArrayList<>();
        //Добавление элементов с созданием при вызове
        //add без указания индекса добавляет в конец
        curArrayListPerson.add(new Person("Имя1", "Фамилия1", MALE));
        //заранее созданных, полученных откуда-либо
        Person P2 = new Person("Имя2", "Фамилия2", MALE);
        curArrayListPerson.add(P2);
        //Можно добавлять дубли. При этом при первом удалении remove один удалится, при втором следующий
        //Добавил на позицию 0
        curArrayListPerson.add(0,P2);
        //Вывод
        System.out.println("curArrayListPerson add1, add2, add2: "+curArrayListPerson.toString());
        //Получение элемента по индексу (как и в массиве нумерация с 0)
        System.out.println("get(2): "+curArrayListPerson.get(2));
        //set(int index, E obj) естественно нужно указывать в какой позиции index нужно задать obj
        //remove(int index), remove(E obj) работает либо по индексту, либо по объекту

        //Инициирование ещё одного ArrayList
        List<Person> addArrayListPerson = new ArrayList<>();
        addArrayListPerson.add(new Person("Имя3", "Фамилия3", MALE));
        addArrayListPerson.add(new Person("Имя4", "Фамилия4", FEMALE));
        System.out.println("curArrayListPerson add1, add2: "+addArrayListPerson.toString());
        //Добавление в ArrayList списка элементов другого ArrayList
        curArrayListPerson.addAll(addArrayListPerson);
        System.out.println("curArrayListPerson.addAll(addArrayListPerson): "+curArrayListPerson.toString());

        //contains(Object obj) возвращает true, если obj является элементом вызывающей коллекции
        // contains,remove(Object obj) при вызове по объекту работают по ссылке, т.е. передав новый объект с похожим
        // реквизитным составом, он не найдется, не удалится
        Person duplicateP2 = new Person("Имя2", "Фамилия2", MALE);
        System.out.println("curArrayListPerson.contains(duplicateP2): "+curArrayListPerson.contains(duplicateP2));
        System.out.println("curArrayListPerson.contains(P2): "+curArrayListPerson.contains(P2));
        //contains(AllCollection<?> с) возвращает true, если вызывающая коллекция содержит все элементы с
        System.out.println("curArrayListPerson.containsAll(addArrayListPerson): "+curArrayListPerson.containsAll(addArrayListPerson));

        //Перебор можно посмотреть в DemoIterator

        //Сортировку можно посмотреть в DemoComparator

    }
}
