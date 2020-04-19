package Olya;

public class triangular extends  Figure {
double hight, base;

    triangular (double hight, double base) {
        this.hight = hight;
        this.base = base;
    }


    double countArea () {
        return 0.5*hight*base;
    }
}
