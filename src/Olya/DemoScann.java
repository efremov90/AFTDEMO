package Olya;
import java.util.Scanner;

public class DemoScann {
    public static void main (String[] args) {
        Scanner scan = new Scanner (System.in);
        Scanner scan2 = new Scanner (System.in);
        System.out.print("Введите любое целое число из интервала от -100 до 100: ");
        int zahl = scan.nextInt();
        if ((zahl > 100)| (zahl < -100)) {
            System.out.println("Тупица, не умеешь читать условие?? Вали отсюда!");
        }
        else if (zahl > 0) {
            System.out.println("Вы ввели положительное число");
        }
        else if (zahl == 0){
            System.out.println("Вы ввели 0");
        }
        else if (zahl < 0 ){
            System.out.println("Вы ввели отрицательное число");
        }
        System.out.println("Сейчас я угадаю его...  это: " + zahl);
        System.out.print("А теперь введите любое слово: ");
        String word = scan2.nextLine();
        System.out.println("Я угадаю первую букву, это: " + word.charAt(0) + "\nА само слово: " + word);
        }
}
