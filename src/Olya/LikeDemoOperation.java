package Olya;

public class LikeDemoOperation {
    /* Задания написать функцию для целого числа a, которая:
    1) при делении a на 3 без остатка выводит "делится на 3 без остатка"
    2) при делении a на 5 без остатка выводит "делится на 5 без остатка"
    3) при делении a на 15 без остатка выводит "делится на 15 без остатка"
    4) в остальных случаях выводит значение а.
     */

    public static  void divide (int a) {
        if ((a % 3) == 0) System.out.println(a + " делится на 3 без остатка");
        if ((a % 5) == 0) System.out.println(a + " делится на 5 без остатка");
        if ((a % 15) == 0) System.out.println(a + " делится на 15 без остатка");
        if  (!((a%3)==0) && !((a%5)==0)) System.out.println(a);
    }
        public static void main (String [] args) {
        divide(3);
        divide(5);
        divide(15);
        divide(22);
        }


}
