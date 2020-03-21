package main.java;

//дополнительно в () можно задать свойства значений
public enum GenderType {
    FEMALE(1,"Женщина"),
    MALE(2,"Мужчина"),
    TRANSGENDER(3,"Трансгендер");

    //в данном случае я объявил свой внутрении код и описание для отражения русского наименования
    //это м.б. произвольный перечень параметров,
    //см. пример enum Planet https://docs.oracle.com/javase/tutorial/java/javaOO/enum.html
    private final int code;
    private final String description;

    GenderType(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
