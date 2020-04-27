package Olya;

public class DemoInterface implements Interface {

    public static void main(String[] args) {
 Interface in1 = new DemoInterface();
 ClassforInterface in2 = new ClassforInterface();

        System.out.println(Interface.z);
        System.out.println(Interface.method());

        System.out.println(in1.som());
        System.out.println(in2.som());


    }
}
