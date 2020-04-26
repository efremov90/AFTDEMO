package Olya;

import java.util.Objects;

public class Point {
    double x, y;

    Point ( double x, double y) {
        this.x = x;
        this.y = y;
    }
// Автоматом добавляем геттеры/сеттеры
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

    // Автоматически добавляем переопределения equals + hashCode с геттерами
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
        return Objects.hash(getX(), getY());
    }
}
