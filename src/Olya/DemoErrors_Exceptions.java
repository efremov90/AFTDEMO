package Olya;

public class DemoErrors_Exceptions {

    public static int getResult (int num) throws Errors_Exceptions {
        int result = 2;
            if (num < 1) {
                throw new Errors_Exceptions("номер должен быть больше 1", num);
            }
            for (int i = 1; i <= num; i++) {
                result += i;
            }
            return result;
        }



    public static void main(String[] args) {
        try {
            int[] ar = new int[5];
            ar[5] = 6;

            System.out.println(ar[4]);
        } catch (ArrayIndexOutOfBoundsException u) {
            System.out.println("Выход за рамки массива");
        }
        System.out.println("Продолжаем работать");


        try {
            int a=0;
            int b=5;
            System.out.println(b/a);}
        catch (ArithmeticException s) {
            s.printStackTrace();
        }
        System.out.println("И снова не останавливаемся");

        try {
            int c = 7;
            if (c > 5) {
                throw new Exception("с не должно быть больше 5");
            }
        }
        catch (Exception r) {
            System.out.println(r.getMessage());
        }


       try {
           System.out.println(DemoErrors_Exceptions.getResult(0));
       }
       catch (Errors_Exceptions d) {
           System.out.println("Перехвачено исключение подкласса исключений " + d);
       }



    }

}
