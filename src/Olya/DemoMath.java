package Olya;

public class DemoMath {
    public static void main(String[] args) {
        int a=56;
        int b=95;
        double c=7.5569;
        // Формула для поиска рандомного числа: (int) (Math.random()*(b-a+1))+a
        int d= (int) (Math.random()*(200+1))-100;
        String phrase = "Сегодня";
        System.out.println("Это число " + Math.max(a, b) + " больше, чем " + Math.min(a,b));
        System.out.println("Округляем " + c + " до ближайшего целого числа " + Math.round(c));
        System.out.println("Округляем " + c + " до ближайшего нижнего целого числа " + Math.floor(c));
        System.out.println("5ая буква слова \"" + phrase + "\" : " + phrase.charAt(4));
        System.out.println("Сумма чисел a и b : " + Math.addExact(a,b));
        System.out.println("В этот раз рандомное значение от -100 до 100 включительно выпадает : " + d);
    }
}
