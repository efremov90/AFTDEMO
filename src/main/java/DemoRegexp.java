package main.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// https://docs.oracle.com/javase/9/docs/api/java/util/regex/Pattern.htmls
// https://www.examclouds.com/ru/java/java-core-russian/regex-russian
public class DemoRegexp {
    public static void main(String[] args) {

        String input;

        //примеры некоторых регулярок http://html5pattern.com/Miscs

        // Класс Pattern не предусматривает никаких публичных конструкторов. Чтобы создать шаблон, необходимо
        // вызвать compile.
        final Pattern TELEPHONE_NUMBER_PATTERN = Pattern.compile("(\\+)\\d{11}"); // (\+)\d{11}
        input = "+123456789123"; //12 цифр
        // Далее создание matcher-объекта, который будет сопоставлять произвольные последовательности символов
        // с регулярным выражением
        Matcher mtp = TELEPHONE_NUMBER_PATTERN.matcher(input);
        if (! mtp.matches()) System.out.println("Некорректный формат " +
                "телефонного номера.");

        final Pattern TIME_PATTERN = Pattern.compile("(0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}");
        input = "30:59:00"; //начинается с 3
        //Можно не объявлять matcher-объект отдельно, а сразу вызывать от имени регулярного выражения.
        //Matcher-объект естественно создается, только автоматически, без присвоения объяленной переменной.
        //Так можно поступать, когда вызывается один раз. Ниже в FIND_WORDS_PATTERN приведен пример,
        // когда необходимо объявлять отдельно заранее
        if (!TIME_PATTERN.matcher(input).matches()) System.out.println("Некорректный формат времени.");

        final Pattern DATE_PATTERN = Pattern.compile("(0[1-9]|1[0-9]|2[0-9]|3[01]).(0[1-9]|1[012]).[0-9]{4}");
        input = "32.03.2020"; //32ое число
        if (!DATE_PATTERN.matcher(input).matches()) System.out.println("Некорректный формат даты.");

        final Pattern VALID_STRING_PATTERN = Pattern.compile("[A-Za-zА-ЯЁа-яё0-9 \\-/.\",()*+_№]*"); // [A-Za-zА-ЯЁа-яё0-9 \-/.",()*+_№]*
        input = "1234#567"; //присутствует #
        if (!VALID_STRING_PATTERN.matcher(input).matches()) System.out.println("Недопустимые символы." +
                " Допустимые символы \\\"A-z А-я 0-9 -/.\\\",()*+_№\\\"\".");

        final Pattern USER_PATTERN = Pattern.compile("^[A-za-z\\d\\.]{5,50}$"); // ^[a-z\d\.]{5,50}$
        input = "sanya923@mail"; //присутствует @
        if (!USER_PATTERN.matcher(input).matches()) System.out.println("Некорректный формат имени пользователя.");

        final Pattern ACCOUNT_PATTERN = Pattern.compile("^[\\d]{20}$"); // ^[\d]{20}$
        input = "40702A10123456789123"; //присутствует A
        if (!ACCOUNT_PATTERN.matcher(input).matches()) System.out.println("Некорректный формат счета.");

        final Pattern PRICE_PATTERN = Pattern.compile("\\d+(\\.\\d{2})?"); // \d+(\.\d{2})? валидны 1 1.00
        input = "1.0"; //только десятичный разряд
        if (!PRICE_PATTERN.matcher(input).matches()) System.out.println("Некорректный формат ценны.");

        final Pattern PERSON_DOCUMENT_PATTERN = Pattern.compile("([0-9]{4}) ([0-9]{6})");
        input = "1234567890"; //отсутствует разделитель - пробел
        if (!PERSON_DOCUMENT_PATTERN.matcher(input).matches()) System.out.println("Серия и номер паспорта " +
                "пользователя не " +
                "соответствует формату СССС НННННН, где CCCC - Серия четыре знака, через пробел НННННН - Номер шесть знаков.");
        System.out.println();

        // Если использовать только [\s\,\!|\.]+\d*, то будут оставаться пробелы, которые расцениваются как лексемы,
        // а не как разделители. чтобы этого избежать пришлось обрамить в () и добавить + т.е. базовая группа
        // [\s\,\!|\.]+\d* может повторяться
        System.out.print("split to ArrayList: ");
        final Pattern SPLIT_WORDS_PATTERN = Pattern.compile("([\\s\\,\\!|\\.]+\\d*)+");
        input = "Hello  Java88!  Hello  1, 2 , 3 JavaScript!88  JavaSE  88.";
        String[] words = SPLIT_WORDS_PATTERN.split(input);
//        for (String word : words)
//            System.out.println(word);
        System.out.println(Arrays.toString(words));
        System.out.println();

        //Тоже самое разбиение на слова, но с использованием find, т.е. непосредственного поиска слов.
        //Start и end позволяют получить индексы начала и конца текущего найденного совпадения
        System.out.print("find to ArrayList: ");
        final Pattern FIND_WORDS_PATTERN = Pattern.compile("(\\d*([A-Za-z]+)\\d*)");
        input = "Hello  Java88!  Hello  1, 2 , 3 JavaScript!88  JavaSE  88.";
        //Тут нужно объявить matcher-объект отдельно заранее, т.к. к нему потом придется обращаться через start и end
        Matcher mfw = FIND_WORDS_PATTERN.matcher(input);
        //ArrayList https://www.examclouds.com/ru/java/java-core-russian/klass-arraylist
        List<String> wordsArrList = new ArrayList<>();
        while (mfw.find())
            wordsArrList.add(input.substring(mfw.start(), mfw.end()));
        System.out.println(wordsArrList.toString());
        System.out.println();

        Pattern pattern = Pattern.compile("hello");
        Matcher matcher = pattern.matcher(input);

        input = "Hello Java! Hello JavaScript! JavaSE 8.";
        pattern = Pattern.compile("Java(\\w*)");
        matcher = pattern.matcher(input);
        String newStr = matcher.replaceAll("HTML");
        System.out.println(newStr);

        System.out.println();
        input = "Hello Java! Hello JavaScript! JavaSE 8.";
        String myStr = input.replaceAll("Java(\\w*)", "HTML");
        System.out.println(myStr); // Hello HTML! Hello HTML! HTML 8.
        System.out.println();
    }
}