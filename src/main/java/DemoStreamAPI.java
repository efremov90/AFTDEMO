package main.java;

import java.util.stream.Stream;

public class DemoStreamAPI {
    public static void main (String [] args) {
        System.out.println("Hello world!");

        Stream<String> streamFromValues = Stream.of("a1", "a2", "a3");
        streamFromValues.forEach(System.out::println);
        System.out.println(streamFromValues.toString());

    }
}
