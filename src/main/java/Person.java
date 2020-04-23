package main.java;

import java.util.Objects;

//ООП https://www.examclouds.com/ru/java/java-core-russian/lesson7
public class Person {

    //реквизитный состав
    //как правило должнен быть приватным
    //получение и проставление значение должно осуществляться через отдельные методы setter и getter
    //это и есть инкапсуляция
    //https://www.examclouds.com/ru/java/java-core-russian/java-beans-conceptions
    private String FirstName;
    private String LastName;
    private String PersonalNumber;
    private GenderType Gender;
    private int Age;

    //constructor можно добавить автоматически: контекстное меню -> generate ->
    public Person(String firstName, String lastName, GenderType gender) {
        FirstName = firstName;
        LastName = lastName;
        Gender = gender;
    }

    public Person(String firstName, String lastName, String personalNumber, GenderType gender, int age) {
        FirstName = firstName;
        LastName = lastName;
        PersonalNumber = personalNumber;
        Gender = gender;
        Age = age;
    }

    //getter и setter также добавляются автоматически
    //про getter и setter https://www.examclouds.com/ru/java/java-core-russian/java-beans-conceptions
    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public String getPersonalNumber() {
        return PersonalNumber;
    }

    public GenderType getGender() {
        return Gender;
    }

    public int getAge() {
        return Age;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public void setPersonalNumber(String personalNumber) {
        PersonalNumber = personalNumber;
    }

    public void setGender(GenderType gender) {
        Gender = gender;
    }

    public void setAge(int age) {
        Age = age;
    }

    //Из DemoOOP помним, что нужно определять equals и hashCode
    //equals и hashCode также добавляются автоматически
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getAge() == person.getAge() &&
                getFirstName().equals(person.getFirstName()) &&
                getLastName().equals(person.getLastName()) &&
                Objects.equals(getPersonalNumber(), person.getPersonalNumber()) &&
                getGender() == person.getGender();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getGender());
    }

    //https://www.examclouds.com/ru/java/java-core-russian/method-tostring
    //также можно автоматически добавить toString для вывода информации об объекте
    //метод переопределил, чтобы вместо английских названий полей, выводились русские
    @Override
    public String toString() {
        return "Человек {" +
                "Имя=" + FirstName +
                ", Фамилия=" + LastName +
                ", №=" + PersonalNumber +
                ", Пол=" + Gender.getDescription() +
                ", Возраст=" + Age +
                '}';
    }
}
