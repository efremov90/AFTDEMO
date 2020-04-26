package Olya;

public class DemoOOPBox {
    static void changeObject (BOX box) {
        box.width *= 2;
        box.height *= 2;
        box.depth *= 2;
    }

    static void changePrimitives (int m, int n) {
        m /= 2;
        n /= 2;
    }

    public static void main(String[] args) {
        BOX boxxx = new BOX(); // испол-ся конструктор без параметров

        BOX box1 = new BOX(4, 5, 6);
        BOX box2 = box1;
        box2.width = 50;
        System.out.println("box1.width = " + box1.width);
        System.out.println("box2.width = " + box2.width);

        changeObject(box1);
        System.out.println("После изменений: " + box1.width + " " + box1.height + " " + box1.depth);

        int x=10; int y=20;
        changePrimitives(x, y);
        System.out.println("x = " + x + " y = " + y);

    }

}
