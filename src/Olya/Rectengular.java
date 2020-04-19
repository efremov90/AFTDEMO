package Olya;

import java.util.Objects;

public class Rectengular extends  Figure {

    double site1, site2;

    Rectengular (double site1, double site2) {
        this.site1 = site1;
        this.site2 = site2;
    }

    double countArea () {
        return  site1*site2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(site1, site2);
    }

    @Override
    public boolean equals (Object o){
        if (this == o) return  true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectengular rectengular = (Rectengular) o;
        return site1 == rectengular.site1 &&
                site2 == rectengular.site2;
    }

}
