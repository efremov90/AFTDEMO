package Olya;

import java.util.concurrent.Callable;

public class DemoOOP {
    public static void main (String[] args) {
       Country russia = new Country("Russia", 146745098, 17098246, "russian");
       Country germany = new Country("Germany", 81408372, 357385, "german");
       City moscow = new City("Russia", 12692466, 2561, "russian", "Moscow");
       City essen = new City("Germany", 583109, 210, "german", "Essen" );
       District nagatino = new District("Russia", 0.12, 9.8, "russian", "Moscow", "Nagatino");
       District blumenthal = new District("Germany", 0.05, 4.8, "german", "Essen", "Blumenthal");

      nagatino.aboutMe();
      System.out.println("In " + russia.countryName + " on square " + russia.square + " km2  live " + russia.population + " people.\nBasic language of the country is " + russia.language + ".");
      System.out.println("In " + russia.countryName + " prozent of people having corona = " + russia.prozentOfSick(10131) + " %.");
        moscow.ProzentCityCorona(6698);
        blumenthal.aboutMe();
        System.out.println("In " + germany.countryName + " on square " + germany.square + " km2  live " + germany.population + " people.\nBasic language of the country is " + germany.language + ".");
        System.out.println("In " + germany.countryName + " prozent of people having corona = " + germany.prozentOfSick(10131) + " %.");
        essen.ProzentCityCorona(3920);
    }
}
