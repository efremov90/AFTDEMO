package Olya;

public class Holiday {
    String country;
    SeasonType nameSeason;

    Holiday (String country, SeasonType nameSeason) {
        this.country = country;
        this.nameSeason = nameSeason;
    }


    @Override
    public String toString() {
        return "Отпуск в{" +
                "Страна='" + country + '\'' +
                ", сезон=" + nameSeason.getName() +
                '}';
    }
}
