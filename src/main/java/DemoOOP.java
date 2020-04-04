package main.java;

import java.util.Objects;

//Уровни доступа для класса https://www.examclouds.com/ru/java/java-core-russian/upravlenie-dostupom
//Если у класса имеется уровень доступа по умолчанию, такой класс
// оказывается доступным только для кода из данного пакета.
class Box {
    double width;
    double height;
    double depth;

    Box(double w, double h, double d) {
        width = w;
        height = h;
        depth = d;
    }

    //https://www.examclouds.com/ru/java/java-core-russian/konstruktori
    //Конструктор по умолчанию необязательно объявлять до объявления собственного И одновременно
    // явного использования конструктора по умолчанию.
    //Если его закомментировать, то в main на строчке Box box1 = new Box(); будет ошибка
    Box() {}
}

class ColorBox extends Box {
    String color;

    public ColorBox(int width, int height, int depth, String color) {
        //https://www.examclouds.com/ru/java/java-core-russian/keyword-super
        // Вызов вызов конструктора суперкласса должен быть всегда в первом операторе,
        // выполняемом в теле конструктора подкласса.
        super(width,height,depth);
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.color = color;
    }
}

class Point {
    double x;
    double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.getX(), getX()) == 0 &&
                Double.compare(point.getY(), getY()) == 0;
    }

    @Override
    public int hashCode() {
        //как выячисляется hashCode можно узнать провалившись по hash в Object, а потом еще hashCode в Array:
        //result = 31 * result + (element == null ? 0 : element.hashCode());
        return Objects.hash(getX(), getY());
    }
}

class ReturnObject {
    int a;

    ReturnObject(int i) {
        a = i;
    }

    ReturnObject incrementByTen() {
        ReturnObject temp = new ReturnObject(a + 10);
        return temp;
    }
}

//https://www.examclouds.com/ru/java/java-core-russian/upravlenie-dostupom
// Если класс оказывается открытым public, он должен быть единственным открытым классом, объявленным в файле,
// а имя этого файла должно совпадать с именем класса.
// Если класс объявлен как public, он доступен из любого другого кода.
public class DemoOOP {
//    Изменение объекта
    static void changeObject(Box box) {
        box.width *= 2;
        box.height /= 2;
        box.depth += 2;
    }

//    Изменение примитивных типов
    static void changePrimitives(int a, int b) {
        a *= 2;
        b /= 2;
    }

    public static void main(String[] args) {

        //для демонстрации конструктора по умолчанию, см. класс Box
        Box box1 = new Box();

        //https://www.examclouds.com/ru/java/java-core-russian/oop-klassi-metodi
        System.out.println("Присваивание переменным ссылок на объекты:");
        Box b1 = new Box(10,20,30);
        //При объявлении переменной b2, вместо создания нового объекта,
        //переменной присваивается ссылка на объект b1.
        Box b2 = b1;
        b2.width = 3;
        System.out.println("Width: " + b1.width);
        System.out.println("Width: " + b2.width);

        //https://www.examclouds.com/ru/java/java-core-russian/objekt-parametr-metoda
        System.out.println("Изменение переданных значений в методе:");
        Box box = new Box(5, 6, 7);
        int x = 10; int y = 10;
        System.out.println("Изменение примитивных типов:");
        System.out.println("x и y перед вызовом: " + x + " " + y);
        changePrimitives(x, y);
        System.out.println("x и y после вызова: " + x + " " + y);
        System.out.println("Изменение объекта:");
        System.out.println("box перед вызовом: " + box.width + " " + box.height + " " + box.depth);
        changeObject(box);
        System.out.println("box после вызова: " + box.width + " " + box.height + " " + box.depth);
        System.out.println("Возврат объекта из метода:");
        ReturnObject ob1 = new ReturnObject(2);
        ReturnObject ob2 = ob1.incrementByTen();
        System.out.println("ob1.a: " + ob1.a);
        System.out.println("ob2.a: " + ob2.a);
        System.out.println();

        //https://www.examclouds.com/ru/java/java-core-russian/nasledovanie
        //Ссылочной переменной суперкласса может быть присвоена ссылка на любой его подкласс.
        Box redBox = new ColorBox(25, 12, 20, "красный");
        // Но доступные члены класса определяются типом ссылочной переменной,
        // а не типом объекта, на который она ссылается
        //При попытке обратиться к переменной color объекта redBox, возникнет ошибка компиляции.
        // System.out.println("Цвет красной коробки: " + redBox.color);

        //equals, hashcode https://javarush.ru/groups/posts/2179-metodih-equals--hashcode-praktika-ispoljhzovanija
        // https://habr.com/ru/post/168195/
        //прочитать до КОНЦА
        Box b3 = new Box(1,1,1);
        Box b4 = new Box(1,1,1);
        System.out.println("b3==b4: "+(b3==b4));
        //тоже вернет false, поскольку для Box equals НЕ переопределен
        System.out.println("b3.equals(b4): "+(b3.equals(b4)));
        //разные значения, потому что для Box hashCode НЕ переопределен
        System.out.println("b3.hashCode: "+b3.hashCode()+" b4.hashCode: "+b4.hashCode());
        System.out.println();

        Point p1 = new Point(1,1);
        Point p2 = new Point(1,1);
        System.out.println("p1==p2: "+(p1==p2));
        //вернет true, потому что для Point equals Переопределен и Сранивает Значения параметров x и y
        System.out.println("p1.equals(p2): "+(p1.equals(p2)));
        //одинаковые значения, потому что для Point hashCode Переопределен и вычисляется По Значениям параметров x и y
        System.out.println("p1.hashCode: "+p1.hashCode()+" p2.hashCode: "+p2.hashCode());
    }
}
