package Olya;

public class DemoFigure {
    static void changeObject (triangular triangular) {
        triangular.hight +=5;
        triangular.base +=5;
    }

    public static void main (String [] args) {
        triangular triangular = new triangular(7.0, 6.0);
        triangular triangular2 = new triangular(7.0, 6.0);
        Quadrat quadrat = new Quadrat(2.5, 5.0);
        Quadrat quadrat1 = new Quadrat(2.5, 5.0);
        Quadrat quadrat2 = quadrat.multiply();
        Rectengular rect = new Rectengular(6.0, 5.0);
        Rectengular rect2 = new Rectengular(6.0, 5.0);


        System.out.println("Параметры треугольника до изменений: " + triangular.countArea());
        changeObject(triangular);
        System.out.println("Параметры треугольника после изменений: "+ triangular.countArea());
        System.out.println("Результат сравнения 2 треугольников с непереопределенным equals: "+ triangular2.equals(triangular));

        System.out.println("Площадь квадрата: " + quadrat.countArea());
        System.out.println("Площадь квадрата после переопределения сторон: " + quadrat2.countArea());


        System.out.println(rect.countArea());
        System.out.println("rect.hashCode: " + rect.hashCode() + " and rect2.hashCode: " + rect2.hashCode());
        System.out.println("Результат сравнения 2 объектов прямоугольников: "+ rect2.equals(rect));
     }
}
