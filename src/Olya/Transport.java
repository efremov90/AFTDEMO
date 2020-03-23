package Olya;

public class Transport {
    int passengers, oilvolume, oilprokm;
    // Конструктор
    Transport(int aa, int bb, int cc) {
        passengers=aa;
        oilvolume=bb;
        oilprokm=cc;
    }
    // Возвратить дальность транспорта
    int distance (){
        return oilvolume*oilprokm;
    }
    // Рассчитать объем топлива для траспорта для N км
    double oilrequired(int kilometers) {
        return (double) kilometers/oilprokm;
    }
}
