package main.java;

import java.util.HashSet;

import static main.java.GenderType.MALE;

public class DemoSet {
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
