package application.actors;

import java.time.LocalDate;

public class Person{
    private String name;
    private final LocalDate age;
    private String phoneNumber;
    private char gender;
    private static int uniqueID = 0;
    private final int ID = uniqueID++;

    // Constructor --------------------------------------------
    public Person(String name, LocalDate age, String phoneNumber, char gender) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    // Getter --------------------------------------------------
    public String getName() {
        return name;
    }

    public LocalDate getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public char getGender() {
        return gender;
    }

    public int getID() {
        return ID;
    }

    // Setter --------------------------------------------------
    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
