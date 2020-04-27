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


}
