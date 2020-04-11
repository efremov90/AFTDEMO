package main.java;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class DemoIterator {
    public static void main (String[] args) {

        //https://juja.com.ua/java/java-collections/iterate-arraylist-java/
        List<String> colors = Arrays.asList("red", "yellow");

        System.out.println("Basic loop:");
        for (int i = 0; i < colors.size(); i++) {
            String color = colors.get(i);
            System.out.println("color: "+color);
        }
        System.out.println();

        System.out.println("foreach:");
        for (String color : colors) {
            System.out.println("color: "+color);
        }
        System.out.println();

        System.out.println("Basic loop with iterator:");
        for (Iterator<String> it = colors.iterator(); it.hasNext(); ) {
            String color = it.next();
            System.out.println("color: "+color);
        }
        System.out.println();

        //https://metanit.com/java/tutorial/5.10.php
        //https://hr-vector.com/java/iterator
        //https://www.examclouds.com/ru/java/java-core-russian/iterator
        System.out.println("Iterator with while loop:");
        Iterator<String> it = colors.iterator();
        while (it.hasNext()) {
            String color = it.next();
            System.out.println("color: "+color);
        }
        System.out.println();

        System.out.println("JDK 8 streaming example lambda expression:");
        colors.stream().forEach(color -> System.out.println("color: "+color));
        System.out.println();

        System.out.println("JDK 8 streaming example method reference:");
        colors.stream().forEach(System.out::println);
    }
}
