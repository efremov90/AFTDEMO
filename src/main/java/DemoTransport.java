package main.java;

public class DemoTransport {
    public static void main (String args[]){
        // Создаем траспортные объекты
        Transport car = new Transport(4, 100, 1);
        Transport bus = new Transport(30, 200, 2);
        double volume1, volume2;
        int distance1, distance2;

        //Рассчитываем дальность транспорта
        distance1=car.distance();
        distance2=bus.distance();

        // Рассчитываем, сколько траспорту нужно топлива для N км
        volume1=car.oilrequired(distance1);
        volume2=bus.oilrequired(distance2);

        System.out.println("A car needs " + volume1 + " литров топлива для дистанции в " + distance1);
        System.out.println("A bus needs " + volume2 + " литров топлива для дистанции в " + distance2);
    }
}
