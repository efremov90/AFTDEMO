package Olya;

public class DemoGetSet {
    String eyescolor, haircolor, skincolor;
    double weigth, tall;

    // Конструктор с одинаковым названием параметра и переменной
    DemoGetSet (String skincolor) {
        this.skincolor = skincolor;
    }

    void setParaments (String a, String b) {
        eyescolor = a;
        haircolor = b;
    }

    void setParametrs (double a, double b){
        weigth = a;
        tall = b;
    }

    double getParametrs () {
        return weigth + tall;
    }

public static void main (String [] args) {
        DemoGetSet person1 = new DemoGetSet("белая");
        DemoGetSet person2 = new DemoGetSet("розовая");

        person1.setParaments("зеленые", "каштановые");
        person1.setParametrs(52, 171);
        person2.setParaments("голубые", "рыжие");
        person2.setParametrs(55, 174);

    System.out.println( "Человек1 имеет " + person1.eyescolor + " глаза и " + person1.haircolor + " волосы. \nСумма вес+рост = " + person1.getParametrs());
    System.out.println( "Человек2 имеет " + person2.eyescolor + " глаза и " + person2.haircolor + " волосы. \nСумма вес+рост = " + person2.getParametrs());
    System.out.println("Цвет кожи человека 1 : "  + person1.skincolor);
}



}
