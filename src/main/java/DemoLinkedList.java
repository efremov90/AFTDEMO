package main.java;

import java.util.LinkedList;

/*
LinkedList https://vertex-academy.com/tutorials/ru/list-java-primer/
http://java-online.ru/java-linkedList.xhtml
https://www.examclouds.com/ru/java/java-core-russian/interface-queue

Реализация https://habr.com/ru/post/127864/
При добавлении элемента, последовательность шагов следующая (в середину):
1. Осуществляется поиск элемента перед которым будет производиться вставка
Запоминается элемент перед которым будет производиться вставка и через prev после которого
2. Создается новый новый экземпляр класса Entry
3. У вставляемого элемента задаются prev и next соответственно равными предшествующему и последующему,
а у них на него (у предшествующего next, у последующего prev задаются)

Обязательно:
Ответы https://ru.stackoverflow.com/questions/568119/Отличие-arraylist-от-linkedlist
Перый абзац https://habr.com/ru/post/262943/
без видео и комментариев https://vk.com/topic-68704273_30860436

Сложность ArrayList и LinkedList см. DemoArrayList

Таким образом LinkedList имеет смысл использовать, если приходится часто добавлять и удалять элементы,
В особенности, если добавлять и удалять в конец/начало, т.е. использовать как очередь или стек

Vector, Stack считаются устаревшими.
https://www.examclouds.com/ru/java/java-core-russian/legacy-collections
*/
public class DemoLinkedList {
    public static void main (String[] args) {

        //Инициирование LinkedList
        LinkedList<Person> curArrayListPerson = new LinkedList<>();
        //В остально аналогично ArrayList, см. DemoArrayList
        //Дополнительно к add, get, remove есть методы интерфейсов Queue, Deque: addFirst, addLast, getFirst, getLast,
        // рор, push

    }
}
