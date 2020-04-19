package main.java;

//String https://www.examclouds.com/ru/java/java-core-russian/stroki
public class DemoString {
    public static void main(String[] args) {
        System.out.println("Размещение в String Pool");
        System.out.println("abcd == abcd: "+("abcd"=="abcd"));
        String s1 = "abcd";
        String s2 = "abcd";
        System.out.println("s1==s2: "+(s1==s2));
        System.out.println();

        System.out.println("Создание отдельных объектов");
        String s3 = new String("abcd");
        String s4 = new String("abcd");
        System.out.println("s3==s4: "+(s3==s4));
        System.out.println("s1==s4: "+(s1==s4));
        System.out.println();

        //Про equals можно освежить память в DemoOOP
        System.out.println("s3.equals(s4): "+s3.equals(s4));
        System.out.println("s1.equals(s4): "+s1.equals(s4));
        System.out.println();

        String s5 = "abcd";
        String s6 = "abce";
        //Если результат метода compareTo меньше нуля - вызывающая строка меньше str (по сортировке).
        //Больше нуля - вызывающая строка больше str.
        //Ноль - две строки эквивалентны.
        System.out.println("s5.compareTo(s6): "+(s5.compareTo(s6)));
        System.out.println();

        //indexOf/lastIndexOf ищет первое/последнее вхождение символа или подстроки
        System.out.println("indexOf: "+"Hello world".indexOf("world"));
        //contains проверяет входит ли подстрока в строку
        System.out.println("contains: "+"Hello World".contains("world"));
        System.out.println();

        //substring вырезает подстроку из строки, используя указанные позиции
        System.out.println("substring: "+"Hello World".substring(5,10));
        //Помним, что у String как и у Array нумерация начинается с 0
        System.out.println("substring(0,1): "+"Hello".substring(0,1));
        System.out.println("replace char: "+"Hello".replace('l', 'R'));
        System.out.println("replace string: "+"Hello world".replace("world","WORLD"));
        System.out.println();

        System.out.println("concat: "+"Hello".concat(" World"));
        System.out.println("toLowerCase: "+"Hello".toLowerCase());
        System.out.println("toUpperCase: "+"Hello".toUpperCase());
        System.out.println("trim: "+"    Hello World ".trim());
        System.out.println();

        //Полезные функции
        String[] wordsArray = {"one", "two", "three"};
        String wordsString = String.join(";", wordsArray);
        System.out.println("join words (Array to String): "+wordsString);
        String[] split = wordsString.split(";");
        System.out.println("split words (String to Array):");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
