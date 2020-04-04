package Olya;

//label - зло, аналог GOTO в BASIC, никогда не пользоваться
public class DemoBreakLabel {
    public static void main (String[] args) {
        label:
        for (int i = 0; i < 10; i++) {
            if (i==6) {
                break;
            }
            System.out.print(i + " : ");
            for (int j=10; j < 15; j++) {
                System.out.print(j + " ");
                break label;
            }
            System.out.println();
        }
    }
}
