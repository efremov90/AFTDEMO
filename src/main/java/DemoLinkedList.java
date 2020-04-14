package main.java;

import java.util.LinkedList;

/*
LinkedList http://java-online.ru/java-linkedList.xhtml
https://www.examclouds.com/ru/java/java-core-russian/interface-queue

Обязательно:
Ответы https://ru.stackoverflow.com/questions/568119/Отличие-arraylist-от-linkedlist
Перый абзац https://habr.com/ru/post/262943/
без видео и комментариев https://vk.com/topic-68704273_30860436

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

Таким образом LinkedList имеет смысл использовать, если приходится часто добавлять и удалять элементы,
В особенности, если добавлять и удалять в конец/начало, т.е. использовать как очередь или стек
*/
public class DemoLinkedList {
    public static void main (String[] args) {

        //Инициирование LinkedList
        LinkedList<Person> curArrayListPerson = new LinkedList<>();
        //В остально аналогично ArrayList, см. DemoArrayList
        //Дополнительно add, get, remove есть версии First и Last, например, addFirst, getLast

    }
}
