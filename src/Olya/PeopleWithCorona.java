package Olya;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class PeopleWithCorona {
    public static void main(String[] args) {

        // Создаю обьект сканера
        Scanner scan = new Scanner(System.in);

        // Составляю таблицу кодов субъектов РФ и соответствующих им названий
        // Я несколько перечислила, а вообще их 89  http://новыеформы.рф/kodirf.html
        HashMap<String, String> cities = new HashMap<>();
        cities.put("02", "Республика Бурятия");
        cities.put("03", "Республика Алтай");
        cities.put("04", "Республика Дагестан");
        cities.put("05", "Республика Ингушетия");
        cities.put("06", "Кабардино-Балкарская Республика");
        cities.put("07", "Республика Калмыкия");
        cities.put("08", "Карачаево-Черкесская Республика");
        cities.put("09", "Республика Карелия");
        cities.put("10", "Республика Комии");
        cities.put("11", "Республика Марий Эл");
        cities.put("12", "Республика Мордовия");

        // Для каждого города (s<код субъекта РФ>)  веду расширяемый список имен больных
        ArrayList<String> s07 = new ArrayList<>();
        {
            s07.add("РОР");
            s07.add("ДДЛ");
            s07.add("HHH");
            s07.add("РОH");
            s07.add("РОП");
            s07.add("АОР");
        }

        ArrayList<String> s08 = new ArrayList<>();
        {
            s08.add("ОРО");
            s08.add("ЛВЛ");
            s08.add("ООЯ");
        }

        ArrayList<String> s02 = new ArrayList<>();
        {
            s02.add("ОРВ");
            s02.add("ДЛД");
            s02.add("ЩЗЛ");
            s02.add("ЩФП");
        }

        ArrayList<String> s03 = new ArrayList<>();
        {
            s03.add("ВДФ");
            s03.add("ЦВВ");
        }

        ArrayList<String> s05 = new ArrayList<>();
        {
            s05.add("ВЛЛ");
        }

        // Составляю таблицу зараженных субъектов РФ и количеством больных людей для каждого из них
        HashMap<String, Integer> people = new HashMap<>();
        people.put("02", s02.size());
        people.put("03", s03.size());
        people.put("05", s05.size());
        people.put("07", s07.size());
        people.put("08", s08.size());

        // Вывожу инфу по количеству больных в каждом субъекте РФ, вводя его код
        System.out.println("Введите код субъекта РФ : ");
        String code = scan.nextLine();

        System.out.println("Количество больных в населенном пункте " + cities.get(code) + " составляет " + people.get(code) + " человек. ");


        String[][] array = {
                {"Номер субъекта", "Нахзвание субъекта"},
                {"01", "Республика Бурятия"},
                {"02", "Бла"},
                {"03", "Блабла"},
                {"04", "Юлаюлаюла"},
                {"05", "Блаблаблабла"},
                {"06", "Блаблаблабла"},
                {"07", "Блаблаблабла"},
                {"08", "Блаблаблабла"},
                {"09", "Бееееееееаааааааааааававаааа"},
                {"10", "Блпппппппппааавввввааааааа05"}
        };
        final int j = 1;
        System.out.println("Введите номер субъекта");
        int i = scan.nextInt();
        System.out.println("Субъект называется: " + array [i][j]);

    }
}



