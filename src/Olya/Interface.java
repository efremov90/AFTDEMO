package Olya;

public interface Interface {
    int z = 2;

    default String  som () {
        return "Метод №1";
    };

    static String method () {
        return "Статический метод";
    }

}
