package Olya;

public class DemoErrors_Exceptions {

    public static int getResult (int num){
        int result = 2;
        try {
            if (num < 1) throw new Errors_Exceptions("номер должен быть больше 0", num);
            for (int i = 1; i <= num; i++) {
                result += i;
            }
            return result;
        }
        catch (Errors_Exceptions g) {
            System.out.println("переделать");
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


        System.out.println(DemoErrors_Exceptions.getResult(0));




    }

}
