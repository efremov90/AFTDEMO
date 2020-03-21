package main.java;

//ООП https://www.examclouds.com/ru/java/java-core-russian/lesson7
public class Person {

    //реквизитный состав
    //как правило должнен быть приватным
    //получение и проставление значение должно осуществляться через отдельные методы setter и getter
    //это и есть инкапсуляция
//    https://www.examclouds.com/ru/java/java-core-russian/java-beans-conceptions
    private String FirstName;
    private String LastName;
    private GenderType Gender;

    //constructor можно добавить автоматически: контекстное меню -> generate ->
    public Person(String firstName, String lastName, GenderType gender) {
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
    }

    //getter и setter также добавляются автоматически
    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public GenderType getGender() {
        return Gender;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setGender(GenderType gender) {
        Gender = gender;
    }

    //также через автометически можно добавить toString для вывода информации об объекте
    //метод переопределил, чтобы вместо английских названий полей, выводились русские
    @Override
    public String toString() {
        return "Человек {" +
                "Имя=" + FirstName +
                ", Фамилия=" + LastName +
                ", Пол=" + Gender.getDescription() +
                '}';
    }
}
