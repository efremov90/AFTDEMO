package Olya;

public enum SeasonType {
    WINTER (1, "Зима"),
    SPRING (2, "Весна"),
    SUMMER (3, "Лето"),
    AUTUMN (4, "Осень");

    private final int code;
    private String name;

    SeasonType (int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static SeasonType getNamebyCode (int code) {
        for (SeasonType s : SeasonType.values()) {
            if (s.getCode() == code )  {
                return s;
            }
        }
        return null;
    }

// Метод со SWITCH
    public static void description (SeasonType choseSeason) {
        switch (choseSeason) {
            case WINTER:
                System.out.println("Снег кружится, летает, летает...");
            break;
            case SPRING:
                System.out.println("Весна идёт, весне дорогу!");
            break;
            case SUMMER:
                System.out.println("Вот оно какое, наше лето, лето яркой зеленью одето!");
            break;
            case AUTUMN:
                System.out.println("Осень, осень, лес остыл и листья сбросил и лихой ветер гонит их за мной");
            break;
        }

    }


}
