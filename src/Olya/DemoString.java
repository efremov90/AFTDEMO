package Olya;

import java.util.Arrays;

public class DemoString {
    public static void main(String[] args) {
         String str1 = new String("Olya");
        System.out.println( str1);
        String str2 = new String(str1 + " Efremova");
        System.out.println(str2);

        char [] char1 = {'Я',' ','и','д','у'};
        String str3 = new String (char1);
        String str4 = new String (char1, 2, 3);
        System.out.println(str3);
        System.out.println("В строке str3 сказуемое: " + str4);
        System.out.println("Длина str3: " + str3.length());
        System.out.println("Длина слова 'Кукушка': " + "Кукушка".length() + "\nа индекс корня 'Кук': " + "Кукушка".indexOf("Кук"));
         System.out.println("Крокодил начинается с 'Кроко' : " + "Крокодил".startsWith("Кроко") + "\nзаканчивается на 'дил' : " + "Крокодил".endsWith("дил") + "\nи имеет в середине 'код' : " + "Крокодил".contains("код"));
         System.out.println("3й символ str1 : " + str1.charAt(2));

        //Сравнение строк
        String str5 = "Олечка";
        String str6 = "Олечка";
        String str7 = "оЛеЧкА";
        System.out.println("str5 == str6 : " + (str5 == str6));
        System.out.println("str5 == str7 : " + (str5 == str7));
        System.out.println("str5 == str7 : " + str5.equals(str7));
        System.out.println("str5 == str7 с игнором регистра : " + str5.equalsIgnoreCase(str7));
        System.out.println("Погода == Погода : " + ("Погода" == "Погода"));
        System.out.println("str1 == 'Olya' : " + (str1 == "Olya"));
        System.out.println(str1.equalsIgnoreCase("olya"));
         System.out.println("str1 == str2 : " + (str1 == str2));
        String str8 = "Удав";
        String str9 = "Удар";
         String str10 = "Удаб";
         System.out.println(str8.compareTo(str9));
         System.out.println(str8.compareTo("Удав"));
         System.out.println(str8.compareTo(str10));

         //Преобразование строк
         String str11 = "Лабо";
         System.out.println("Лабо+ратория = " + str11.concat("ратория"));
         String str12 = str11.toUpperCase().replace("Л","Ж");
        System.out.println( str12);
        System.out.println(str12.substring(0,1));

        String[] arrayString = {"Сегодня","я","иду","гулять"};
        String sentense = String.join(" ", arrayString);
        System.out.println(sentense);
        String [] split = sentense.split(" ");
        for (String a : split) {
            System.out.println(a);
        }

        //Преобразование строки в массив символов
        String line = "Линия";
        char [] tochar = new char[line.length()];
                tochar = line.toCharArray();
        System.out.println( tochar);
        System.out.println("Второй символ массива: " + tochar[2]);


    }
}
