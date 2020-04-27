package Olya;

import java.lang.reflect.Array;
import java.util.Arrays;

import static Olya.SeasonType.*;

public class DemoEnumSeason {
    public static void main(String[] args) {
        System.out.println(WINTER + " " + WINTER.getCode() + " " + WINTER.getName());
        SeasonType s1 = SeasonType.valueOf("SUMMER");
        System.out.println(s1);
        System.out.println(Arrays.toString(SeasonType.values()));

        SeasonType [] s2 = SeasonType.values();
        for (SeasonType x : s2) {
            System.out.print(x);
            System.out.println();
        }

        System.out.println("Получить название сезона по коду: " + SeasonType.getNamebyCode(4));


    }

}
