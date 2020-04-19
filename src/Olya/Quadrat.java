package Olya;

public class Quadrat extends Rectengular {

    Quadrat (double site1, double site2) {
    super(site1, site2);
    }



    @Override
    double countArea () {
       return site1*site1;
    }

    Quadrat multiply () {
        Quadrat x = new Quadrat(site1*2, site2*3);
        return x;
    }

}
