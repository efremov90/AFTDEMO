package main.java;

/*
HashMap https://vertex-academy.com/tutorials/ru/map-v-java-hashmap/
http://java-online.ru/java-map.xhtml Держать в уме, что Hashtable считается устаревшим
https://devcolibri.com/hashmap-и-hashset-что-это-на-самом-деле/
https://javahelp.online/collections/map-v-java-s-primerami
https://www.examclouds.com/ru/java/java-core-russian/map
http://developer.alexanderklimov.ru/android/java/hashmap.php

Реализация https://habr.com/ru/post/128017/
table — Массив типа Entry[], который является хранилищем ссылок на списки (цепочки) значений;
При добавлении элемента, последовательность шагов следующая:
1. Генерируется хэш на основе ключа key.hashCode()
2. Определяется позиция в массиве, куда будет помещен элемент indexFor(hash, tableLength)
Помним, что элементы массива это ссылки на списки.
Поскольку позиция в массиве расчитывается с учетом hashCode ключа, то если ключом является объект собственного класса,
т.е. не String, не Integer и т.д., то если у него будет плохая хэш-функция, например, константу постоянно возвращать,
 то HashMap по сути превратится в LinkedList, поскольку все элементы будут вставлять в один и тот же список.
 Соответственно HashMap потеряет все свои преимущества и будет работать медленно как LinkedList.
3. Хэш и ключ нового элемента поочередно сравниваются с хэшами и ключами элементов из списка.
При совпадении этих параметров, значение элемента списка перезаписывается.
4. Если совпадений не выявлено, будет вызван метод addEntry(hash, key, value, index) для добавления нового элемента.
Новый элемент добавляется в начало цепочки.
5. Все элементы с null-ключами всегда помещаются в table[0]. При этом если с null-ключом уже есть, то перезаписывается.

Когда массив table[] заполняется до предельного значения, его размер увеличивается вдвое и происходит
перераспределение элементов (пересчитываются индексы с учетом нового размера и перераспределяет по новому
массиву).
Аналогично ArrayList можно задать начальную размерность. По умолчанию создается на 16 элементов.
В отличии от ArrayList можно задать коэффициент загрузки (наполнения). По умолчани 0.75. Это означает, что
при заполнении емкости на 3/4 произойдет ее расширение в 2 раза (всегда в 2, у ArrayList в 1.5).
Таким образом выводы аналогичны ArrayList. Если задать начальную размерность 1, то при добавлении первых элементов
массив будет часто перераспределяться память, а частые перераспределения уменьшаю производительность
Создавать заранее специально очень большим, тоже не имеет смысла, будет выделена лишняя память,
которая реально не будет использована.

Как и у ArrayList при удалении размер массива автоматически не уменьшается. А принудительно невозможно.

Сложность HashMap (по ключу элемента):
        |   HashMap   |  LinkedHashMap  |   TreeMap   |
add     |     O(1)    |       O(1)      |   O(log N)  |
get     |     O(1)    |       O(1)      |   O(log N)  |
remove  |     O(1)    |       O(1)      |   O(log N)  |
contains|     O(1)    |       O(1)      |   O(log N)  |

Операции получения и удаления элемента могут выполняться за время O(1), если хэш-функция равномерно распределяет
элементы и отсутствуют коллизии. Среднее же время работы будет Θ(1 + α), где α — коэффициент загрузки. В самом худшем
случае, время выполнения может составить Θ(n) (все элементы в одном списке);

HashMap позволяет дублировать значения, но не ключи.

HashMap - хранит значения в произвольном порядке (нет гарантий, что порядок сохранится на протяжении времени), но
позволяет быстро искать элементы карты.
Позволяет задавать ключ или значение ключевым словом null.
LinkedHashMap - хранит значения в порядке добавления, но работает медленнее и занимает больше места (кроме самого
элемента хранятся еще указатели на следующий и предыдущий элементы списка).
TreeMap - сама сортирует значения по заданному критерию. TreeMap используется либо с Comparable элементами, либо в
связке с Comparator. https://javarush.ru/groups/posts/2584-osobennosti-treemap
TreeMap реализован на красно-черном дереве https://ru.wikipedia.org/wiki/Красно-чёрное_дерево

Hashtable считается устаревшим. Новый синхронный потокобезопасный вариант это ConcurrentHashMap.
https://www.examclouds.com/ru/java/java-core-russian/legacy-collections
http://javastudy.ru/interview/collections/

ConcurrentHashMap Многопоточный аналог HashMap. Все данные делятся на отдельные сегменты и блокируются только
отдельные сегменты при изменении, что позволяет значительно ускорить работу в многопоточном
режиме. https://itnan.ru/post.php?c=1&p=314386

Полезные ссылки при подготовке к собеседованию
http://javastudy.ru/interview/collections/
https://itnan.ru/post.php?c=1&p=314386
https://itvdn.com/ru/blog/article/jjd

Нюансы работы с коллекциями и массивами https://javarush.ru/groups/posts/855-10-oshibok-zachastuju-dopuskaemihkh-java-razrabotchikami
*/

import java.util.*;

import static main.java.GenderType.FEMALE;
import static main.java.GenderType.MALE;

public class DemoHashMap {
    public static void main(String[] args) {

        HashMap<String,Person> curHashMap = new HashMap<>();
        //Добавление элементов с созданием при вызове
        curHashMap.put("N1",new Person("Имя1", "Фамилия1", "N1",MALE,30));
        //заранее созданных, полученных откуда-либо
        Person P2 = new Person("Имя2", "Фамилия2", "N2",FEMALE,31);
        curHashMap.put(P2.getPersonalNumber(),P2);
        //При добавлении дубля по ключу значения перезаписыаются
        curHashMap.put("N1",new Person("Имя3", "Фамилия3", "N3",MALE,30));
        //replace(K key, V value) Замена значения value для записи с ключом key
        //т.е. если элемента с key нет, то в отличии от put добавления нового не произойдет
        curHashMap.replace("N4",new Person("Имя4", "Фамилия4", "N4",MALE, 35));
        //Вывод
        System.out.println("curHashMap put, put, put, replace: "+curHashMap.toString());

        //Получение элемента по ключу
        System.out.println("get('N2'): "+curHashMap.get("N2"));

        //containsKey(Object key) возвращает true, если key является элементом вызывающей коллекции
        //также как и во всех коллекциях
        System.out.println("curHashMap.containsKey('N2'): "+curHashMap.containsKey("N2"));

        //Получение ключей записей в виде коллекции Set<K>
        System.out.println("curHashMap.keySet(): "+curHashMap.keySet());
        //Получение значений записей в виде коллекции Collection<V>
        System.out.println("curHashMap.values(): "+curHashMap.values());
        //Получения объектов записей в виде коллекции Set<Map.Entry<K,V>>
        System.out.println("curHashMap.entrySet(): "+curHashMap.entrySet());
        System.out.println();

        //Перебор https://javahelp.online/collections/map-v-java-s-primerami
        //Варианты перебора аналогичны DemoIterator
        //Помним, что HashMap хранит значения в произвольном порядке, поэтому вывод будет каждый раз в произвольном
        // порядке.
        //В данном случае использовался перебор entrySet().
        // Также можно работать с keySet(), values().
        for (Map.Entry<String, Person> stringPersonEntry : curHashMap.entrySet()) {
            System.out.println("key = "+stringPersonEntry.getKey()+"; "+"person = "+stringPersonEntry.getValue());
        }
        System.out.println();

        //Сортировка через List<Map.Entry<String, Person>>
        List<Map.Entry<String, Person>> listMapEntry = new LinkedList<>(curHashMap.entrySet());
        Collections.sort(
                listMapEntry,
                //Comparator аналогично DemoComparator
                (o1,o2)->((o1.getValue().getLastName().compareTo(o2.getValue().getLastName()))
                        +(o1.getValue().getFirstName().compareTo(o2.getValue().getFirstName())))
        );
        System.out.println("Сортировка по ФИ List<Map.Entry<String, Person>>:");
        System.out.println(listMapEntry.toString());
        System.out.println();

        //Сортировка через List<Person> аналогично DemoComparator
        List<Person> persons = new LinkedList<>(curHashMap.values());
        Collections.sort(
                persons,
                (o1,o2)->((o1.getLastName().compareTo(o2.getLastName()))
                        +(o1.getFirstName().compareTo(o2.getFirstName())))
        );
        System.out.println("Сортировка по ФИ List<Person>:");
        System.out.println(persons.toString());
        System.out.println();

        //Сортировка через SortedMap http://espressocode.top/sortedmap-java-examples/
        //Интерфейс SortedМap расширяет Мар. Упорядочивает элементы по ключу. По умолчанию в порядке возрастания.
        // Упорядочивания по значению нет. При использовании в качестве ключа String, Integer и т.п. используется их
        // базовый Comparator. Для своих классов нужно обязательно определять свой Comparator, иначе будет ошибка:
        // java.lang.ClassCastException: class main.java.Person cannot be cast to class java.lang.Comparable
        // Нулевой ключ или нулевое значение недопустимы.
        // Реализует этот интерфейс класс TreeMap.
        // https://javahelp.online/collections/java-sortedmap
        System.out.println("Сортировка SortedMap:");
        //Используется конструктор класса TreeMap TreeMap(Map<? extends K, ? extends V> m)
        //Поскольку ключом у curHashMap является String, то сортировка автоматически будет по реализованному в нём
        // Comparator'у
        SortedMap sortedMap = new TreeMap(curHashMap);
        System.out.println(sortedMap);
        System.out.println();

        //Можно переопредять Comparator, см. DemoComparator
        System.out.println("Сортировка SortedMap (обратно):");
        //Используется конструктор класса TreeMap TreeMap(Comparator<? super К> comparator)
        SortedMap<String,Person> descSortedMap = new TreeMap<String, Person>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                //Для обратного порядка нужно o2 сравнивать с o1
                return o2.compareTo(o1);
            }
        });
        descSortedMap.putAll(curHashMap);
        System.out.println(descSortedMap);
        //У SortedMap есть дополнительные методы: firstKey, lastKey http://espressocode.top/sortedmap-java-examples/
        System.out.println("lastKey() = "+descSortedMap.lastKey()+"; get(lastKey()) = "+descSortedMap.get(descSortedMap.lastKey()));
        //Также еще есть методы headMap, tailMap, subMap
        System.out.println();

        //Сортировка через SortedSet http://espressocode.top/sortedset-java-examples/
        //Про HashSet см. DemoHashSet
        //Здесь за одно будет сразу показана работа с SortedSet
        //Реализует этот интерфейс класс TreeSet.
        //В остальном аналогично описанию SortedМap, см. выше
        // https://javahelp.online/collections/sortedset-v-java
        System.out.println("Сортировка SortedSet по возрасту:");
        SortedSet<Person> ascAgeTreeSet = new TreeSet<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                //Вместо compareTo можно также реализовать через разницу
                return o1.getAge()-o2.getAge();
            }
        });
        ascAgeTreeSet.addAll(curHashMap.values());
        System.out.println(ascAgeTreeSet);
        //У SortedSet есть дополнительные методы: first(), last() http://espressocode.top/sortedset-java-examples/
        System.out.println("last() = "+ascAgeTreeSet.last());
        //Также еще есть методы headSet, tailSet, subSet
        System.out.println();

        //Сортировка через SortedSet
        System.out.println("Сортировка SortedSet по возрасту (обратно):");
        //Через lyambda, см. DemoComparator
        SortedSet<Person> descAgeSortedSet = new TreeSet<>(
                //Для использования compareTo int нужно привести к Integer
                (o1,o2)->((Integer) o2.getAge()).compareTo((Integer) o1.getAge())
        );
        descAgeSortedSet.addAll(curHashMap.values());
        System.out.println(descAgeSortedSet);
    }
}